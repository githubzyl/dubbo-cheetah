package com.cheetah.dubbo.reptile.config;

import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/2/20 21:22
 */
@Configuration
public class MongoConfig {

    /* *
     * @Description 设置最大连接闲置时间，解决Prematurely reached end of stream异常
     * @Author zhaoyl
     * @Date   2019/2/20
     * @Param  []
     * @return com.mongodb.MongoClientOptions
     **/
    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder().maxConnectionIdleTime(6000).build();
    }

}
