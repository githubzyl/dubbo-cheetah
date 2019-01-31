package com.cheetah.dubbo.reptile.qqmusic.common;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 16:45
 * @Version v1.0
 */
public class URLConstant {

    //获取歌手
    public final static String GET_SINGER = "https://u.y.qq.com/cgi-bin/musicu.fcg?-=getUCGI46973428388280203&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=";

    //获取歌曲
    public final static String GET_SONG = "https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&ct=24&songstatus=1&order=listen";

    //获取专辑
    public final static String GET_ALBUM = "https://u.y.qq.com/cgi-bin/musicu.fcg?-=getUCGI8116480554737562&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=";

    //获取歌手简介，返回xml格式
    public final static String GET_SINGER_DESC = "https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_singer_desc.fcg?utf8=1&outCharset=utf-8&format=xml&singermid=";

    //获取歌曲下载vkey的链接地址
    public final static String GET_VKEY = "https://u.y.qq.com/cgi-bin/musicu.fcg?-=getplaysongvkey3355628850003496&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=";

    //下载地址
    public final static String DOWNLOAD_SONG = "http://dl.stream.qqmusic.qq.com/";

}
