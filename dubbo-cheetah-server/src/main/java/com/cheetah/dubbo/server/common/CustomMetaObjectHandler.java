package com.cheetah.dubbo.server.common;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * <p>Description: 注入公共字段自动填充,任选注入方式即可</p>
 * @author   zhaoyl
 * @date      2018-12-29
 * @version  v1.0
 */
public class CustomMetaObjectHandler implements MetaObjectHandler{

	@Override
	public void insertFill(MetaObject metaObject) {
		
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setFieldValByName("lastModifyTime", new Date(), metaObject);		
	}

}
