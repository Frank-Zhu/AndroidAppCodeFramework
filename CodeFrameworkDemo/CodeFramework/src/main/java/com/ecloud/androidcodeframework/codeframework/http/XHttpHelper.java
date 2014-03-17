package com.ecloud.androidcodeframework.codeframework.http;

import com.ecloud.androidcodeframework.codeframework.download.DownloadInfo;
import com.ecloud.androidcodeframework.codeframework.download.DownloadManager;
import com.ecloud.androidcodeframework.codeframework.intface.XHttpRequestDefaultCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午2:23
 * Description: XUtils联网请求工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class XHttpHelper {
    private RequestCallBack<File> downloadCallback;
    private DownloadInfo downloadInfo;
    private DownloadManager downloadManager;
    private RequestParams params;
    private XHttpRequestDefaultCallBack callBack;
    private String url;

    /**
     *Get / Post 请求数据构造函数
     * @param url url地址
     * @param params 请求参数
     * @param isGetRequest 是否Get请求
     * @param callBack 请求回调函数
     */
    public XHttpHelper(String url, RequestParams params, boolean isGetRequest, XHttpRequestDefaultCallBack callBack){
        this.url = url;
        this.params = params;
        if(callBack == null){
            this.callBack = new XHttpRequestDefaultCallBack();
        }else{
            this.callBack = callBack;
        }

        getHttpRequest(isGetRequest);
    }

    /**
     * 下载请求构造函数
     * @param downloadManager 下载管理类
     * @param downloadInfo 下载的文件数据结构
     * @param downloadCallback 下载回调函数
     */
    public XHttpHelper(DownloadManager downloadManager, DownloadInfo downloadInfo,RequestCallBack<File> downloadCallback){
        this.downloadManager = downloadManager;
        this.downloadInfo = downloadInfo;
        this.downloadCallback = downloadCallback;
    }

    /**
     * Get / Post 请求数据
     * @param isGetRequest 是否Get请求
     */
    public void getHttpRequest(boolean isGetRequest){
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        if(params != null){
            if(isGetRequest){
                HttpHandler<String> send = http.send(HttpRequest.HttpMethod.GET, url, params, callBack);
            }else{
                HttpHandler<String> send = http.send(HttpRequest.HttpMethod.POST, url, params, callBack);
            }
        }else{
            if(isGetRequest){
                HttpHandler<String> send = http.send(HttpRequest.HttpMethod.GET, url, callBack);
            }else{
                HttpHandler<String> send = http.send(HttpRequest.HttpMethod.POST, url, callBack);
            }
        }
    }

    /**
     * 下载文件
     */
    public void downloadHttpRequest(){
        if(downloadInfo != null && downloadManager != null){
            try {
                downloadManager.addNewDownload(downloadInfo.getDownloadUrl(),
                        downloadInfo.getFileName(),
                        downloadInfo.getFileSavePath(),
                        downloadInfo.isAutoResume(), // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
                        downloadInfo.isAutoRename(), // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
                        downloadCallback);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }
}
