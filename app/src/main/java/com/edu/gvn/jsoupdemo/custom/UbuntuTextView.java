package com.edu.gvn.jsoupdemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HuuTho on 9/4/2016.
 */
public class UbuntuTextView extends TextView {

    public UbuntuTextView(Context context) {
        super(context);
        fontText(context);
    }

    public UbuntuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        fontText(context);
    }

    public UbuntuTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        fontText(context);

    }

    private void fontText(Context context){
      //  Typeface ubuntu = Typeface.createFromAsset(context.getAssets(),"font/Ubuntu-L.ttf");
   //     setTypeface(ubuntu);
    }
}
