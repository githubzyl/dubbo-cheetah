package com.cheetah.dubbo.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenderEnum implements IEnum<String> {

	MALE("0", "男"), FEMALE("1", "女");

	private String code;
	private String name;

	GenderEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public String getValue() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}
	
}
