package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;

import com.leslie.dream.mxzlw.base.BaseFragment;
import com.leslie.dream.mxzlw.base.BasePresenter;
import com.leslie.dream.mxzlw.model.Bill;
import com.leslie.dream.mxzlw.model.MyHouse;
import com.leslie.dream.mxzlw.util.Util_0;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @Author dzl on 2017/7/10.
 */
public class AccountBillPresenter extends BasePresenter {
    public static final int LOAD_MORE = BaseFragment.LOAD_MORE;

    public AccountBillPresenter(IBillView view, boolean show_error) {
        super(view, show_error);
    }

    @Override
    protected void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle) {
        int success = 0;
        String msg = "";
        List<Bill> list = null;
        try {
            success =jsonObject.getInt("success");
            msg = jsonObject.getString("msg");
            if (success==1){
                list = Util_0.parseArray(jsonObject, Bill.class, "bill");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ((IBillView) view).onSuccessBill(url_type, load_type, success, msg, list);
    }

    public interface IBillView extends IBaseView {
        public void onSuccessBill(int url_type, int load_type, int succsee, String msg, List<Bill> list);
    }

}
