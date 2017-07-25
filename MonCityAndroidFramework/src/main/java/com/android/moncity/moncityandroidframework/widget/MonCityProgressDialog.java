package com.android.moncity.moncityandroidframework.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.android.moncity.moncityandroidframework.R;


/**
 * loading_dialog
 * Created by Luxj on 2015/7/20 09:26
 */
public class MonCityProgressDialog extends Dialog {
    private TextView msg;

    public MonCityProgressDialog(Context context) {
        super(context);
        init();
        setMessage(R.string.loading_value);
    }

    /**
     * @param text 要显示的内容
     */
    public MonCityProgressDialog(Context context, String text) {
        super(context);
        init();
        if (text != null) {
            msg.setText(text);
        }
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int colorResource = android.R.color.transparent;
        getWindow().setBackgroundDrawableResource(colorResource);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.layout_loading_dialog);
        msg = (TextView) findViewById(R.id.dialog_msg);
    }

    /**
     * @param text 要显示的内容
     */
    public void setMessage(String text) {
        if (text != null) {
            msg.setText(text);
        }
    }

    /**
     * @param aStringResources 可传入R.string内的资源
     */
    public void setMessage(int aStringResources) {
        if (aStringResources != 0) {
            msg.setText(aStringResources);
        }
    }
}
