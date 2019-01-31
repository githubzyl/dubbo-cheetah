package com.cheetah.dubbo.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 19:01
 * @Version v1.0
 */
public class JsonUtils {

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        convert(text);
        return JSONObject.parseArray(text, clazz);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        convert(text);
        return JSONObject.parseObject(text, clazz);
    }

    /**
    * @Description 将json字符串中的下划线转换成了驼峰形式
    * @author zhaoyl
    * @date  2019/1/29 19:09
    * @param [json]
    * @return void
    **/
    public static void convert(Object json) {
        if (json instanceof JSONArray) {
            JSONArray arr = (JSONArray) json;
            for (Object obj : arr) {
                convert(obj);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
                Object value = jo.get(key);
                String[] key_strs = key.split("_");
                if (key_strs.length > 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < key_strs.length; i++) {
                        String ks = key_strs[i];
                        if (!"".equals(ks)) {
                            if (i == 0) {
                                sb.append(ks);
                            } else {
                                int c = ks.charAt(0);
                                if (c >= 97 && c <= 122) {
                                    int v = c - 32;
                                    sb.append((char) v);
                                    if (ks.length() > 1) {
                                        sb.append(ks.substring(1));
                                    }
                                } else {
                                    sb.append(ks);
                                }
                            }
                        }
                    }
                    jo.remove(key);
                    jo.put(sb.toString(), value);
                }
                convert(value);
            }
        }
    }

    public static Object convert(String json) {
        Object obj = JSON.parse(json);
        convert(obj);
        return obj;
    }

}
