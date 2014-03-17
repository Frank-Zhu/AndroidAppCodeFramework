package com.ecloud.androidcodeframework.codeframework.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午4:36
 * Description: MD5加密工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class Md5Encryption {
    /**
     * 获取MD5字符串
     * @param content 需要转换的字符串
     * @return String
     */
    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }
}
