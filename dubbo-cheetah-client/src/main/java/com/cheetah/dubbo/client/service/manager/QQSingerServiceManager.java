package com.cheetah.dubbo.client.service.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IQqSingerService;
import com.cheetah.dubbo.base.entity.QqSinger;
import com.cheetah.dubbo.common.supers.SuperController;
import com.cheetah.dubbo.common.utils.SqlUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 17:26
 * @Version v1.0
 */
@Component
public class QQSingerServiceManager extends SuperController {

    @Reference(version = InterfaceVersion.VERSION_1_0)
    private IQqSingerService qqSingerService;

    public IPage<QqSinger> queryPage(Integer pageNum, Integer pageSize, Map<String,Object> params){
        return qqSingerService.queryPage(pageNum,pageSize,params);
    }

}
