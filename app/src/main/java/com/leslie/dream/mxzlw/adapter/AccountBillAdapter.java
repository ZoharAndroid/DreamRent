package com.leslie.dream.mxzlw.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseAdapter;
import com.leslie.dream.mxzlw.model.Bill;


import java.util.List;

/**
 * Created by Quinn on 2017/7/12.
 */

public class AccountBillAdapter extends BaseAdapter<Bill> {

    public AccountBillAdapter(Activity context, List<Bill> datas) {
        super(context, datas);

    }



    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DataViewHoler(inflateView(R.layout.item_account_bill, parent));
    }

    class DataViewHoler extends BaseViewHolder {

        TextView account_item_msg;
        TextView account_item_time;
        TextView account_item_money;



        public DataViewHoler(View itemView) {
            super(itemView);
            account_item_msg = fv(R.id.bill_msg_tv);
            account_item_time = fv(R.id.bill_time_tv);
            account_item_money = fv(R.id.bill_money_tv);

        }
        @Override
        public void onBindData(Bill bill, int position) {
            account_item_msg.setText(bill.getMsg());
            account_item_time.setText(bill.getTime());
            account_item_money.setText(bill.getMoney());

        }
    }
}
