package com.cheetah.dubbo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@EnableDubbo
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class CheetahDubboServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheetahDubboServerApplication.class, args);
	}
}
