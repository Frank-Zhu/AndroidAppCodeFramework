package com.ecloud.androidcodeframework.codeframework.encryption;

import com.ecloud.androidcodeframework.codeframework.utils.LogHelper;

import org.apache.http.util.EncodingUtils;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午4:59
 * Description: DES加解密工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DesEncryptionHelper {
    private final static String TAG = DesEncryptionHelper.class.getSimpleName();

    /**
     * 解密字符串
     * @param msg 需要解密的字符串
     * @return String 解密之后的字符串
     */
    public static String DesDecryption(String msg) {
        DesEncryption m = new DesEncryption();
        LogHelper.d(TAG, "msg = " + msg);
        String decryptionMsg = m.DecodeStr(msg,msg.length());
        return hexStr2Str(toHex(decryptionMsg));
    }

    /**
     * 解密数据流
     * @param buf 需要解密的数据流
     * @param len 需要解密的流长度
     * @return byte[]
     */
    public static byte[] DesDecryption(byte[] buf, int len) {
        DesEncryption m = new DesEncryption();
        LogHelper.d(TAG, "decryptionMsg = " + EncodingUtils.getString(buf, "UTF-8"));
        return m.Decode(buf,len);
    }

    /**
     * 加密字符串
     * @param msg 需要加密的字符串
     * @return String 加密之后的字符串
     */
    public static String DesEncryption(String msg) {
        DesEncryption m = new DesEncryption();
        LogHelper.d(TAG, "msg = " + msg);
        String encryptionMsg = m.EncodeStr(msg,msg.length());
        return hexStr2Str(encryptionMsg);
    }

    /**
     * 加密数据流
     * @param buf 需要加密的流
     * @param len 需要加密的流长度
     * @return byte[] 加密之后的流
     */
    public static byte[] DesEncryption(byte[] buf, int len) {
        DesEncryption m = new DesEncryption();
        LogHelper.d(TAG, "encryptionMsg = " + EncodingUtils.getString(buf, "UTF-8"));
        return m.Encode(buf,len);
    }

    /**
     * 字符串转16进制
     * @param str 需要转换的字符串
     * @return String
     */
    public static String toHex(String str) {
        String hexString = "0123456789ABCDEF";
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            char temp1 = hexString.charAt((aByte & 0xf0) >> 4);// 作用同 n / 16
            char temp2 = hexString.charAt(aByte & 0x0f);// 作用同 n
            if (!(temp1 == '0' && temp2 == '0')) {
                sb.append(temp1);
                sb.append(temp2);
            }
        }
        return sb.toString();
    }

    /**
     * 16进制转字符串
     * @param hexStr 需要转换的字符串
     * @return String
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }
}
