package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class DBItemReaderJob {

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
    public Job dbItemReaderJobDemo() {
        return jobBuilderFactory.get("dbItemReaderJobDemo")
                .start(dbItemReaderJobDemoStep1())
                .build();
    }

    @Bean
    public Step dbItemReaderJobDemoStep1() {
        return stepBuilderFactory.get("dbItemReaderJobDemoStep1")
                .<Permission, Permission>chunk(2)
                .faultTolerant()
                .reader(dbItemRead())
                .writer(list -> {
                    for (Permission p : list) {
                        System.out.println("权限：" + p);
                    }
                })
                .build();

    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<Permission> dbItemRead() {
        JdbcPagingItemReader<Permission> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(5);
        reader.setRowMapper(new PermissionRowMapper());
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("vc_code,vc_name,vc_url");
        provider.setFromClause("from t_permission");
        Map<String, Order> sort = new HashMap<>();
        sort.put("vc_code",Order.ASCENDING);
        provider.setSortKeys(sort);
        reader.setQueryProvider(provider);
        return reader;
    }

}
