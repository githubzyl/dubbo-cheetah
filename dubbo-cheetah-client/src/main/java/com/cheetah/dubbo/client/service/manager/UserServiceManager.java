package com.cheetah.dubbo.client.service.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.QqSinger;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.base.entity.User;
import com.cheetah.dubbo.api.service.IUserService;

import java.util.Map;

@Component
public class UserServiceManager {

	@Reference(version = InterfaceVersion.VERSION_1_0)
	private IUserService userService;
	
	public IPage<User> queryPage(Integer pageNum, Integer pageSize, Map<String,Object> params){
		IPage<User> page = new Page<>(pageNum,pageSize);
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		return userService.page(page, wrapper);
	}

}
