package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.adapter.AccountBillAdapter;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewActivity;
import com.leslie.dream.mxzlw.model.Bill;
import com.leslie.dream.mxzlw.presenter.AccountBillPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AccountActivity extends BaseRecyclerViewActivity implements AccountBillPresenter.IBillView{

    private RelativeLayout account_back_rl;//返回
    private TextView account_pay_tv;//充值
    private TextView balance_usable_tv;//可用余额
    private TextView all_profit_tv;//总收益
    private TextView this_month_tv;//本月收益
    private TextView last_month_tv;//上月收益

    private AccountBillAdapter adapter;
    private List<Bill> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        inview();
        datas = new ArrayList<>();
        adapter = new AccountBillAdapter(context, datas);
        recyclerview.setHasFixedSize(true);
        adapter.setOnItemClickListener(this);
        setRefreshLister(recyclerview);
        // 使用 setIAdapter 不是setAdapter
        recyclerview.setIAdapter(adapter);
    }

    private void inview() {
        recyclerview = fv(R.id.account_recyclerview);
        account_back_rl = fv(R.id.account_back_rl);
        account_pay_tv = fv(R.id.account_pay_tv);
        balance_usable_tv = fv(R.id.balance_usable_tv);
        all_profit_tv = fv(R.id.all_profit_tv);
        this_month_tv = fv(R.id.this_month_tv);
        last_month_tv = fv(R.id.last_month_tv);

        setOnClickListener(account_back_rl);
        setOnClickListener(account_pay_tv);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.account_back_rl://返回
                finish();
                break;
            case R.id.account_pay_tv://充值
                startActivity(RechargeActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(View itemView, int position, int... what) {
        super.onItemClick(itemView, position, what);
    }

    @Override
    public void onLoadMore(View view) {
        super.onLoadMore(view);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
    }

    @Override
    public void onFooterEmptyClick(View v) {

        // reqData(URL_LIST, LOAD_TOP);
    }

    @Override
    public void onFooterEndClick(View v) {

    }

    @Override
    public void onFooterErrorClick(View v) {

        // presenter.reqDataReTry();
    }

    @Override
    public void onSuccessBill(int url_type, int load_type, int succsee, String msg, List<Bill> list) {

    }

    @Override
    public String getUrl(int url_type) {
        return null;
    }

    @Override
    public Map<String, String> getParams(int url_type, int load_type, Bundle bundle) {
        return null;
    }

    @Override
    public void showLoadingUI(int url_type, int load_type) {

    }

    @Override
    public void hideLoadingUI(int url_type, int load_type, boolean success) {

    }
}
