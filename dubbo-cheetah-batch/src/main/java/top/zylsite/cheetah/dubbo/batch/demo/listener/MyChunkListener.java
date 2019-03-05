package top.zylsite.cheetah.dubbo.batch.demo.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 16:48
 */
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        System.out.println("before chunk:" + context.getStepContext().getStepName());

    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        System.out.println("after chunk:" + context.getStepContext().getStepName());
    }

}
