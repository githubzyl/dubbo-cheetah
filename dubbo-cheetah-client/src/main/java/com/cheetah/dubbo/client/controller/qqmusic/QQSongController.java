package com.cheetah.dubbo.client.controller.qqmusic;

import com.cheetah.dubbo.client.service.manager.QQSongServiceManager;
import com.cheetah.dubbo.common.supers.SuperController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/2/19 13:55
 */
@Api(description = "QQ音乐-歌曲相关")
@RestController
@RequestMapping("/qqmusic/song")
public class QQSongController extends SuperController {

    @Autowired
    private QQSongServiceManager qqSongServiceManager;

    @ApiOperation(value = "歌曲列表分页")
    @GetMapping("/page")
    public Object page(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false) String song_name) {
        Map<String,Object> map = new HashMap<>();
        map.put("song_name",song_name);
        return this.ajaxSuccess(qqSongServiceManager.queryPage(pageNum,pageSize,map));
    }

}
