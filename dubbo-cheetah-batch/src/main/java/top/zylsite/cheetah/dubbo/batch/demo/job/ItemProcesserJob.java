package top.zylsite.cheetah.dubbo.batch.demo.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import top.zylsite.cheetah.dubbo.batch.demo.entity.Permission;
import top.zylsite.cheetah.dubbo.batch.demo.entity.PermissionRowMapper;

import javax.sql.DataSource;
import java.util.Arrays;
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
public class ItemProcesserJob {

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
    public Job itemProcesserJobDemo() throws Exception {
        return jobBuilderFactory.get("itemProcesserJobDemo")
                .start(itemProcesserJobDemoStep1())
                .build();
    }

    @Bean
    public Step itemProcesserJobDemoStep1() throws Exception {
        return stepBuilderFactory.get("itemProcesserJobDemoStep1")
                .<Permission, Permission>chunk(2)
                .faultTolerant()
                .reader(dbItemRead())
                .processor(codeUpcaseProcesser())
                .writer(flatFileItemWriter())
                .build();

    }

    @Bean
    public Job compositeItemProcesserJobDemo() throws Exception {
        return jobBuilderFactory.get("compositeItemProcesserJobDemo")
                .start(compositeItemProcesserJobDemoStep1())
                .build();
    }

    @Bean
    public Step compositeItemProcesserJobDemoStep1() throws Exception {
        return stepBuilderFactory.get("compositeItemProcesserJobDemoStep1")
                .<Permission, Permission>chunk(2)
                .reader(dbItemRead())
                .processor(compositeItemProcessor())
                .writer(flatFileItemWriter())
                .faultTolerant()//容错处理
                .skip(Exception.class)//跳过此异常
                .skipLimit(5)//跳过的最大次数
                .retry(Exception.class)//出现哪种异常时重试
                .retryLimit(5)//重试的最大次数
                .build();

    }

    @Bean
    @StepScope
    public ItemProcessor<? super Permission,? extends Permission> codeUpcaseProcesser() {
        return new ItemProcessor<Permission, Permission>() {
            @Override
            public Permission process(Permission permission) throws Exception {
                permission.setVcCode(permission.getVcCode().toUpperCase());
                return permission;
            }
        };
    }

    //有多种处理方式
    @Bean
    @StepScope
    public ItemProcessor<? super Permission,? extends Permission> idFilterProcesser() {
        return new ItemProcessor<Permission, Permission>() {
            @Override
            public Permission process(Permission permission) throws Exception {
                if(permission.getId() % 2 == 0){
                    return permission;
                }
                return null;//相当于把此数据过滤掉（从列表中删除）
            }
        };
    }

    @Bean
    @StepScope
    public CompositeItemProcessor<Permission,Permission> compositeItemProcessor(){
        CompositeItemProcessor<Permission,Permission> processor = new CompositeItemProcessor<>();
        processor.setDelegates(Arrays.asList(codeUpcaseProcesser(), idFilterProcesser()));
        return processor;
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
