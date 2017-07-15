package com.leslie.dream.mxzlw.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @Author dzl on 2017/7/4.
 */
public class BaseDialog extends Dialog {

    public BaseDialog(Context context, int... themeResId) {
        super(context, themeResId.length > 0 ? themeResId[0] : 0);
    }

    protected Dialog self() {
        return this;
    }

    /**
     * 设置dialog 置底
     */
    public static void setLayoutGravity(Dialog dialog) {
        setLayoutGravity(dialog, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
    }

    /**
     * 设置 位置、透明度
     */
    public static void setLayoutGravity(Dialog dialog, int gravity, float... dimAmount) {

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = gravity;

        if (dimAmount.length > 0) {
            params.dimAmount = dimAmount[0];
        }

        window.setAttributes(params);

    }
}
