package com.cheetah.dubbo.reptile.qqmusic.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cheetah.dubbo.base.entity.vo.QQSongVO;
import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.URLConstant;
import com.cheetah.dubbo.reptile.qqmusic.service.QQSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 16:48
 * @Version v1.0
 */
@Component
public class GetQQSongService {

    @Autowired
    private QQSongService songService;

    public int get(String singer_mid, Integer cur_page, Integer num, Integer max_page) {
        int total = 0;
        int start_page = cur_page;
        List<QQSongVO> songVOList = new ArrayList<>();
        Long singerId = null;
        if(-1 == max_page){
            max_page = Integer.MAX_VALUE;
        }
        while(start_page <= max_page){
            String url = buildParam(singer_mid, start_page, num);
            JSONObject result = this.handleResult(url);
            JSONArray songList = result.getJSONArray("list");
            if (null == songList || songList.size() <= 0) {
                break;
            }
            singerId = result.getLong("singer_id");
            JSONObject json = null;
            QQSongVO songVO = null;
            for (int i = 0, size = songList.size(); i < size; i++) {
                json = songList.getJSONObject(i);
                json = json.getJSONObject("musicData");
                songVO = JSONObject.parseObject(json.toJSONString(), QQSongVO.class);
                songVOList.add(songVO);
            }
            if(!CollectionUtils.isEmpty(songVOList)){
                total += songService.batchInsert(singerId, songVOList);
                songVOList.clear();
            }
            start_page++;
        }
        return total;
    }

    public String buildParam(String singer_mid, Integer cur_page, Integer num) {
        int begin = (cur_page - 1) * num;
        StringBuilder sb = new StringBuilder(URLConstant.GET_SONG);
        sb.append("&singermid=").append(singer_mid)
                .append("&num=").append(num)
                .append("&begin=").append(begin);
        return sb.toString();
    }

    public JSONObject handleResult(String url) {
        String result = HttpUtils.get(url);
        JSONObject json = JSONObject.parseObject(result);
        if (0 == json.getIntValue("code")) {
            return json.getJSONObject("data");
        }
        return null;
    }

}
