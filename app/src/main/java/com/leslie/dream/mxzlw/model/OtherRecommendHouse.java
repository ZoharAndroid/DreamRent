package com.leslie.dream.mxzlw.model;

import com.leslie.dream.mxzlw.base.BaseModel;

/**
 * Created by zzh on 2017/7/13.
 *
 * 房屋详情页：其他推荐房屋
 */

public class OtherRecommendHouse extends BaseModel {
    private String recommends_title;
    private String recommends_pic;
    private String recommends_rent;
    private String recommends_intro;
    private int recommends_id;
    private String recommends_type;

    public String getRecommends_title() {
        return recommends_title;
    }

    public void setRecommends_title(String recommends_title) {
        this.recommends_title = recommends_title;
    }

    public String getRecommends_pic() {
        return recommends_pic;
    }

    public void setRecommends_pic(String recommends_pic) {
        this.recommends_pic = recommends_pic;
    }

    public String getRecommends_rent() {
        return recommends_rent;
    }

    public void setRecommends_rent(String recommends_rent) {
        this.recommends_rent = recommends_rent;
    }

    public String getRecommends_intro() {
        return recommends_intro;
    }

    public void setRecommends_intro(String recommends_intro) {
        this.recommends_intro = recommends_intro;
    }

    public int getRecommends_id() {
        return recommends_id;
    }

    public void setRecommends_id(int recommends_id) {
        this.recommends_id = recommends_id;
    }

    public String getRecommends_type() {
        return recommends_type;
    }

    public void setRecommends_type(String recommends_type) {
        this.recommends_type = recommends_type;
    }
}
