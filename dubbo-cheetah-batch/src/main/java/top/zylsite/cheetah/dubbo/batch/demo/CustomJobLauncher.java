package top.zylsite.cheetah.dubbo.batch.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Map;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/5 16:45
 */
@Component
@Slf4j
public class CustomJobLauncher implements ApplicationContextAware {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private JobOperator jobOperator;

    private ApplicationContext context;

    @Bean
    public JobRegistryBeanPostProcessor jobRegistrar() throws Exception {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        postProcessor.setBeanFactory(context.getAutowireCapableBeanFactory());
        postProcessor.afterPropertiesSet();
        return postProcessor;
    }


    @Bean
    public JobOperator jobOperator(){
        SimpleJobOperator operator = new SimpleJobOperator();
        operator.setJobLauncher(jobLauncher);
        operator.setJobParametersConverter(new DefaultJobParametersConverter());
        operator.setJobExplorer(jobExplorer);
        operator.setJobRepository(jobRepository);
        operator.setJobRegistry(jobRegistry);
        return operator;
    }

    /* *
     * @Description 启动任务
     * @Author zhaoyl
     * @Date   \
     * @Param  [job, params]
     * @return void
     **/
    public void run(Job job, Map<String,Object> params) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = null;
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        if(!CollectionUtils.isEmpty(params)){
            params.forEach((k,v) -> {
                if(v instanceof String){
                    parametersBuilder.addParameter(k, new JobParameter(v.toString()));
                }else if(v instanceof Double){
                    parametersBuilder.addParameter(k, new JobParameter((Double) v));
                }else if(v instanceof Date){
                    parametersBuilder.addParameter(k, new JobParameter((Date) v));
                }else if(v instanceof Long){
                    parametersBuilder.addParameter(k, new JobParameter((Long) v));
                }else{
                    log.warn("Job【"+job.getName()+"】的参数【"+k+"】的类型【"+k.getClass().getName()+"】不合法,参数类型只支持String、Date、Long、Double四种类型");
                }
            });
            parameters = parametersBuilder.toJobParameters();
        }
        jobLauncher.run(job, parameters);
    }

    public void start(String jobName,String param) throws JobParametersInvalidException, JobInstanceAlreadyExistsException, NoSuchJobException {
        jobOperator.start(jobName,param);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
