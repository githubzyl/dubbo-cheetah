package com.cheetah.dubbo.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.api.common.DubboConstants;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IQqAlbumService;
import com.cheetah.dubbo.base.entity.QqAlbum;
import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.base.mapper.QqAlbumMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.cheetah.dubbo.common.utils.SqlUtil;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2019-01-30
 */
@Service(version = InterfaceVersion.VERSION_1_0,protocol= {DubboConstants.PROTOCOL_DUBBO})
@org.springframework.stereotype.Service
public class QqAlbumServiceImpl extends SuperServiceImpl<QqAlbumMapper, QqAlbum> implements IQqAlbumService {

    @Override
    public IPage<QqAlbum> queryPage(Integer pageNum, Integer pageSize, Map<String, Object> params) {
        IPage<QqAlbum> page = new Page<>(pageNum,pageSize);
        QueryWrapper<QqAlbum> wrapper = new QueryWrapper<>();
        if(null != params.get("album_name")){
            String albumName = params.get("album_name").toString();
            albumName = SqlUtil.escapeLike(albumName);
            wrapper.like(QqAlbum.FIELD_ALBUM_NAME, albumName);
        }
        return this.page(page, wrapper);
    }

}
