package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import top.zylsite.cheetah.dubbo.batch.demo.entity.Permission;
import top.zylsite.cheetah.dubbo.batch.demo.entity.PermissionFieldSetMapper;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 19:41
 */
@Configuration
public class FlatFileItemReaderJob {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatFileItemReaderJobDemo() {
        return jobBuilderFactory.get("flatFileItemReaderJobDemo")
                .start(flatFileItemReaderJobDemoStep1())
                .build();
    }

    @Bean
    public Step flatFileItemReaderJobDemoStep1() {
        return stepBuilderFactory.get("flatFileItemReaderJobDemoStep1")
                .<Permission, Permission>chunk(2)
                .faultTolerant()
                .reader(flatFileItemRead())
                .writer(list -> {
                    for (Permission p : list) {
                        System.out.println("权限：" + p);
                    }
                })
                .build();

    }

    @Bean
    @StepScope
    public FlatFileItemReader<Permission> flatFileItemRead() {
        FlatFileItemReader<Permission> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("file/t_permission.txt"));
        reader.setLinesToSkip(1);//跳过第1行
        //解析数据
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"vcCode","vcName","vcUrl"});
        //把解析出的数据映射为对象
        DefaultLineMapper<Permission> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new PermissionFieldSetMapper());
        //映射检查
        mapper.afterPropertiesSet();
        reader.setLineMapper(mapper);
        return reader;
    }

}
