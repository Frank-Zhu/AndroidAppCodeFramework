package com.ecloud.collection.clothes.parameter;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午10:31
 * Description: Http 参数生成工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class HttpParameterHelper {
    /**
     *生成Http请求基本参数
     * @return List<BasicNameValuePair> 参数
     */
    public static List<BasicNameValuePair> makeBaseParam() {
        return new ArrayList<BasicNameValuePair>();
    }
}
