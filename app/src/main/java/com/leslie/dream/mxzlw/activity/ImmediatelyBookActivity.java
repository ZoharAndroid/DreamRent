package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.ZBaseActionBarActivity;


/**
 * Created by zzh on 2017/7/11.
 *
 * Activity:立即预约
 */

public class ImmediatelyBookActivity extends ZBaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediately_book);
        initActionbar("租约合同");
    }
}
