package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.adapter.MyHouseAdapter;
import com.leslie.dream.mxzlw.base.BaseAdapter;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewActivity;
import com.leslie.dream.mxzlw.model.MyHouse;
import com.leslie.dream.mxzlw.presenter.MyHousePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyHouseActivity extends BaseRecyclerViewActivity implements MyHousePresenter.IMyHouseView{

    private RelativeLayout actionbar_back_rl;

    private MyHouseAdapter adapter;
    private List<MyHouse> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_house);
        inView();

        datas = new ArrayList<>();
        adapter = new MyHouseAdapter(context, datas);
        recyclerview.setHasFixedSize(true);
        adapter.setOnItemClickListener(this);
        setRefreshLister(recyclerview);
        // 使用 setIAdapter 不是setAdapter
        recyclerview.setIAdapter(adapter);

    }

    private void inView() {
        actionbar_back_rl = fv(R.id.myhouse_back_rl);
        recyclerview = fv(R.id.myhouse_recyclerview);

        setOnClickListener(actionbar_back_rl);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.myhouse_back_rl:
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
    public void onSuccessMyHouse(int url_type, int load_type, List<MyHouse> list) {

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
