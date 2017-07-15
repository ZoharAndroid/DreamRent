package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.leslie.dream.mxzlw.base.BasePresenter;
import com.leslie.dream.mxzlw.model.User;

import org.json.JSONObject;

/**
 * @author on 2017/7/10.
 */
public class LoginPresenter extends BasePresenter {

    public LoginPresenter(ILoginView view, boolean show_error) {
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

        ((ILoginView) view).onSuccessLogin(url_type, load_type, user,success,msg);
    }

    public interface ILoginView extends IBaseView {
        public void onSuccessLogin(int url_type, int load_type, User user, int success,String msg);
    }
}
