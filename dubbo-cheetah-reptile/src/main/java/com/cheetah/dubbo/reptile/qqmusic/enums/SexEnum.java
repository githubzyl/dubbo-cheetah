package com.cheetah.dubbo.reptile.qqmusic.enums;

import com.cheetah.dubbo.common.supers.SuperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements SuperEnum<String, Integer> {

	ALL(-100, "全部"),
	MALE(0, "男"),
	FEMALE(1, "女"),
	COMBINATION(2, "组合")
	;

	Integer value;
	String label;

	SexEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "[value=" + this.getValue() + ",label=" + this.getLabel() + "]";
	}
	
}
