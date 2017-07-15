package com.leslie.dream.mxzlw.model;

import com.leslie.dream.mxzlw.base.BaseModel;

/**
 * Created by zzh on 2017/7/12.
 *
 * 房屋model
 */

public class House extends BaseModel {

    private int house_id;//id
    private String house_intro;//介绍
    private String house_rent;//租金：1024
    private String house_pic;//图片uri
    private String house_type;//类型：办公室
    private String house_title;//名字：梦想创业城3006室

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getHouse_intro() {
        return house_intro;
    }

    public void setHouse_intro(String house_intro) {
        this.house_intro = house_intro;
    }

    public String getHouse_rent() {
        return house_rent;
    }

    public void setHouse_rent(String house_rent) {
        this.house_rent = house_rent;
    }

    public String getHouse_pic() {
        return house_pic;
    }

    public void setHouse_pic(String house_pic) {
        this.house_pic = house_pic;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getHouse_title() {
        return house_title;
    }

    public void setHouse_title(String house_title) {
        this.house_title = house_title;
    }

}
