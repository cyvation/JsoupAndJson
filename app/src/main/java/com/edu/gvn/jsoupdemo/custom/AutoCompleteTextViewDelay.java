package com.edu.gvn.jsoupdemo.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by HuuTho on 9/13/2016.
 */
public class AutoCompleteTextViewDelay extends AutoCompleteTextView {

    public static int TIME_DELAY_SEND = 750;
    private int WHAT_DELAY = 0;

    private Handler hDelay = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            AutoCompleteTextViewDelay.this.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };

    public AutoCompleteTextViewDelay(Context context) {
        super(context);
    }

    public AutoCompleteTextViewDelay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCompleteTextViewDelay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        hDelay.removeMessages(WHAT_DELAY);
        hDelay.sendMessageDelayed(hDelay.obtainMessage(WHAT_DELAY,text),TIME_DELAY_SEND);
    }
}
