package com.cheetah.dubbo.api.common;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseModel<T extends Model<?>> extends Model<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 租户ID
	 */
	@TableField(value = "tenant_id",fill = FieldFill.INSERT)
	private Integer tenantId;
	
	/**
	 * 创建人
	 */
	private Integer creater;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最后一次修改人
	 */
	private Integer lastModifier;
	
	/**
	 * 最后一次修改时间
	 */
	@TableField(value = "last_modify_time",fill = FieldFill.UPDATE)
	private Date lastModifyTime;

}
