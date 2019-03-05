package top.zylsite.cheetah.dubbo.batch.demo.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import top.zylsite.cheetah.dubbo.batch.demo.entity.Permission;
import top.zylsite.cheetah.dubbo.batch.demo.entity.PermissionRowMapper;

import javax.sql.DataSource;
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
public class XMLFileItemWriterJob {

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
    public Job xmlFileItemWriterJobDemo() throws Exception {
        return jobBuilderFactory.get("xmlFileItemWriterJobDemo")
                .start(xmlFileItemWriterJobDemoStep1())
                .build();
    }

    @Bean
    public Step xmlFileItemWriterJobDemoStep1() throws Exception {
        return stepBuilderFactory.get("xmlFileItemWriterJobDemoStep1")
                .<Permission, Permission>chunk(10)
                .faultTolerant()
                .reader(dbItemRead())
                .writer(xmlFileItemWriter())
                .build();

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
