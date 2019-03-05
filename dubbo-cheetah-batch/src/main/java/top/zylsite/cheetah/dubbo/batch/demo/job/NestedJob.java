package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 15:26
 */
@Configuration
@EnableBatchProcessing
public class NestedJob {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job chiledJobOne;

    @Autowired
    private Job chiledJobTwo;

    @Bean
    public Job parentJob(JobRepository repository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory.get("parentJob")
                .start(childJob1(repository,transactionManager))
                .next(childJob2(repository,transactionManager))
                .build();
    }

    //返回job类型的step，特殊的step
    private Step childJob1(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob1"))
                .job(chiledJobOne)
                .launcher(launcher)//使用启动父job的启动对象
                .repository(repository)//存储器
                .transactionManager(transactionManager)//事务管理器
                .build();
    }

    private Step childJob2(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob2"))
                .job(chiledJobTwo)
                .launcher(launcher)//使用启动父job的启动对象
                .repository(repository)//存储器
                .transactionManager(transactionManager)//事务管理器
                .build();
    }

}
