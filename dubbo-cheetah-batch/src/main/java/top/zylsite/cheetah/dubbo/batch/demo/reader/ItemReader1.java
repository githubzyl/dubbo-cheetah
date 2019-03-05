package top.zylsite.cheetah.dubbo.batch.demo.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Description: TODO</p>
 *
 * @author zhaoyl
 * @version v1.0
 * @date 2019/3/4 19:48
 */
public class ItemReader1 implements ItemReader<String> {

    private Iterator<String> iterator;

    public ItemReader1(List<String> data){
        this.iterator = data.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        //一个数据一个数据的读
        if(iterator.hasNext()){
            return iterator.next();
        }
        return null;
    }

}
