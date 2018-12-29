package com.cheetah.dubbo.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.cheetah.dubbo.server.common.CustomMetaObjectHandler;

@EnableTransactionManagement
@Configuration
@MapperScan("com.cheetah.dubbo.server.mapper")
public class MybatisPlusConfig {

	/**
	 * <p>Description: SQL执行效率插件,plus 的性能优化【生产环境可以关闭】</>
	 * @author zhaoyl
	 * @return
	 */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
 
    /**
     * <p>Description: mybatis-plus分页插件</>
     * @author zhaoyl
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    /**
     * <p>Description: 乐观锁插件</>
     * @author zhaoyl
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
 
    /**
     * <p>Description: 注入sql注入器(逻辑删除插件)</>
     * @author zhaoyl
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
    
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new CustomMetaObjectHandler();
    }

}
