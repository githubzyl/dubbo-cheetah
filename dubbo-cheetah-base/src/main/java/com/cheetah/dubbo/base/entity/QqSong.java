package com.cheetah.dubbo.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("t_qq_song")
public class QqSong extends SuperEntity<QqSong> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("song_id")
    private Long songId;

    @TableField("song_mid")
    private String songMid;

    @TableField("song_name")
    private String songName;

    @TableField("song_orig")
    private String songOrig;

    @TableField("str_media_mid")
    private String strMediaMid;

    /**
     * 时长，单位：秒
     */
    @TableField("duration")
    private Integer duration;

    @TableField("album_id")
    private Long albumId;

    @TableField("album_mid")
    private String albumMid;

    @TableField("album_name")
    private String albumName;

    @TableField("album_desc")
    private String albumDesc;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public void setSongMid(String songMid) {
        this.songMid = (songMid == null ? null : songMid.trim());
    }

    public void setSongName(String songName) {
        this.songName = (songName == null ? null : songName.trim());
    }

    public void setSongOrig(String songOrig) {
        this.songOrig = (songOrig == null ? null : songOrig.trim());
    }

    public void setStrMediaMid(String strMediaMid) {
        this.strMediaMid = (strMediaMid == null ? null : strMediaMid.trim());
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public void setAlbumMid(String albumMid) {
        this.albumMid = (albumMid == null ? null : albumMid.trim());
    }

    public void setAlbumName(String albumName) {
        this.albumName = (albumName == null ? null : albumName.trim());
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = (albumDesc == null ? null : albumDesc.trim());
    }

    public static final String FIELD_ID = "id";

    public static final String FIELD_SONG_ID = "song_id";

    public static final String FIELD_SONG_MID = "song_mid";

    public static final String FIELD_SONG_NAME = "song_name";

    public static final String FIELD_SONG_ORIG = "song_orig";

    public static final String FIELD_STR_MEDIA_MID = "str_media_mid";

    public static final String FIELD_DURATION = "duration";

    public static final String FIELD_ALBUM_ID = "album_id";

    public static final String FIELD_ALBUM_MID = "album_mid";

    public static final String FIELD_ALBUM_NAME = "album_name";

    public static final String FIELD_ALBUM_DESC = "album_desc";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
