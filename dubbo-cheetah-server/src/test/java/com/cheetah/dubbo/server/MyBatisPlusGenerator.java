package com.cheetah.dubbo.server;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class MyBatisPlusGenerator {

	public final static String BASE_DIR =  "E:/workspace/web/WorkSpace/dubbo-cheetah";
	
	@Test
	public void genCode() throws SQLException {
		// 1. 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setActiveRecord(true) // 是否支持AR模式
				.setAuthor("Jason") // 作者
				.setOutputDir(BASE_DIR+"/dubbo-cheetah-server/src/main/java") // 生成路径
				.setFileOverride(true) // 文件覆盖
				.setIdType(IdType.AUTO) // 主键策略
				.setServiceName("I%sService") // 设置生成的service接口的名字的首字母是否为I
				.setBaseResultMap(true)// 生成基本的resultMap
				.setOpen(false)//不打开输出目录
				.setDateType(DateType.ONLY_DATE)
				.setBaseColumnList(true);// 生成基本的SQL片段

		// 2. 数据源配置
		DataSourceConfig dsConfig = new DataSourceConfig();
		dsConfig.setDbType(DbType.MYSQL) // 设置数据库类型
				.setDriverName("com.mysql.jdbc.Driver")
				.setUrl("jdbc:mysql://47.98.113.109:8732/cheetah_new")
				.setUsername("root")
				.setPassword("123456");

		// 3. 策略配置globalConfiguration中
		StrategyConfig stConfig = new StrategyConfig();
		stConfig.setCapitalMode(true) // 全局大写命名
				.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
				.setTablePrefix("t_")
                .entityTableFieldAnnotationEnable(true)
                .setSuperEntityClass("com.cheetah.dubbo.api.common.BaseModel")
                .setSuperControllerClass("com.cheetah.dubbo.api.common.BaseController")
                .setVersionFieldName("version")
                .setLogicDeleteFieldName("is_del")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityColumnConstant(true)
//                .setExclude("DATABASECHANGELOG","DATABASECHANGELOGLOCK")
                .setSuperEntityColumns("tenant_id","creater","create_time","last_modifier","last_modify_time");
                
                
		// 4. 包名策略配置
		Map<String,String> pathInfo = new HashMap<>(4);
		pathInfo.put(ConstVal.ENTITY_PATH, BASE_DIR + "/dubbo-cheetah-api/src/main/java/com/cheetah/dubbo/api/entity");
		pathInfo.put(ConstVal.SERVICE_PATH, BASE_DIR + "/dubbo-cheetah-api/src/main/java/com/cheetah/dubbo/api/service");
		
		pathInfo.put(ConstVal.MAPPER_PATH, BASE_DIR +"/dubbo-cheetah-server/src/main/java/com/cheetah/dubbo/server/mapper");
		pathInfo.put(ConstVal.SERVICE_IMPL_PATH, BASE_DIR +"/dubbo-cheetah-server/src/main/java/com/cheetah/dubbo/server/service/impl");
		
		//pathInfo.put(ConstVal.CONTROLLER_PATH, BASE_DIR +"/dubbo-cheetah-client/src/main/java/com/cheetah/dubbo/client/controller");
		
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setPathInfo(pathInfo);
		pkConfig.setParent("com.cheetah.dubbo")
		        .setMapper("server.mapper")// dao
				.setService("api.service")// servcie
				.setServiceImpl("server.service.impl")
				.setEntity("api.entity");
//		       .setController("client.controller")// controller
//				.setXml("mapper.xml");// mapper.xml
		

		// 5. 整合配置
		AutoGenerator ag = new AutoGenerator();
		ag.setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig).setPackageInfo(pkConfig);

		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);
//		templateConfig.setController(null);
		templateConfig.setEntity("/templates/custom-entity.java");
		templateConfig.setServiceImpl("/templates/custom-serviceImpl.java");
		ag.setTemplate(templateConfig);
		
		// 6. 执行
		ag.setTemplateEngine(new FreemarkerTemplateEngine());//使用freemarker生成
		ag.execute();
	}

}
