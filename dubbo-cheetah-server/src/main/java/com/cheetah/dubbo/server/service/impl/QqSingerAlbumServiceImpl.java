package com.cheetah.dubbo.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cheetah.dubbo.api.common.DubboConstants;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IQqSingerAlbumService;
import com.cheetah.dubbo.base.entity.QqSingerAlbum;
import com.cheetah.dubbo.base.mapper.QqSingerAlbumMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;

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
public class QqSingerAlbumServiceImpl extends SuperServiceImpl<QqSingerAlbumMapper, QqSingerAlbum> implements IQqSingerAlbumService {

}
