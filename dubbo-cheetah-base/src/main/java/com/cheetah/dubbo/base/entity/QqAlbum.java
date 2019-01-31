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
@TableName("t_qq_album")
public class QqAlbum extends SuperEntity<QqAlbum> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("album_id")
    private Long albumId;

    @TableField("album_mid")
    private String albumMid;

    @TableField("album_name")
    private String albumName;

    @TableField("album_desc")
    private String albumDesc;

    @TableField("album_type")
    private String albumType;

    @TableField("album_lan")
    private String albumLan;

    @TableField("album_pic")
    private String albumPic;

    public void setId(Long id) {
        this.id = id;
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
    public void setAlbumType(String albumType) {
        this.albumType = (albumType == null ? null : albumType.trim());
    }
    public void setAlbumLan(String albumLan) {
        this.albumLan = (albumLan == null ? null : albumLan.trim());
    }
    public void setAlbumPic(String albumPic) {
        this.albumPic = (albumPic == null ? null : albumPic.trim());
    }

    public static final String FIELD_ID = "id";

    public static final String FIELD_ALBUM_ID = "album_id";

    public static final String FIELD_ALBUM_MID = "album_mid";

    public static final String FIELD_ALBUM_NAME = "album_name";

    public static final String FIELD_ALBUM_DESC = "album_desc";

    public static final String FIELD_ALBUM_TYPE = "album_type";

    public static final String FIELD_ALBUM_LAN = "album_lan";

    public static final String FIELD_ALBUM_PIC = "album_pic";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
