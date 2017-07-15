package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActionbarActivity;
import com.leslie.dream.mxzlw.util.CashierInputFilter;
import com.leslie.dream.mxzlw.widget.v7.BiuEditText;
import com.leslie.dream.mxzlw.widget.v7.SelectPicPopupWindow;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Quinn on 2017/7/14.//充值
 */

public class RechargeActivity extends BaseActionbarActivity{

    private CircleImageView recharge_mode_img;
    private BiuEditText et_money;
    private TextView tv_recharge;

    private int mode = 0;

    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initActionbar("账户充值");
        initView();
        InputFilter[] filters={new CashierInputFilter()};
        et_money.setFilters(filters);
    }

    private void initView() {
        recharge_mode_img = fv(R.id.recharge_mode_img);
        et_money = fv(R.id.et_money);
        tv_recharge = fv(R.id.tv_recharge);

        setOnClickListener(recharge_mode_img);
        setOnClickListener(tv_recharge);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.recharge_mode_img:
                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(fv(R.id.recharge_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_recharge://充值

                if (mode == 1){
                    toast("微信");
                }else if (mode == 2){
                    toast("支付宝");
                }else {
                    toast("请先选择支付方式");
                    //实例化SelectPicPopupWindow
                    menuWindow = new SelectPicPopupWindow(this, itemsOnClick);
                    //显示窗口
                    menuWindow.showAtLocation(fv(R.id.recharge_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                }
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.pop_wx_img:
                    recharge_mode_img.setImageResource(R.drawable.wechat);
                    mode = 1;
                    toast("微信支付");
                    break;
                case R.id.pop_zfb_img:
                    recharge_mode_img.setImageResource(R.drawable.alipay);
                    mode = 2;
                    toast("支付宝支付");
                    break;

            }


        }
    };

}
