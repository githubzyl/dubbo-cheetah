package com.cheetah.dubbo.reptile.qqmusic.enums;

import com.cheetah.dubbo.common.supers.SuperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AreaEnum implements SuperEnum<String, Integer> {

	ALL(-100, "全部"),
	INLAND(200, "内地"),
	HK_AND_TAIWAN(2, "港台"),
	EUROPE_AND_AMERICA(5, "欧美"),
	JAPAN(4, "日本"),
	KOREA(3, "韩国"),
	OTHER(6, "其他")
	;

	Integer value;
	String label;

	AreaEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "[value=" + this.getValue() + ",label=" + this.getLabel() + "]";
	}
	
}
