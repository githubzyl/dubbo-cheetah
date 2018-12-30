package com.cheetah.dubbo.generator;

import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>Description: 自定义freemarker渲染引擎，加入java静态方法，生成枚举属性</p>
 * @author   zhaoyl
 * @date      2018-12-30
 * @version  v1.0
 */
public class CustomFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

	@Override
	public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
	    //处理枚举类型
	    Object obj = objectMap.get("table");
	    if(null != obj) {
	    	TableInfo tableInfo = (TableInfo) obj;
	    	List<TableField> tableFields = tableInfo.getFields();
	    	if(!CollectionUtils.isEmpty(tableFields)) {
	    		DBColumnEnumType type = null;
	    		for(TableField field : tableFields) {
	    			type = DBColumnEnumType.getEnumTypeByColumnName(field.getName());
	    			if(null != type) {
	    				field.setColumnType(type);
	    				tableInfo.setImportPackages(type.getPkg());
	    			}
	    		}
	    	}
	    }
		super.writer(objectMap, templatePath, outputFile);
	}

}
