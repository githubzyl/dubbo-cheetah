package com.cheetah.dubbo.generator;

import java.util.Map;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

/**
 * <p>Description: 自定义freemarker渲染引擎，加入java静态方法，生成枚举属性</p>
 * @author   zhaoyl
 * @date      2018-12-30
 * @version  v1.0
 */
public class CustomFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

	@Override
	public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
		BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build();
		TemplateHashModel staticModels = wrapper.getStaticModels();  
		TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get("com.cheetah.dubbo.generator.ColumnEnumUtils");
	    objectMap.put("ColumnEnumUtils", fileStatics);
		super.writer(objectMap, templatePath, outputFile);
	}

}
