package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActionbarActivity;

/**
 * Created by Quinn on 2017/7/15.
 */

public class AboutUsActivity extends BaseActionbarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initActionbar("关于我们");
    }
}
