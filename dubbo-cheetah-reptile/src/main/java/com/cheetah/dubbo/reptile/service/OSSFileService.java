package com.cheetah.dubbo.reptile.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.cheetah.dubbo.reptile.qqmusic.common.OSSConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Slf4j
@Service
public class OSSFileService {

    private static OSSClient client;

    static {
        client = new OSSClient(OSSConstant.END_POINT, OSSConstant.ACCESSKEY_ID, OSSConstant.ACCESSKEY_SECRET);
    }

    public PutObjectResult uploadToDefaultBucket(String url, String dir, String fileName, ObjectMetadata metadata) {
        String objectKey = dir + fileName;
        return this.upload(url, OSSConstant.DEFAULT_BUCKET_NAME, objectKey, metadata);
    }

    public PutObjectResult upload(String url, String bucketName, String objectKey, ObjectMetadata metadata) {
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
            return client.putObject(bucketName, objectKey, inputStream, metadata);
        } catch (IOException e) {
            log.error("远程文件流上传失败:", e);
        }
        return null;
    }

    public String getPreviewUrl(String dir, String fileName, boolean isImage){
        StringBuilder sb = new StringBuilder(OSSConstant.OSS_BIND_DOMAIN);
        sb.append(dir).append(fileName);
        if(isImage){
            sb.append(OSSConstant.OSS_FILE_URL_SUFFIX);
        }
        return sb.toString();
    }

}
