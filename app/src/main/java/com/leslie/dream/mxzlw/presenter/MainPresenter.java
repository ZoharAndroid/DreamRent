package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;

import com.leslie.dream.mxzlw.base.BasePresenter;
import com.leslie.dream.mxzlw.model.BannerHome;
import com.leslie.dream.mxzlw.model.House;
import com.leslie.dream.mxzlw.model.MenuHome;
import com.leslie.dream.mxzlw.util.Util_0;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zzh on 2017/7/12.
 */

public class MainPresenter extends BasePresenter {

    public MainPresenter(IMainView view, boolean show_error) {
        super(view, show_error);

    }

    @Override
    protected void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle) {
        int code = 0;
        try {
            code = jsonObject.getInt("code");
            ArrayList<House> houseList = (ArrayList) Util_0.parseArray(jsonObject, House.class,"houses");
            ArrayList<MenuHome> menuList =(ArrayList) Util_0.parseArray(jsonObject,MenuHome.class,"menus");
            ArrayList<BannerHome> bannerList =(ArrayList) Util_0.parseArray(jsonObject,BannerHome.class,"banners");

            ((IMainView)view).onSuccessGetMainData(url_type,load_type,houseList,menuList,bannerList,code);
        } catch (Exception e) {

        }
    }

    public interface IMainView extends IBaseView {
        public void onSuccessGetMainData(int url_type, int load_type, ArrayList<House> houses, ArrayList<MenuHome> menus ,ArrayList<BannerHome> banners , int code);
    }

    @Override
    public void reqData(int url_type, boolean request_type) {
        super.reqData(url_type, request_type);

    }
}
