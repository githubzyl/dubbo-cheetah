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
@TableName("t_qq_album_song")
public class QqAlbumSong extends SuperEntity<QqAlbumSong> {

    private static final long serialVersionUID = 1L;

    @TableField("album_id")
    private Long albumId;

    @TableField("song_id")
    private Long songId;

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public static final String FIELD_ALBUM_ID = "album_id";

    public static final String FIELD_SONG_ID = "song_id";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
