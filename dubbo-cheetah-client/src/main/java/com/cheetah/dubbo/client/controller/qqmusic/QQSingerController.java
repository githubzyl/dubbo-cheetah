package com.cheetah.dubbo.client.controller.qqmusic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.base.entity.User;
import com.cheetah.dubbo.client.service.manager.QQSingerServiceManager;
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
@Api(description = "QQ音乐-歌手相关")
@RestController
@RequestMapping("/qqmusic/singer")
public class QQSingerController extends SuperController {

    @Autowired
    private QQSingerServiceManager qqSingerServiceManager;

    @ApiOperation(value = "歌手列表分页")
    @GetMapping("/page")
    public Object page(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false) String singer_name) {
        Map<String,Object> map = new HashMap<>();
        map.put("singer_name",singer_name);
        return this.ajaxSuccess(qqSingerServiceManager.queryPage(pageNum,pageSize,map));
    }

}
