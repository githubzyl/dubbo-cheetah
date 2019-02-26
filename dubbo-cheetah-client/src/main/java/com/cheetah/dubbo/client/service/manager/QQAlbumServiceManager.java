package com.cheetah.dubbo.client.service.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IQqAlbumService;
import com.cheetah.dubbo.api.service.IQqSongService;
import com.cheetah.dubbo.base.entity.QqAlbum;
import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.common.supers.SuperController;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 17:26
 * @Version v1.0
 */
@Component
public class QQAlbumServiceManager extends SuperController {

    @Reference(version = InterfaceVersion.VERSION_1_0)
    private IQqAlbumService qqAlbumService;

    public IPage<QqAlbum> queryPage(Integer pageNum, Integer pageSize, Map<String,Object> params){
        return qqAlbumService.queryPage(pageNum,pageSize,params);
    }

}
