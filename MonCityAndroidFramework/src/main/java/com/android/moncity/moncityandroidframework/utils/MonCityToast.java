package com.android.moncity.moncityandroidframework.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.android.moncity.moncityandroidframework.R;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class MonCityToast {
    private static Toast toast;
    private static Toast toastcenter;

    public static void show(Context context, String string) {

        try {
            if (string != null & string.trim().equals(""))
                return;
            string = checkToasString(context, string);
            if (toast == null) {
                toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            }
            toast.setText(string);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(Context context, int stringId) {

        try {
            if (stringId == 0)
                return;
            String string = checkToasString(context, context.getString(stringId));
            if (toast == null) {
                toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            }
            toast.setText(string);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCenter(Context context, String string) {
        if (string.trim().equals(""))
            return;
        string = checkToasString(context, string);
        try {
            if (toastcenter == null) {
                toastcenter = Toast.makeText(context, string, Toast.LENGTH_SHORT);
                toastcenter.setGravity(Gravity.CENTER, 0, -50);
            }
            toastcenter.setText(string);
            toastcenter.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String checkToasString(Context context, String str) {

        String temp = str.toLowerCase();
        if (temp.contains("timeout")) {
            temp = context.getString(R.string.timeout);
        } else if (temp.contains("connect") || temp.contains("host")) {
            temp = context.getString(R.string.faile_to_connect);
        }
        return temp;
    }
}
