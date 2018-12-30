package com.cheetah.dubbo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.api.common.supers.SuperController;
import com.cheetah.dubbo.api.entity.User;
import com.cheetah.dubbo.client.service.manager.UserServiceManager;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2018-12-29
 */
@RestController
@RequestMapping("/user")
public class UserController extends SuperController {

	@Autowired
	private UserServiceManager userServiceManager;

	@GetMapping("/page")
	public Object page(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<User> page = new Page<>(pageNum, pageSize);
		return userServiceManager.queryPage(page);
	}

}
