package com.cheetah.dubbo.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.api.common.DubboConstants;
import com.cheetah.dubbo.api.common.InterfaceVersion;
import com.cheetah.dubbo.api.service.*;
import com.cheetah.dubbo.base.entity.*;
import com.cheetah.dubbo.base.entity.vo.QQSongVO;
import com.cheetah.dubbo.base.mapper.QqSongMapper;
import com.cheetah.dubbo.common.supers.SuperServiceImpl;
import com.cheetah.dubbo.common.utils.ReflectionUtilEX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2019-01-30
 */
@Service(version = InterfaceVersion.VERSION_1_0,protocol= {DubboConstants.PROTOCOL_DUBBO})
@org.springframework.stereotype.Service
public class QqSongServiceImpl extends SuperServiceImpl<QqSongMapper, QqSong> implements IQqSongService {

}
