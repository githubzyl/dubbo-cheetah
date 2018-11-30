package com.cheetah.dubbo.server.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.IDemoService;

@Service(version = InterfaceVersion.VERSION_1_0)
public class DemoServiceImpl implements IDemoService {

	@Override
	public Object hello(String content) throws Exception {
		return content;
	}

}
