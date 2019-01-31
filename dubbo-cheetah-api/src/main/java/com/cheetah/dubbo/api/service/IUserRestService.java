package com.cheetah.dubbo.api.service;

import com.cheetah.dubbo.base.entity.User;

/**
 * <p>Description: 对外提供rest访问的service</p>
 * @author   zhaoyl
 * @date      2019-01-02
 * @version  v1.0
 */
public interface IUserRestService {

	public User queryById(Integer id);
	
}
