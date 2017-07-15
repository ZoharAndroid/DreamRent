package com.leslie.dream.mxzlw.adapter;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseAdapter;
import com.leslie.dream.mxzlw.model.MyHouse;

import java.util.List;

/**
 * @Author dzl on 2017/7/7.
 */
public class MyHouseAdapter extends BaseAdapter<MyHouse> {

    public static final int VIEW_TYPE_DATA = 0; // 数据
    public static final int VIEW_TYPE_IMG = 1;  // 图片格式

    public MyHouseAdapter(Activity context, List<MyHouse> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getView_type_home() == 1 ? VIEW_TYPE_IMG : VIEW_TYPE_DATA;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DataViewHoler(inflateView(R.layout.item_my_huose, parent));


    }


    class DataViewHoler extends BaseViewHolder {

        ImageView myhouse_item_img;

        TextView myhouse_item_name;
        TextView myhouse_item_address;
        TextView myhouse_item_rent;



        public DataViewHoler(View itemView) {
            super(itemView);

            myhouse_item_img = fv(R.id.myhouse_item_img);
            myhouse_item_name = fv(R.id.myhouse_item_name);
            myhouse_item_address = fv(R.id.myhouse_item_address);
            myhouse_item_rent = fv(R.id.myhouse_item_rent);


        }
        @Override
        public void onBindData(MyHouse house, int position) {
            setText(myhouse_item_name, "房名");
            setText(myhouse_item_address, "地址");
            setText(myhouse_item_rent, "租金");


            loadImage(myhouse_item_img, "图片url",R.drawable.context);//默认图片

        }
    }
}
