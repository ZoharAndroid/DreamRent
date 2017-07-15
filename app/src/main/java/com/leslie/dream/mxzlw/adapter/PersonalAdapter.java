package com.leslie.dream.mxzlw.adapter;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseAdapter;

import java.util.List;

/**
 * @Author yyx on 2017/7/4.
 */
public class PersonalAdapter extends BaseAdapter<String> {

    private int i; //标识
    private static final int AVATAR = 1;
    private static final int SEX = 2;

    public PersonalAdapter(Activity context, List<String> datas, int i) {
        super(context, datas);
        this.i = i;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (i == AVATAR) { // 1为修改头像
            return new AvatarViewHoler(inflateView(R.layout.item_text_layout, parent));
        } else if (i == SEX) { // 2 为修改性别
            return new SexViewHoler(inflateView(R.layout.item_text_layout, parent));
        }

        return null;
    }

    class AvatarViewHoler extends BaseViewHolder {

        private TextView tv_text;

        public AvatarViewHoler(View itemView) {
            super(itemView);
            tv_text = fv(R.id.tv_text);
        }

        @Override
        public void onBindData(String s, int position) {
            setText(tv_text, datas.get(position).toString());
        }

    }

    class SexViewHoler extends BaseViewHolder {

        private TextView tv_text;

        public SexViewHoler(View itemView) {
            super(itemView);
            tv_text = fv(R.id.tv_text);
        }

        @Override
        public void onBindData(String s, int position) {
            setText(tv_text, datas.get(position).toString());
        }

    }

}
