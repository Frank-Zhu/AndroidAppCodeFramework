package com.ecloud.androidcodeframework.codeframework.app;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午10:53
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class AppServerConfig {
    public static final int CODE_SERVER_ERROR = -1;//服务器错误
    public static final int CODE_FAIL = 0;//验证失败
    public static final int CODE_SUCCESS = 1;//验证成功

    public static final int REQUEST_TIMEOUT = 30*1000;//设置请求超时10秒钟
    public static final int DATA_TIMEOUT = 30*1000;  //设置等待数据超时时间10秒钟

    /***************网络错误字符串常量定义**********************/
    public static final String NET_404_STR = "请求链接无效，404错误";
    public static final String NET_500_STR = "服务器端程序出错，500错误";
    public static final String NET_900_STR = "网络传输协议出错，900错误";
    public static final String NET_901_STR = "连接超时，901错误";
    public static final String NET_902_STR = "网络中断，902错误";
    public static final String NET_903_STR = "网络数据流传输出错，903错误，IO异常";
    public static final String NET_UNKNOWN_STR = "未知错误";
    public static final String NET_ERROR_STR = "网络错误";

    public static final String HTTP_ERROR_BASE_CONTENT = "{\"code\":0,\"msg\":\"网络错误\",\"total\":0,\"data\":[]}";

}
