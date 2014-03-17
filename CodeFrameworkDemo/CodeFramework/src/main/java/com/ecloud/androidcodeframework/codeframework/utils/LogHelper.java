package com.ecloud.androidcodeframework.codeframework.utils;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-15 下午8:54
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class LogHelper {
    private static final boolean DEBUG_V = true;
    private static final boolean DEBUG_D = true;
    private static final boolean DEBUG_I = true;
    private static final boolean DEBUG_W = true;
    private static final boolean DEBUG_E = true;

    public static void v(String tag, String msg) {
        if(DEBUG_V) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, boolean isFeatureDebug) {
        if(DEBUG_V && isFeatureDebug) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if(DEBUG_V) {
            android.util.Log.v(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if(DEBUG_D) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, boolean isFeatureDebug) {
        if(DEBUG_D && isFeatureDebug) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if(DEBUG_D) {
            android.util.Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if(DEBUG_I) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, boolean isFeatureDebug) {
        if(DEBUG_I && isFeatureDebug) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if(DEBUG_I) {
            android.util.Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg, boolean isFeatureDebug) {
        if(DEBUG_W && isFeatureDebug) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if(DEBUG_W) {
            android.util.Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if(DEBUG_W) {
            android.util.Log.w(tag, tr);
        }
    }

    public static void e(String tag, String msg) {
        if(DEBUG_E) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, boolean isFeatureDebug) {
        if(DEBUG_E && isFeatureDebug) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if(DEBUG_E) {
            android.util.Log.e(tag, msg, tr);
        }
    }
}
