package com.cheetah.dubbo.base.enums;

import com.cheetah.dubbo.common.supers.SuperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LockStatusEnum implements SuperEnum<String, String> {

	UNLOCKED("0", "非锁定"), LOCKED("1", "锁定");

	String value;
	String label;

	LockStatusEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "[value=" + this.getValue() + ",label=" + this.getLabel() + "]";
	}

}
