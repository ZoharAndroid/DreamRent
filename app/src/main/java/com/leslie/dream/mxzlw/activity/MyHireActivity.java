package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.adapter.MyHireAdapter;
import com.leslie.dream.mxzlw.adapter.MyHouseAdapter;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewActivity;
import com.leslie.dream.mxzlw.model.MyHouse;
import com.leslie.dream.mxzlw.presenter.MyHirePresenter;
import com.leslie.dream.mxzlw.presenter.MyHousePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyHireActivity extends BaseRecyclerViewActivity implements MyHirePresenter.IMyHireView{

    private RelativeLayout actionbar_back_rl;

    private MyHireAdapter adapter;
    private List<MyHouse> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hire);
        inView();

        datas = new ArrayList<>();
        adapter = new MyHireAdapter(context, datas);
        recyclerview.setHasFixedSize(true);
        adapter.setOnItemClickListener(this);
        setRefreshLister(recyclerview);
        // 使用 setIAdapter 不是setAdapter
        recyclerview.setIAdapter(adapter);

    }

    private void inView() {
        actionbar_back_rl = fv(R.id.myhire_back_rl);
        recyclerview = fv(R.id.myhire_recyclerview);

        setOnClickListener(actionbar_back_rl);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.myhire_back_rl:
                finish();
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
    public void onSuccessMyHire(int url_type, int load_type, List<MyHouse> list) {

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
