package com.leslie.dream.mxzlw.widget.v7;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dzl on 16/11/16.
 */

public class AuthCodeTextView extends TextView {

    private long countDownInterval = 1000;
    private long millisInFuture = 60 * countDownInterval;
    private DownTimer timer;

    public AuthCodeTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {

    }

    public void start() {
        cancel();
        timer = new DownTimer(this, millisInFuture, countDownInterval);
        timer.start();
    }


    public void cancel() {
        try {

            if (timer != null) {
                timer.cancel();
            }
        } catch (Exception e) {

        }
    }

    public boolean isStop() {
        if (timer != null) {
            return timer.isStop();
        }
        return true;
    }

    public AuthCodeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AuthCodeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setCountDownTime(long millisInFuture, long... countDownInterval) {

        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval.length > 0 ? countDownInterval[0] : 1000;
    }

    private static class DownTimer extends CountDownTimer {
        private TextView btn;
        private long countDownInterval;
        private CharSequence text;

        boolean isStop = false;

        public DownTimer(TextView btn, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            this.btn = btn;
            this.countDownInterval = countDownInterval;
            btn.setEnabled(false);
            text = btn.getText();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (millisUntilFinished > 0) {
                try {
                    btn.setText("(" + (millisUntilFinished / countDownInterval) + "s)");
                } catch (Exception e) {

                }
            } else {
                onFinish();
            }
        }

        @Override
        public void onFinish() {
            stop();
            try {
                btn.setEnabled(true);
                btn.setText(text);
                btn = null;
            } catch (Exception e) {

            }
        }


        public void stop() {
            try {
                cancel();
            } catch (Exception e) {

            }
            isStop = true;

        }

        public boolean isStop() {
            return isStop;
        }
    }


}
