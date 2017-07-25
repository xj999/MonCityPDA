package com.android.moncity.moncityandroidframework.utils;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 *
 */
public class MonCityLog {


    static void init() {
        Logger.clearLogAdapters();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void d(String message, Object... args) {
        init();
        Logger.t(getClassName()).d(message, args);
    }

    public static void e(String message, Object... args) {
        init();
        Logger.t(getClassName()).e(message, args);
    }


    public static void e(Throwable throwable, String message, Object... args) {
        init();
        Logger.t(getClassName()).e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        init();
        Logger.t(getClassName()).i(message, args);
    }


    public static void v(String message, Object... args) {
        init();
        Logger.t(getClassName()).v(message, args);
    }


    public static void w(String message, Object... args) {
        init();
        Logger.t(getClassName()).w(message, args);
    }


    public static void wtf(String message, Object... args) {
        init();
        Logger.t(getClassName()).wtf(message, args);
    }

    public static void json(String json) {
        init();
        Logger.t(getClassName()).json(json);
    }

    public static void xml(String xml) {
        init();
        Logger.t(getClassName()).xml(xml);
    }


    /**
     * @return 当前的类名(simpleName)
     */
    private static String getClassName() {

        String result;
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());

        //如果调用位置在匿名内部类的话，就会产生类似于 MainActivity$3 这样的TAG
        //可以把$后面的部分去掉
        int i = result.indexOf("$");

        return i == -1 ? result : result.substring(0, i);
    }

}
