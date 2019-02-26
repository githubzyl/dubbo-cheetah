package com.cheetah.dubbo.reptile.controller;

import com.cheetah.dubbo.base.entity.vo.QQSingerDesc;
import com.cheetah.dubbo.common.supers.SuperController;
import com.cheetah.dubbo.reptile.qqmusic.common.QQMusicConstant;
import com.cheetah.dubbo.reptile.qqmusic.core.*;
import com.cheetah.dubbo.reptile.qqmusic.service.QQSingerService;
import com.cheetah.dubbo.reptile.qqmusic.service.QQSongService;
import com.cheetah.dubbo.reptile.service.MongoFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 19:20
 * @Version v1.0
 */
@Api(description = "qq音乐抓取")
@Controller
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

    @Autowired
    private MongoFileService mongoFileService;

    @Autowired
    private QQSingerService qqSingerService;

    @Autowired
    private QQSongService qqSongService;

    @Autowired
    private QQMusicSearchService qqMusicSearchService;

    @ApiOperation(value = "获取歌手")
    @GetMapping("/singer")
    @ResponseBody
    public Object getSinger(@RequestParam @ApiParam(value = "歌手类型") Integer index, @RequestParam @ApiParam(value = "开始页码") Integer cur_page, @RequestParam(defaultValue = "100") @ApiParam(value = "最大页数") Integer max_page) {
        return this.ajaxSuccess(getQQSingerService.get(index, cur_page, max_page));
    }

    @ApiOperation(value = "获取歌曲")
    @GetMapping("/song")
    @ResponseBody
    public Object getSong(@RequestParam @ApiParam(value = "歌手mid") String singer_mid, @RequestParam @ApiParam(value = "开始页码") Integer cur_page, @RequestParam(defaultValue = "100") @ApiParam(value = "每页条数") Integer num, @RequestParam(defaultValue = "-1") @ApiParam(value = "最大页数") Integer max_page) {
        return this.ajaxSuccess(getQQSongService.get(singer_mid, cur_page, num, max_page));
    }

    @ApiOperation(value = "获取专辑")
    @GetMapping("/album")
    @ResponseBody
    public Object getAlbum(@RequestParam @ApiParam(value = "歌手mid") String singer_mid, @RequestParam @ApiParam(value = "开始页码") Integer cur_page, @RequestParam(defaultValue = "100") @ApiParam(value = "每页条数") Integer num, @RequestParam(defaultValue = "-1") @ApiParam(value = "最大页数") Integer max_page) {
        return this.ajaxSuccess(getQQAlbumService.get(singer_mid, cur_page, num, max_page));
    }

    @ApiOperation(value = "获取歌手简介")
    @GetMapping("/singer/desc")
    @ResponseBody
    public Object getSingerDesc(@RequestParam @ApiParam(value = "歌手mid") String singer_mid) throws Exception {
        QQSingerDesc desc = getQQSingerDescService.getDesc(singer_mid);
        String str = null;
        if (null != desc) {
            str = desc.toHtmlString();
        }
        return this.ajaxSuccess(str);
    }

    @ApiOperation(value = "下载歌曲")
    @GetMapping("/song/download")
    public void downloadSong(@RequestParam @ApiParam(value = "歌曲mid") String song_mid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        downloadSongService.download(song_mid, request, response);
    }

    @ApiOperation(value = "上传歌手头像到OSS")
    @GetMapping("/singer/photo/upload")
    @ResponseBody
    public Object uploadSingerImageToOss(@RequestParam @ApiParam(value = "歌手mid") String singer_mid) throws Exception {
        return this.ajaxSuccess(qqSingerService.uploadPhoto(singer_mid));
    }

    @ApiOperation(value = "上传歌曲文件到OSS")
    @GetMapping("/song/m4a/upload")
    @ResponseBody
    public Object uploadSongM4aToOss(@RequestParam @ApiParam(value = "歌曲mid") String song_mid) throws Exception {
        return this.ajaxSuccess(qqSongService.uploadSongM4a(song_mid));
    }

    @ApiOperation(value = "获取QQ音乐歌曲的下载url")
    @GetMapping("/song/url")
    @ResponseBody
    public Object getSongUrl(@RequestParam @ApiParam(value = "歌曲mid") String song_mid) {
        return this.ajaxSuccess(downloadSongService.getDownloadUrl(song_mid));
    }

    @ApiOperation(value = "根据关键字搜索补全搜索框")
    @GetMapping("/searchbox")
    @ResponseBody
    public Object searchBox(@RequestParam @ApiParam(value = "搜索关键字") String keyword) {
        return this.ajaxSuccess(qqMusicSearchService.searchByKeywordForSearchBox(keyword));
    }

    @ApiOperation(value = "根据关键字搜索")
    @GetMapping("/search")
    @ResponseBody
    public Object searchByKeyword(@RequestParam @ApiParam(value = "搜索关键字") String keyword,
                                  @RequestParam(defaultValue = "1") @ApiParam(value = "搜索类型") Integer search_type,
                                  @RequestParam(defaultValue = "1") @ApiParam(value = "搜索页码") Integer page_num) {
       return this.ajaxSuccess(qqMusicSearchService.searchByKeyword(keyword,search_type,page_num));
    }


}
