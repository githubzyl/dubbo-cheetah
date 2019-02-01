package com.cheetah.dubbo.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.cheetah.dubbo.common.supers.SuperEntity;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jason
 * @since 2019-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_mongo_file")
public class MongoFile extends SuperEntity<MongoFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("main_id")
    private Long mainId;

    @TableField("file_id")
    private String fileId;

    public void setId(Long id) {
        this.id = id;
    }
    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }
    public void setFileId(String fileId) {
        this.fileId = (fileId == null ? null : fileId.trim());
    }

    public static final String FIELD_ID = "id";

    public static final String FIELD_MAIN_ID = "main_id";

    public static final String FIELD_FILE_ID = "file_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
