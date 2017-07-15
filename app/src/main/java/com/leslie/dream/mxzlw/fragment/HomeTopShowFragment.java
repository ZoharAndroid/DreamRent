package com.leslie.dream.mxzlw.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.activity.ShowBannerInfoActivity;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.model.BannerHome;
import com.leslie.dream.mxzlw.widget.HouseTopShowScrollViewPager;

import java.util.ArrayList;


/**
 * Created by zzh on 2017/7/5.
 * <p>
 * Fragment:HOME头部轮播显示Fragment
 */

public class HomeTopShowFragment extends HouseDetailBaseFragment {

    private HouseTopShowScrollViewPager mViewPagerTopShow;


    private Handler mHandler;

   private ArrayList<BannerHome> mTopShowList;//头条广告信息


    public HomeTopShowFragment(){
        mTopShowList = HomeFragment.mBannerList;
    }


    @Override
    public View initFragmentView() {
        //填充布局文件
        View mHouseTopShowView = View.inflate(mActivity, R.layout.fragment_home_top_show, null);

        mViewPagerTopShow = (HouseTopShowScrollViewPager)mHouseTopShowView.findViewById(R.id.vp_home_top_show);

        return mHouseTopShowView;
    }

    @Override
    public void initData() {

        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    int currentItem = mViewPagerTopShow.getCurrentItem();
                    if (currentItem < 3 - 1) {
                        // 如果小于头条新闻的大小，直接加1
                        currentItem++;
                    } else {
                        currentItem = 0;// 如果是最后一个，直接从头开始
                    }
                    mViewPagerTopShow.setCurrentItem(currentItem);

                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }
            };
            mHandler.sendEmptyMessageDelayed(0, 3000);

        }

        mViewPagerTopShow.setAdapter(new HouseTopShowViewAdapter());




    }


    /**
     * ViewPager适配器
     */
    class HouseTopShowViewAdapter extends PagerAdapter {
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView image = new ImageView(mActivity);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(R.drawable.example_home_top_show);
            container.addView(image);
            // 设置头条新闻的触摸监听:如果一直按住头条新闻图片，就不自动进行轮播，一松开就自动进行轮播
            image.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // 清空handler的消息，停止自动轮播
                            mHandler.removeCallbacksAndMessages(null);
                            //点击头条广告事件
                            BannerHome banner = new BannerHome();
                            banner.setBanner_jumpUrl(mTopShowList.get(position).getBanner_jumpUrl());
                            banner.setBanner_name(mTopShowList.get(position).getBanner_name());
                            banner.setBanner_pic(mTopShowList.get(position).getBanner_pic());
                            Intent intent = new Intent(getActivity(),ShowBannerInfoActivity.class);
                            intent.putExtra(Constants.HOME_TOP_BANNER,banner);
                            startActivity(intent);
                            Toast.makeText(getActivity(),mTopShowList.get(position).getBanner_name()+";"+mTopShowList.get(position).getBanner_jumpUrl(),Toast.LENGTH_SHORT).show();
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            mHandler.sendEmptyMessageDelayed(0, 3000);
                            break;
                        case MotionEvent.ACTION_UP:
                            mHandler.sendEmptyMessageDelayed(0, 3000);
                            break;

                        default:
                            break;
                    }
                    return true;
                }
            });


            return image;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

