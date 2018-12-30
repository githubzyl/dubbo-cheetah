package com.cheetah.dubbo.generator;

/**
 * <p>Description: 生成的字段根据属性名映射到枚举类型</p>
 * @author   zhaoyl
 * @date      2018-12-30
 * @version  v1.0
 */
public class ColumnEnumUtils {

	public static String getFieldType(String fieldName, String fieldType) {
		String type = ColumnEnumType.getEnumTypeByFieldName(fieldName);
		return null == type ? fieldType : type;
	}
	
	public static boolean isString(String fieldName) {
		String type = ColumnEnumType.getEnumTypeByFieldName(fieldName);
		return null == type ? true : false;
	}
	
}
