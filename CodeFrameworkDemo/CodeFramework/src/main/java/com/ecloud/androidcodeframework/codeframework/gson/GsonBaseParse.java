package com.ecloud.androidcodeframework.codeframework.gson;

import com.ecloud.androidcodeframework.codeframework.app.AppServerConfig;
import com.ecloud.androidcodeframework.codeframework.utils.LogHelper;
import com.ecloud.androidcodeframework.codeframework.utils.StringHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午9:40
 * Description: 数据解析基类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class GsonBaseParse {
    protected final String TAG = getClass().getSimpleName();
    protected String content;

    public GsonBaseParse(String content){
        this.content = content;
        LogHelper.i(TAG,"content--->" + content);
        if (StringHelper.isEmptyString(content)) {
            this.content = AppServerConfig.HTTP_ERROR_BASE_CONTENT;
        }
    }

    /**
     * 虚拟解析方法
     * @param className 返回数据结构类名
     * @throws Exception 异常信息
     */
    protected abstract void parse(Class<?> className) throws Exception;
}
