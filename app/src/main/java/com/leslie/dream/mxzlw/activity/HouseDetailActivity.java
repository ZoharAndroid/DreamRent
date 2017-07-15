package com.leslie.dream.mxzlw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseRecyclerViewActivity;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.fragment.HouseDetailContentFragment;
import com.leslie.dream.mxzlw.manager.FragmentManagerUtil;
import com.leslie.dream.mxzlw.model.House;

/**
 * Created by zzh on 2017/7/12.
 *
 * 详情页
 */

public class HouseDetailActivity extends BaseRecyclerViewActivity {

    public static House mHouse;
    private ImageButton ibCollection;//收藏按钮
    private Button btnBookSeeHouse;//预约看房
    private Button btnImmediatelyBook;//马上预约
    private ImageButton mImageButtonBack;//返回

    public HouseDetailActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取传递过来的数据
        mHouse =  (House)getIntent().getExtras().get(Constants.HOUSE_DETAIL_DATA);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_house_detail);

        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化房屋详情页布局控件
     */
    public void initView() {
        ibCollection = (ImageButton) findViewById(R.id.ib_collection);
        btnBookSeeHouse = (Button) findViewById(R.id.btn_book_see_house);
        btnImmediatelyBook = (Button) findViewById(R.id.btn_immediately_book);
        mImageButtonBack = (ImageButton) findViewById(R.id.ib_house_detail_back);

        //加载Fragment布局
        FragmentManagerUtil.setForegroundFragment(getSupportFragmentManager(),new HouseDetailContentFragment(),R.id.fl_detail_content);

    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 初始化事件
     */
    public void initEvent() {
        ibCollection.setOnClickListener(this);
        btnBookSeeHouse.setOnClickListener(this);
        btnImmediatelyBook.setOnClickListener(this);
        mImageButtonBack.setOnClickListener(this);
    }

    /**
     * 按钮点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_book_see_house:
                bookSeeHouse();
                break;
            case R.id.btn_immediately_book:
                immediatelyBook();
                break;
            case R.id.ib_collection:
                collection();
                break;
            case R.id.ib_house_detail_back:
                back();
            default:
                break;
        }
    }

    /**
     * 按钮：预约看房
     */
    public void bookSeeHouse() {
        Intent intent = new Intent(this,BookHouseActivity.class);
        startActivity(intent);
    }

    /**
     * 按钮：立即预约
     */
    public void immediatelyBook() {
        Intent intent = new Intent(this,ImmediatelyBookActivity.class);
        startActivity(intent);
    }

    /**
     * 收藏
     */
    public void collection() {
        Toast.makeText(this,"收藏",Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回
     */
    public void back(){
        finish();
    }
}
