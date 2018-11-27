package com.cheetah.dubbo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cheetah.dubbo.client.service.DemoConsumerService;

@RestController
public class DemoController {

	@Autowired
    private DemoConsumerService demoConsumerService;
	
	@GetMapping("/solr/search/{page}/{pageSize}")
	public Object solrSearch(@PathVariable Integer page, @PathVariable Integer pageSize ) throws Exception {
		return demoConsumerService.solrSearch(page, pageSize);
	}
	
}
