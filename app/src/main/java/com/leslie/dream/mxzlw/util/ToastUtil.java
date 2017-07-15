package com.leslie.dream.mxzlw.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * toast
 *
 * @Author dzl on 2017/7/5.
 */
public class ToastUtil {
    private static Toast toast;

    public static void show(Context context, CharSequence text) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    text,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void show(Context context, View view) {
        if (toast == null) {
            toast = new Toast(context);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

}
