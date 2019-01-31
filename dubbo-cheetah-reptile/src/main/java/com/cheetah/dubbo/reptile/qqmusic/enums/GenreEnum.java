package com.cheetah.dubbo.reptile.qqmusic.enums;

import com.cheetah.dubbo.common.supers.SuperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenreEnum implements SuperEnum<String, Integer> {

	ALL(-100, "全部"),
	POPULAR(1, "流行"),
	HIP_HOP(6, "嘻哈"),
	ROCK(2, "摇滚"),
	ELECTRONIC(4, "电子"),
	BALLAD(3, "民谣"),
	R_B(8, "R&B"),
	FOLK_SONG(10, "民歌"),
	LIGHT_MUSIC(9, "轻音乐"),
	JAZZ(5, "爵士"),
	CLASSICAL(14,"古典"),
	RURAL(25,"乡村"),
	BLUES(20,"蓝调")
	;

	Integer value;
	String label;

	GenreEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "[value=" + this.getValue() + ",label=" + this.getLabel() + "]";
	}
	
}
