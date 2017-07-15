package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.view.View;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.adapter.MyCollectionAdapter;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewActivity;
import com.leslie.dream.mxzlw.model.MyHouse;
import com.leslie.dream.mxzlw.presenter.MyCollectionPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCollectionActivity extends BaseRecyclerViewActivity implements MyCollectionPresenter.IMyCollectionView{


    private MyCollectionAdapter adapter;
    private List<MyHouse> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        initActionbar("我的收藏");
        inView();

        datas = new ArrayList<>();
        adapter = new MyCollectionAdapter(context, datas);
        recyclerview.setHasFixedSize(true);
        adapter.setOnItemClickListener(this);
        setRefreshLister(recyclerview);
        // 使用 setIAdapter 不是setAdapter
        recyclerview.setIAdapter(adapter);

    }

    private void inView() {
        recyclerview = fv(R.id.mycollection_recyclerview);

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
    public void onSuccessMyCollection(int url_type, int load_type, List<MyHouse> list) {

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
