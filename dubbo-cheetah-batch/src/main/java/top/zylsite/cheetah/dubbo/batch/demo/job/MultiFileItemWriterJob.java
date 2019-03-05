package top.zylsite.cheetah.dubbo.batch.demo.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import top.zylsite.cheetah.dubbo.batch.demo.entity.Permission;
import top.zylsite.cheetah.dubbo.batch.demo.entity.PermissionFieldSetMapper;
import top.zylsite.cheetah.dubbo.batch.demo.entity.PermissionRowMapper;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 从多个文件读取</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 19:41
 */
@Configuration
public class MultiFileItemWriterJob {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job multiFileItemWriterJobDemo() throws Exception {
        return jobBuilderFactory.get("multiFileItemWriterJobDemo")
                .start(multiFileItemWriterJobDemoStep1())
                .build();
    }

    @Bean
    public Step multiFileItemWriterJobDemoStep1() throws Exception {
        return stepBuilderFactory.get("multiFileItemWriterJobDemoStep1")
                .<Permission, Permission>chunk(5)
                .faultTolerant()
                .reader(dbItemRead())
                .writer(mutliFileItemWriter())
                .build();

    }

    @Bean
    public Job multiClassifierFileItemWriterJobDemo() throws Exception {
        return jobBuilderFactory.get("multiClassifierFileItemWriterJobDemo")
                .start(multiClassifierFileItemWriterJobDemoStep1())
                .build();
    }

    @Bean
    public Step multiClassifierFileItemWriterJobDemoStep1() throws Exception {
        return stepBuilderFactory.get("multiClassifierFileItemWriterJobDemoStep1")
                .<Permission, Permission>chunk(5)
                .faultTolerant()
                .reader(dbItemRead())
                .writer(mutliClassifierFileItemWriter())
                .stream(flatFileItemWriter())
                .stream(xmlFileItemWriter())
                .build();

    }

    @Bean
    @StepScope
    public CompositeItemWriter<Permission> mutliFileItemWriter() throws Exception {
        CompositeItemWriter<Permission> writer = new CompositeItemWriter<>();
        writer.setDelegates(Arrays.asList(flatFileItemWriter(),xmlFileItemWriter()));
        writer.afterPropertiesSet();
        return writer;
    }

    @Bean
    @StepScope
    public ClassifierCompositeItemWriter<Permission> mutliClassifierFileItemWriter() throws Exception {
        ClassifierCompositeItemWriter<Permission> writer = new ClassifierCompositeItemWriter<>();
        writer.setClassifier(new Classifier<Permission, ItemWriter<? super Permission>>() {
            @Override
            public ItemWriter<? super Permission> classify(Permission permission) {
                //按照permission的id分类
                int id = permission.getId();
                if(id % 2 == 0){
                    try {
                        return flatFileItemWriter();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        return xmlFileItemWriter();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
        return writer;
    }



    @Bean
    @StepScope
    public FlatFileItemWriter<Permission> flatFileItemWriter() throws Exception {
        FlatFileItemWriter<Permission> writer = new FlatFileItemWriter<>();
        //输出的文件路径
        String path = "F:\\permission.txt";
        writer.setResource(new FileSystemResource(path));
        //把permission对象转成字符串写入文件
        writer.setLineAggregator(new LineAggregator<Permission>() {
            ObjectMapper mapper = new ObjectMapper();

            @Override
            public String aggregate(Permission permission) {
                try {
                    return mapper.writeValueAsString(permission);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        writer.afterPropertiesSet();
        return writer;
    }

    @Bean
    @StepScope
    public StaxEventItemWriter<Permission> xmlFileItemWriter() throws Exception {
        StaxEventItemWriter<Permission> writer = new StaxEventItemWriter<>();
        //输出的文件路径
        String path = "F:\\permission.xml";
        writer.setResource(new FileSystemResource(path));
        XStreamMarshaller marshaller = new XStreamMarshaller();
        //指定根标签的类型
        Map<String,Class> aliases = new HashMap<>();
        aliases.put("permission", Permission.class);
        marshaller.setAliases(aliases);
        writer.setMarshaller(marshaller);
        writer.setRootTagName("permissions");
        writer.afterPropertiesSet();
        return writer;
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<Permission> dbItemRead() {
        JdbcPagingItemReader<Permission> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(5);
        reader.setRowMapper(new PermissionRowMapper());
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("id,vc_code,vc_name,vc_url");
        provider.setFromClause("from t_permission_batch");
        Map<String, Order> sort = new HashMap<>();
        sort.put("id", Order.ASCENDING);
        provider.setSortKeys(sort);
        reader.setQueryProvider(provider);
        return reader;
    }

}
