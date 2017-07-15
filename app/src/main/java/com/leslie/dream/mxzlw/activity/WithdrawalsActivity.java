package com.leslie.dream.mxzlw.activity;

import android.annotation.SuppressLint;
import android.icu.math.BigDecimal;
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
 * Created by Quinn on 2017/7/14.
 * //提现
 */

public class WithdrawalsActivity extends BaseActionbarActivity {

    private CircleImageView withdrawals_mode_img;//到账方式
    private BiuEditText et_money_withdrawals;//输入金额
    private TextView tv_withdrawals;//提现
    private TextView balance_withdrawals_tv;//余额
    private TextView all_withdrawals_tv;//全部提现

    private int mode = 0;

    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawals);
        initActionbar("账户提现");
        initView();
        InputFilter[] filters={new CashierInputFilter()};
        et_money_withdrawals.setFilters(filters);
    }

    private void initView() {
        withdrawals_mode_img = fv(R.id.withdrawals_mode_img);
        et_money_withdrawals = fv(R.id.et_money_withdrawals);
        tv_withdrawals = fv(R.id.tv_withdrawals);
        balance_withdrawals_tv = fv(R.id.balance_withdrawals_tv);
        all_withdrawals_tv = fv(R.id.all_withdrawals_tv);

        setOnClickListener(withdrawals_mode_img);
        setOnClickListener(all_withdrawals_tv);
        setOnClickListener(tv_withdrawals);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.withdrawals_mode_img:
                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(fv(R.id.withdrawals_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_withdrawals://提现
                String money = et_money_withdrawals.getText().toString().trim();
                String balance = balance_withdrawals_tv.getText().toString().trim();

                //toast(doubleToInt(money)+"*****"+doubleToInt(balance));
                if (doubleToInt(balance) >= doubleToInt(money) && doubleToInt(money) > 0){//判断余额与输入金额的大小
                    if (mode == 1){
                        toast("微信");
                    }else if (mode == 2){
                        toast("支付宝");
                    }else {
                        toast("请先选择支付方式");
                        //实例化SelectPicPopupWindow
                        menuWindow = new SelectPicPopupWindow(this, itemsOnClick);
                        //显示窗口
                        menuWindow.showAtLocation(fv(R.id.withdrawals_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                    }
                }else {
                    toast("余额不足");
                }

                break;
            case R.id.all_withdrawals_tv://全部提现
                et_money_withdrawals.setText(balance_withdrawals_tv.getText().toString().trim());
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.pop_wx_img:
                    withdrawals_mode_img.setImageResource(R.drawable.wechat);
                    mode = 1;
                    toast("微信支付");
                    break;
                case R.id.pop_zfb_img:
                    withdrawals_mode_img.setImageResource(R.drawable.alipay);
                    mode = 2;
                    toast("支付宝支付");
                    break;

            }


        }
    };

    private int doubleToInt(String money){
        int a = money.indexOf(".");
        int b = money.length()-(a+1);
        String c="";
        if (a>=0){
            if (b==2){
                c = money;
            }else if (b==1){
            c = money+"0";
        }else if (b == 0){
            c = money+"00";
        }
    }else {
        c = money+".00";
        }

        return Integer.parseInt(c.replace(".",""));
    }

}
