package com.cheetah.dubbo.generator;

import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

public enum DBColumnEnumType implements IColumnType {

	GENDER("GenderEnum", "com.cheetah.dubbo.api.enums.GenderEnum", "gender"),
	USER_STATUS("UserStatusEnum", "com.cheetah.dubbo.api.enums.UserStatusEnum","user_status"),
	LOCK_STATUS("LockStatusEnum", "com.cheetah.dubbo.api.enums.LockStatusEnum", "lock_status")
	;
	
	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 包路径
	 */
	private final String pkg;

	/**
	 * 属性名
	 */
	private final String columnName;

	DBColumnEnumType(final String type, final String pkg, String columnName) {
		this.type = type;
		this.pkg = pkg;
		this.columnName = columnName;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getPkg() {
		return pkg;
	}

	public String getColumnName() {
		return columnName;
	}
	
	public static DBColumnEnumType getEnumTypeByColumnName(String columnName) {
		if (null == columnName) {
			return null;
		}
		for (DBColumnEnumType type : DBColumnEnumType.values()) {
			if (columnName.equals(type.getColumnName())) {
				return type;
			}
		}
		return null;
	}

}
