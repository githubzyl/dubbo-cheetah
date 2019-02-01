package com.cheetah.dubbo.server.service.impl;

import com.cheetah.dubbo.base.entity.MongoFile;
import com.cheetah.dubbo.base.mapper.MongoFileMapper;
import com.cheetah.dubbo.api.service.IMongoFileService;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.common.DubboConstants;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2019-02-01
 */
@Service(version = InterfaceVersion.VERSION_1_0,protocol= {DubboConstants.PROTOCOL_DUBBO})
@org.springframework.stereotype.Service
public class MongoFileServiceImpl extends SuperServiceImpl<MongoFileMapper, MongoFile> implements IMongoFileService {

}
