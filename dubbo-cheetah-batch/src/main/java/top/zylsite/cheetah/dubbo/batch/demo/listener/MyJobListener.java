package top.zylsite.cheetah.dubbo.batch.demo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * <p>Description: job监听器</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 16:45
 */
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before job:" + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after job:" + jobExecution.getJobInstance().getJobName());
    }

}
