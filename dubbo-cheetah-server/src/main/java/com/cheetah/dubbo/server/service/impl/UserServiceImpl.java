package com.cheetah.dubbo.server.service.impl;

import com.cheetah.dubbo.api.entity.User;
import com.cheetah.dubbo.server.mapper.UserMapper;
import com.cheetah.dubbo.api.service.IUserService;
import com.cheetah.dubbo.api.common.supers.SuperServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import com.cheetah.dubbo.api.common.DubboConstants;
import com.cheetah.dubbo.api.common.InterfaceVersion;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2019-01-02
 */
@Service(version = InterfaceVersion.VERSION_1_0,protocol=DubboConstants.PROTOCOL_DUBBO)
@org.springframework.stereotype.Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements IUserService {

}
