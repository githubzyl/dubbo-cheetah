package com.cheetah.dubbo.api.common.supers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>Description: 根serviceImpl,用来实现一些公用的service方法</p>
 * @author   zhaoyl
 * @date      2018-12-30
 * @version  v1.0
 */
public class SuperServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<BaseMapper<T>, T>
		implements ISuperService<T> {

}
