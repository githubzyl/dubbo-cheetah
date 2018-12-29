package com.cheetah.dubbo.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserStatusEnum implements IEnum<String> {

	ENABLE("0", "正常"), DISABLE("1", "禁用");

	private String code;
	private String name;

	UserStatusEnum(String code, String name) {
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
