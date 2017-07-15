package com.leslie.dream.mxzlw.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * fragment 管理工具
 *
 * @Author 2017/6/30.
 */
public class FragmentManagerUtil {

    /**
     * 切换 fragment
     */
    public static void setForegroundFragment(FragmentManager manager, Fragment fragment, int resId) {
        // 添加新的Fragment
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(resId, fragment);// 替换Fragment
        ft.commitAllowingStateLoss();
    }

    public static void setForegroundFragment(FragmentManager manager, Fragment fragment, int resId,String Tag) {
        // 添加新的Fragment
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(resId, fragment,Tag);// 替换Fragment
        ft.commitAllowingStateLoss();
    }

}
