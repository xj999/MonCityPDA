package com.android.moncity.pda;

import android.app.Application;

import com.android.moncity.moncityandroidframework.utils.MonCityLog;
import com.lzy.okgo.OkGo;

import org.litepal.LitePal;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        LitePal.initialize(this);
        MonCityLog.init();
    }
}
