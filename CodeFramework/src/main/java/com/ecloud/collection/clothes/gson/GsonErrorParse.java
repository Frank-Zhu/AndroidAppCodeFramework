package com.ecloud.collection.clothes.gson;

import com.ecloud.collection.clothes.app.AppServerConfig;
import com.ecloud.collection.clothes.entity.ErrorBaseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午9:50
 * Description: 数据请求失败Or成功解析类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class GsonErrorParse {
    public static ErrorBaseBean mErrorBaseBean;

    /**
     * 解析HTTP返回数据是否成功
     * @param content 返回数据字符串
     * @return ErrorBaseBean
     */
    public static ErrorBaseBean parse(String content){
        mErrorBaseBean = new ErrorBaseBean();
        JSONObject json;
        try {
            json = new JSONObject(content);
            if(json.has("code")){
                mErrorBaseBean.setCode(json.getInt("status"));
            }else{
                mErrorBaseBean.setCode(AppServerConfig.CODE_SERVER_ERROR);
            }
            if(json.has("msg")){
                mErrorBaseBean.setMsg(json.getString("msg"));
            }else{
                mErrorBaseBean.setMsg(AppServerConfig.NET_ERROR_STR);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mErrorBaseBean;
    }
}
