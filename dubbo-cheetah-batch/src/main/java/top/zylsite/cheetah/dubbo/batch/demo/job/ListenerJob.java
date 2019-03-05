package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zylsite.cheetah.dubbo.batch.demo.listener.MyChunkListener;
import top.zylsite.cheetah.dubbo.batch.demo.listener.MyJobListener;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/1 9:49
 */
@Configuration
@EnableBatchProcessing
public class ListenerJob {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job listenerJobDemo() {
        return jobBuilderFactory.get("listenerJobDemo")
                .start(listenerJobStep1())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step listenerJobStep1() {
        return stepBuilderFactory.get("listenerJobStep1")
               .<String,String>chunk(2)//读写和处理数据
               .faultTolerant()
               .listener(new MyChunkListener())
               .reader(read())
               .processor(process())
               .writer(write())
               .build();
    }

    @Bean
    public ItemProcessor<String,String> process() {
        return new ItemProcessor<String, String>() {
            @Override
            public String process(String s) throws Exception {
                return s + "-1";
            }
        };
    }

    @Bean
    public ItemReader<String> read() {
        return new ListItemReader<>(Arrays.asList("java","spring","mybatis"));
    }

    @Bean
    public ItemWriter<String> write() {
        return new ItemWriter<String>() {
            @Override
            public void write(List list) throws Exception {
                list.forEach(str -> {
                    System.out.println("lister job str = " + str);
                });
            }
        };
    }

}
