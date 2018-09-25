package com.firstelite.ble.manager.view;

import android.util.Log;

public class LogUtil {

    private static boolean mIsVisible = false;

    public static int VERBOSE = Log.VERBOSE;
    public static int INFO = Log.INFO;
    public static int WARN = Log.WARN;
    public static int ERROR = Log.ERROR;
    public static int DEBUG = Log.DEBUG;
    public static int ASSERT = Log.ASSERT;

    public static void setDebug(boolean isDebug){
        mIsVisible = isDebug;
    }

    public static void log_D(String tag, final String msg){
        if(mIsVisible)
            Log.d(tag, msg);
    }

    public static void log_D(String tag, final String msg, final Throwable throwable){
        if(mIsVisible)
            Log.d(tag, msg, throwable);
    }

    public static void log_E(String tag, final String msg){
        if(mIsVisible)
            Log.e(tag, msg);
    }

    public static void log_E(String tag, final String msg, final Throwable throwable){
        if(mIsVisible)
            Log.e(tag, msg, throwable);
    }

    public static void log_I(String tag, final String msg){
        if(mIsVisible)
            Log.i(tag, msg);
    }

    public static void log_I(String tag, final String msg, final Throwable throwable){
        if(mIsVisible)
            Log.i(tag, msg, throwable);
    }

    public static void log_V(String tag, final String msg){
        if(mIsVisible)
            Log.v(tag, msg);
    }

    public static void log_V(String tag, final String msg, final Throwable throwable){
        if(mIsVisible)
            Log.v(tag, msg, throwable);
    }

    public static void log_W(String tag, final String msg){
        if(mIsVisible)
            Log.w(tag, msg);
    }

    public static void log_W(String tag, final String msg, final Throwable throwable){
        if(mIsVisible)
            Log.w(tag, msg, throwable);
    }

    public static void log_W(String tag, final Throwable throwable){
        if(mIsVisible)
            Log.w(tag, throwable);
    }

}
