package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.leslie.dream.mxzlw.base.BasePresenter;
import com.leslie.dream.mxzlw.model.Admin;
import com.leslie.dream.mxzlw.model.HouseDetail;
import com.leslie.dream.mxzlw.model.OtherRecommendHouse;
import com.leslie.dream.mxzlw.util.Util_0;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zzh on 2017/7/13.
 *
 * 房屋详情页数据解析
 */

public class HouseDetailPresenter extends BasePresenter {

    public HouseDetailPresenter(IHouseDetailView view, boolean show_error) {
        super(view, show_error);

    }

    @Override
    protected void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle) {
        HouseDetail mHouseDetail;
        Admin mAdmin;
        int code = 0;
        try {
            code = jsonObject.getInt("code");
            mAdmin = JSON.parseObject(jsonObject.getJSONObject("admin").toString(), Admin.class);
            ArrayList<OtherRecommendHouse> otherRecommendHouseList =(ArrayList) Util_0.parseArray(jsonObject,OtherRecommendHouse.class,"recommends");
            mHouseDetail = JSON.parseObject(jsonObject.getJSONObject("house").toString(), HouseDetail.class);


            ((IHouseDetailView)view).onSuccessGetHouseData(url_type,load_type,mAdmin,otherRecommendHouseList,mHouseDetail,code);
        } catch (Exception e) {

        }
    }


    public interface IHouseDetailView extends IBaseView {
        public void onSuccessGetHouseData(int url_type, int load_type, Admin mAdmin, ArrayList<OtherRecommendHouse> otherRecommendHouseList,HouseDetail mHouseDetail  , int code);
    }
}
