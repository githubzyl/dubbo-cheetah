package com.cheetah.dubbo.server.service.impl;

import com.cheetah.dubbo.api.entity.User;
import com.cheetah.dubbo.server.mapper.UserMapper;
import com.cheetah.dubbo.api.service.IUserService;
import com.alibaba.dubbo.config.annotation.Service;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.common.BaseService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2018-12-29
 */
@Service(version = InterfaceVersion.VERSION_1_0)
public class UserServiceImpl extends BaseService<UserMapper, User> implements IUserService {

}
