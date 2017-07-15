package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActionbarActivity;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.config.DreamApplication;
import com.leslie.dream.mxzlw.config.UrlApi;
import com.leslie.dream.mxzlw.dialog.LockUiDialog;
import com.leslie.dream.mxzlw.model.User;
import com.leslie.dream.mxzlw.presenter.RegisterPresenter;
import com.leslie.dream.mxzlw.presenter.SmsPresenter;
import com.leslie.dream.mxzlw.util.FormAuthUtil;
import com.leslie.dream.mxzlw.util.SharePreferenceUtil;
import com.leslie.dream.mxzlw.widget.v7.AuthCodeTextView;
import com.leslie.dream.mxzlw.widget.v7.BiuEditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Quinn on 2017/7/10.
 */

public class RegisterActivity extends BaseActionbarActivity implements RegisterPresenter.IRegisterView, SmsPresenter.IAuthCodeView {
    public static final int URL_SMS = 2;

    private BiuEditText  et_phone;
    private BiuEditText et_sms;//短信验证码
    private BiuEditText et_passwd;
    private BiuEditText et_passwd_again;
    private BiuEditText et_number;//推荐号

    private AuthCodeTextView tv_sms; // 获取验证码
    private RegisterPresenter presenter;
    private SmsPresenter presenter_sms;
    private LockUiDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initActionbar("注册");
        initView();
        String code = getIntent().getStringExtra("code");
        if (code != null && code.length() > 0){
            et_number.setText(code);
            et_number.setFocusableInTouchMode(false);
        }

    }

    private void initView() {

        et_phone = fv(R.id.et_phone);
        et_sms = fv(R.id.et_sms);
        et_passwd = fv(R.id.et_passwd);
        et_passwd_again = fv(R.id.et_passwd_again);
        tv_sms = fv(R.id.tv_sms);
        et_number=fv(R.id.et_number);

        tv_sms.setCountDownTime(Constants.BEBUG ? 60 * 1000 : 60 * 1000);

        setOnClickListener(tv_sms);
        setOnClickListener(fv(R.id.tv_commit));


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.tv_commit:
                onCommit();
                break;
            case R.id.tv_sms:
                onGetSms();
                break;
        }

    }

    private void onGetSms() {
        String msg = FormAuthUtil.checkPhone(et_phone.getText().toString());
        if (!isEmpty(msg)) {
            toast(msg);
            return;
        }

        if (presenter_sms == null) {
            presenter_sms = new SmsPresenter(this, false);
        }

        tv_sms.start();
        presenter_sms.reqData(URL_SMS,true);

    }

    private void onCommit() {

        String msg = FormAuthUtil.checkPhone(et_phone.getText().toString());
        if (!isEmpty(msg)) {
            toast(msg);
            return;
        }

        msg = FormAuthUtil.checkSms(et_sms.getText().toString());
        if (!isEmpty(msg)) {
            toast(msg);
            return;
        }

        msg = FormAuthUtil.checkPasswd(et_passwd.getText().toString());
        if (!isEmpty(msg)) {
            toast(msg);
            return;
        }

        msg = FormAuthUtil.checkEqual(et_passwd.getText().toString(), //
                et_passwd_again.getText().toString(), "请确认两个密码是否一致");
        if (!isEmpty(msg)) {
            toast(msg);
            return;
        }

        if (presenter == null) {
            presenter = new RegisterPresenter(this, true);
        }

        presenter.reqData(URL_LIST,true);

    }

    @Override
    public void onSuccessSms(int url_type, int load_type, String msg) {
        toast(msg);
    }

    @Override
    public void onSuccessRegister(int url_type, int load_type,User user, int success, String msg) {
        if (success == 1){
            toast("注册成功");
            SharePreferenceUtil.saveCacheUser(context, user);
            DreamApplication.refreshUser();
            finish();
        }else {
            toast(msg);
        }



    }

    @Override
    public String getUrl(int url_type) {
        if (url_type == URL_SMS) {
            return UrlApi.USER_GET_SMS_NUM;
        }
        return UrlApi.USER_REG;
    }

    @Override
    public Map<String, String> getParams(int url_type, int load_type, Bundle bundle) {
        Map<String, String> params = new HashMap<>();
        params.put("username", et_phone.getText().toString());
        params.put("mobile", et_phone.getText().toString());
        params.put("password", et_passwd.getText().toString());
        params.put("referral", et_number.getText().toString());
        //params.put("sms", et_passwd.getText().toString());
        return params;
    }

    @Override
    public void showLoadingUI(int url_type, int load_type) {
        if (url_type == URL_LIST) {
            if (dialog == null) {

                dialog = new LockUiDialog(context, R.style.LoadingDialog);
                dialog.setContentText("注册中");
            }
            dialog.show();
        }

    }

    @Override
    public void hideLoadingUI(int url_type, int load_type, boolean success) {
        if (url_type == URL_LIST) {
            dialog.dismiss();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv_sms.cancel();
    }
}
