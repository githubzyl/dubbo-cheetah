package com.cheetah.dubbo.api.common.supers;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

import com.baomidou.mybatisplus.core.enums.IEnum;

public interface SuperEnum<V extends Serializable, K extends Serializable> extends IEnum<V>{
	
	String DEFAULT_VALUE_NAME = "value";

    String DEFAULT_LABEL_NAME = "label";
    
    @SuppressWarnings("unchecked")
	default V getValue() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return (V) field.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	default K getLabel() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_LABEL_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return (K) field.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
}
