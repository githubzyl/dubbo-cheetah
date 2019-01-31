package com.cheetah.dubbo.reptile.qqmusic.common.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 16:56
 * @Version v1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryAlbumParam extends QueryParam {

    private SingerAlbum singerAlbum;

}
