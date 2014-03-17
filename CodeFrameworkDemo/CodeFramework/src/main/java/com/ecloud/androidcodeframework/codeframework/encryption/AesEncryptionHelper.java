package com.ecloud.androidcodeframework.codeframework.encryption;

import com.ecloud.androidcodeframework.codeframework.utils.LogHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午4:52
 * Description: AES加解密工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class AesEncryptionHelper {
    private final static String TAG = "DesUntils";
    private final static String masterPassword = "abcd";//密钥
    private final static byte[] masterPasswordByte = {'a','b','c','d'};//密钥

    /**
     * 解密字符串
     * @param msg 需要解密的字符串
     * @return String 解密之后的字符串
     */
    public static String AesDecryption(String msg) {
        LogHelper.d(TAG, "msg = " + msg);
        String decryptionMsg = "";
        try {
            decryptionMsg = AesEncryption.decrypt(masterPassword, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogHelper.d(TAG, "decryptionMsg = " + decryptionMsg);
        return decryptionMsg;
    }

    /**
     * 解密数据流
     * @param buf 需要解密的数据流
     * @return byte[]
     */
    public static byte[] AesDecryption(byte[] buf) {
        try {
            return AesEncryption.decryptByte(masterPasswordByte, buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密字符串
     * @param msg 需要加密的字符串
     * @return String 加密之后的字符串
     */
    public static String AesEncryption(String msg) {
        LogHelper.d(TAG, "msg = " + msg);
        String encryptionMsg = "";
        try {
            encryptionMsg = AesEncryption.encrypt(masterPassword, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogHelper.d(TAG, "encryptionMsg = " + encryptionMsg);
        return encryptionMsg;
    }

    /**
     * 加密数据流
     * @param buf 需要加密的流
     * @return byte[] 加密之后的流
     */
    public static byte[] AesEncryption(byte[] buf) {
        try {
            return AesEncryption.encryptByte(masterPasswordByte, buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
