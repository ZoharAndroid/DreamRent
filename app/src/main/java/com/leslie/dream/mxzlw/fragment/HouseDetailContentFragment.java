package com.leslie.dream.mxzlw.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.activity.HouseDetailActivity;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewFragment;
import com.leslie.dream.mxzlw.config.UrlApi;
import com.leslie.dream.mxzlw.model.Admin;
import com.leslie.dream.mxzlw.model.House;
import com.leslie.dream.mxzlw.model.HouseDetail;
import com.leslie.dream.mxzlw.model.OtherRecommendHouse;
import com.leslie.dream.mxzlw.presenter.HouseDetailPresenter;
import com.leslie.dream.mxzlw.util.ToastUtil;
import com.leslie.dream.mxzlw.widget.CircleImageViewWithBorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zzh on 2017/7/4.
 * <p>
 * Fragment:房屋详情页内容Fragment
 */

public class HouseDetailContentFragment extends BaseRecyclerViewFragment implements HouseDetailPresenter.IHouseDetailView {

    public static Admin mHouseAdmin;//管理人
    public static ArrayList<OtherRecommendHouse> mOtherRecommendList;//其他房屋推荐
    public static HouseDetail mHouseDetailItem;//房屋详情

    private TextView mTextViewHome;//房间数
    private TextView mTextViewSize;//房间面积
    private Button mButtonSeeWhole;//查看全景图
    private CircleImageViewWithBorder mImageViewHeard;//业务员头像
    private TextView mTextViewName;//业务员姓名
    private TextView mTextViewServiceCount;//服务次数
    private TextView mTextViewGrad;//评分
    private ImageButton mImageButtonCall;//业务员电话
    private TextView mTextViewAddress;//地址
    private android.support.v4.app.FragmentTransaction mTransaction;

    private House mHouse; // ListView选中的房屋，从HouseDetailActivity传递过来的数据

    //private HouseDetail mHouseDetail;//解析出来的房屋详情对象

    private HouseDetailPresenter presenter;

    @Override
    public void onNetworkLazyLoad() {

    }

    public HouseDetailContentFragment() {
        //获取数据
        mHouse = ((HouseDetailActivity) getActivity()).mHouse;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (main_layout == null) {
            main_layout = inflateView(R.layout.fragment_house_detail_content, container);
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

    public void initView() {
        mTextViewHome = (TextView) main_layout.findViewById(R.id.tv_house_home_describe);
        mTextViewSize = (TextView) main_layout.findViewById(R.id.tv_house_size);
        mButtonSeeWhole = (Button) main_layout.findViewById(R.id.btn_see_whole_scan);
        mImageViewHeard = (CircleImageViewWithBorder) main_layout.findViewById(R.id.civb_steward_head_icon);
        mTextViewName = (TextView) main_layout.findViewById(R.id.tv_steward_name);
        mTextViewServiceCount = (TextView) main_layout.findViewById(R.id.tv_steward_service_count);
        mTextViewGrad = (TextView) main_layout.findViewById(R.id.tv_steward_grad);
        mImageButtonCall = (ImageButton) main_layout.findViewById(R.id.ib_steward_call_phone);
        mTextViewAddress = (TextView) main_layout.findViewById(R.id.tv_house_detail_content_address);
    }


    public void initData() {
        //初始化Fragment管理器
        android.support.v4.app.FragmentManager mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

    }

    @Override
    public void showLoadingUI(int url_type, int load_type) {

    }

    @Override
    public void hideLoadingUI(int url_type, int load_type, boolean success) {

    }


    @Override
    public String getUrl(int url_type) {
        return UrlApi.HOSUE_DETAIL;
    }

    @Override
    public void onSuccessGetHouseData(int url_type, int load_type, Admin admin, ArrayList<OtherRecommendHouse> otherRecommendHouseList, HouseDetail mHouseDetail, int code) {
        //初始化数据
        mHouseAdmin = admin;
        mOtherRecommendList = otherRecommendHouseList;
        mHouseDetailItem = mHouseDetail;

        //添加房屋头部轮播显示
        mTransaction.replace(R.id.fl_house_detail_content_top_show, new HouseTopShowFragment());
        //房屋特点和价格
        mTransaction.replace(R.id.fl_house_detail_content_character, new HouseCharacterPriceFragment());
        //配套设施
        mTransaction.replace(R.id.fl_house_detail_content_allocation_facilities, new HouseAllocationFacilities());
        //房源描述
        mTransaction.replace(R.id.fl_house_detail_content_house_describe, new HouseDescribeFragment());
        //其他推荐
        mTransaction.replace(R.id.fl_house_detail_content_other_recommend, new OtherHomeRecommendFragment());
        mTransaction.commit();

        //处理自己包含的控件事情
        dealWithOwnFragmentThing();

    }

    @Override
    public Map<String, String> getParams(int url_type, int load_type, Bundle bundle) {
        int houseId = mHouse.getHouse_id();
        Map<String, String> params = new HashMap<>();
        params.put("houseID", String.valueOf(houseId));
        return params;
    }


    /**
     * 初始化网络请求
     */
    public void requData() {
        if (presenter == null) {
            presenter = new HouseDetailPresenter(this, false);
        }
        presenter.reqData(URL_LIST, LOAD_AUTO, false);
    }


    /**
     * 处理本Fragment(HouseDetailContentFragment)的事情：
     */
    private void dealWithOwnFragmentThing() {
        //设置房间类型：几房几厅和面积
        mTextViewHome.setText(mHouseDetailItem.getHouse_rooms() + "室" + mHouseDetailItem.getHouse_halls() + "厅");
        mTextViewSize.setText(mHouseDetailItem.getHouse_acreage() + "m²");
        //查看全景图
        mButtonSeeWhole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "查看全景图", Toast.LENGTH_SHORT).show();
            }
        });

        //设置业务员的一些信息
        mTextViewName.setText(mHouseAdmin.getAdmin_nickname());
        mTextViewServiceCount.setText("服务次数:" + mHouseAdmin.getAdmin_servicenum());
        mTextViewGrad.setText("评分:" + mHouseAdmin.getAdmin_overallrating());
        //拨打业务员电话
        mImageButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adminPhone = mHouseAdmin.getAdmin_phone();
                if (adminPhone.isEmpty()) {
                    ToastUtil.show(getActivity(), "暂未提供手机号码");
                } else {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_DIAL);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setData(Uri.parse("tel:" + mHouseAdmin.getAdmin_phone()));
                        startActivity(intent);
                    }catch (Exception e){
                       final Dialog callAdminDialog =  createDialog(R.layout.dialog_admin_call,R.style.LoadingDialog,true);
                        TextView mTvPhone = (TextView) callAdminDialog.findViewById(R.id.tv_admin_phone);
                        TextView mTvCallCanel = (TextView) callAdminDialog.findViewById(R.id.dialog_tv_call_cancel);
                        TextView mTvAddContact = (TextView) callAdminDialog.findViewById(R.id.dialog_tv_call_add_contacts);
                        mTvPhone.setText(adminPhone);
                        mTvCallCanel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                callAdminDialog.dismiss();
                            }
                        });
                        mTvAddContact.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Uri uri = Uri.parse("content://contacts/people");
                                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                                startActivity(intent);
                            }
                        });
                        callAdminDialog.show();
                    }

                }
            }
        });

        //设置地址
        mTextViewAddress.setText(mHouseDetailItem.getHouse_address());
    }


}
