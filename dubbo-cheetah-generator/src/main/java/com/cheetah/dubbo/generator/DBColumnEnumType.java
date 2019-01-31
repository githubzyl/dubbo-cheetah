package com.cheetah.dubbo.generator;

import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.cheetah.dubbo.base.enums.GenderEnum;
import com.cheetah.dubbo.base.enums.LockStatusEnum;
import com.cheetah.dubbo.base.enums.UserStatusEnum;

public enum DBColumnEnumType implements IColumnType {

	GENDER("gender", GenderEnum.class),
	USER_STATUS("user_status", UserStatusEnum.class),
	LOCK_STATUS("lock_status", LockStatusEnum.class)
	;

	/**
	 * 属性名
	 */
	private String columnName;

	/**
	 * 枚举类
	 */
	private Class<?> enumClass;
	
	/**
	 * 类型
	 */
	private String type;

	/**
	 * 包路径
	 */
	private String pkg;

	DBColumnEnumType(String columnName, Class<?> enumClass) {
		this.columnName = columnName;
		this.enumClass = enumClass;

		this.type = this.enumClass.getSimpleName();
		this.pkg = this.enumClass.getCanonicalName();
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

	public Class<?> getEnumClass() {
		return enumClass;
	}

	public void setEnumClass(Class<?> enumClass) {
		this.enumClass = enumClass;
	}

}
