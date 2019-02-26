package com.cheetah.dubbo.reptile.qqmusic.core;

import com.alibaba.fastjson.JSONObject;
import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.QQMusicConstant;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/2/21 11:04
 */
@Component
public class QQMusicSearchService {

    public List<JSONObject> searchByKeywordForSearchBox(String keyword){
        String url = QQMusicConstant.SEARCH_BOX_URL;
        try {
            url += URLEncoder.encode(keyword,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = this.handleResult(url);
        List<JSONObject> list = new ArrayList<>();
        if(null != jsonObject){
            this.add("song", list, jsonObject);
            this.add("singer", list, jsonObject);
            this.add("album", list, jsonObject);
            this.add("mv", list, jsonObject);
        }
        return list;
    }

    public JSONObject searchByKeyword(String keyword, int search_type, int page_num){
        String searchUrl = "";
        if(QQMusicConstant.SEARCH_TYPE_ALBUM == search_type){
            searchUrl = QQMusicConstant.SEARCH_ALBUM_URL;
        }else if(QQMusicConstant.SEARCH_TYPE_MV == search_type){
            searchUrl = QQMusicConstant.SEARCH_MV_URL;
        }else{
            searchUrl = QQMusicConstant.SEARCH_SONG_URL;
        }
        searchUrl += "&p=" + page_num + "&w=";
        try {
            searchUrl += URLEncoder.encode(keyword,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this.handleResult(searchUrl);
    }


    public JSONObject handleResult(String url) {
        String result = HttpUtils.get(url);
        JSONObject json = JSONObject.parseObject(result);
        if (0 == json.getIntValue("code")) {
            return json.getJSONObject("data");
        }
        return null;
    }

    public void add(String key, List<JSONObject> list,JSONObject jsonObject){
        if(jsonObject.containsKey(key)){
            JSONObject attr = jsonObject.getJSONObject(key);
            if(null != attr.getJSONArray("itemlist")
                && attr.getJSONArray("itemlist").size() > 0){
                attr.put("type_name", key);
                list.add(attr);
            }
        }
    }

}
