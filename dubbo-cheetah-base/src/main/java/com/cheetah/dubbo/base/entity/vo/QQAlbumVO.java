package com.cheetah.dubbo.base.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/30 19:08
 * @Version v1.0
 */
@Data
public class QQAlbumVO implements Serializable {

    private static final long serialVersionUID = 6193215431516058054L;

    private Long albumId;

    private String albumMid;

    private String albumName;

    private String albumDesc;

    private String albumType;

    private String albumLan;

    private String albumPic;

    @JSONField(name="albumid")
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    @JSONField(name="album_mid")
    public void setAlbumMid(String albumMid) {
        this.albumMid = albumMid;
    }

    @JSONField(name="album_name")
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @JSONField(name="desc")
    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    @JSONField(name="albumtype")
    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    @JSONField(name="lan")
    public void setAlbumLan(String albumLan) {
        this.albumLan = albumLan;
    }

    public void setAlbumPic(String albumPic) {
        if(null == albumPic){
            albumPic = "https://y.gtimg.cn/music/photo_new/T002R300x300M000" + this.albumMid + ".jpg";
        }
        this.albumPic = albumPic;
    }

}
