package com.leslie.dream.mxzlw.config;

import android.app.Application;

import com.leslie.dream.mxzlw.model.User;
import com.leslie.dream.mxzlw.util.SharePreferenceUtil;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by Quinn on 2017/7/7.
 */

public class DreamApplication extends Application {

    private static DreamApplication instance;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initNetwork();
        initUser();

    }


    private void initUser() {
        user = SharePreferenceUtil.getCacheUser(this);
        if (user == null) {
            user = new User();
        }
    }

    public static DreamApplication getInstance()
    {
        return instance;
    }


    public static void refreshUser() {
        getInstance().initUser();
    }

    public static User getUser() {
        getInstance().initUser();
        return getInstance().user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    private void initNetwork() {
        NoHttp.initialize(this);
        NoHttp.initialize(this, new NoHttp.Config()
                .setNetworkExecutor(new OkHttpNetworkExecutor())
        );
    }


}
