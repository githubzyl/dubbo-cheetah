package com.cheetah.dubbo.base;

import com.cheetah.dubbo.base.entity.QqSong;
import com.cheetah.dubbo.base.entity.vo.QQSongVO;
import com.cheetah.dubbo.common.utils.ReflectionUtilEX;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/30 13:45
 * @Version v1.0
 */
public class Test {

    @org.junit.Test
    public void test1(){
        QQSongVO songVO = new QQSongVO();
        songVO.setSongId(1112L);
        QqSong song = new QqSong();
        ReflectionUtilEX.copyProperities(songVO,song);
        System.out.println(song.getSongId());
    }

}
