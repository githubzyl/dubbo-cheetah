package top.zylsite.cheetah.dubbo.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import top.zylsite.cheetah.dubbo.batch.demo.CustomJobLauncher;

@SpringBootApplication
@EnableBatchProcessing
public class DubboCheetahBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboCheetahBatchApplication.class, args);
    }

}
