package com.leslie.dream.mxzlw.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.base.BaseActionbarActivity;
import com.leslie.dream.mxzlw.config.DreamApplication;
import com.leslie.dream.mxzlw.model.User;
import com.leslie.dream.mxzlw.util.QRCodeUtil;

/**
 * Created by Quinn on 2017/7/13.
 */

public class MyShareActivity extends BaseActionbarActivity {

    private ImageView qrCode;
    private TextView share_number_tv;
    private TextView share_tome_tv;
    private TextView share_team_tv;
    private TextView share_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_share);
        initActionbar("我的分享");
        inview();
        User user = DreamApplication.getUser();
        String phone = user.getUsername();
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.portrait);
        qrCode.setImageBitmap(QRCodeUtil.createImage(phone, 200, 200, logo));
    }

    private void inview() {
        qrCode = fv(R.id.my_share_img);
        share_number_tv = fv(R.id.share_number_tv);
        share_tome_tv = fv(R.id.share_tome_tv);
        share_team_tv = fv(R.id.share_team_tv);
        share_tv = fv(R.id.share_tv);

        setOnClickListener(share_tv);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.share_tv:
                //分享
                break;
        }
    }


}
