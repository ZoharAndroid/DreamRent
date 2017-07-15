package com.leslie.dream.mxzlw.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;


/**
 * @Author dzl on 2017/7/4.
 */
public class BaseActionbarActivity extends BaseActivity {

    protected LinearLayout actionbar_root;

    protected RelativeLayout actionbar_placeholder;
    protected RelativeLayout actionbar_back_rl;     // back
    protected RelativeLayout actionbar_content;
    protected RelativeLayout actionbar_menu_rl;

    protected TextView actionbar_title; // title

    protected ImageView actionbar_back_img;
    protected ImageView actionbar_menu_img;
    protected ImageView actionbar_menu_img_hint;

    protected TextView actionbar_menu_tv;
    protected TextView actionbar_menu_tv_left;
    protected TextView actionbar_menu_tv_number;


    /**
     * 初始化actionbar
     */
    protected void initActionbar(CharSequence title) {

        actionbar_back_img = fv(R.id.actionbar_back_img);
        actionbar_back_rl = fv(R.id.actionbar_back_rl);

        actionbar_menu_img = fv(R.id.actionbar_menu_img);
        actionbar_menu_img_hint = fv(R.id.actionbar_menu_img_hint);
        actionbar_menu_rl = fv(R.id.actionbar_menu_rl);
        actionbar_menu_tv = fv(R.id.actionbar_menu_tv);
        actionbar_menu_tv_left = fv(R.id.actionbar_menu_tv_left);
        actionbar_menu_tv_number = fv(R.id.actionbar_menu_tv_number);
        actionbar_placeholder = fv(R.id.actionbar_placeholder);

        actionbar_root = fv(R.id.actionbar_root);
        actionbar_content = fv(R.id.actionbar_content);
        actionbar_title = fv(R.id.actionbar_title);

        setOnClickListener(actionbar_back_rl);
        //setOnClickListener(actionbar_menu_rl);
        setOnClickListener(actionbar_menu_tv);
        setOnClickListener(actionbar_menu_tv_left);

        setText(actionbar_title, title);

        if (loadview_ll == null){
            initLoadView();
        }

    }

    /**
     * 设置标题
     */
    protected void setActionbarTitle(CharSequence text) {
        setText(actionbar_title, text);
    }

    /**
     * 设置右菜单文本
     */
    protected void setMenuRightText(CharSequence text) {
        setText(actionbar_menu_tv, text);
        setViewVisible(actionbar_menu_tv, true);
        setViewVisible(actionbar_menu_rl, true);
    }

    /**
     * 设置右菜单的左边文本
     */
    protected void setMenuLittleRightText(CharSequence text) {
        setText(actionbar_menu_tv_left, text);
        setViewVisible(actionbar_menu_tv_left, true);
        setViewVisible(actionbar_menu_rl, true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_back_rl:
                finish();
                break;
            case R.id.actionbar_menu_tv:
                onActionbarRightClick();
                break;
            case R.id.actionbar_menu_tv_left:
                onActionbarLittleRightClick();
                break;
        }
    }

    /**
     * actionbar 右菜单 左边点击监听
     */
    public void onActionbarLittleRightClick() {

    }

    /**
     * actionbar 右菜单点击监听
     */
    public void onActionbarRightClick() {

    }

    public void onActionbarLeftClick() {
        finish();
    }

}
