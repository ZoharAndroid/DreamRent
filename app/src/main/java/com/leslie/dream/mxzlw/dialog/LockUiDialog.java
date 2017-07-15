package com.leslie.dream.mxzlw.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.util.CommonUtils;
import com.leslie.dream.mxzlw.widget.v7.ProgressImageView;


/**
 * 二次确认 dialog
 * <p>
 * Created by dzl on 2017/7/4.
 * <pre/>
 * LockUiDialog dialog = new LockUiDialog(context, R.style.LoadingDialog);
 * dialog.show();
 */
public class LockUiDialog extends BaseDialog {

    public RelativeLayout dialog_root;
    public LinearLayout dialog_ll_content;
    public ProgressImageView dialog_img_load;
    public TextView dialog_tv_title;

    public LockUiDialog(Context context, int... themeResId) {
        super(context, themeResId.length > 0 ? themeResId[0] : 0);

        init(context);
    }

    protected void init(Context context) {

        View view = CommonUtils.inflateView(context, R.layout.dialog_template_lock_ui);

        dialog_root = CommonUtils.fv(R.id.dialog_root, view);
        dialog_ll_content = CommonUtils.fv(R.id.dialog_ll_content, view);
        dialog_tv_title = CommonUtils.fv(R.id.dialog_tv_title, view);
        dialog_img_load = CommonUtils.fv(R.id.dialog_img_load, view);

        setContentView(view);

        setCanceledOnTouchOutside(false);

    }


    public void setContentText(CharSequence text) {
        CommonUtils.setText(dialog_tv_title, text);
    }

}
