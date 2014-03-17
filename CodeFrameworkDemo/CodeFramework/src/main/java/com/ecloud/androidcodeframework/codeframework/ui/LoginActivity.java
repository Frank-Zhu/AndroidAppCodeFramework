package com.ecloud.androidcodeframework.codeframework.ui;

import android.os.Bundle;

import com.ecloud.androidcodeframework.codeframework.R;
import com.ecloud.androidcodeframework.codeframework.utils.LogHelper;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogHelper.d(TAG,"--> onCreate");
    }
}
