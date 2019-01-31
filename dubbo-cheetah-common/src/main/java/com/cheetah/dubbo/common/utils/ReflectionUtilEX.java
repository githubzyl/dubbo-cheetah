package com.cheetah.dubbo.common.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射工具类
 * 
 * @author: zhaoyl
 * @since: 2017年12月14日 上午9:03:07
 * @history:
 */
public class ReflectionUtilEX {

	public static ConcurrentHashMap<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<String, BeanCopier>();

	/**
	 * 判断某个类是否存在某个属性, 如果存在，返回它的简单类型，不存在返回null
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static String isExistField(Class<?> clazz, String fieldName) {
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(fieldName)) {
				return fields[i].getType().getSimpleName();
			}
		}
		return null;
	}

	/**
	 * 获取某个对象的所有属性
	 * 
	 * @param object
	 * @return
	 * @create: 2017年12月14日 上午9:03:43 zhaoyl
	 * @history:
	 */
	public static Field[] getAllFields(Object object) {
		Class<? extends Object> clazz = object.getClass();
		return getAllFields(clazz);
	}

	/**
	 * 将实体类的集合转换成Map集合
	 * 
	 * @param entityList
	 * @return
	 * @create: 2017年12月14日 上午9:06:11 zhaoyl
	 * @history:
	 */
	public static List<Map<String, Object>> convertEntityListToMapList(List<? extends Object> entityList) {
		if (null == entityList || entityList.size() <= 0) {
			return null;
		}
		List<Map<String, Object>> mapList = new ArrayList<>();
		for (Object t : entityList) {
			mapList.add(ReflectionUtilEX.convertEntityToMap(t));
		}
		return mapList;
	}

	/**
	 * 将对象转换成Map
	 * 
	 * @param obj
	 * @return
	 * @create: 2017年12月14日 上午9:11:56 zhaoyl
	 * @history:
	 */
	public static Map<String, Object> convertEntityToMap(Object obj) {
		if (null == obj) {
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		try {
			Field[] fields = ReflectionUtilEX.getAllFields(obj);
			for (Field field : fields) {
				field.setAccessible(true);
				if (!"serialVersionUID".equals(field.getName())) {
					map.put(field.getName(), field.get(obj));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将对象转换成Map
	 * 
	 * @return
	 * @create: 2018年3月22日 下午1:45:23 zhaoyl
	 * @history:
	 */
	public static <T> T convertMapToEntity(Map<String, Object> map, Class<T> clazz) {
		try {
			T t = clazz.newInstance();
			Field[] fields = ReflectionUtilEX.getAllFields(clazz);
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				String fieldType = field.getType().getSimpleName();
				if (!"serialVersionUID".equals(fieldName)) {
					field.set(t, convertFieldType(fieldType, map.get(fieldName)));
				}
			}
			return t;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setProperty(Object target, String fieldName, Object fieldValue) {
		try {
			Field[] fields = ReflectionUtilEX.getAllFields(target.getClass());
			for (Field field : fields) {
				field.setAccessible(true);
				if (fieldName.equals(field.getName())) {
					field.set(target, fieldValue);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getProperty(Object target, String fieldName) {
		try {
			Field[] fields = ReflectionUtilEX.getAllFields(target.getClass());
			for (Field field : fields) {
				field.setAccessible(true);
				if (fieldName.equals(field.getName())) {
					return field.get(target);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void copyProperities(Object source, Object target) {
		String key = genKey(source.getClass(), target.getClass(), false);
		BeanCopier copier = null;
		if (!BEAN_COPIERS.containsKey(key)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			BEAN_COPIERS.put(key, copier);
		} else {
			copier = BEAN_COPIERS.get(key);
		}
		copier.copy(source, target, null);
	}

	public static void copyProperitiesWithDateConverter(Object source, Object target) {
		String key = genKey(source.getClass(), target.getClass(), true);
		BeanCopier copier = null;
		if (!BEAN_COPIERS.containsKey(key)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), true);
			BEAN_COPIERS.put(key, copier);
		} else {
			copier = BEAN_COPIERS.get(key);
		}
		copier.copy(source, target, new DateConverterBeanCopier());
	}
	
	private static String genKey(Class<?> srcClazz, Class<?> destClazz, boolean useConverter) {
		String str = useConverter ? "1" : "0";
		return srcClazz.getName() + destClazz.getName() + str;
	}

	private static Field[] getAllFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<>();
		while (clazz != null) {
			fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}

	private static Object convertFieldType(String type, Object obj) {
		if (null == obj || "".equals(obj)) {
			return null;
		}
		String str = obj.toString();
		if ("String".equals(type)) {
			return str;
		} else if ("Integer".equals(type)) {
			return Integer.parseInt(str);
		} else if ("Long".equals(type)) {
			return Long.parseLong(str);
		} else if ("Double".equals(type)) {
			return Double.parseDouble(str);
		} else if ("Float".equals(type)) {
			return Float.parseFloat(str);
		} else if ("Short".equals(type)) {
			return Short.parseShort(str);
		}
		return obj;
	}
}

class DateConverterBeanCopier implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Object arg1, Class arg0, Object context) {
		if (arg1 instanceof String && arg0 != String.class) {
			String p = (String) arg1;
			if (p == null || p.trim().length() == 0) {
				return null;
			}
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.parse(p.trim());
			} catch (Exception e) {
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					return df.parse(p.trim());
				} catch (ParseException ex) {
					return arg1;
				}
			}
		} /** 输入String ,输出String */
		else if (arg1 instanceof String && arg0 == String.class) {
			return arg1;
		} /** 输入Date ,输出String */
		else if (arg1 instanceof java.util.Date) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.format((java.util.Date) arg1);
			} catch (Exception e) {
				return null;
			}
		} /** 输入Date ,输出String */
		else if (arg1 instanceof java.sql.Date) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df.format((java.sql.Date) arg1);
			} catch (Exception e) {
				return null;
			}
		}
		return arg1;
	}

}