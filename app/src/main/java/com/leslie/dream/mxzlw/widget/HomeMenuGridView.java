package com.leslie.dream.mxzlw.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by zzh on 2017/7/7.
 * <p>
 * 自定义ListView
 */

public class HomeMenuGridView extends GridView {

    public HomeMenuGridView(Context context) {
        super(context);
    }

    public HomeMenuGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeMenuGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }


}
