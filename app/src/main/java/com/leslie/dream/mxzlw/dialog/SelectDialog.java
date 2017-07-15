package com.leslie.dream.mxzlw.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.util.CommonUtils;
import com.leslie.dream.mxzlw.widget.v7.RecyclerViewWrap;


/**
 * 二次确认 dialog
 * <p>
 * Created by dzl on 2017/7/4.
 * <pre/>
 * SelectDialog dialog = new SelectDialog(context,R.style.LoadingDialog);
 * dialog.show();
 */
public class SelectDialog extends BaseDialog {

    public RelativeLayout dialog_root;
    public RelativeLayout dialog_rl_content;

    public TextView dialog_tv_title;
    public TextView dialog_tv_cancel;

    public RecyclerViewWrap dialog_recyclerview;
    public View dialog_view_placeholder; // 占位

    private boolean cancel = true;

    public SelectDialog(Context context, int... themeResId) {
        super(context, themeResId.length > 0 ? themeResId[0] : 0);

        init(context);
    }

    protected void init(Context context) {

        View view = CommonUtils.inflateView(context, R.layout.dialog_template_select);

        dialog_root = CommonUtils.fv(R.id.dialog_root, view);
        dialog_rl_content = CommonUtils.fv(R.id.dialog_rl_content, view);
        dialog_tv_title = CommonUtils.fv(R.id.dialog_tv_title, view);
        dialog_tv_cancel = CommonUtils.fv(R.id.dialog_tv_cancel, view);
        dialog_view_placeholder = CommonUtils.fv(R.id.dialog_view_placeholder, view);

        dialog_recyclerview = CommonUtils.fv(R.id.dialog_recyclerview, view);

        dialog_recyclerview.setLayoutManager(new LinearLayoutManager(context));

        dialog_tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self().dismiss();
            }
        });

        dialog_view_placeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancel) {
                    self().dismiss();
                }
            }
        });

        setContentView(view);
        setLayoutGravity(this);

    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
        this.cancel = cancel;
    }

    public void setContentText(CharSequence text) {
        CommonUtils.setText(dialog_tv_title, text);
    }

}
