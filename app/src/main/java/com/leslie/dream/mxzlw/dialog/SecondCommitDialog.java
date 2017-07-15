package com.leslie.dream.mxzlw.dialog;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.util.CommonUtils;


/**
 * 二次确认 dialog
 * <p>
 * Created by dzl on 2017/7/4.
 * <pre/>
 * SecondCommitDialog dialog = new SecondCommitDialog(context,R.style.LoadingDialog);
 * dialog.show();
 */
public class SecondCommitDialog extends BaseDialog {

    public RelativeLayout dialog_root;
    public TextView dialog_tv_title;
    public TextView dialog_tv_content;
    public TextView dialog_tv_cancel;
    public TextView dialog_tv_commit;

    public OnDialogItemClickListener listener;

    public OnDialogItemClickListener getDialogItemClickListener() {
        return listener;
    }

    public void setDialogItemClickListener(OnDialogItemClickListener listener) {
        this.listener = listener;
    }

    public SecondCommitDialog(Context context, int... themeResId) {
        super(context, themeResId.length > 0 ? themeResId[0] : 0);

        init(context);
    }

    protected void init(Context context) {

        View view = CommonUtils.inflateView(context, R.layout.dialog_template_second_commit);

        dialog_root = CommonUtils.fv(R.id.dialog_root, view);
        dialog_tv_title = CommonUtils.fv(R.id.dialog_tv_title, view);
        dialog_tv_content = CommonUtils.fv(R.id.dialog_tv_content, view);
        dialog_tv_cancel = CommonUtils.fv(R.id.dialog_tv_cancel, view);
        dialog_tv_commit = CommonUtils.fv(R.id.dialog_tv_commit, view);

        dialog_tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSecondCommitListener(false);
                }
                self().dismiss();
            }
        });

        dialog_tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSecondCommitListener(true);
                }
                self().dismiss();

            }
        });

        setContentView(view);

    }

    /**
     * 监听
     */
    public interface OnDialogItemClickListener {
        void onSecondCommitListener(boolean is_commit);
    }

    public void setContentText(CharSequence text) {
        CommonUtils.setText(dialog_tv_content, text);
    }
    public void setTitleText(CharSequence text) {
        CommonUtils.setText(dialog_tv_title, text);
        dialog_tv_title.setVisibility(View.VISIBLE);
    }
}
