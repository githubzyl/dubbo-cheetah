package com.cheetah.dubbo.reptile;

import com.cheetah.dubbo.common.utils.HttpUtils;
import com.cheetah.dubbo.reptile.qqmusic.common.URLConstant;
import com.cheetah.dubbo.reptile.qqmusic.common.param.DownloadSongParam;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 17:02
 * @Version v1.0
 */
public class Test1 {

    @Test
    public void test1() throws UnsupportedEncodingException {
        String data = "%7B%22req%22%3A%7B%22module%22%3A%22CDN.SrfCdnDispatchServer%22%2C%22method%22%3A%22GetCdnDispatch%22%2C%22param%22%3A%7B%22guid%22%3A%225166809342%22%2C%22calltype%22%3A0%2C%22userip%22%3A%22%22%7D%7D%2C%22req_0%22%3A%7B%22module%22%3A%22vkey.GetVkeyServer%22%2C%22method%22%3A%22CgiGetVkey%22%2C%22param%22%3A%7B%22guid%22%3A%225166809342%22%2C%22songmid%22%3A%5B%22001Qu4I30eVFYb%22%5D%2C%22songtype%22%3A%5B0%5D%2C%22uin%22%3A%220%22%2C%22loginflag%22%3A1%2C%22platform%22%3A%2220%22%7D%7D%2C%22comm%22%3A%7B%22uin%22%3A0%2C%22format%22%3A%22json%22%2C%22ct%22%3A24%2C%22cv%22%3A0%7D%7D";
        data = URLDecoder.decode(data, "utf-8");
        System.out.println(data);
    }

    @Test
    public void test2() {
        String data = new DownloadSongParam("001Qu4I30eVFYb").toJsonString();
        String url = URLConstant.GET_VKEY + data;
        String result = HttpUtils.get(url);
        System.out.println(result);
    }


}
