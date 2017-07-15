package com.leslie.dream.mxzlw.model;

import com.leslie.dream.mxzlw.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by zzh on 2017/7/12.
 *
 * 主页Model
 */

public class MainPage extends BaseModel {
    private int code;//返回码：200
    private ArrayList<House> houses; //房屋列表
    private ArrayList<MenuHome> menus;//Tab菜单列表
    private ArrayList<BannerHome> banners;//首页广告列表

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public void setHouses(ArrayList<House> houses) {
        this.houses = houses;
    }

    public ArrayList<MenuHome> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<MenuHome> menus) {
        this.menus = menus;
    }

    public ArrayList<BannerHome> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<BannerHome> banners) {
        this.banners = banners;
    }
}
