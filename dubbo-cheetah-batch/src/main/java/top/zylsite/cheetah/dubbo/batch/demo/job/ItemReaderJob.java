package top.zylsite.cheetah.dubbo.batch.demo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zylsite.cheetah.dubbo.batch.demo.reader.ItemReader1;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 19:41
 */
@Configuration
public class ItemReaderJob {

    //注入创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //任务是由Step决定的
    //注入创建Step对象的对象
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemReaderJobDemo() {
        return jobBuilderFactory.get("itemReaderJobDemo")
                .start(itemReaderJobStep1())
                .build();
    }

    private Step itemReaderJobStep1() {
        return stepBuilderFactory.get("itemReaderJobStep1")
                .<String, String>chunk(2)
                .faultTolerant()
                .reader(itemReader1())
                .writer(list -> {
                    for (String str : list) {
                        System.out.println(str + "...");
                    }
                })
                .build();

    }

    @Bean
    public ItemReader1 itemReader1() {
        List<String> data = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
        return new ItemReader1(data);
    }

}
