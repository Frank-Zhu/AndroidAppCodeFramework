package com.ecloud.collection.clothes.gson;

import com.ecloud.collection.clothes.app.AppServerConfig;
import com.ecloud.collection.clothes.entity.ErrorBaseBean;
import com.google.gson.Gson;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午9:46
 * Description: 数据解析类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class GsonParse extends GsonBaseParse {

    private Object bean;

    public GsonParse(String content) throws Exception {
        super(content);
    }

    /**
     * 数据解析函数
     * @param className 返回数据结构类名
     * @throws Exception
     */
    @Override
    protected void parse(Class<?> className) throws Exception {
        ErrorBaseBean mErrorBaseBean;
        mErrorBaseBean = GsonErrorParse.parse(content);
        if(mErrorBaseBean.getCode() == AppServerConfig.CODE_SUCCESS){
            Gson gson = new Gson();
            bean = gson.fromJson(content, className);
        }else{
            bean = mErrorBaseBean;
        }
    }

    /**
     * 获取HTTP返回数据结构
     * @param className 返回数据结构类名
     * @return Object
     * @throws Exception
     */
    public Object getBean(Class<?> className) throws Exception {
        parse(className);
        return bean;
    }
}
