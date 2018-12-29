package com.cheetah.dubbo.client.service.manager;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.entity.User;
import com.cheetah.dubbo.api.service.IUserService;

@Component
public class UserServiceManager {

	@Reference(version = InterfaceVersion.VERSION_1_0)
	private IUserService userService;
	
	public IPage<User> queryPage(Page<User> page){
		return userService.page(page);
	}

}
