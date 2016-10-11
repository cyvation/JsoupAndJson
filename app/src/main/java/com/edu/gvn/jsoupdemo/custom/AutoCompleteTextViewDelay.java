package com.edu.gvn.jsoupdemo.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by hnc on 11/10/2016.
 */

public class AutoCompleteTextViewDelay extends AutoCompleteTextView {

    private static final int WHAT = 101 ;
    private static final int TIME_DELAY = 1000 ;

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
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    private Handler mHandleDelay = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AutoCompleteTextViewDelay.super.performFiltering((CharSequence) msg.obj,msg.arg1);
        }
    };

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        mHandleDelay.removeMessages(WHAT);
        mHandleDelay.sendMessageDelayed(mHandleDelay.obtainMessage(WHAT,text),TIME_DELAY);
    }
}
