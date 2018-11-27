package com.cheetah.dubbo.client.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.ISolrService;

@Component
public class DemoConsumerService {

	@Reference(version = InterfaceVersion.VERSION_1_0)
	private ISolrService solrService;

	public Object solrSearch(int page, int pageSize) throws Exception {
		page = (page > 0 ? (page - 1) : 0);
		pageSize = (pageSize > 0 ? pageSize : 10);
		return solrService.search(page,pageSize);
	}

}
