package com.cheetah.dubbo.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.api.common.DubboConstants;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IQqSingerService;
import com.cheetah.dubbo.base.entity.QqSinger;
import com.cheetah.dubbo.base.mapper.QqSingerMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.cheetah.dubbo.common.utils.SqlUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2019-01-30
 */
@Service(version = InterfaceVersion.VERSION_1_0,protocol= {DubboConstants.PROTOCOL_DUBBO})
@org.springframework.stereotype.Service
public class QqSingerServiceImpl extends SuperServiceImpl<QqSingerMapper, QqSinger> implements IQqSingerService {

    @Override
    public IPage<QqSinger> queryPage(Integer pageNum, Integer pageSize, Map<String,Object> params){
        IPage<QqSinger> page = new Page<>(pageNum,pageSize);
        QueryWrapper<QqSinger> wrapper = new QueryWrapper<>();
        if(null != params.get("singer_name")){
            String singerName = params.get("singer_name").toString();
            singerName = SqlUtil.escapeLike(singerName);
            wrapper.like(QqSinger.FIELD_SINGER_NAME, singerName);
        }
        return this.page(page, wrapper);
    }

}
