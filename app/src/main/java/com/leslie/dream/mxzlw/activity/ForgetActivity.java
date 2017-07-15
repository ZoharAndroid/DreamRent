package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActionbarActivity;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.config.DreamApplication;
import com.leslie.dream.mxzlw.widget.v7.AuthCodeTextView;
import com.leslie.dream.mxzlw.widget.v7.BiuEditText;

/**
 * Created by Quinn on 2017/7/10.
 */

public class ForgetActivity extends BaseActionbarActivity {
    public static final int URL_SMS = 2;

    private BiuEditText et_phone;
    private BiuEditText et_sms;
    private BiuEditText et_passwd;
    private BiuEditText et_passwd_again;

    private AuthCodeTextView tv_sms; // 验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initActionbar("重置密码");
        initView();

    }

    private void initView() {

        et_phone = fv(R.id.et_phone_fg);
        et_sms = fv(R.id.et_sms_fg);
        et_passwd = fv(R.id.et_passwd_fg);
        et_passwd_again = fv(R.id.et_passwd_again_fg);
        tv_sms = fv(R.id.tv_sms_fg);


        tv_sms.setCountDownTime(Constants.BEBUG ? 60 * 1000 : 60 * 1000);

        setOnClickListener(tv_sms);
        setOnClickListener(fv(R.id.tv_commit));


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.tv_commit:
                //onCommit();
                break;
            case R.id.tv_sms:
                //onGetSms();
                break;
        }

    }


}
