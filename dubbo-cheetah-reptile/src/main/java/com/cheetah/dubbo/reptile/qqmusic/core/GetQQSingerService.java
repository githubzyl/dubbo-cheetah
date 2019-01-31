package com.cheetah.dubbo.reptile.qqmusic.core;

import com.alibaba.fastjson.JSONObject;
import com.cheetah.dubbo.base.entity.QqSinger;
import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.common.utils.JsonUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.URLConstant;
import com.cheetah.dubbo.reptile.qqmusic.common.param.Comm;
import com.cheetah.dubbo.reptile.qqmusic.common.param.QuerySingerParam;
import com.cheetah.dubbo.reptile.qqmusic.common.param.SingerList;
import com.cheetah.dubbo.reptile.qqmusic.common.param.SingerListParam;
import com.cheetah.dubbo.reptile.qqmusic.service.QQSingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 16:48
 * @Version v1.0
 */
@Component
public class GetQQSingerService {

    @Autowired
    private QQSingerService singerService;

    public void get(Integer index,Integer cur_page){
        QuerySingerParam param = buildParam(index,cur_page);
        String data = JSONObject.toJSONString(param);
        try {
            data = URLEncoder.encode(data,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = URLConstant.GET_SINGER + data;
        String result = this.handleResult(url);
        List<QqSinger> list = JsonUtils.parseArray(result, QqSinger.class);
        singerService.batchInsert(list);
    }

    public QuerySingerParam buildParam(Integer index,Integer cur_page){
        QuerySingerParam param = new QuerySingerParam();
        param.setComm(new Comm());
        SingerListParam singerListParam = new SingerListParam();
        singerListParam.setIndex(index);
        singerListParam.setCur_page(cur_page);
        SingerList singerList = new SingerList();
        singerList.setParam(singerListParam);
        param.setSingerList(singerList);
        return param;
    }

    public String handleResult(String url){
        String result = HttpUtils.get(url);
        JSONObject json = JSONObject.parseObject(result);
        if(0 == json.getIntValue("code")){
            json = json.getJSONObject("singerList");
            if(0 == json.getIntValue("code")){
                json = json.getJSONObject("data");
                return json.getJSONArray("singerlist").toJSONString();
            }
        }
        return null;
    }

}
