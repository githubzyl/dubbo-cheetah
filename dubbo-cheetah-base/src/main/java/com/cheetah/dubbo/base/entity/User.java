package com.cheetah.dubbo.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cheetah.dubbo.base.enums.GenderEnum;
import com.cheetah.dubbo.base.enums.LockStatusEnum;
import com.cheetah.dubbo.base.enums.UserStatusEnum;
import com.cheetah.dubbo.common.supers.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Jason
 * @since 2019-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
public class User extends SuperEntity<User> {

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = (userName == null ? null : userName.trim());
    }

    public void setPassword(String password) {
        this.password = (password == null ? null : password.trim());
    }

    public void setRealName(String realName) {
        this.realName = (realName == null ? null : realName.trim());
    }

    public void setMobile(String mobile) {
        this.mobile = (mobile == null ? null : mobile.trim());
    }

    public void setEmail(String email) {
        this.email = (email == null ? null : email.trim());
    }

    public void setUserStatus(UserStatusEnum userStatus) {
        this.userStatus = userStatus;
    }

    public void setLockStatus(LockStatusEnum lockStatus) {
        this.lockStatus = lockStatus;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public void setPhoto(String photo) {
        this.photo = (photo == null ? null : photo.trim());
    }

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
