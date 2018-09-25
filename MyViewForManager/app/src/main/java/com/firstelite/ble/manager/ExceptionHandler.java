package com.firstelite.ble.manager;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    public ExceptionHandler()
    {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        ex.printStackTrace(pw);
        String errMsg = sw.getBuffer().toString();
        System.out.print("全局捕获：" + errMsg);
        Log.d("未捕获的错误：", errMsg);

    }
}
