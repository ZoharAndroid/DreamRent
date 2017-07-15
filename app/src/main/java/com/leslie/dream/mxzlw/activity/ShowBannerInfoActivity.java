package com.leslie.dream.mxzlw.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActionbarActivity;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.model.BannerHome;

/**
 * Created by zzh on 2017/7/14.
 */

public class ShowBannerInfoActivity extends BaseActionbarActivity {

    private WebView mWebViewBanner;

    private BannerHome mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBanner =  (BannerHome) getIntent().getExtras().get(Constants.HOME_TOP_BANNER);
        setContentView(R.layout.activity_home_banner_show);
        initActionbar(mBanner.getBanner_name());
        initView();
        initData();
    }

    public void initView(){
        mWebViewBanner = (WebView) findViewById(R.id.wb_banner_show);
    }

    public void initData(){


        //WebView设置
        WebSettings settings = mWebViewBanner.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);// 可以双击

        mWebViewBanner.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 让自己的WebView加载url
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 开始网页加载
                super.onPageStarted(view, url, favicon);
               // pbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 结束加载
                super.onPageFinished(view, url);
                //pbLoading.setVisibility(View.INVISIBLE);
            }
        });


        mWebViewBanner.loadUrl(mBanner.getBanner_jumpUrl());
        //Toast.makeText(this, mBanner.getBanner_name()+";"+mBanner.getBanner_jumpUrl(),Toast.LENGTH_SHORT).show();
    }
}
