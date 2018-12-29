package com.cheetah.dubbo.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheetah.dubbo.api.common.BaseModel;
import com.cheetah.dubbo.api.enums.GenderEnum;
import com.cheetah.dubbo.api.enums.LockStatusEnum;
import com.cheetah.dubbo.api.enums.UserStatusEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Jason
 * @since 2018-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
public class User extends BaseModel<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 登陆密码
     */
    @TableField("password")
    private String password;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;

    /**
     * 用户状态(0：正常，1：禁用，默认正常)
     */
    @TableField("user_status")
    private UserStatusEnum userStatus;

    /**
     * 锁定状态0：非锁定，1：锁定，默认非锁定)
     */
    @TableField("lock_status")
    private LockStatusEnum lockStatus;

    /**
     * 性别(男：'0',女：'1')
     */
    @TableField("gender")
    private GenderEnum gender;

    /**
     * 头像
     */
    @TableField("photo")
    private String photo;

    /**
     * 部门ID
     */
    @TableField("department_id")
    private Integer departmentId;

    /**
     * 是否删除(0:未删除,1:已删除,默认0)
     */
    @TableField("is_del")
    @com.baomidou.mybatisplus.annotation.TableLogic
    private Integer isDel;

    /**
     * 乐观锁版本号
     */
    @TableField("version")
    @com.baomidou.mybatisplus.annotation.Version
    private Integer version;


    public static final String FIELD_ID = "id";

    public static final String FIELD_USER_NAME = "user_name";

    public static final String FIELD_PASSWORD = "password";

    public static final String FIELD_REAL_NAME = "real_name";

    public static final String FIELD_MOBILE = "mobile";

    public static final String FIELD_EMAIL = "email";

    public static final String FIELD_USER_STATUS = "user_status";

    public static final String FIELD_LOCK_STATUS = "lock_status";

    public static final String FIELD_GENDER = "gender";

    public static final String FIELD_PHOTO = "photo";

    public static final String FIELD_DEPARTMENT_ID = "department_id";

    public static final String FIELD_IS_DEL = "is_del";

    public static final String FIELD_VERSION = "version";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
