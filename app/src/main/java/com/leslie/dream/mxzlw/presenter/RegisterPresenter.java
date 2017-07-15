package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.leslie.dream.mxzlw.base.BasePresenter;
import com.leslie.dream.mxzlw.model.User;

import org.json.JSONObject;

/**
 * @author yyx on 2017/7/10.
 */
public class RegisterPresenter extends BasePresenter {
    public RegisterPresenter(IRegisterView view, boolean show_error) {
        super(view, show_error);
    }

    @Override
    protected void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle) {


        User user = null;
        int success = 0;
        String msg = "";
        try {
            success = jsonObject.getInt("success");
            if (success == 1){
                user = JSON.parseObject(jsonObject.getJSONObject("msg").toString(), User.class);
            }else {
                msg = jsonObject.getString("msg");
            }

        } catch (Exception e) {

        }


        ((IRegisterView) view).onSuccessRegister(url_type, load_type, user, success, msg);
    }

    public interface IRegisterView extends IBaseView {
        public void onSuccessRegister(int url_type, int load_type, User user, int success,String msg);
    }
}
