package com.leslie.dream.mxzlw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActionbarActivity;
import com.leslie.dream.mxzlw.config.DreamApplication;
import com.leslie.dream.mxzlw.config.UrlApi;
import com.leslie.dream.mxzlw.dialog.LockUiDialog;
import com.leslie.dream.mxzlw.model.User;
import com.leslie.dream.mxzlw.presenter.LoginPresenter;
import com.leslie.dream.mxzlw.util.FormAuthUtil;
import com.leslie.dream.mxzlw.util.SharePreferenceUtil;
import com.leslie.dream.mxzlw.widget.v7.BiuEditText;

import java.util.HashMap;
import java.util.Map;

import static com.leslie.dream.mxzlw.util.FormAuthUtil.convertMD5;

/**
 * Created by Quinn on 2017/7/7.
 */

public class LoginActivity extends BaseActionbarActivity implements LoginPresenter.ILoginView {

    private LoginPresenter presenter;
    private BiuEditText et_passwd;
    private BiuEditText et_phone;

    private LockUiDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionbar("登录");
        initView();
    }

    private void initView() {

        et_passwd = (BiuEditText) findViewById(R.id.et_passwd);
        et_phone = (BiuEditText) findViewById(R.id.et_phone);

        TextView tv_commit = (TextView) findViewById(R.id.tv_commit);
        TextView tv_forget = (TextView) findViewById(R.id.tv_forget);
        TextView tv_register = (TextView) findViewById(R.id.tv_register);

        setOnClickListener(tv_commit);
        setOnClickListener(tv_forget);
        setOnClickListener(tv_register);

        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        if (!"".equals(phoneNumber)){
            setText(et_phone, phoneNumber);
        }
        if (!"".equals(DreamApplication.getUser())){
            String phone = DreamApplication.getUser().getUsername();
            setText(et_phone, phone);
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.tv_commit://登录
                onCommit();
                break;
            case R.id.tv_forget://忘记密码
                startActivity(ForgetActivity.class);
                break;
            case R.id.tv_register://注册
                //startActivity(RegisterActivity.class);
                //finish();
                IntentIntegrator integrator = new IntentIntegrator(this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class);
                integrator.setPrompt("请扫描二维码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            default:
                break;
        }

    }



    private void onCommit() {

        String msg = FormAuthUtil.checkPhone(et_phone.getText().toString());
        if (!isEmpty(msg)) {
            toast(msg);
            return;
        }


        msg = FormAuthUtil.checkPasswd(et_passwd.getText().toString());
        if (!isEmpty(msg)) {
            toast(msg);
            return;
        }

        if (presenter == null) {
            presenter = new LoginPresenter(this, true);
        }

        presenter.reqData(URL_LIST,true);
    }

    @Override
    public void onSuccessLogin(int url_type, int load_type, User user, int success,String msg) {

        if (success == 1){
            toast("登录成功");
            SharePreferenceUtil.saveCacheUser(context, user);
            DreamApplication.refreshUser();
            finish();
        }else {
            toast(msg);
        }
    }

    @Override
    public String getUrl(int url_type) {

        return UrlApi.USER_LOGIN;
    }

    @Override
    public Map<String, String> getParams(int url_type, int load_type, Bundle bundle) {

        Map<String, String> params = new HashMap<>();
        params.put("username", et_phone.getText().toString());
        params.put("password", et_passwd.getText().toString());

        return params;
    }

    @Override
    public void showLoadingUI(int url_type, int load_type) {

        if (url_type == URL_LIST) {
            if (dialog == null) {

                dialog = new LockUiDialog(context, R.style.LoadingDialog);
                dialog.setContentText("登录中");
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

    //回调获取扫描得到的条码值

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast("扫码取消！");
            } else {
                //toast("扫描成功，条码值: " + result.getContents());
                startActivity(RegisterActivity.class,"code",result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
