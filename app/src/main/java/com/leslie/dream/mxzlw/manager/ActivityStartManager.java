package com.leslie.dream.mxzlw.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;


import com.leslie.dream.mxzlw.activity.LoginActivity;
import com.leslie.dream.mxzlw.base.BaseFragment;
import com.leslie.dream.mxzlw.config.DreamApplication;

import java.io.Serializable;

/**
 * 页面启动管理
 * <p/>
 * eg: ActivityStartManager.startActivity(context,XXXActivity.class, true, "a", 12);
 *
 * @author dzl 2017/7/5.
 */
public class ActivityStartManager {

    /**
     * 启动 页面
     * <p/>
     * obj[0] = true时可拦截登录
     */
    public static void startActivity(Context context, Class<? extends Activity> clz, Object... obj) {

        if (checkLogin(context, obj)) {
            return;
        }

        Intent intent = new Intent(context, clz);
        putExtra(intent, obj);
        context.startActivity(intent);

    }

    /**
     * 有回调的启动 页面
     * <p/>
     * obj[0] = true时可拦截登录
     */
    public static void startActivity(int requst_code, Object context, Class<? extends Activity> clz, Object... obj) {

        if (!(context instanceof Activity || context instanceof BaseFragment)) {
            return;
        }

        if (context instanceof Activity) {
            Activity activity = (Activity) context;

            if (checkLogin(activity, obj)) {
                return;
            }

            Intent intent = new Intent(activity, clz);
            putExtra(intent, obj);
            activity.startActivityForResult(intent, requst_code);

        } else if (context instanceof BaseFragment) {
            BaseFragment fragment = (BaseFragment) context;

            if (checkLogin(fragment.getActivity(), obj)) {
                return;
            }

            Intent intent = new Intent(fragment.getActivity(), clz);
            putExtra(intent, obj);
            fragment.startActivityForResult(intent, requst_code);
        }

    }

    /**
     * 拦截登录
     */
    public static boolean checkLogin(Context context, Object... obj) {
        // 登录

        if (obj.length > 0 && obj[0] instanceof Boolean && (Boolean) obj[0] && !"".equals(DreamApplication.getUser().getToken())) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    /**
     * 绑定参数
     */
    public static void putExtra(Intent intent, Object... obj) {

        int i = 0;
        // 过滤登录
        if (obj.length > 0 && obj[0] instanceof Boolean) {
            i++;
        }

        if (obj.length < 2) {
            return;
        }

        for (int len = obj.length - i; i < len; i += 2) {

            Object key = obj[i];
            if (key instanceof String) {

                String name = (String) key;
                Object value = obj[i + 1];
                if (value instanceof String) {
                    intent.putExtra(name, (String) value);

                } else if (value instanceof Integer) {
                    int v = (Integer) value;
                    intent.putExtra(name, v);

                } else if (value instanceof Long) {
                    long v = (Long) value;
                    intent.putExtra(name, v);

                } else if (value instanceof Double) {
                    double v = (Double) value;
                    intent.putExtra(name, v);

                } else if (value instanceof Serializable) {
                    intent.putExtra(name, (Serializable) value);

                } else if (value instanceof Parcelable) {
                    intent.putExtra(name, (Parcelable) value);

                } else if (value instanceof String[]) {
                    intent.putExtra(name, (String[]) value);

                } else {
                    intent.putExtra(name, value.toString());

                }
            } else {
                continue;
            }

        }
    }

}
