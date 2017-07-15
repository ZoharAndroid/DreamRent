package com.leslie.dream.mxzlw.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.model.HouseFurniture;

import java.util.ArrayList;

/**
 * Created by zzh on 2017/7/5.
 * <p>
 * Fragment :房屋配置设施Fragment
 */

public class HouseAllocationFacilities extends HouseDetailBaseFragment {

    private View mHouseFacility;
    private LinearLayout mLLRootContainer;

    private ArrayList<HouseFurniture> mHouseFacilityList;//房屋家电、家具配置信息

    public HouseAllocationFacilities(){
        //得到房源配置信息
        mHouseFacilityList = HouseDetailContentFragment.mHouseDetailItem.getHouse_furnitures();
    }

    @Override
    public View initFragmentView() {
        mHouseFacility = View.inflate(mActivity, R.layout.fragment_house_allocation_facilities, null);
        initView();
        return mHouseFacility;
    }


    @Override
    public void initData() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i=0;i<mHouseFacilityList.size();i++) {
            //动态添加布局
            RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.item_house_facility,null);
            ImageView imageview = (ImageView) rl.findViewById(R.id.iv_house_facility_image);
            TextView tvName = (TextView) rl.findViewById(R.id.tv_house_facility_name);
            TextView tvCount = (TextView) rl.findViewById(R.id.tv_house_facility_count);

            //设置设备图片

            //设置设备名
            tvName.setText(mHouseFacilityList.get(i).getHouse_furnitures_name());
            //设置设配数目
            tvCount.setText(mHouseFacilityList.get(i).getHouse_furnitures_count());

            mLLRootContainer.addView(rl);
        }
    }


    /**
     * 初始化View
     */
    public void initView() {
        mLLRootContainer = (LinearLayout) mHouseFacility.findViewById(R.id.ll_house_facility_container);
    }

}
