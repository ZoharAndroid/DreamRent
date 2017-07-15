package com.leslie.dream.mxzlw.widget.v7;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.util.CommonUtils;


/**
 * @Author dzl on 2017/7/5.
 */
public abstract class BaseRelativeLayout extends RelativeLayout {

    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    public BaseRelativeLayout(Context context) {
        this(context, null);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected abstract void init(Context context);

    public void setText(TextView view, CharSequence text) {
        CommonUtils.setText(view, text);
    }

    /**
     * 设置view 可见性
     */
    public void setViewVisible(View view, boolean... isVisible) {
        CommonUtils.setViewVisible(view, isVisible);
    }
}
