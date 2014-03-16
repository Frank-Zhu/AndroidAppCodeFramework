package com.ecloud.collection.clothes.ui;

import android.app.Activity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午2:03
 * Description: activity 基类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseActivity extends Activity {
    protected  String TAG  = getClass().getSimpleName();
    protected ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageLoader = ImageLoader.getInstance();
    }
}
