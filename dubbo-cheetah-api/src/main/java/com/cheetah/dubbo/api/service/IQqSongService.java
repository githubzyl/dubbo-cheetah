package com.cheetah.dubbo.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.common.supers.ISuperService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jason
 * @since 2019-01-30
 */
public interface IQqSongService extends ISuperService<QqSong> {

    IPage<QqSong> queryPage(Integer pageNum, Integer pageSize, Map<String,Object> params);

}
