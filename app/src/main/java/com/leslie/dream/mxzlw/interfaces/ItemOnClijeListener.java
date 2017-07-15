package com.leslie.dream.mxzlw.interfaces;

import android.view.View;

/**
 * Created by hx on 2017/7/4.
 */
public interface ItemOnClijeListener {
    void onAddClick(View itemView, int position);
    void onMinusClick(View itemView, int position);
    void OndeletClick(int position);
}
