package com.cheetah.dubbo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cheetah.dubbo.client.kafka.KafkaSender;
import com.cheetah.dubbo.client.service.DemoConsumerService;

@RestController
public class DemoController {

	@Autowired
    private DemoConsumerService demoConsumerService;
	
	@Autowired
	private KafkaSender kafkaSender;
	
	@GetMapping("/solr/search/{content")
	public Object solrSearch(@PathVariable String content ) throws Exception {
		return demoConsumerService.sayHello(content);
	}
	
	@GetMapping("/kafka/send/{total}")
	public Object kafkaSend(@PathVariable Integer total) {
		kafkaSender.send(total);
		return "SUCCESS";
	}
	
}
