package com.cheetah.dubbo.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * <p>Description: 注入公共字段自动填充,任选注入方式即可</p>
 * @author   zhaoyl
 * @date      2018-12-29
 * @version  v1.0
 */
public class CustomMetaObjectHandler implements MetaObjectHandler{

	@Override
	public void insertFill(MetaObject metaObject) {
//		setFieldValByName("tenantId", 1, metaObject);
//		setFieldValByName("creater", 1, metaObject);
//		setFieldValByName("createTime", new Date(), metaObject);
//		this.updateFill(metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
//		setFieldValByName("lastModifier", 1, metaObject);
//		setFieldValByName("lastModifyTime", new Date(), metaObject);
	}

}
