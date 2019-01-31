package com.cheetah.dubbo.reptile.qqmusic.common.param;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/31 12:40
 * @Version v1.0
 */
@Data
public class DownloadSongParam implements Serializable {

    private static final long serialVersionUID = 3729698660714193332L;

    @JSONField(serialize = false)
    private String songMid;

    private Comm comm;
    private Req req;
    private Req0 req_0;

    public DownloadSongParam(String songMid){
        this.comm = new Comm();
        this.req = new Req();
        this.req_0 = new Req0(songMid);
    }

    public String toJsonString(){
        String p = JSONObject.toJSONString(this);
        try {
            p = URLEncoder.encode(p, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Data
    private class Req {

        private String module = "CDN.SrfCdnDispatchServer";
        private String method = "GetCdnDispatch";
        private Param param;

        public Req(){
            this.param = new Param();
        }

        @Data
        private class Param {

            private String guid = "5166809342";
            private Integer calltype = 0;
            private String userip = "";

        }

    }

    @Data
    private class Req0 {

        @JSONField(serialize = false)
        private String songMid;

        private String module = "vkey.GetVkeyServer";
        private String method = "CgiGetVkey";
        private Param param;

        public Req0(String songMid){
            this.param = new Param(songMid);
        }

        @Data
        private class Param {

            private String guid = "5166809342";
            private String[] songmid = new String[1];
            private Integer[] songtype = new Integer[]{0};
            private String uin = "0";
            private Integer loginflag =1;
            private Integer platform = 20;

            public Param(String songmid){
                this.songmid[0] = songmid;
            }

        }

    }

    @Data
    private class Comm{

        private Integer uin = 0;
        private String format = "json";
        private Integer ct = 24;
        private Integer cv = 0;

    }

}
