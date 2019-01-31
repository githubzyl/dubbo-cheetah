package com.cheetah.dubbo.reptile.qqmusic.common.param;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 17:16
 * @Version v1.0
 */
@Data
public class SingerList{

    private String module = "Music.SingerListServer";
    private String method = "get_singer_list";
    private SingerListParam param;

}
