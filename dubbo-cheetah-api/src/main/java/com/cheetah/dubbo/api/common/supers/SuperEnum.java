package com.cheetah.dubbo.api.common.supers;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

import com.baomidou.mybatisplus.core.enums.IEnum;

public interface SuperEnum<V extends Serializable, K extends Serializable> extends IEnum<V> {

	String DEFAULT_VALUE_NAME = "value";

	String DEFAULT_LABEL_NAME = "label";

	@SuppressWarnings("unchecked")
	default V getValue() {
		return (V) get(DEFAULT_VALUE_NAME);
	}

	@SuppressWarnings("unchecked")
	default K getLabel() {
		return (K) get(DEFAULT_LABEL_NAME);
	}

	default Object get(String propertyName) {
		Field field = ReflectionUtils.findField(this.getClass(), propertyName);
		if (field == null)
			return null;
		try {
			field.setAccessible(true);
			return field.get(this);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
