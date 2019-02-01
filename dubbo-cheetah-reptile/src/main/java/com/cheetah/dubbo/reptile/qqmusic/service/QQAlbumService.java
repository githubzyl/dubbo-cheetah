package com.cheetah.dubbo.reptile.qqmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.QqAlbum;
import com.cheetah.dubbo.base.entity.QqSinger;
import com.cheetah.dubbo.base.entity.QqSingerAlbum;
import com.cheetah.dubbo.base.entity.vo.QQAlbumVO;
import com.cheetah.dubbo.base.mapper.QqAlbumMapper;
import com.cheetah.dubbo.base.mapper.QqSingerAlbumMapper;
import com.cheetah.dubbo.base.mapper.QqSingerMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.cheetah.dubbo.common.utils.ReflectionUtilEX;
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
public class QQAlbumService extends SuperServiceImpl<QqAlbumMapper, QqAlbum> {

    @Autowired
    private QqSingerAlbumMapper qqSingerAlbumMapper;

    @Autowired
    private QqSingerMapper qqSingerMapper;

    @Transactional
    public int batchInsert(String singerMid,List<QQAlbumVO> albums) {
        if(CollectionUtils.isEmpty(albums)){
            return 0;
        }
        int total = 0;
        Long singerId = this.getSingerId(singerMid);
        QqAlbum album = null;
        for(QQAlbumVO albumVO : albums){
            if(!isExistAlbum(albumVO.getAlbumId())){
                album = new QqAlbum();
                albumVO.setAlbumPic(null);
                ReflectionUtilEX.copyProperities(albumVO,album);
                this.save(album);
                total++;
                if(!isExistSingerAlbum(singerId,album.getAlbumId())){
                    QqSingerAlbum singerAlbum = new QqSingerAlbum();
                    singerAlbum.setSingerId(singerId);
                    singerAlbum.setAlbumId(album.getAlbumId());
                    qqSingerAlbumMapper.insert(singerAlbum);
                }
            }
        }
        return total;
    }

    private boolean isExistAlbum(Long albumId){
        QueryWrapper<QqAlbum> wrapper = new QueryWrapper<>();
        wrapper.eq(QqAlbum.FIELD_ALBUM_ID, albumId);
        int count = this.count(wrapper);
        return count > 0 ? true : false;
    }

    private boolean isExistSingerAlbum(Long singerId, Long albumId){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<QqSingerAlbum> wrapper = new QueryWrapper<>();
        map.put(QqSingerAlbum.FIELD_SINGER_ID,singerId);
        map.put(QqSingerAlbum.FIELD_ALBUM_ID,albumId);
        wrapper.allEq(map);
        int count = qqSingerAlbumMapper.selectCount(wrapper);
        return count > 0 ? true : false;
    }

    private Long getSingerId(String singerMid){
        QueryWrapper<QqSinger> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSinger.FIELD_SINGER_MID, singerMid);
        List<QqSinger> list = qqSingerMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0).getSingerId();
    }

}
