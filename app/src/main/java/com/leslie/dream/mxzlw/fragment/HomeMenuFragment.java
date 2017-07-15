package com.leslie.dream.mxzlw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewFragment;
import com.leslie.dream.mxzlw.model.MenuHome;
import com.leslie.dream.mxzlw.widget.HomeMenuGridView;

import java.util.ArrayList;

/**
 * Created by zzh on 2017/7/14.
 * <p>
 * 主页的8个菜单
 */

public class HomeMenuFragment extends BaseRecyclerViewFragment {

    private ArrayList<MenuHome> mMenusList;//首页tab菜单

    private HomeMenuGridView mGradViewMenu;

    private int[] imageIds = new int[]{R.drawable.menu0,R.drawable.menu1,R.drawable.menu_2,R.drawable.menu3,R.drawable.menu4,R.drawable.menu5,R.drawable.menu6,R.drawable.menu7};

    @Override
    public void onNetworkLazyLoad() {

    }

    public HomeMenuFragment() {
        mMenusList = HomeFragment.mMenusList;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (main_layout == null) {
            main_layout = inflateView(R.layout.fragment_home_menu, container);
            initView();
        }
        return main_layout;
    }


    public void initData() {
        HomeMenuAdapter mAdapter = new HomeMenuAdapter();
        mGradViewMenu.setAdapter(mAdapter);
    }


    public void initEvent() {
        //每个item的点击效果
        mGradViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"点击Menu"+mMenusList.get(position).getMenu_name(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void initView() {
        mGradViewMenu = (HomeMenuGridView) main_layout.findViewById(R.id.gv_home_menu);
    }


    /**
     * GridView适配器
     */
    class HomeMenuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mMenusList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler hodler;
            if (convertView == null) {
                hodler = new ViewHodler();
                convertView = View.inflate(getActivity(), R.layout.item_home_menu, null);
                hodler.iv = (ImageView) convertView.findViewById(R.id.iv_menu_image);
                hodler.tv = (TextView) convertView.findViewById(R.id.tv_menu_title);
                convertView.setTag(hodler);
            } else {
                hodler = (ViewHodler) convertView.getTag();
            }

            //设置图片
            hodler.iv.setImageResource(imageIds[position]);

            //设置标题
            hodler.tv.setText(getItem(position).getMenu_name());


            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public MenuHome getItem(int position) {
            return mMenusList.get(position);
        }
    }


    class ViewHodler {
        ImageView iv;
        TextView tv;

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
}
