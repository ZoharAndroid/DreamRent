package com.leslie.dream.mxzlw.model;

import com.leslie.dream.mxzlw.base.BaseModel;

/**
 * Created by zzh on 2017/7/12.
 *
 * 首页 TAB 菜单
 */

public class MenuHome extends BaseModel {

    private String menu_pic; // 菜单图片
    private String menu_name;// 类型名

    public String getMenu_pic() {
        return menu_pic;
    }

    public void setMenu_pic(String menu_pic) {
        this.menu_pic = menu_pic;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
}
