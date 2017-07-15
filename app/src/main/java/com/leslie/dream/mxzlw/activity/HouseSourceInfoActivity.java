package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.ZBaseActionBarActivity;

/**
 * Created by zzh on 2017/7/14.
 */

public class HouseSourceInfoActivity extends ZBaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_souce_info);
        initActionbar("房源信息");
    }
}
