package com.leslie.dream.mxzlw.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.activity.HouseDetailActivity;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewFragment;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.model.House;
import com.leslie.dream.mxzlw.model.OtherRecommendHouse;

import java.util.ArrayList;

/**
 * Created by zzh on 2017/7/10.
 *
 * 详情页其他推荐房屋列表
 *
 */

public class OtherHomeRecommendFragment extends BaseRecyclerViewFragment {

    private ListView mLVHomeHouse;

    private ArrayList<OtherRecommendHouse> mOtherHouseList;


    public OtherHomeRecommendFragment() {
        mOtherHouseList = HouseDetailContentFragment.mOtherRecommendList;
    }

    @Override
    public void onNetworkLazyLoad() {
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (main_layout == null) {
            main_layout = inflateView(R.layout.fragment_home_recommend, container);
            initView();
        }
        return main_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        initEvent();
    }

    public void initView() {
        mLVHomeHouse = (ListView) main_layout.findViewById(R.id.lv_home_recommend_list);
    }

    public void initData() {
        HomeHouseRecommendAdapter adapter = new HomeHouseRecommendAdapter();
        mLVHomeHouse.setAdapter(adapter);
    }

    public void initEvent() {
        mLVHomeHouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OtherRecommendHouse otherHouse;
                otherHouse = mOtherHouseList.get(position);
                House house = new House();
                house.setHouse_title(otherHouse.getRecommends_title());
                house.setHouse_type(otherHouse.getRecommends_type());
                house.setHouse_pic(otherHouse.getRecommends_pic());
                house.setHouse_rent(otherHouse.getRecommends_rent());
                house.setHouse_intro(otherHouse.getRecommends_intro());
                house.setHouse_id(otherHouse.getRecommends_id());
                Intent intent = new Intent(getActivity(), HouseDetailActivity.class);
                intent.putExtra(Constants.HOUSE_DETAIL_DATA,house);
                startActivity(intent);

            }
        });
    }


    /**
     * ListView的adapter
     */
    class HomeHouseRecommendAdapter extends BaseAdapter {
        @Override
        public OtherRecommendHouse getItem(int position) {
            return mOtherHouseList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public int getCount() {
            return mOtherHouseList.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                view = View.inflate(getActivity(), R.layout.item_other_remcomment_house, null);
                holder.tvType = (TextView) view.findViewById(R.id.tv_house_item_type);
                holder.ivPic = (ImageView) view.findViewById(R.id.iv_house_item_image);
                holder.tvTitle = (TextView) view.findViewById(R.id.tv_house_item_title);
                holder.tvRemarks = (TextView) view.findViewById(R.id.tv_house_item_remark);
                holder.tvPrice = (TextView) view.findViewById(R.id.tv_house_item_price);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.ivPic.setImageResource(R.drawable.top_show_view_default_image);
            holder.tvPrice.setText("￥" + getItem(i).getRecommends_rent() + "/月");
            holder.tvTitle.setText("【"+getItem(i).getRecommends_title()+"】");
            holder.tvRemarks.setText(getItem(i).getRecommends_intro());
            holder.tvType.setText(getItem(i).getRecommends_type());

            return view;
        }
    }

    class ViewHolder {
        ImageView ivPic;
        TextView tvType;
        TextView tvTitle;
        TextView tvRemarks;
        TextView tvPrice;
    }


}
