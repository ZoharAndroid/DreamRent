package com.leslie.dream.mxzlw.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.activity.HouseSourceInfoActivity;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewFragment;

/**
 * Created by zzh on 2017/7/12.
 *
 * 首页：托管出租
 */

public class HomeTrustRentFragment extends BaseRecyclerViewFragment{

    private Button mBtnTrustRent;


    public HomeTrustRentFragment() {
    }

    @Override
    public void onNetworkLazyLoad() {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (main_layout == null) {
            main_layout = inflateView(R.layout.fragment_home_trust_rent, container);
            initView();
        }
        return main_layout;
    }


    public void initView(){
        mBtnTrustRent = (Button) main_layout.findViewById(R.id.btn_trust_rent);
    }

    public void initEvent(){
        mBtnTrustRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HouseSourceInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initEvent();
    }
}
