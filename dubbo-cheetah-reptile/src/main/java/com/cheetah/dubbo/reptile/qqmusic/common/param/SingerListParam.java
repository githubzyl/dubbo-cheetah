package com.cheetah.dubbo.reptile.qqmusic.common.param;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 17:15
 * @Version v1.0
 */
@Data
public class SingerListParam{

    private final static int DEFAULT_PAGE_SIZE = 80;

    private Integer area = -100;
    private Integer sex = -100;
    private Integer genre = -100;
    private Integer index = -100;

    private Integer sin = 0;
    private Integer cur_page = 1;

    public void setCur_page(Integer cur_page){
        this.cur_page = cur_page;
        this.sin = (cur_page - 1) * DEFAULT_PAGE_SIZE;
    }

}
