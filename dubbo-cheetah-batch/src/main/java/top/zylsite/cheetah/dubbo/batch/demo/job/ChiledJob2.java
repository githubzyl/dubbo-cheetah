package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 15:26
 */
@Configuration
@EnableBatchProcessing
public class ChiledJob2 {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chiledJobTwo() {
        return jobBuilderFactory.get("chiledJobTwo")
                .start(chiledJobTwoStep1())
                .next(chiledJobTwoStep2())
                .build();
    }

    @Bean
    public Step chiledJobTwoStep1() {
        return stepBuilderFactory.get("chiledJobTwoStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("the child job two step1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step chiledJobTwoStep2() {
        return stepBuilderFactory.get("chiledJobTwoStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("the child job two step2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}
