package com.cheetah.dubbo.common.supers;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description: 用来定义一些表公用的字段</p>
 * @author   zhaoyl
 * @date      2018-12-30
 * @version  v1.0
 */
@Getter
@Setter
public abstract class SuperEntity<T extends Model<?>> extends Model<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 租户ID
	 */
	//@TableField(value = "tenant_id",fill = FieldFill.INSERT)
	//private Integer tenantId;
	
	/**
	 * 创建人
	 */
	//@TableField(value = "creater",fill = FieldFill.INSERT)
	//private Integer creater;
	
	/**
	 * 创建时间
	 */
	//@TableField(value = "create_time",fill = FieldFill.INSERT)
	//private Date createTime;
	
	/**
	 * 最后一次修改人
	 */
	//@TableField(value = "last_modifier",fill = FieldFill.INSERT_UPDATE)
	//private Integer lastModifier;
	
	/**
	 * 最后一次修改时间
	 */
	//@TableField(value = "last_modify_time",fill = FieldFill.INSERT_UPDATE)
	//private Date lastModifyTime;

}
