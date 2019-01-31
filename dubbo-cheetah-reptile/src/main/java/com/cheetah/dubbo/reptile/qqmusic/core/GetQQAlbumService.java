package com.cheetah.dubbo.reptile.qqmusic.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cheetah.dubbo.base.entity.vo.QQAlbumVO;
import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.URLConstant;
import com.cheetah.dubbo.reptile.qqmusic.common.param.Comm;
import com.cheetah.dubbo.reptile.qqmusic.common.param.QueryAlbumParam;
import com.cheetah.dubbo.reptile.qqmusic.common.param.SingerAlbum;
import com.cheetah.dubbo.reptile.qqmusic.common.param.SingerAlbumParam;
import com.cheetah.dubbo.reptile.qqmusic.service.QQAlbumService;
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
public class GetQQAlbumService {

    @Autowired
    private QQAlbumService qqAlbumService;

    public void get(String singer_mid, Integer cur_page, Integer num, Integer max_page) {
        QueryAlbumParam param = buildParam(singer_mid,cur_page,num);
        String data = JSONObject.toJSONString(param);
        try {
            data = URLEncoder.encode(data,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = URLConstant.GET_ALBUM + data;
        JSONArray jsonArray = this.handleResult(url);
        if(null == jsonArray || jsonArray.size() <= 0){
            return;
        }
        List<QQAlbumVO> list = JSONObject.parseArray(jsonArray.toJSONString(), QQAlbumVO.class);
        qqAlbumService.batchInsert(singer_mid,list);
    }

    public QueryAlbumParam buildParam(String singer_mid, Integer cur_page,Integer num){
        QueryAlbumParam param = new QueryAlbumParam();
        param.setComm(new Comm());
        SingerAlbumParam singerAlbumParam = new SingerAlbumParam();
        singerAlbumParam.setSingermid(singer_mid);
        singerAlbumParam.setCur_page(cur_page);
        singerAlbumParam.setNum(num);
        SingerAlbum singerAlbum = new SingerAlbum();
        singerAlbum.setParam(singerAlbumParam);
        param.setSingerAlbum(singerAlbum);
        return param;
    }

    public JSONArray handleResult(String url) {
        String result = HttpUtils.get(url);
        JSONObject json = JSONObject.parseObject(result);
        if (0 == json.getIntValue("code")) {
            json = json.getJSONObject("singerAlbum");
            if (0 == json.getIntValue("code")) {
                json = json.getJSONObject("data");
                if(json.containsKey("list")){
                    return json.getJSONArray("list");
                }
            }
        }
        return null;
    }

}
