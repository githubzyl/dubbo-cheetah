package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
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
 * @date 2019/3/1 9:49
 */
@Configuration
@EnableBatchProcessing
public class DeciderDemo {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job deciderJobDemo() {
        //当某一步的状态是COMPLETED时再执行下一步
        return jobBuilderFactory.get("deciderJobDemo")
               .start(deciderStep1())
               .next(myDecider())
               .from(myDecider()).on("even").to(deciderStep2())
               .from(myDecider()).on("odd").to(deciderStep3())
               .from(deciderStep3()).on("*").to(myDecider())
               .end()
               .build();
    }

    @Bean
    public JobExecutionDecider myDecider(){
        return new MyDecider();
    }


    @Bean
    public Step deciderStep1() {
        return stepBuilderFactory.get("deciderStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this is first decider step");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step deciderStep2() {
        return stepBuilderFactory.get("deciderStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this is second decider step");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step deciderStep3() {
        return stepBuilderFactory.get("deciderStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this is third decider step");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}
