package com.cheetah.dubbo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@EnableDubbo
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class CheetahDubboClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheetahDubboClientApplication.class, args);
	}
}
