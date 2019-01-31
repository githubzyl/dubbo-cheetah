package com.cheetah.dubbo.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheetah.dubbo.common.supers.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jason
 * @since 2019-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_qq_singer_song")
public class QqSingerSong extends SuperEntity<QqSingerSong> {

    private static final long serialVersionUID = 1L;

    @TableField("singer_id")
    private Long singerId;

    @TableField("song_id")
    private Long songId;

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }
    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public static final String FIELD_SINGER_ID = "singer_id";

    public static final String FIELD_SONG_ID = "song_id";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
