package com.cheetah.dubbo.reptile;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class DubboCheetahReptileApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboCheetahReptileApplication.class, args);
    }

}

