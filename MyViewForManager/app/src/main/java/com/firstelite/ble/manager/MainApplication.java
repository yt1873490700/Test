package com.firstelite.ble.manager;

import android.content.Context;


import com.firstelite.ble.manager.common.BaseApplication;

import java.io.File;
import java.lang.reflect.Method;

public class MainApplication extends BaseApplication {

    private final String TAG = MainApplication.class.getSimpleName();
    private final boolean DEBUG = true;

    public MainApplication() {
        new ExceptionHandler();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     *
     * @see android.app.Application#onTerminate()
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /*
     * @see miky.android.common.BaseApplication#initOther()
     */
    @Override
    public void initOther() {

    }



                    // 获取手机的序列号
    public String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;

    }

    /*
     * @see miky.android.common.BaseApplication#isDebug()
     *
     * @return
     */
    @Override
    public boolean isDebug() {
        if (DEBUG) {
            System.out.println("*******************" + TAG + " enter*****************");
        }
        return MainApplication.this.getResources().getBoolean(R.bool.debug_config);
    }

    /** * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File(context.getFilesDir().getParent() + "/databases"));
    }

    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /** 清除APP项目下所有缓存文件 */
    public static void cleanAllCache(Context context) {
        deleteFilesByDirectory(new File(context.getFilesDir().getParent()));
    }
}
