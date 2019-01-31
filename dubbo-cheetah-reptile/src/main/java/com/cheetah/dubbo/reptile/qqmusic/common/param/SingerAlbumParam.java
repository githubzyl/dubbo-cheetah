package com.cheetah.dubbo.reptile.qqmusic.common.param;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/30 19:17
 * @Version v1.0
 */
@Data
public class SingerAlbumParam {

    private final static int DEFAULT_PAGE_SIZE = 30;

    private String singermid;
    private Integer begin = 0;
    private Integer num = DEFAULT_PAGE_SIZE;

    private String order = "time";
    private Integer exstatus = 1;

    private Integer cur_page = 1;

    public void setNum(Integer num){
        this.num = (num > DEFAULT_PAGE_SIZE) ? num : DEFAULT_PAGE_SIZE;
    }

    public void setCur_page(Integer cur_page){
        this.begin = (cur_page -1 ) * num;
    }

}
