package com.leslie.dream.mxzlw.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.activity.AboutUsActivity;
import com.leslie.dream.mxzlw.activity.AccountActivity;
import com.leslie.dream.mxzlw.activity.LoginActivity;
import com.leslie.dream.mxzlw.activity.MyCollectionActivity;
import com.leslie.dream.mxzlw.activity.MyHireActivity;
import com.leslie.dream.mxzlw.activity.MyHouseActivity;
import com.leslie.dream.mxzlw.activity.MyShareActivity;
import com.leslie.dream.mxzlw.activity.PersonalActivity;
import com.leslie.dream.mxzlw.activity.ScanActivity;
import com.leslie.dream.mxzlw.activity.WithdrawalsActivity;
import com.leslie.dream.mxzlw.base.BaseFragment;
import com.leslie.dream.mxzlw.config.DreamApplication;
import com.leslie.dream.mxzlw.model.User;


public class MyselfFragment extends BaseFragment{

    private ImageView my_head_img;
    private TextView my_nickname_tv;//昵称

    private LinearLayout my_account_ll;//我的账户
    private LinearLayout my_collection_ll;//我的收藏
    private LinearLayout my_share_ll;//我要分享
    private LinearLayout my_extend_ll;//我的推广

    private LinearLayout my_rental_ll;//我的租房
    private LinearLayout my_hire_ll;//我的出租
    private LinearLayout my_service_ll;//我的服务
    private LinearLayout my_appointment_ll;//我的预约
    private LinearLayout my_cash_ll;//我要提现
    private LinearLayout my_install_ll;//个人设置
    private LinearLayout my_voucher_ll;//抵用券
    private LinearLayout my_customer_service_ll;//客服帮助
    private LinearLayout my_about_us_ll;//关于我们

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (main_layout == null) {
            main_layout = inflateView(R.layout.fragment_my_self, container);

            inView();

        }
        return main_layout;
    }

    private void inView() {
        my_head_img = fv(R.id.my_head_img);
        my_nickname_tv = fv(R.id.my_nickname_tv);

        my_account_ll = fv(R.id.my_account_ll);
        my_collection_ll = fv(R.id.my_collection_ll);
        my_share_ll = fv(R.id.my_share_ll);
        my_extend_ll = fv(R.id.my_extend_ll);

        my_rental_ll = fv(R.id.my_rental_ll);
        my_hire_ll = fv(R.id.my_hire_ll);
        my_service_ll = fv(R.id.my_service_ll);
        my_appointment_ll = fv(R.id.my_appointment_ll);
        my_install_ll = fv(R.id.my_install_ll);
        my_cash_ll = fv(R.id.my_cash_ll);
        my_voucher_ll = fv(R.id.my_voucher_ll);
        my_customer_service_ll = fv(R.id.my_customer_service_ll);
        my_about_us_ll = fv(R.id.my_about_us_ll);

        setOnClickListener(my_head_img);
        setOnClickListener(my_account_ll);
        setOnClickListener(my_collection_ll);
        setOnClickListener(my_share_ll);
        setOnClickListener(my_extend_ll);
        setOnClickListener(my_rental_ll);
        setOnClickListener(my_hire_ll);
        setOnClickListener(my_service_ll);
        setOnClickListener(my_appointment_ll);
        setOnClickListener(my_install_ll);
        setOnClickListener(my_cash_ll);
        setOnClickListener(my_voucher_ll);
        setOnClickListener(my_customer_service_ll);
        setOnClickListener(my_about_us_ll);



    }

    private void checkLoing(Class<? extends Activity> myClass){
        if (!"".equals(DreamApplication.getUser().getToken())){
            startActivity(myClass);
        }else {
            startActivity(LoginActivity.class);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_head_img://头像
                checkLoing(PersonalActivity.class);
                break;
            case R.id.my_account_ll://我的账户
                checkLoing(AccountActivity.class);
                break;
            case R.id.my_collection_ll://我的收藏
                checkLoing(MyCollectionActivity.class);
                break;
            case R.id.my_share_ll://我要分享
                checkLoing(MyShareActivity.class);
                break;
            case R.id.my_extend_ll://我的推广

                break;
            case R.id.my_rental_ll://我的租房
                checkLoing(MyHouseActivity.class);
                break;
            case R.id.my_hire_ll://我的出租
                checkLoing(MyHireActivity.class);
                break;
            case R.id.my_service_ll://我的服务

                break;
            case R.id.my_appointment_ll://我的预约

                break;
            case R.id.my_install_ll://个人设置
                checkLoing(PersonalActivity.class);
                break;
            case R.id.my_cash_ll://我要提现
                checkLoing(WithdrawalsActivity.class);
                break;
            case R.id.my_voucher_ll://抵用券

                break;
            case R.id.my_customer_service_ll://客服帮助

                break;
            case R.id.my_about_us_ll://关于我们
                startActivity(AboutUsActivity.class);

                break;


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        User user = DreamApplication.getUser();
        if (!"".equals(user.getToken())) {
            String nickName = user.getNickname();
            String headUrl = user.getAvatarUrl();
            if (!"".equals(headUrl)) {
                loadImage(my_head_img, headUrl, R.drawable.portrait);
            }
            if (nickName != null && nickName.length() > 0){
                my_nickname_tv.setText(nickName);
            }else {
                my_nickname_tv.setText("昵称");
            }

        }else {
            my_nickname_tv.setText("请登录");
        }
    }

}
