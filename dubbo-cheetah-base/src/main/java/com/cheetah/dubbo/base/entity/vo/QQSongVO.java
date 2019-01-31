package com.cheetah.dubbo.base.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/30 10:25
 * @Version v1.0
 */
@lombok.NoArgsConstructor
@Data
public class QQSongVO implements Serializable {

    private static final long serialVersionUID = 4674054306689952765L;

    private Long albumId;

    private String albumMid;

    private String albumName;

    private String albumDesc;

    private Long songId;

    private String songMid;

    private String songName;

    private String songOrig;

    private String strMediaMid;

    private Integer duration;

    @JSONField(name="albumid")
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    @JSONField(name="albummid")
    public void setAlbumMid(String albumMid) {
        this.albumMid = albumMid;
    }

    @JSONField(name="albumname")
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @JSONField(name="albumdesc")
    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    @JSONField(name="songid")
    public void setSongId(Long songId) {
        this.songId = songId;
    }

    @JSONField(name="songmid")
    public void setSongMid(String songMid) {
        this.songMid = songMid;
    }

    @JSONField(name="songname")
    public void setSongName(String songName) {
        this.songName = songName;
    }

    @JSONField(name="songorig")
    public void setSongOrig(String songOrig) {
        this.songOrig = songOrig;
    }

    @JSONField(name="strMediaMid")
    public void setStrMediaMid(String strMediaMid) {
        this.strMediaMid = strMediaMid;
    }

    @JSONField(name="interval")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
