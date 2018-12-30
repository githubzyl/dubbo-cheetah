package com.cheetah.dubbo.generator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

	public final static String BASE_DIR =  "E:/workspace/web/WorkSpace/dubbo-cheetah";
	
	public static String ENTITY_PATH = BASE_DIR + "/dubbo-cheetah-api/src/main/java/com/cheetah/dubbo/api/entity";
	public static String SERVICE_PATH = BASE_DIR + "/dubbo-cheetah-api/src/main/java/com/cheetah/dubbo/api/service";
	
	public static String MAPPER_PATH = BASE_DIR +"/dubbo-cheetah-server/src/main/java/com/cheetah/dubbo/server/mapper";
	public static String MAPPER_XML_PATH = BASE_DIR + "/dubbo-cheetah-server/src/main/resources/mybatis/mapper";
	public static String SERVICE_IMPL_PATH = BASE_DIR +"/dubbo-cheetah-server/src/main/java/com/cheetah/dubbo/server/service/impl";
	
	public static String CONTROLLER_PATH = BASE_DIR +"/dubbo-cheetah-client/src/main/java/com/cheetah/dubbo/client/controller";
	
	public static String[] superEntityColumns = new String[]{"tenant_id","creater","create_time","last_modifier","last_modify_time"};
	
	public static String[] tables = new String[]{};
	
	@Test
	public void genCode() throws SQLException {
		// 1. 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setActiveRecord(true) // 是否支持AR模式
				.setAuthor("Jason") // 作者
				//.setOutputDir(BASE_DIR+"/dubbo-cheetah-server/src/main/java") // 生成路径
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
				.setPassword("123456")
				.setTypeConvert(new ColumnTypeConvert());

		// 3. 策略配置globalConfiguration中
		StrategyConfig stConfig = new StrategyConfig();
		stConfig.setCapitalMode(true) // 全局大写命名
				.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
				.setTablePrefix("t_")
                .entityTableFieldAnnotationEnable(true)
                .setSuperEntityClass("com.cheetah.dubbo.api.common.supers.SuperEntity")
                .setSuperMapperClass("com.cheetah.dubbo.api.common.supers.SuperMapper")
                .setSuperServiceClass("com.cheetah.dubbo.api.common.supers.ISuperService")
                .setSuperServiceImplClass("com.cheetah.dubbo.api.common.supers.SuperServiceImpl")
                .setSuperControllerClass("com.cheetah.dubbo.api.common.BaseController")
                .setVersionFieldName("version")
                .setLogicDeleteFieldName("is_del")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityColumnConstant(true)
//                .setExclude("DATABASECHANGELOG","DATABASECHANGELOGLOCK")
                .setSuperEntityColumns(superEntityColumns);
                
                
		// 4. 包名策略配置
		Map<String,String> pathInfo = new HashMap<>(4);
		pathInfo.put(ConstVal.ENTITY_PATH, ENTITY_PATH);
		pathInfo.put(ConstVal.SERVICE_PATH, SERVICE_PATH);
		
		pathInfo.put(ConstVal.MAPPER_PATH, MAPPER_PATH);
		pathInfo.put(ConstVal.XML_PATH, MAPPER_XML_PATH);
		pathInfo.put(ConstVal.SERVICE_IMPL_PATH, SERVICE_IMPL_PATH);
		
		//pathInfo.put(ConstVal.CONTROLLER_PATH, CONTROLLER_PATH);
		
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setPathInfo(pathInfo);
		pkConfig.setParent("com.cheetah.dubbo")
		        .setMapper("server.mapper")// dao
				.setService("api.service")// servcie
				.setServiceImpl("server.service.impl")
				.setEntity("api.entity")
		        .setXml("mapper");// mapper.xml
//		       .setController("client.controller")// controller

		

		// 5. 整合配置
		CustomGenerator ag = new CustomGenerator();
		ag.setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig).setPackageInfo(pkConfig);

		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setXml(null);
//		templateConfig.setController(null);
		templateConfig.setEntity("/templates/custom-entity.java");
		templateConfig.setMapper("/templates/custom-mapper.java");
		templateConfig.setXml("/templates/custom-mapper.xml");
		templateConfig.setService("/templates/custom-service.java");
		templateConfig.setServiceImpl("/templates/custom-serviceImpl.java");
		templateConfig.setController("/templates/custom-controller.java");
		ag.setTemplate(templateConfig);
		
		InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
               
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/custom-mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return "/mapper/" + tableInfo.getEntityName() + ".xml";
			}
        });
        cfg.setFileOutConfigList(focList);
		
        ag.setCfg(cfg);
		// 6. 执行
		ag.setTemplateEngine(new CustomFreemarkerTemplateEngine());//使用freemarker生成
		ag.execute();
	}

}
