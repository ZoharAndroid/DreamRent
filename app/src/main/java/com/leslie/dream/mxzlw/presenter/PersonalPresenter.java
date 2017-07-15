package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.leslie.dream.mxzlw.base.BasePresenter;
import com.leslie.dream.mxzlw.model.User;

import org.json.JSONObject;

/**
 * Created on 2017/7/11.
 */

public class PersonalPresenter extends BasePresenter {
    private static final int AVATAR = 1;
    private static final int NAME = 2;
    private static final int SEX = 3;

    public PersonalPresenter(IPersonalView view, boolean show_error) {
        super(view, show_error);
    }

    @Override
    protected void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle) {

        int success = 0;
        String msg = "";
        String data = "";
        try {
            success = jsonObject.getInt("success");
            if (success == 1){
                User user = JSON.parseObject(jsonObject.getJSONObject("msg").toString(),
                        User.class);
                if (url_type == AVATAR) {
                    //头像
                } else if (url_type == NAME) {

                    data = user.getNickname();
                } else if (url_type == SEX) {

                    data = user.getSex();
                }
            }else {
                msg = jsonObject.getString("msg");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ((IPersonalView) view).onSuccessUser(url_type, load_type, success, msg, data);

    }

    public interface IPersonalView extends IBaseView {
        public void onSuccessUser(int url_type, int load_type, int success, String msg, String data);
    }
}
