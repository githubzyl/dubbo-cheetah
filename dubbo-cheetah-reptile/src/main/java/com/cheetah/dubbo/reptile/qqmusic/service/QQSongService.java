package com.cheetah.dubbo.reptile.qqmusic.service;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.QqAlbumSong;
import com.cheetah.dubbo.base.entity.QqSingerSong;
import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.base.entity.vo.QQSongVO;
import com.cheetah.dubbo.base.mapper.QqAlbumSongMapper;
import com.cheetah.dubbo.base.mapper.QqSingerSongMapper;
import com.cheetah.dubbo.base.mapper.QqSongMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.cheetah.dubbo.common.utils.EncdDecd;
import com.cheetah.dubbo.common.utils.ReflectionUtilEX;
import com.cheetah.dubbo.reptile.qqmusic.common.OSSConstant;
import com.cheetah.dubbo.reptile.qqmusic.core.DownloadSongService;
import com.cheetah.dubbo.reptile.service.OSSFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 19:15
 * @Version v1.0
 */
@Component
public class QQSongService extends SuperServiceImpl<QqSongMapper, QqSong> {

    @Autowired
    private QqSingerSongMapper qqSingerSongMapper;

    @Autowired
    private QqAlbumSongMapper qqAlbumSongMapper;

    @Autowired
    private OSSFileService ossFileService;

    @Autowired
    private DownloadSongService downloadSongService;

    @Transactional
    public int batchInsert(Long singerId, List<QQSongVO> list) {
        if(CollectionUtils.isEmpty(list)){
            return 0;
        }
        int total = 0;
        for(QQSongVO songVO : list){
            total += this.saveSingle(singerId,songVO);
        }
        return total;
    }

    public int saveSingle(Long singerId, QQSongVO songVO){
        if(!isExistSong(songVO.getSongId())){
            QqSong song = new QqSong();
            ReflectionUtilEX.copyProperities(songVO,song);
            this.save(song);
            return 1;
        }
        if(!isExistSingerSong(singerId,songVO.getSongId())){
            QqSingerSong singerSong = new QqSingerSong();
            singerSong.setSingerId(singerId);
            singerSong.setSongId(songVO.getSongId());
            qqSingerSongMapper.insert(singerSong);
        }
        if(!isExistAlbumSong(songVO.getAlbumId(),songVO.getSongId())){
            QqAlbumSong albumSong = new QqAlbumSong();
            albumSong.setAlbumId(songVO.getAlbumId());
            albumSong.setSongId(songVO.getSongId());
            qqAlbumSongMapper.insert(albumSong);
        }
        return 0;
    }

    public String uploadSongM4a(String song_mid) throws Exception {
        QueryWrapper<QqSong> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSong.FIELD_SONG_MID, song_mid);
        QqSong qqSong = this.getOne(wrapper);
        if(null == qqSong){
            return "未找到对应歌曲";
        }
        String songUrl = downloadSongService.getDownloadUrl(song_mid);
        String fileName = EncdDecd.MD5String(qqSong.getSongId() + song_mid) + ".m4a";
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("song_mid",song_mid);
        PutObjectResult result = ossFileService.uploadToDefaultBucket(songUrl, OSSConstant.AUDIO_DIRECTORY, fileName,null);
        if(null == result){
            throw new Exception("歌曲文件上传到OSS服务器失败");
        }
        return ossFileService.getPreviewUrl(OSSConstant.AUDIO_DIRECTORY, fileName, false);
    }

    private boolean isExistSong(Long songId){
        QueryWrapper<QqSong> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSong.FIELD_SONG_ID, songId);
        int count = this.count(wrapper);
        return count > 0 ? true : false;
    }

    private boolean isExistSingerSong(Long singerId, Long songId){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<QqSingerSong> wrapper = new QueryWrapper<>();
        map.put(QqSingerSong.FIELD_SINGER_ID,singerId);
        map.put(QqSingerSong.FIELD_SONG_ID,songId);
        wrapper.allEq(map);
        int count = qqSingerSongMapper.selectCount(wrapper);
        return count > 0 ? true : false;
    }

    private boolean isExistAlbumSong(Long albumId, Long songId){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<QqAlbumSong> wrapper = new QueryWrapper<>();
        map.put(QqAlbumSong.FIELD_ALBUM_ID,albumId);
        map.put(QqAlbumSong.FIELD_SONG_ID,songId);
        wrapper.allEq(map);
        int count = qqAlbumSongMapper.selectCount(wrapper);
        return count > 0 ? true : false;
    }

}
