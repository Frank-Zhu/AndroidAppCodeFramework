package com.ecloud.androidcodeframework.codeframework.entity;

import java.io.Serializable;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午10:00
 * Description: 网络返回数据基类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseBean implements Serializable{
    protected int code;
    protected String msg;
    protected int total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
