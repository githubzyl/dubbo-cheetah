package com.cheetah.dubbo.client.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IDemoService;

@Component
public class DemoConsumerService {

	@Reference(version = InterfaceVersion.VERSION_1_0)
	private IDemoService demoService;

	public Object sayHello(String content) throws Exception {
		return demoService.hello(content);
	}

}
