package com.android.moncity.moncityandroidframework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.android.moncity.moncityandroidframework.R;


/**
 * Created by Luxj on 2015/7/20 09:31
 */
public class MonCityProgressBar extends ProgressBar {
    public MonCityProgressBar(Context context) {
        super(context);
        init();
    }

    public MonCityProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MonCityProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setIndeterminate(true);
        setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_medium_white));
    }

}
