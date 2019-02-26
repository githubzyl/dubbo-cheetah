package com.cheetah.dubbo.reptile.qqmusic.service;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.QqSinger;
import com.cheetah.dubbo.base.mapper.QqSingerMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.cheetah.dubbo.common.utils.EncdDecd;
import com.cheetah.dubbo.reptile.qqmusic.common.OSSConstant;
import com.cheetah.dubbo.reptile.service.OSSFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 19:15
 * @Version v1.0
 */
@Component
public class QQSingerService extends SuperServiceImpl<QqSingerMapper, QqSinger> {

    @Autowired
    private OSSFileService ossFileService;

    @Transactional
    public int batchInsert(List<QqSinger> singers) {
        if(CollectionUtils.isEmpty(singers)){
            return 0;
        }
        int total = 0;
        for(QqSinger singer : singers){
            if(!isExist(singer.getSingerId())){
                this.save(singer);
                total++;
            }
        }
        return total;
    }

    public String uploadPhoto(String singer_mid) throws Exception {
        QueryWrapper<QqSinger> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSinger.FIELD_SINGER_MID, singer_mid);
        QqSinger qqSinger = this.getOne(wrapper);
        if(null == qqSinger || StringUtils.isBlank(qqSinger.getSingerPic())){
            return "未找到对应歌手或歌手的头像URL为空";
        }
        String singerPic = qqSinger.getSingerPic();
        String fileName = EncdDecd.MD5String(qqSinger.getSingerId() + singer_mid) + ".webp";
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("singer_mid",singer_mid);
        PutObjectResult result = ossFileService.uploadToDefaultBucket(singerPic, OSSConstant.IMAGE_DIRECTORY, fileName,null);
        if(null == result){
            throw new Exception("歌手头像上传到OSS服务器失败");
        }
        return ossFileService.getPreviewUrl(OSSConstant.IMAGE_DIRECTORY, fileName, true);
    }

    private boolean isExist(Long singerId){
        QueryWrapper<QqSinger> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSinger.FIELD_SINGER_ID, singerId);
        int count = this.count(wrapper);
        return count > 0 ? true : false;
    }

}
