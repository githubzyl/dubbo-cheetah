package com.cheetah.dubbo.api.enums;

import com.cheetah.dubbo.api.common.supers.SuperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenderEnum implements SuperEnum<String, String> {

	MALE("0", "男"), FEMALE("1", "女");

	String value;
	String label;

	GenderEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
}
