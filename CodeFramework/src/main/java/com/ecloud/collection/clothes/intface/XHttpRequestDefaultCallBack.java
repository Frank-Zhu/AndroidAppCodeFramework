package com.ecloud.collection.clothes.intface;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午2:31
 * Description: XHttp网络请求默认回调函数适配器
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class XHttpRequestDefaultCallBack extends RequestCallBack<String> {
    @Override
    public void onLoading(long total, long current, boolean isUploading) {
        super.onLoading(total, current, isUploading);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStopped() {
        super.onStopped();
    }

    @Override
    public void onSuccess(ResponseInfo<String> stringResponseInfo) {

    }

    @Override
    public void onFailure(HttpException e, String s) {

    }
}
