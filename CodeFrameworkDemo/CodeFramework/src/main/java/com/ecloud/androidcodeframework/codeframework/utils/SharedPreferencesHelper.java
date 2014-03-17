package com.ecloud.androidcodeframework.codeframework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ecloud.androidcodeframework.codeframework.encryption.AesEncryptionHelper;
import com.ecloud.androidcodeframework.codeframework.encryption.DesEncryptionHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午5:09
 * Description: SharedPreferences工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class SharedPreferencesHelper {
    private static final String TAG = SharedPreferencesHelper.class.getSimpleName();
    private static final String SharedPreferenceFileName = "setData";

    /**
     * 保存字符串
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @param value 值
     */
    public static void setString(Context mContext, int mode, String key, String value){
        LogHelper.d(TAG, "setString -->  key = " + key + " ---> value = " + value);
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 读取字符串
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @return String
     */
    public static String getString(Context mContext, int mode, String key){
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        String value = sp.getString(key, "");
        LogHelper.d(TAG, "getString -->  key = " + key + " ---> value = " + value);
        return value;
    }

    /**
     * 保存AES加密字符串
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @param value 值
     */
    public static void setAesEncryptionString(Context mContext, int mode, String key, String value){
        LogHelper.d(TAG, "setAesEncryptionString -->  key = " + key + " ---> value = " + value);
        String encryptionValue = AesEncryptionHelper.AesEncryption(value);
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, encryptionValue);
        editor.commit();
    }

    /**
     * 读取AES解密字符串
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @return String
     */
    public static String getAesEncryptionString(Context mContext, int mode, String key){
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        String value = sp.getString(key, "");
        LogHelper.d(TAG, "getAesEncryptionString -->  key = " + key + " ---> value = " + value);
        return AesEncryptionHelper.AesDecryption(value);
    }

    /**
     * 保存DES加密字符串
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @param value 值
     */
    public static void setDesEncryptionString(Context mContext, int mode, String key, String value){
        LogHelper.d(TAG, "setDesEncryptionString -->  key = " + key + " ---> value = " + value);
        String encryptionValue = DesEncryptionHelper.DesEncryption(value);
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, encryptionValue);
        editor.commit();
    }

    /**
     * 读取DES解密字符串
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @return String
     */
    public static String getDesEncryptionString(Context mContext, int mode, String key){
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        String value = sp.getString(key, "");
        LogHelper.d(TAG, "getDesEncryptionString -->  key = " + key + " ---> value = " + value);
        return DesEncryptionHelper.DesDecryption(value);
    }

    /**
     * 保存整形数据
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @param value 值
     */
    public static void setInt(Context mContext, int mode, String key, int value){
        LogHelper.d(TAG, "setInt -->  key = " + key + " ---> value = " + value);
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 读取整形数据
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @return String
     */
    public static int getInt(Context mContext, int mode, String key){
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        int value = sp.getInt(key, 0);
        LogHelper.d(TAG, "getInt -->  key = " + key + " ---> value = " + value);
        return value;
    }

    /**
     * 保存boolean数据
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @param value 值
     */
    public static void setBoolean(Context mContext, int mode, String key, boolean value){
        LogHelper.d(TAG, "setBoolean -->  key = " + key + " ---> value = " + value);
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 读取boolean数据
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @return String
     */
    public static boolean getBoolean(Context mContext, int mode, String key){
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        boolean value = sp.getBoolean(key, false);
        LogHelper.d(TAG, "getBoolean -->  key = " + key + " ---> value = " + value);
        return value;
    }

    /**
     * 读取boolean数据
     * @param mContext 上下文
     * @param mode 模式
     * @param key key
     * @return String
     */
    public static boolean getBoolean(Context mContext, int mode, String key, boolean defaultValue){
        SharedPreferences sp = mContext.getSharedPreferences(SharedPreferenceFileName, mode);
        boolean value = sp.getBoolean(key, defaultValue);
        LogHelper.d(TAG, "getBoolean -->  key = " + key + " ---> value = " + value);
        return value;
    }
}
