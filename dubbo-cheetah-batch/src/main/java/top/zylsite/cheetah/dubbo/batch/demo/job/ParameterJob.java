package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zylsite.cheetah.dubbo.batch.demo.listener.MyChunkListener;
import top.zylsite.cheetah.dubbo.batch.demo.listener.MyJobListener;

import java.util.Map;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 18:54
 */
@Configuration
@EnableBatchProcessing
public class ParameterJob implements StepExecutionListener {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private Map<String,JobParameter> parameters;

    @Bean
    public Job parameterJobDemo() {
        return jobBuilderFactory.get("parameterJobDemo")
                .start(parameterJobStep1())
                .build();
    }

    //job执行的是step，给step传递数据即可
    //使用step级别的监听来传递数据
    @Bean
    public Step parameterJobStep1() {
        return stepBuilderFactory.get("parameterJobStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        //输出接收到的参数
                        System.out.println(parameters.get("info"));
                        return RepeatStatus.FINISHED;
                    }
                })
                .listener(this)
                .build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameters = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
