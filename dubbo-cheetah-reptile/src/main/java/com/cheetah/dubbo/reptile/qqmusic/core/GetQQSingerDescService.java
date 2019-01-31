package com.cheetah.dubbo.reptile.qqmusic.core;

import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.URLConstant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 16:48
 * @Version v1.0
 */
@Component
public class GetQQSingerDescService {

    public String get(String singerMid){
        String url = URLConstant.GET_SINGER_DESC + singerMid;
        String result = this.handleResult(url);
        return result;
    }

    public String handleResult(String url){
        Map<String,String> header = new HashMap<>();
        header.put("referer","https://c.y.qq.com/xhr_proxy_utf8.html");
        String result = HttpUtils.get(url,header);
        return result;
    }

}
