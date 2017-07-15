package com.leslie.dream.mxzlw.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.model.HouseDetail;
import com.leslie.dream.mxzlw.util.DisplayUtils;


/**
 * Created by zzh on 2017/7/5.
 * <p>
 * Fragment:房屋标题、特点和价格Fragment
 */

public class HouseCharacterPriceFragment extends HouseDetailBaseFragment {

    public static final String TAG = "HouseCharacterPrice";

    private TextView mTextViewHouseName;//房屋名称
    private LinearLayout mLlHouseCharacterOne;//房屋特点１
    private LinearLayout mLlHouseCharacterTwo;//房屋特点２
    private LinearLayout mLlHouseCharacterThree;//房屋特点3
    private TextView mTextViewPrice;//价格

    private HouseDetail mHouseDetailData;

    public HouseCharacterPriceFragment() {
        mHouseDetailData = HouseDetailContentFragment.mHouseDetailItem;
    }

    @Override
    public View initFragmentView() {
        View mViewCharacterPrice = View.inflate(mActivity, R.layout.fragment_house_character_price, null);
        mTextViewHouseName = (TextView) mViewCharacterPrice.findViewById(R.id.tv_house_character_price_title);
        mLlHouseCharacterOne = (LinearLayout) mViewCharacterPrice.findViewById(R.id.ll_house_character_include_one);
        mLlHouseCharacterTwo = (LinearLayout) mViewCharacterPrice.findViewById(R.id.ll_house_character_include_two);
        mLlHouseCharacterThree = (LinearLayout) mViewCharacterPrice.findViewById(R.id.ll_house_character_include_three);
        mTextViewPrice = (TextView) mViewCharacterPrice.findViewById(R.id.tv_house_price);
        return mViewCharacterPrice;
    }

    @Override
    public void initData() {
        String houseParams = mHouseDetailData.getHouse_params();
        if (!houseParams.isEmpty()) {
            String[] houseParam = houseParams.split(",");
            for (int i = 0; i < houseParam.length; i++) {
                TextView textView = new TextView(mActivity);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setPadding(DisplayUtils.dp2px(mActivity, 5), DisplayUtils.dp2px(mActivity, 3), DisplayUtils.dp2px(mActivity, 5), DisplayUtils.dp2px(mActivity, 3));
                textView.setLayoutParams(params);
                textView.setText(houseParam[i]);
                //textView.setBackgroundColor(Color.GRAY);
                textView.setBackgroundResource(R.drawable.text_house_tab_style);
                if (i < 3) {
                    if (i != 0) {
                        params.leftMargin = DisplayUtils.dp2px(mActivity, 3);
                    }
                    mLlHouseCharacterOne.addView(textView);
                } else if (i < 6) {
                    if (i != 3) {
                        params.leftMargin = DisplayUtils.dp2px(mActivity, 3);
                    }
                    mLlHouseCharacterTwo.addView(textView);
                } else {
                    if (i != 6) {
                        params.leftMargin = DisplayUtils.dp2px(mActivity, 3);
                    }
                    mLlHouseCharacterThree.addView(textView);
                }

            }
        }
        mTextViewHouseName.setText(mHouseDetailData.getHouse_title());
        mTextViewPrice.setText("￥" + mHouseDetailData.getHouse_rent() + "/月");
    }
}
