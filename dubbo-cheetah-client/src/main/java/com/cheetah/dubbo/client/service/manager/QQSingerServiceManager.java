package com.cheetah.dubbo.client.service.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IQqSingerService;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 17:26
 * @Version v1.0
 */
@Component
public class QQSingerServiceManager {

    @Reference(version = InterfaceVersion.VERSION_1_0)
    private IQqSingerService qqSingerService;

}
