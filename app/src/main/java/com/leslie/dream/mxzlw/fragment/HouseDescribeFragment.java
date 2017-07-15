package com.leslie.dream.mxzlw.fragment;

import android.view.View;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.model.HouseDetail;


/**
 * Created by zzh on 2017/7/6.
 * <p>
 * Fragment:房源描述fragment
 */

public class HouseDescribeFragment extends HouseDetailBaseFragment {


    private TextView mTextViewDescribe;
    private HouseDetail mHouseDetail;

    public HouseDescribeFragment(){
        mHouseDetail = HouseDetailContentFragment.mHouseDetailItem;
    }

    @Override
    public View initFragmentView() {
        View mHouseDescibe = View.inflate(mActivity, R.layout.fragment_house_describe, null);
        mTextViewDescribe = (TextView) mHouseDescibe.findViewById(R.id.tv_fragment_house_describe);
        return mHouseDescibe;
    }

    @Override
    public void initData() {
        //HouseDetailActivity mHouseDetailActivity = (HouseDetailActivity) getActivity();
        //mHouseDetailData = mHouseDetailActivity.getHouseDetailContentFragment().getHouseDetail();
        // String[] strings =mHouseDetailData.house.house_remarks.split("。");
        //StringBuffer sb = new StringBuffer();
        //for (int i=0;i<strings.length;i++){
        //    sb.append(strings[i]);
        //     if (i==(strings.length-1)){
        //         sb.append("\n");
        //     }else{
        //         sb.append("\n");
        //        sb.append("\n");
        //    }
        //}
        // mTextViewDescribe.setText(sb.toString());

        mTextViewDescribe.setText(mHouseDetail.getHouse_description());
    }
}
