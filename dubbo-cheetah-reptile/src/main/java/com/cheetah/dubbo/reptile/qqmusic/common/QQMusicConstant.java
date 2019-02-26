package com.cheetah.dubbo.reptile.qqmusic.common;

/**
 * <p>Description: </p>
 *
 * @Author zhaoyl
 * @Date 2019/1/29 16:45
 * @Version v1.0
 */
public class QQMusicConstant {

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

    //自动补全搜索框搜索url
    public final static String SEARCH_BOX_URL = "https://c.y.qq.com/splcloud/fcgi-bin/smartbox_new.fcg?is_xml=0&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&key=";

    //搜索单曲的URL
    public final static String SEARCH_SONG_URL = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&remoteplace=txt.yqq.center&searchid=52081129967631295&aggr=1&catZhida=1&lossless=0&flag_qc=0&n=20&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&new_json=1&t=0&cr=1";

    //搜索专辑的URL
    public final static String SEARCH_ALBUM_URL = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&remoteplace=txt.yqq.album&searchid=78703974378347629&aggr=0&catZhida=1&lossless=0&n=30&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&sem=10&t=8";

    //搜索mv的url
    public final static String SEARCH_MV_URL = "https://c.y.qq.com/soso/fcgi-bin/client_search_cp?ct=24&qqmusic_ver=1298&remoteplace=txt.yqq.mv&searchid=132228677159936813&aggr=0&catZhida=1&lossless=0&n=28&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&sem=1&t=12";


    public final static int SEARCH_TYPE_SONG = 1;//单曲
    public final static int SEARCH_TYPE_SINGER = 2;//歌手
    public final static int SEARCH_TYPE_ALBUM = 3;//专辑
    public final static int SEARCH_TYPE_MV = 4;//MV

}
