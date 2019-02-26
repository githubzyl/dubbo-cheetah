package com.cheetah.dubbo.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.api.common.DubboConstants;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IQqSongService;
import com.cheetah.dubbo.base.entity.QqSinger;
import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.base.mapper.QqSongMapper;
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
public class QqSongServiceImpl extends SuperServiceImpl<QqSongMapper, QqSong> implements IQqSongService {

    @Override
    public IPage<QqSong> queryPage(Integer pageNum, Integer pageSize, Map<String,Object> params){
        IPage<QqSong> page = new Page<>(pageNum,pageSize);
        QueryWrapper<QqSong> wrapper = new QueryWrapper<>();
        if(null != params.get("song_name")){
            String songName = params.get("song_name").toString();
            songName = SqlUtil.escapeLike(songName);
            wrapper.like(QqSong.FIELD_SONG_NAME, songName);
        }
        return this.page(page, wrapper);
    }

}
