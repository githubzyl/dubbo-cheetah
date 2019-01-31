package com.cheetah.dubbo.reptile.controller;

import com.cheetah.dubbo.common.supers.SuperController;
import com.cheetah.dubbo.reptile.qqmusic.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 19:20
 * @Version v1.0
 */
@RestController
@RequestMapping("/qqmusic")
@EnableAsync
public class QQMusicController extends SuperController {

    @Autowired
    private GetQQSingerService getQQSingerService;

    @Autowired
    private GetQQSongService getQQSongService;

    @Autowired
    private GetQQAlbumService getQQAlbumService;

    @Autowired
    private GetQQSingerDescService getQQSingerDescService;

    @Autowired
    private DownloadSongService downloadSongService;

    @GetMapping("/singer")
    public Object getSinger(Integer index,Integer cur_page, @RequestParam(defaultValue = "100") Integer max_page){
        for(int i = cur_page ; i <= max_page; i++){
            getQQSingerService.get(index,i);
        }
        return "SUCCESS";
    }

    @GetMapping("/song")
    public Object getSong(String singer_mid, Integer cur_page, @RequestParam(defaultValue = "100") Integer num, @RequestParam(defaultValue = "-1") Integer max_page){
        getQQSongService.get(singer_mid,cur_page,num,max_page);
        return "SUCCESS";
    }

    @GetMapping("/album")
    public Object getAlbum(String singer_mid, Integer cur_page, @RequestParam(defaultValue = "100") Integer num, @RequestParam(defaultValue = "-1") Integer max_page){
        getQQAlbumService.get(singer_mid,cur_page,num,max_page);
        return "SUCCESS";
    }

    @GetMapping("/singer/desc")
    public Object getSingerDesc(String singer_mid){
        return getQQSingerDescService.get(singer_mid);
    }

    @GetMapping("/song/download")
    public Object downloadSong(String song_mid){
        return downloadSongService.downloadSong(song_mid);
    }

}
