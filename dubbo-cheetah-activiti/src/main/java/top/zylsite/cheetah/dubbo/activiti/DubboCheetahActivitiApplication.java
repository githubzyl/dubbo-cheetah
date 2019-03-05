package top.zylsite.cheetah.dubbo.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DubboCheetahActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboCheetahActivitiApplication.class, args);
    }

}
