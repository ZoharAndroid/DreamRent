package com.leslie.dream.mxzlw.presenter;

import android.os.Bundle;


import com.leslie.dream.mxzlw.base.BaseFragment;
import com.leslie.dream.mxzlw.base.BasePresenter;
import com.leslie.dream.mxzlw.model.MyHouse;
import com.leslie.dream.mxzlw.util.Util_0;

import org.json.JSONObject;

import java.util.List;

/**
 * @Author dzl on 2017/7/10.
 */
public class MyHousePresenter extends BasePresenter {
    public static final int LOAD_MORE = BaseFragment.LOAD_MORE;

    public MyHousePresenter(IMyHouseView view, boolean show_error) {
        super(view, show_error);
    }

    @Override
    protected void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle) {

        List<MyHouse> list = Util_0.parseArray(jsonObject, MyHouse.class, "houses");

        ((IMyHouseView) view).onSuccessMyHouse(url_type, load_type, list);
    }

    public interface IMyHouseView extends IBaseView {
        public void onSuccessMyHouse(int url_type, int load_type, List<MyHouse> list);
    }

}
