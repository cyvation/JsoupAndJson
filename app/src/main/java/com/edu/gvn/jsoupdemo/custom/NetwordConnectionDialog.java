package com.edu.gvn.jsoupdemo.custom;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.edu.gvn.jsoupdemo.R;

/**
 * Created by HuuTho on 10/8/2016.
 */

public class NetwordConnectionDialog extends Dialog implements View.OnClickListener {

    private TextView mTitle, mMessage;
    private Button mCancel, mReload, mGoToSettings;

    private OnCancelClickListener onCancelClickListener;
    private OnReloadClickListener onReloadClickListener;
    private OnGoToSettingsClickListerner onGoToSettingsClickListerner;

    public NetwordConnectionDialog(Context context, String title, String msg) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogCustomize = inflater.inflate(R.layout.dialog_network_connection, null);

        mTitle = (TextView) dialogCustomize.findViewById(R.id.txt_title_dialog);
        mMessage = (TextView) dialogCustomize.findViewById(R.id.txt_message);
        mCancel = (Button) dialogCustomize.findViewById(R.id.btn_cancel);
        mReload = (Button) dialogCustomize.findViewById(R.id.btn_reload);
        mGoToSettings = (Button) dialogCustomize.findViewById(R.id.btn_go_to_setting);

        if (title != null && !TextUtils.isEmpty(title)) mTitle.setText(title);
        else mTitle.setText("Title Default");

        if (msg != null && !TextUtils.isEmpty(msg)) mMessage.setText(msg);
        else mMessage.setText(msg);

        mCancel.setOnClickListener(this);
        mReload.setOnClickListener(this);
        mGoToSettings.setOnClickListener(this);

        setContentView(dialogCustomize);

    }

    public void setReloadButtonClick(OnReloadClickListener onReloadClickListener) {
        this.onReloadClickListener = onReloadClickListener;
    }

    public void setCancelButtonClick(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
    }

    public void setGoToSettingButtomClick(OnGoToSettingsClickListerner onGoToSettingsClickListerner) {
        this.onGoToSettingsClickListerner = onGoToSettingsClickListerner;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reload:
                if (onReloadClickListener != null) {
                    onReloadClickListener.onReloadClick();
                }
                dismiss();
                break;

            case R.id.btn_cancel:
                if (onCancelClickListener != null) {
                    onCancelClickListener.onCancelClick();
                }
                dismiss();
                break;

            case R.id.btn_go_to_setting:
                if (onGoToSettingsClickListerner != null) {
                    onGoToSettingsClickListerner.onGoToSettingClick();
                }
                dismiss();
                break;
        }
    }

    public interface OnReloadClickListener {
        void onReloadClick();
    }

    public interface OnCancelClickListener {
        void onCancelClick();
    }

    public interface OnGoToSettingsClickListerner {
        void onGoToSettingClick();
    }

}
