package com.cheetah.dubbo.reptile.qqmusic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.base.entity.QqSinger;
import com.cheetah.dubbo.base.mapper.QqSingerMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
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

    private boolean isExist(Long singerId){
        QueryWrapper<QqSinger> wrapper = new QueryWrapper<>();
        wrapper.eq(QqSinger.FIELD_SINGER_ID, singerId);
        int count = this.count(wrapper);
        return count > 0 ? true : false;
    }

}
