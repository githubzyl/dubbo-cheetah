package com.cheetah.dubbo.api.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LockStatusEnum implements IEnum<String> {

	UNLOCKED("0", "非锁定"), LOCKED("1", "锁定");

	private String code;
	private String name;

	LockStatusEnum(String code, String name) {
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
