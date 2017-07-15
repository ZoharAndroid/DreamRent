package com.leslie.dream.mxzlw.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.ZBaseActionBarActivity;
import com.leslie.dream.mxzlw.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zzh on 2017/7/11.
 *
 * Actvity：预约看房
 */

public class BookHouseActivity extends ZBaseActionBarActivity {

    private TextView mTvSetSeeTime;
    private LinearLayout mLlSelectBookTime;
    private EditText mETBookName;
    private EditText mETBookPhone;
    private Button mBtnSendBook;

    private CustomDatePicker datePicker, timePicker;

    private String time;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_see_house);
        initActionbar("预约看房");

        initView();

        //初始化预约时间
        initPicker();

        initEvent();

    }

    public void initEvent(){
        //预约时间点击时间
        mLlSelectBookTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.show(time);
            }
        });

        //发起预约按钮点击事件
        mBtnSendBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookHouseActivity.this,"发起预约", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView(){
        mTvSetSeeTime =(TextView) findViewById(R.id.tv_set_book_time);
        mLlSelectBookTime =(LinearLayout) findViewById(R.id.ll_select_book_time);
        mETBookName = (EditText) findViewById(R.id.et_contact_name);
        mETBookPhone = (EditText) findViewById(R.id.et_contact_phone);
        mBtnSendBook =(Button) findViewById(R.id.btn_send_book_see_house);
    }


    /**
     * 初始化预约时间
     */
    private void initPicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        time = sdf.format(new Date());
        date = time.split(" ")[0];
        //设置当前显示的日期
        //currentDate.setText(date);
        //设置当前显示的时间
        //mTvSetSeeTime.setText(time);

        /**
         * 设置年月日
         */
        datePicker = new CustomDatePicker(this, "请选择日期", new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                mTvSetSeeTime.setText(time.split(" ")[0]);
            }
        }, "2007-01-01 00:00", time);
        datePicker.showSpecificTime(false); //显示时和分
        datePicker.setIsLoop(false);
        datePicker.setDayIsLoop(true);
        datePicker.setMonIsLoop(true);

        timePicker = new CustomDatePicker(this, "请选择时间", new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                mTvSetSeeTime.setText(time);
            }
        }, "2007-01-01 00:00", "2027-12-31 23:59");//"2027-12-31 23:59"
        timePicker.showSpecificTime(true);
        timePicker.setIsLoop(true);
    }

}
