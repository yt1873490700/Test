package com.firstelite.ble.manager.common;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.firstelite.ble.manager.data.ContextUtil;
import com.firstelite.ble.manager.view.LogUtil;


public abstract class BaseApplication extends Application {

    public abstract boolean isDebug();
    public abstract void initOther();

    @Override
    public final void onCreate() {
        initAttr();
        super.onCreate();
        initOther();
    }

    /**
     * @Title: initAttr
     * @Description: TODO
     * @param
     * @return void
     * @exception/throws  description
     */
    private void initAttr() {
        logPkInfo();
        ContextUtil.getInstance().setApplicationResource(BaseApplication.this.getResources());
        ContextUtil.getInstance().setApplicationContext(BaseApplication.this.getApplicationContext());
        boolean isDebug = isDebug();
        LogUtil.setDebug(isDebug);
    }

    /**
     * @Title: logPkInfo
     * @Description: 打印进入app的日志和版本号
     * @param
     * @return void
     * @exception/throws  description
     */
    private  void logPkInfo() {
        try {
            Context contect = getApplicationContext();
            PackageManager packageManager = contect.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(contect.getPackageName(), 0);
            String str = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            if(str != null){
                System.out.println("**=================    "+str+"    =================**");
            }
            str = packageInfo.versionName;
            if(str != null){
                System.out.println("  ================= VersionName : "+str+" =================  ");
            }
        } catch (Exception e) {
        }
    }
}


