package com.ecloud.collection.clothes.http;

import com.ecloud.collection.clothes.app.AppServerConfig;
import com.ecloud.collection.clothes.app.AppUrlConfig;
import com.ecloud.collection.clothes.utils.LogHelper;
import com.ecloud.collection.clothes.utils.StringHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午8:30
 * Description: 网络请求工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class HttpHelper {
    protected final static String TAG = HttpHelper.class.getSimpleName();

    /**
     * 添加请求超时时间和等待时间
     * @return HttpClient
     */
    public static HttpClient getHttpClient(){
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, AppServerConfig.REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, AppServerConfig.DATA_TIMEOUT);
        return new DefaultHttpClient(httpParams);
    }

    /**
     * 获取HTTP上传参数字符串
     *
     * @param param          上传参数list
     * @param isCheckChinese 是否检查中文字符
     * @return String
     */
    public static String getParamContent(List<BasicNameValuePair> param, boolean isCheckChinese) {
        LogHelper.d(TAG, "--> getParamContent");
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (BasicNameValuePair pair : param) {
            String key = pair.getName();
            String value = pair.getValue();
            if (StringHelper.isEmptyString(value)) {
                value = "null";
            } else if (isCheckChinese) {
                if (StringHelper.hasChinese(value)) {
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (isFirst) {
                sb.append(key).append("=").append(value);
                isFirst = false;
            } else {
                sb.append("&").append(key).append("=").append(value);
            }
        }
        return sb.toString();
    }

    /**
     * HTTP Post 请求网络数据
     * @param inter url地址
     * @param param 上传参数
     * @return String
     */
    public static String onHttpPost(String inter, List<BasicNameValuePair> param) {
        String path = getParamContent(param, true);
        String httpUrl = AppUrlConfig.HttpBaseUrl + inter;
        LogHelper.d(TAG, "httpUrl-------" + httpUrl + "?path = " + path);

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(httpUrl);

        String resultStr = null;
        try {
			/* 添加请求参数到请求对象 */
            post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));

            HttpResponse response = httpClient.execute(post);
            int state = response.getStatusLine().getStatusCode();
            if (state == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    resultStr = EntityUtils.toString(entity);
                    LogHelper.d(TAG, resultStr);
                }
            } else if (state == 404) {
                LogHelper.i(TAG, AppServerConfig.NET_404_STR);
            } else if (state == 500) {
                LogHelper.i(TAG, AppServerConfig.NET_500_STR);
            }
        } catch (ClientProtocolException e) {
            LogHelper.d(TAG, AppServerConfig.NET_900_STR);
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            LogHelper.d(TAG, AppServerConfig.NET_901_STR);
            e.printStackTrace();
        } catch (InterruptedIOException e) {
            LogHelper.d(TAG, AppServerConfig.NET_902_STR);
            e.printStackTrace();
        } catch (IOException e) {
            LogHelper.d(TAG, AppServerConfig.NET_903_STR);
            e.printStackTrace();
        } catch (Exception e) {
            LogHelper.i(TAG, AppServerConfig.NET_UNKNOWN_STR);
            e.printStackTrace();
        }
        return resultStr;
    }

    /**
     * HTTP Get 请求网络数据
     * @param inter url地址
     * @param param 参数
     * @return String
     */
    public static String onHttpGet(String inter, List<BasicNameValuePair> param) {
        String path = getParamContent(param, true);
        String url = AppUrlConfig.HttpBaseUrl + inter;
        if(StringHelper.notEmptyString(path)){
            url += "?" + path;
        }
        LogHelper.d(TAG, "url-------" + url);

        HttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);

        String resultStr = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            int state = response.getStatusLine().getStatusCode();
            if (state == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    resultStr = EntityUtils.toString(entity);
                    LogHelper.i(TAG, resultStr);
                }
            } else if (state == 404) {
                LogHelper.i(TAG, AppServerConfig.NET_404_STR);
            } else if (state == 500) {
                LogHelper.i(TAG, AppServerConfig.NET_500_STR);
            }
        } catch (ClientProtocolException e) {
            LogHelper.i(TAG, AppServerConfig.NET_900_STR);
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            LogHelper.i(TAG, AppServerConfig.NET_901_STR);
            e.printStackTrace();
        } catch (InterruptedIOException e) {
            LogHelper.i(TAG, AppServerConfig.NET_902_STR);
            e.printStackTrace();
        } catch (IOException e) {
            LogHelper.i(TAG, AppServerConfig.NET_903_STR);
            e.printStackTrace();
        } catch (Exception e) {
            LogHelper.i(TAG, AppServerConfig.NET_UNKNOWN_STR);
            e.printStackTrace();
        }
        return resultStr;
    }

    /**
     * Http Post 上传文件
     * @param inter url地址
     * @param mpEntity 文件参数
     * @return String
     */
    public static String onHttpPostFile(String inter, MultipartEntity mpEntity) {
        String resultStr = null;
        try {
            String sUri = AppUrlConfig.HttpBaseUrl + inter;
            LogHelper.i(TAG,"url-------" + sUri);

            HttpClient httpClient = getHttpClient();
            HttpPost request = new HttpPost(sUri);
            request.setEntity(mpEntity);

            HttpResponse httpResponse = httpClient.execute(request);
            int state = httpResponse.getStatusLine().getStatusCode();
            if (state == 200) {
                resultStr = EntityUtils.toString(httpResponse.getEntity());
                LogHelper.d(TAG, "resultStr = " + resultStr);
            } else if (state == 404) {
                LogHelper.i(TAG, AppServerConfig.NET_404_STR);
            } else if (state == 500) {
                LogHelper.i(TAG, AppServerConfig.NET_500_STR);
            }
        } catch (ClientProtocolException e) {
            LogHelper.i(TAG, AppServerConfig.NET_900_STR);
            e.printStackTrace();
        } catch (IOException e) {
            LogHelper.i(TAG, AppServerConfig.NET_903_STR);
            e.printStackTrace();
        } catch (Exception e) {
            LogHelper.i(TAG, AppServerConfig.NET_UNKNOWN_STR);
            e.printStackTrace();
        }
        return resultStr;
    }
}
