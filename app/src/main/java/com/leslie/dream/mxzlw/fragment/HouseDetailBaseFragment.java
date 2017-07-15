package com.leslie.dream.mxzlw.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zzh on 2017/7/4.
 *
 * Fragment基类：HouseDetailBaseFragment
 */

public abstract class HouseDetailBaseFragment extends Fragment {

    public Activity mActivity;
    // fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    // 处理fragment布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initFragmentView();
    }

    // 依附Activity完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
    }

    /**
     *初始化Fragment界面
     */
    public abstract View initFragmentView();

    /**
     * 初始化数据
     */
    public void initData(){

    }




}
