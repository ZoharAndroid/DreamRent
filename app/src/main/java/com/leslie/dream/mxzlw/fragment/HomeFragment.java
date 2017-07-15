package com.leslie.dream.mxzlw.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewFragment;
import com.leslie.dream.mxzlw.config.UrlApi;
import com.leslie.dream.mxzlw.manager.FragmentManagerUtil;
import com.leslie.dream.mxzlw.model.BannerHome;
import com.leslie.dream.mxzlw.model.House;
import com.leslie.dream.mxzlw.model.MenuHome;
import com.leslie.dream.mxzlw.presenter.MainPresenter;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends BaseRecyclerViewFragment implements MainPresenter.IMainView {

    private static final int REQUEST_CODE_PICK_CITY = 0;

    private RadioGroup mRadioGroupSelect;

    public static ArrayList<House> mHouseDataList;//房屋列表
    public static ArrayList<MenuHome> mMenusList;//首页tab菜单
    public static ArrayList<BannerHome> mBannerList;//头条广告

    private MainPresenter presenter;
    private HomeRecommendFragment homeRecommendFragment;
    private FragmentTransaction transaction;


    /*UI组件*/
    private EditText et_search;
    private ImageView iv_search;
    private LinearLayout mLLHomeRoot;
    private TextView mTextViewCity;


    @Override
    public void onNetworkLazyLoad() {

    }


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (main_layout == null) {
            main_layout = inflateView(R.layout.fragment_home, container);
            initView();
            requData();
        }
        return main_layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();

        initEvent();
    }

    public void initView() {
        mLLHomeRoot = (LinearLayout) main_layout.findViewById(R.id.ll_home_root);
        mRadioGroupSelect = (RadioGroup) main_layout.findViewById(R.id.rg_home_tab_select_group);
        et_search = (EditText) main_layout.findViewById(R.id.et_search);
        iv_search = (ImageView) main_layout.findViewById(R.id.iv_search);
        mTextViewCity = (TextView) main_layout.findViewById(R.id.tv_home_city_name);
    }


    public void initEvent() {

        mTextViewCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动
                startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);

            }
        });

        mLLHomeRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mLLHomeRoot.setFocusable(true);
                mLLHomeRoot.setFocusableInTouchMode(true);
                mLLHomeRoot.requestFocus();
                HideKeyboard(et_search);
                return false;
            }
        });

        mRadioGroupSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home_tab_recommend:
                        //房屋推荐
                        FragmentManagerUtil.setForegroundFragment(getFragmentManager(), new HomeRecommendFragment(), R.id.fl_home_recommend_content);
                        break;
                    case R.id.rb_home_tab_rent:
                        //托管出租
                        FragmentManagerUtil.setForegroundFragment(getFragmentManager(), new HomeTrustRentFragment(), R.id.fl_home_recommend_content);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                mTextViewCity.setText(city);
            }
        }
    }

    @Override
    public void showLoadingUI(int url_type, int load_type) {

    }

    @Override
    public void hideLoadingUI(int url_type, int load_type, boolean success) {

    }

    @Override
    public String getUrl(int url_type) {
        return UrlApi.MAIN_PAGER;
    }

    @Override
    public Map<String, String> getParams(int url_type, int load_type, Bundle bundle) {
        return null;
    }


    @Override
    public void onSuccessGetMainData(int url_type, int load_type, ArrayList<House> houses, ArrayList<MenuHome> menus, ArrayList<BannerHome> banners, int code) {
        if (code == 200) {
            mHouseDataList = houses;
            mMenusList = menus;
            mBannerList = banners;


            FragmentManager manager = getFragmentManager();
            transaction = manager.beginTransaction();
            //房屋推荐
            homeRecommendFragment = new HomeRecommendFragment();
            transaction.replace(R.id.fl_home_recommend_content, homeRecommendFragment);
            //首页菜单
            transaction.replace(R.id.fl_home_menu, new HomeMenuFragment());
            //首页图片轮播
            transaction.replace(R.id.fl_home_top_show, new HomeTopShowFragment());
            transaction.commit();
        }
    }

    /**
     * 初始化网络请求
     */
    public void requData() {
        if (presenter == null) {
            presenter = new MainPresenter(this, false);
        }
        presenter.reqData(URL_LIST, LOAD_AUTO, false);
    }


    /*初始化搜索框*/
    private void initData() {
        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {
                    //隐藏软件盘
                    HideKeyboard(et_search);
                }
            }
        });

        // 搜索框的键盘搜索键
        // 点击回调
        et_search.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键
            // 修改回车键功能
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 隐藏键盘，这里getCurrentFocus()需要传入Activity对象，如果实际不需要的话就不用隐藏键盘了，免得传入Activity对象，这里就先不实现了
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                    //根据输入的内容模糊查询商品，并跳转到另一个界面，这个需要根据需求实现
                    Toast.makeText(context, et_search.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });


        //点击搜索按钮后的事件
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏软键盘
                HideKeyboard(et_search);

                //根据输入的内容模糊查询商品，并跳转到另一个界面，这个根据需求实现
                Toast.makeText(context, et_search.getText().toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * 隐藏软件键盘
     *
     * @param v
     */
    public void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }

    }


}
