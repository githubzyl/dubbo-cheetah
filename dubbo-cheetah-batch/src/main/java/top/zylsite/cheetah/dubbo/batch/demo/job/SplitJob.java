package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/1 9:49
 */
@Configuration
@EnableBatchProcessing
public class SplitJob {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job splitJobDemo() {
        //当某一步的状态是COMPLETED时再执行下一步
        return jobBuilderFactory.get("splitJobDemo")
                .start(splitFlow1())
                .split(new SimpleAsyncTaskExecutor())
                .add(splitFlow2())
                .end()
               .build();
    }

    //创建flow对象，指明包含哪些step
    @Bean
    public Flow splitFlow1(){
        return new FlowBuilder<Flow>("splitFlow1")
                .start(splitFlowStep1())
                .next(splitFlowStep2())
                .build();
    }

    @Bean
    public Flow splitFlow2(){
        return new FlowBuilder<Flow>("splitFlow2")
                .start(splitFlowStep3())
                .build();
    }

    @Bean
    public Step splitFlowStep1() {
        return stepBuilderFactory.get("splitFlowStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this is first split flow step");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step splitFlowStep2() {
        return stepBuilderFactory.get("splitFlowStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this is second split flow step");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step splitFlowStep3() {
        return stepBuilderFactory.get("splitFlowStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("this is third split flow step");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}
