package com.ecloud.collection.clothes.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ecloud.collection.clothes.R;
import com.ecloud.collection.clothes.utils.LogHelper;
import com.ecloud.collection.clothes.utils.StringHelper;
import com.ecloud.collection.clothes.utils.ToastHelper;

public class LoginActivity extends BaseActivity {

    private EditText mLoginAccountEdit;
    private EditText mLoginPasswordEdit;
    private Button mLoginButton;
    private Spinner mStoreSpinner;
    private String mLoginAccountStr;
    private String mLoginPasswordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogHelper.d(TAG,"--> onCreate");
        setUpViewComponent();
    }

    private void setUpViewComponent() {
        mLoginAccountEdit = (EditText)findViewById(R.id.et_account);
        mLoginPasswordEdit = (EditText)findViewById(R.id.et_password);
        mLoginButton = (Button)findViewById(R.id.btn_login);
        mStoreSpinner = (Spinner)findViewById(R.id.sp_store_choose);
        setUpButtonListener();
    }

    private void setUpButtonListener() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginAccountStr = mLoginAccountEdit.getText().toString().trim();
                mLoginPasswordStr = mLoginPasswordEdit.getText().toString().trim();
                if(StringHelper.notEmptyString(mLoginAccountStr)
                        && StringHelper.notEmptyString(mLoginPasswordStr)){
                    onLoginAccount();
                }else{
                    ToastHelper.showShortToast(getApplicationContext(),R.string.account_password_empty);
                }
            }
        });
    }

    private void onLoginAccount() {
        LogHelper.d(TAG, "--> onLoginAccount");
        LogHelper.d(TAG,"mLoginAccountStr = " + mLoginAccountStr);
        LogHelper.d(TAG,"mLoginPasswordStr = " + mLoginPasswordStr);
    }

}
