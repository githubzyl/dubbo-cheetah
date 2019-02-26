package com.cheetah.dubbo.reptile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;

public class OSSTest {

    @Test
    public void upload() throws Exception {
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAIegzSYWFrlUuX";
        String accessKeySecret = "5YW76SocX8ALAo0yoZfaVGeeN8l4HB";
        String bucketName = "jason-file-1";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = new URL("http://y.gtimg.cn/music/photo_new/T001R150x150M0000013RsPD3Xs0FG.webp").openStream();
        PutObjectResult result = ossClient.putObject(bucketName, "image/banyang.webp", inputStream);

        GetObjectRequest request = new GetObjectRequest(bucketName,"image/banyang.webp");
        OSSObject object = ossClient.getObject(request);
        ossClient.shutdown();
    }

}
