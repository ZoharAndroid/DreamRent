package com.leslie.dream.mxzlw.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.model.HouseDetail;
import com.leslie.dream.mxzlw.model.HouseDetailPics;
import com.leslie.dream.mxzlw.widget.HouseTopShowScrollViewPager;

import java.util.ArrayList;


/**
 * Created by zzh on 2017/7/5.
 * <p>
 * Fragment:房屋头部轮播显示Fragment
 */

public class HouseTopShowFragment extends HouseDetailBaseFragment {

    private HouseTopShowScrollViewPager mViewPagerTopShow;
    private TextView mTextViewReleaseData;
    private TextView mTextViewCollectionCount;
    private TextView mTextViewExploreCount;

    private Handler mHandler;

    private HouseDetail mHouseDetail;
    private ArrayList<HouseDetailPics> mHouseDetailPicsList;


    public HouseTopShowFragment(){
        mHouseDetail = HouseDetailContentFragment.mHouseDetailItem;
        mHouseDetailPicsList =mHouseDetail.getHouse_pics();
    }


    @Override
    public View initFragmentView() {
        //填充布局文件
        View mHouseTopShowView = View.inflate(mActivity, R.layout.fragment_house_top_show, null);

        mViewPagerTopShow = (HouseTopShowScrollViewPager)mHouseTopShowView.findViewById(R.id.vp_house_detail_top_show);
        mTextViewCollectionCount = (TextView) mHouseTopShowView.findViewById(R.id.tv_house_top_show_collection_count);
        mTextViewExploreCount = (TextView) mHouseTopShowView.findViewById(R.id.tv_house_top_show_explore_count);
        mTextViewReleaseData = (TextView) mHouseTopShowView.findViewById(R.id.tv_house_top_show_release_date);

        return mHouseTopShowView;
    }

    @Override
    public void initData() {

        //房屋浏览数目
        mTextViewExploreCount.setText("浏览人数:"+mHouseDetail.getHouse_browsenum());
        //房屋发布时间
        mTextViewReleaseData.setText(mHouseDetail.getHouse_releasetime()+"发");
        //房屋收藏数目
        mTextViewCollectionCount.setText("收藏数:"+mHouseDetail.getHouse_collectionnum());


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
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView image = new ImageView(mActivity);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(R.drawable.top_show_view_default_image);
            container.addView(image);
            // 设置头条新闻的触摸监听:如果一直按住头条新闻图片，就不自动进行轮播，一松开就自动进行轮播
            image.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // 清空handler的消息，停止自动轮播
                            mHandler.removeCallbacksAndMessages(null);
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

