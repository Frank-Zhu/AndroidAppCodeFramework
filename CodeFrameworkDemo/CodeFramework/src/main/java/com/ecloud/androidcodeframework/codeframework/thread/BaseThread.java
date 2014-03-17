package com.ecloud.androidcodeframework.codeframework.thread;

import android.os.Handler;
import android.os.Message;

import com.ecloud.androidcodeframework.codeframework.http.HttpHelper;
import com.ecloud.androidcodeframework.codeframework.utils.LogHelper;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午11:26
 * Description: HTTP请求线程基类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseThread extends Thread {
    protected final String TAG = getClass().getSimpleName();
    protected Handler handler;
    protected Message msg = new Message();
    protected int what;

    protected String result = null;

    private String url;
    private List<BasicNameValuePair> params;
    private MultipartEntity mpEntity;

    private boolean isPost;
    private boolean isUploadFile;

    public BaseThread(Handler handler, int what) {
        this.handler = handler;
        this.what = what;
    }

    /**
     * 设置HTTP请求参数
     * @param url url地址
     * @param mpEntity 文件参数
     */
    public void setParams(String url, MultipartEntity mpEntity) {
        this.url = url;
        this.isUploadFile = true;
        this.mpEntity = mpEntity;
        start();
    }

    /**
     * 设置HTTP请求参数
     * @param url url地址
     * @param params 上传参数
     * @param isPost 是否Post请求
     */
    public void setParams(String url, List<BasicNameValuePair> params, boolean isPost) {
        this.url = url;
        this.params = params;
        this.isPost = isPost;
        start();
    }

    @Override
    public void run() {
        super.run();
        try {
            LogHelper.d(TAG,"isUploadFile = " + isUploadFile);
            LogHelper.d(TAG,"isPost = " + isPost);
            if(isUploadFile){
                result = HttpHelper.onHttpPostFile(url,mpEntity);
            }else if(isPost){
                result = HttpHelper.onHttpPost(url,params);
            }else{
                result = HttpHelper.onHttpGet(url, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回数据给handler
     * @param obj 返回数据结构
     */
    public void sendMessageToHandler(Object obj){
        msg.what = what;
        msg.obj = obj;
        handler.sendMessage(msg);
    }
}
