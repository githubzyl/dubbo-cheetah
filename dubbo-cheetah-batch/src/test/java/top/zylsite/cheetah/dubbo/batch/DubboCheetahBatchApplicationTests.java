package top.zylsite.cheetah.dubbo.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.zylsite.cheetah.dubbo.batch.demo.CustomJobLauncher;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboCheetahBatchApplicationTests {

    @Autowired
    private CustomJobLauncher customJobLauncher;

    @Autowired
    private Job parameterJobDemo;

    @Test
    public void contextLoads() throws Exception{
        Map<String,Object> param = new HashMap<>();
        param.put("info","123456");
        //customJobLauncher.run(parameterJobDemo,param);
        customJobLauncher.start("parameterJobDemo","info=123456789");
    }

}
