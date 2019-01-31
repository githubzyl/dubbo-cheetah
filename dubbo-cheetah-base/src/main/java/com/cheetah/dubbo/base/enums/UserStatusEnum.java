package com.cheetah.dubbo.base.enums;

import com.cheetah.dubbo.common.supers.SuperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserStatusEnum implements SuperEnum<String, String> {

	ENABLE("0", "正常"), DISABLE("1", "禁用");

	String value;
	String label;

	UserStatusEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "[value=" + this.getValue() + ",label=" + this.getLabel() + "]";
	}
	
}
