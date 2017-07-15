package com.leslie.dream.mxzlw.model;

import com.leslie.dream.mxzlw.base.BaseModel;

/**
 * Created by zzh on 2017/7/12.
 *
 * 首页广告model
 */

public class BannerHome extends BaseModel {

    private String banner_pic;//广告图片
    private String banner_jumpUrl;//跳转uri
    private String banner_name;//广告名

    public String getBanner_pic() {
        return banner_pic;
    }

    public void setBanner_pic(String banner_pic) {
        this.banner_pic = banner_pic;
    }

    public String getBanner_jumpUrl() {
        return banner_jumpUrl;
    }

    public void setBanner_jumpUrl(String banner_jumpUrl) {
        this.banner_jumpUrl = banner_jumpUrl;
    }

    public String getBanner_name() {
        return banner_name;
    }

    public void setBanner_name(String banner_name) {
        this.banner_name = banner_name;
    }
}
