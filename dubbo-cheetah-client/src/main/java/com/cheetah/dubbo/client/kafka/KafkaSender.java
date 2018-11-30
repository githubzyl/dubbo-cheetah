package com.cheetah.dubbo.client.kafka;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private Gson gson = new GsonBuilder().create();

	// 发送消息方法
	public void send(int total) {
		Message message = null;
		for (int i = 0; i <= total; i++) {
			message = new Message();
			message.setId(System.currentTimeMillis());
			message.setMsg(UUID.randomUUID().toString());
			message.setSendTime(new Date());
			kafkaTemplate.send("cluster_kafka_topic", gson.toJson(message));
		}
	}

}
