package com.ecloud.androidcodeframework.codeframework.thread;

import android.os.Handler;

import com.ecloud.androidcodeframework.codeframework.gson.GsonParse;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午7:42
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class GetDataRequestThread extends BaseThread {

    private Class<?> className;

    public GetDataRequestThread(Handler handler, int what, String url, List<BasicNameValuePair> param, Class<?> className,boolean isPost) {
        super(handler, what);
        this.className = className;
        setParams(url,param,isPost);
    }

    @Override
    public void run() {
        super.run();
        try {
            Object bean = new GsonParse(result).getBean(className);
            sendMessageToHandler(bean);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
