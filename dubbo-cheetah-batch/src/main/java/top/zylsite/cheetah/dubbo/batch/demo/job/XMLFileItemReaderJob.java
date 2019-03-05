package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import top.zylsite.cheetah.dubbo.batch.demo.entity.Permission;
import top.zylsite.cheetah.dubbo.batch.demo.entity.PermissionFieldSetMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 19:41
 */
@Configuration
public class XMLFileItemReaderJob {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job xmlFileItemReaderJobDemo() {
        return jobBuilderFactory.get("xmlFileItemReaderJobDemo")
                .start(xmlFileItemReaderJobDemoStep1())
                .build();
    }

    @Bean
    public Step xmlFileItemReaderJobDemoStep1() {
        return stepBuilderFactory.get("xmlFileItemReaderJobDemoStep1")
                .<Permission, Permission>chunk(10)
                .faultTolerant()
                .reader(xmlFileItemRead())
                .writer(list -> {
                    for (Permission p : list) {
                        System.out.println("权限：" + p);
                    }
                })
                .build();

    }

    @Bean
    @StepScope
    public StaxEventItemReader<Permission> xmlFileItemRead() {
        StaxEventItemReader<Permission> reader = new StaxEventItemReader<>();
        reader.setResource(new ClassPathResource("file/t_permission.xml"));
        //指定需要处理的根标签
        reader.setFragmentRootElementName("RECORD");
        //把xml转成对象
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        //指定根标签的类型
        Map<String,Class> map = new HashMap<>();
        map.put("RECORD", Permission.class);
        unmarshaller.setAliases(map);

        reader.setUnmarshaller(unmarshaller);
        return reader;
    }

}
