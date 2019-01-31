package com.cheetah.dubbo.reptile.qqmusic.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.common.utils.CustomFileUtil;
import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.URLConstant;
import com.cheetah.dubbo.reptile.qqmusic.common.param.DownloadSongParam;
import com.cheetah.dubbo.reptile.qqmusic.service.QQSongService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    //下载歌曲
    public String downloadSong(String songMid){
        String songName = this.getSongName(songMid);
        if(StringUtils.isBlank(songName)){
            return "未找到歌曲";
        }
        String downloadUrl = URLConstant.DOWNLOAD_SONG + getPurl(songMid);
        String localPath = DOWNLOAD_PATH + songName + ".m4a";
        String error = CustomFileUtil.downloadRemoteFile(downloadUrl,localPath);
        return null == error ? "歌曲已下载至["+localPath+"]" : "歌曲下载失败";
    }

    //获取下载的拼接地址
    public String getPurl(String songMid){
        String data = new DownloadSongParam(songMid).toJsonString();
        String url = URLConstant.GET_VKEY + data;
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

    public String getSongName(String songMid){
        QueryWrapper<QqSong> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSong.FIELD_SONG_MID, songMid);
        QqSong qqSong = qqSongService.getOne(wrapper);
        if(null == qqSong){
            return null;
        }
        return qqSong.getSongName();
    }

}
