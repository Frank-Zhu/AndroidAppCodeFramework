package com.ecloud.collection.clothes.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午8:03
 * Description: 弹出信息工具类
 *
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ToastHelper {

    /**
     * Description: 显示短时间Toast信息
     * @param context 上下文
     * @param msg 显示的消息内容
     */
    public  static void showShortToast(Context context,String msg){
        showToast(context,msg,true);
    }

    /**
     * Description: 显示短时间Toast信息
     * @param context 上下文
     * @param resId 显示的消息资源ID
     */
    public  static void showShortToast(Context context,int resId){
        showToast(context,resId,true);
    }

    /**
     *  Description: 显示长时间Toast信息
     * @param context 上下文
     * @param msg 显示的消息内容
     */
    public  static void showLongToast(Context context,String msg){
        showToast(context,msg,false);
    }

    /**
     *  Description: 显示长时间Toast信息
     * @param context 上下文
     * @param resId 显示的消息资源ID
     */
    public  static void showLongToast(Context context,int resId){
        showToast(context,resId,false);
    }

    /**
     * Description: 显示Toast信息
     * @param context 上下文
     * @param msg 显示的消息内容
     * @param isShort 是否短时间
     */
    public static void showToast(Context context, String msg, boolean isShort){
        if(isShort){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Description: 显示Toast信息
     * @param context 上下文
     * @param resId 显示的消息资源ID
     * @param isShort 是否短时间
     */
    public static void showToast(Context context, int resId, boolean isShort){
        if(isShort){
            Toast.makeText(context,resId,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,resId,Toast.LENGTH_LONG).show();
        }
    }
}
