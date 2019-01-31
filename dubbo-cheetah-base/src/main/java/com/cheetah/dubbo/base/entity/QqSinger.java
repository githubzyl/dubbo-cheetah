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
@TableName("t_qq_singer")
public class QqSinger extends SuperEntity<QqSinger> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("singer_id")
    private Long singerId;

    @TableField("singer_mid")
    private String singerMid;

    @TableField("singer_name")
    private String singerName;

    @TableField("singer_pic")
    private String singerPic;

    @TableField("country")
    private String country;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public void setSingerMid(String singerMid) {
        this.singerMid = (singerMid == null ? null : singerMid.trim());
    }

    public void setSingerName(String singerName) {
        this.singerName = (singerName == null ? null : singerName.trim());
    }

    public void setSingerPic(String singerPic) {
        this.singerPic = (singerPic == null ? null : singerPic.trim());
    }

    public void setCountry(String country) {
        this.country = (country == null ? null : country.trim());
    }

    public static final String FIELD_ID = "id";

    public static final String FIELD_SINGER_ID = "singer_id";

    public static final String FIELD_SINGER_MID = "singer_mid";

    public static final String FIELD_SINGER_NAME = "singer_name";

    public static final String FIELD_SINGER_PIC = "singer_pic";

    public static final String FIELD_COUNTRY = "country";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
