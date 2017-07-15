package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActivity;
import com.leslie.dream.mxzlw.fragment.CategoryFragment;
import com.leslie.dream.mxzlw.fragment.HomeFragment;
import com.leslie.dream.mxzlw.fragment.MyselfFragment;
import com.leslie.dream.mxzlw.fragment.ServiceFragment;
import com.leslie.dream.mxzlw.manager.FragmentManagerUtil;


public class MainActivity extends BaseActivity {

    public static final String HOME_FRAGMENT_TAG = "home_fragment";

    private long outTime = 0;// 退出间隔时间
    private Fragment[] fragments;
    private int current_index = -1;

    private ImageView img_home;
    private ImageView img_myself;

    private ImageView img_category;
    private ImageView img_service;

    private TextView tv_home;
    private TextView tv_myself;

    private TextView tv_category;
    private TextView tv_service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragments = new Fragment[4];
        int index = getIntent().getIntExtra("index", 0);
        setTab(index);

    }

    private void initView() {

        LinearLayout ll_home = fv(R.id.ll_home);
        LinearLayout ll_myself = fv(R.id.ll_myself);
        LinearLayout ll_category = fv(R.id.ll_category);
        LinearLayout ll_service = fv(R.id.ll_service);

        img_home = fv(R.id.img_home);
        img_myself = fv(R.id.img_myself);

        img_category = fv(R.id.img_category);
        img_service = fv(R.id.img_service);

        tv_home = fv(R.id.tv_home);
        tv_myself = fv(R.id.tv_myself);

        tv_category = fv(R.id.tv_category);
        tv_service = fv(R.id.tv_service);

        setOnClickListener(ll_home);
        setOnClickListener(ll_myself);
        setOnClickListener(ll_category);
        setOnClickListener(ll_service);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                setTab(0);
                break;
            case R.id.ll_category:
                setTab(1);
                break;
            case R.id.ll_service:
                setTab(2);
                break;
            case R.id.ll_myself:
                setTab(3);
                break;
        }

    }

    // 切换 fragment
    private void setTab(int index) {

        if (index < 0 || index > fragments.length - 1) {
            index = 0;
        }

        Fragment f = fragments[index];

        setViewSelect(img_home);
        setViewSelect(img_category);

        setViewSelect(img_myself);
        setViewSelect(img_service);

        setViewSelect(tv_home);
        setViewSelect(tv_category);

        setViewSelect(tv_myself);
        setViewSelect(tv_service);

        switch (index) {

            case 1:
                if (f == null) {
                    f = new CategoryFragment();
                }
                setViewSelect(img_category, true);
                setViewSelect(tv_category, true);
                break;

            case 2:
                if (f == null) {
                    f = new ServiceFragment();
                }
                setViewSelect(img_service, true);
                setViewSelect(tv_service, true);
                break;
            case 3:
                if (f == null) {
                    f = new MyselfFragment();
                }
                setViewSelect(img_myself, true);
                setViewSelect(tv_myself, true);
                break;
            default:
                if (f == null) {

                    f = new HomeFragment();
                }
                setViewSelect(img_home, true);
                setViewSelect(tv_home, true);
                break;
        }

        if (fragments[index] == null && f != null) {
            fragments[index] = f;
        }

        if (f != null && current_index != index) {
            current_index = index;
            if (current_index == 0) {
                FragmentManagerUtil.setForegroundFragment(getSupportFragmentManager(), f, R.id.rl_mainLayout, HOME_FRAGMENT_TAG);
            } else {
                FragmentManagerUtil.setForegroundFragment(getSupportFragmentManager(), f, R.id.rl_mainLayout);
            }
        }

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long now = System.currentTimeMillis() / 1000;
            if (now - outTime <= 2) {
                finish();
            } else {
                toast("再次点击，退出应用");
                outTime = now;
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }



}
