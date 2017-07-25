package com.android.moncity.moncityandroidframework.base;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.moncity.moncityandroidframework.R;
import com.android.moncity.moncityandroidframework.utils.KeyBoardUtil;
import com.android.moncity.moncityandroidframework.utils.MonCityToast;
import com.android.moncity.moncityandroidframework.widget.MonCityProgressDialog;
import com.lzy.okgo.OkGo;

import okhttp3.Call;

/**
 * @author Luxj
 * @date create 2017/7/19
 * @description 底层activity 所有activity都必须继承此类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private int hashcode;
    private static long lastClickTime;
    private Button r3Btn;
    private MonCityProgressDialog progressDialog;
    private boolean flag = false;
    private ImageButton r1Btn;
    private ImageButton r2Btn;
    private TextView nav_title;
    private LinearLayout rootContent;
    private View layout_nav;
    private ImageButton nav_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hashcode = this.hashCode();
        getDelegate().setContentView(R.layout.layout_base);
        Window window = getWindow();
        nav_title = (TextView) window.findViewById(R.id.nav_title);
        window.findViewById(R.id.layout_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavLayoutClick();
            }
        });
        nav_back = (ImageButton) window.findViewById(R.id.nav_back);
        rootContent = (LinearLayout) window.findViewById(R.id.content);
        layout_nav = window.findViewById(R.id.layout_nav);
        r1Btn = (ImageButton) window.findViewById(R.id.nav_right1);
        r2Btn = (ImageButton) window.findViewById(R.id.nav_right2);
        r3Btn = (Button) window.findViewById(R.id.nav_right3);
        if (r1Btn != null) {
            r1Btn.setVisibility(View.GONE);
            r1Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onR1BtnClick();
                }
            });
        }
        //
        if (r2Btn != null) {
            r2Btn.setVisibility(View.GONE);
            r2Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onR2BtnClick();
                }
            });
        }
        if (r3Btn != null) {
            r3Btn.setVisibility(View.GONE);
            r3Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onR3BtnClick();
                }
            });
        }
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        data(savedInstanceState);
    }

    protected void onNavLayoutClick() {
        KeyBoardUtil.closeKeyBoard(this);
    }

    public void setNavBackVisibility(int visibility) {
        nav_back.setVisibility(visibility);
    }


    /**
     * 设置title
     *
     * @param titleText
     */
    public void setTitleText(String titleText) {
        if (nav_title != null) {
            if (titleText.length() > 14) {
                titleText = titleText.substring(0, 13) + "...";
            }
            nav_title.setText(titleText);
        }
    }

    /**
     * 设置头部隐藏
     */
    public void setNavGone() {
        if (layout_nav != null) {
            layout_nav.setVisibility(View.GONE);
        }
    }

    /**
     *
     */
    protected void setR1BtnImage(int resource) {
        try {
            r1Btn.setVisibility(View.VISIBLE);
            r1Btn.setImageResource(resource);
        } catch (Exception e) {
        }
    }

    /**
     *
     */
    protected void setR2BtnImage(int resource) {
        try {
            if (resource == 0) {
                r2Btn.setVisibility(View.GONE);
            } else {
                r2Btn.setVisibility(View.VISIBLE);
                r2Btn.setImageResource(resource);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 设置右侧按钮文字
     *
     * @param text
     */
    protected void setR3BtnText(String text) {
        if (text == null || text.isEmpty()) {
            r3Btn.setVisibility(View.GONE);
        } else {
            r3Btn.setVisibility(View.VISIBLE);
            r3Btn.setText(text);
        }
    }

    /**
     */
    protected void onR1BtnClick() {
    }

    /**
     */
    protected void onR2BtnClick() {
    }

    /**
     */
    protected void onR3BtnClick() {
    }


    @Override
    public View findViewById(int id) {
        return rootContent.findViewById(id);
    }

    private void clearContentView() {
        rootContent.removeAllViews();
    }

    @Override
    public void setContentView(int layoutResID) {
        clearContentView();
        getLayoutInflater().inflate(layoutResID, rootContent, true);
    }

    @Override
    public void setContentView(View view) {
        clearContentView();
        rootContent.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        clearContentView();
        rootContent.addView(view, params);
    }

    private void data(Bundle savedInstanceState) {
        initData(savedInstanceState);
    }

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && flag) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 1000) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        try {
            cancelTag(String.valueOf(hashcode), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cancelTag(String tag, int aa) {
        for (Call call : OkGo.getInstance().getOkHttpClient().dispatcher().queuedCalls()) {
            String tags = String.valueOf(call.request().tag());
            if (tags.startsWith(tag.substring(0, tag.length() - 2))) {
                call.cancel();
            }
        }
        for (Call call : OkGo.getInstance().getOkHttpClient().dispatcher().runningCalls()) {
            String tags = String.valueOf(call.request().tag());
            if (tags.startsWith(tag.substring(0, tag.length() - 2))) {
                call.cancel();
            }
        }
    }

    private ProgressDialog dialog;

    public void showToast(String msg) {
        MonCityToast.show(this, msg);
    }

    public void showToast(int id, String temp) {
        MonCityToast.show(this, getString(id, temp));
    }

    public void showToast(int stringId) {
        MonCityToast.show(this, getString(stringId));
    }

    public void showLoading() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(R.string.loading_value));
        dialog.setCancelable(false);
        dialog.show();
    }

    public void showLoading(String msg) {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setMessage(msg);
        dialog.show();
    }

    public void showLoading(int id) {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(getString(id));
        dialog.setCancelable(false);
        dialog.show();
    }


    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        KeyBoardUtil.closeKeyBoard(this);
        super.onBackPressed();
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }


}