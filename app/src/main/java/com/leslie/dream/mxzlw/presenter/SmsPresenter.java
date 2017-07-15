package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;


import com.leslie.dream.mxzlw.base.BasePresenter;

import org.json.JSONObject;

/**
 * @author yyx on 2017/7/10.
 */
public class SmsPresenter extends BasePresenter {
    public SmsPresenter(IAuthCodeView view, boolean show_error) {
        super(view, show_error);
    }

    @Override
    protected void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle) {

        String msg = jsonObject.optString("msg");
     ((IAuthCodeView) view).onSuccessSms(url_type, load_type, msg);
    }

    public interface IAuthCodeView extends IBaseView {
        public void onSuccessSms(int url_type, int load_type, String msg);
    }
}
