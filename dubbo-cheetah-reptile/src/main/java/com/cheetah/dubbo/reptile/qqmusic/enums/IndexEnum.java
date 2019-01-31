package com.cheetah.dubbo.reptile.qqmusic.enums;

import com.cheetah.dubbo.common.supers.SuperEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IndexEnum implements SuperEnum<String, Integer> {

	POPULAR(-100, "热门"),
	A(1, "A"),
	B(2, "B"),
	C(3, "C"),
	D(4, "D"),
	E(5, "E"),
	F(6, "F"),
	G(7, "G"),
	H(8, "H"),
	I(9, "I"),
	J(10, "J"),
	K(11, "K"),
	L(12, "L"),
	M(13, "M"),
	N(14, "N"),
	O(15, "O"),
	P(16, "P"),
	Q(17, "Q"),
	R(18, "R"),
	S(19, "S"),
	T(20, "T"),
	U(21, "U"),
	V(22, "V"),
	W(23, "W"),
	X(24, "X"),
	Y(25, "Y"),
	Z(26, "Z"),
	WELL(27, "#"),
	;

	Integer value;
	String label;

	IndexEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "[value=" + this.getValue() + ",label=" + this.getLabel() + "]";
	}
	
}
