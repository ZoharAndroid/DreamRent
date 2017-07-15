package com.leslie.dream.mxzlw.model;

import com.leslie.dream.mxzlw.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by zzh on 2017/7/13.
 *
 * 房屋详情页
 *
 */

public class HouseDetailPage extends BaseModel {
    private int code;
    private Admin admin;
    private ArrayList<OtherRecommendHouse> recommends;
    private ArrayList<HouseDetail> house;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ArrayList<OtherRecommendHouse> getRecommends() {
        return recommends;
    }

    public void setRecommends(ArrayList<OtherRecommendHouse> recommends) {
        this.recommends = recommends;
    }

    public ArrayList<HouseDetail> getHouse() {
        return house;
    }

    public void setHouse(ArrayList<HouseDetail> house) {
        this.house = house;
    }
}
