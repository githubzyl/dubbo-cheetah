package com.cheetah.dubbo.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

public class ColumnTypeConvert extends MySqlTypeConvert {

	@Override
	public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
		return super.processTypeConvert(globalConfig, fieldType);
	}

}
