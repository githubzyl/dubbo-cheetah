package com.cheetah.dubbo.reptile.qqmusic.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.MongoFile;
import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.common.utils.CustomFileUtil;
import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.QQMusicConstant;
import com.cheetah.dubbo.reptile.qqmusic.common.param.DownloadSongParam;
import com.cheetah.dubbo.reptile.qqmusic.service.QQSongService;
import com.cheetah.dubbo.reptile.service.MongoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/31 12:35
 * @Version v1.0
 */
@Component
public class DownloadSongService {

    private final static String DOWNLOAD_PATH = "E:/qqmusic/";

    @Autowired
    private QQSongService qqSongService;

    @Autowired
    private MongoFileService mongoFileService;

    //下载歌曲
    public String downloadSong(String songMid, Long songId, String songName){
        String fileType = "m4a";
        songName += "." + fileType;
        String downloadUrl = getDownloadUrl(songMid);
        String localPath = DOWNLOAD_PATH + songName;
        String error = CustomFileUtil.downloadRemoteFile(downloadUrl,localPath);
        MongoFile fileInfo = mongoFileService.upload(localPath,songId, songName, fileType);
        return null != fileInfo ? fileInfo.getFileId() : null;
    }

    public void download(String songMid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        QqSong qqSong = this.getSong(songMid);
        if(null == qqSong || null == qqSong.getSongId()){
            return;
        }
        if(mongoFileService.isExistFile(qqSong.getSongId())){
            mongoFileService.download(qqSong.getSongId(),request,response);
        }else{
            String error =  this.downloadSong(songMid, qqSong.getSongId(), qqSong.getSongName());
            if(null == error){
                mongoFileService.download(qqSong.getSongId(),request,response);
            }
        }
    }

    public String getDownloadUrl(String songMid){
       return QQMusicConstant.DOWNLOAD_SONG + getPurl(songMid);
    }


    //获取下载的拼接地址
    public String getPurl(String songMid){
        String data = new DownloadSongParam(songMid).toJsonString();
        String url = QQMusicConstant.GET_VKEY + data;
        String result = HttpUtils.get(url);
        JSONObject json = JSONObject.parseObject(result);
        if (0 == json.getIntValue("code")) {
            json = json.getJSONObject("req_0");
            if (0 == json.getIntValue("code")) {
                json = json.getJSONObject("data");
                JSONArray midurlinfo = json.getJSONArray("midurlinfo");
                json = midurlinfo.getJSONObject(0);
                return json.getString("purl");
            }
        }
        return null;
    }

    public QqSong getSong(String songMid){
        QueryWrapper<QqSong> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSong.FIELD_SONG_MID, songMid);
        QqSong qqSong = qqSongService.getOne(wrapper);
        if(null == qqSong){
            return null;
        }
        return qqSong;
    }

}
