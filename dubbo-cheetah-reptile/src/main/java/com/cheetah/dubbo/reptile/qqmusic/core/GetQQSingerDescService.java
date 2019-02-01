package com.cheetah.dubbo.reptile.qqmusic.core;

import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.URLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    private String singerDescDb = "singer_desc";

    public String get(String singerMid){
        Query query = new Query();
        Criteria criteria = Criteria.where("singerMid").is(singerMid);
        query.addCriteria(criteria);
        List<Map> list = mongoTemplate.find(query,Map.class,singerDescDb);
        if(CollectionUtils.isEmpty(list)){
            String url = URLConstant.GET_SINGER_DESC + singerMid;
            String result = this.handleResult(url);
            Map<String,Object> map = new HashMap<>();
            map.put("singerMid",singerMid);
            map.put("desc", result);
            mongoTemplate.insert(map,singerDescDb);
            return result;
        }
        return list.get(0).get("desc").toString();
    }

    public String handleResult(String url){
        Map<String,String> header = new HashMap<>();
        header.put("referer","https://c.y.qq.com/xhr_proxy_utf8.html");
        String result = HttpUtils.get(url,header);
        return result;
    }

}
