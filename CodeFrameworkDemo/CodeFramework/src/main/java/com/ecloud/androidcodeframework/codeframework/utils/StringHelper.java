package com.ecloud.androidcodeframework.codeframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午7:53
 * Description: 字符串相关工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu             1.0                    1.0
 * Why & What is modified:
 */
public class StringHelper {
    /**
     * Description: 是否为空字符串
     * @param string 字符串
     * @return boolean
     */
    public static boolean isEmptyString(String string){
        return string == null || string.equals("") || string.toLowerCase().equals("null");
    }

    /**
     * Description: 字符串不为空串
     * @param  string 字符串
     * @return boolean
     */
    public static boolean notEmptyString(String string){
        return !isEmptyString(string);
    }

    /**
     * 判断字符串中是否有中文字符
     * @param str 需要判断的字符串
     * @return boolean
     */
    public static boolean hasChinese(String str) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            int size = m.groupCount();
            for (int i = 0; i <= size; i++) {
                count = count + 1;
            }
        }
        return count > 0;
    }
}
