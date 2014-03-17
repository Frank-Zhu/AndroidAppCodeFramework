package com.ecloud.androidcodeframework.codeframework.entity;

import java.io.Serializable;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午9:57
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ErrorBaseBean extends BaseBean implements Serializable {
    protected Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
