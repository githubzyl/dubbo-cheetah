package com.cheetah.dubbo.generator;

public enum ColumnEnumType {

	GENDER("gender", "com.cheetah.dubbo.api.enums.GenderEnum"),
	USER_STATUS("userStatus", "com.cheetah.dubbo.api.enums.UserStatusEnum"),
	LOCK_STATUS("lockStatus", "com.cheetah.dubbo.api.enums.LockStatusEnum")
	;

	String fieldName;
	String enumType;

	ColumnEnumType(String fieldName, String enumType) {
		this.fieldName = fieldName;
		this.enumType = enumType;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public String getEnumType() {
		return this.enumType;
	}

	public static String getEnumTypeByFieldName(String fieldName) {
		if (null == fieldName) {
			return null;
		}
		for (ColumnEnumType type : ColumnEnumType.values()) {
			if (fieldName.equals(type.getFieldName())) {
				return type.getEnumType();
			}
		}
		return null;
	}

}
