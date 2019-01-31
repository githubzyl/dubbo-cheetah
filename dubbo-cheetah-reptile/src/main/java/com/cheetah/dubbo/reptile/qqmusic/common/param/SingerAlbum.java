package com.cheetah.dubbo.reptile.qqmusic.common.param;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/30 19:16
 * @Version v1.0
 */
@Data
public class SingerAlbum {

    private String module = "music.web_singer_info_svr";
    private String method = "get_singer_album";
    private SingerAlbumParam param;

}
