package com.firstelite.ble.manager.data;


import android.content.Context;
import android.content.res.Resources;

public class ContextUtil {

    /**
     * @Fields mResUtil : TODO description here
     */
    private static ContextUtil mResUtil = null;

    /**
     * @Title: _Instance
     * @Description: TODO 获取资源实例
     * @param @return
     * @return mResUtil
     * @exception/throws  description
     */
    public static final ContextUtil getInstance(){
        if(mResUtil == null){
            mResUtil = new ContextUtil();
        }
        return mResUtil;
    }

    /**
     * @Fields mResources : TODO description here
     */
    private Resources mResources = null;
    /**
     * @Fields mContext : TODO description here
     */
    private Context mContext = null;

    /**
     * @Title: setApplicationResource
     * @Description: TODO 设置全局资源对象
     * @param @param resources
     * @return void
     * @exception/throws  description
     */
    public void setApplicationResource(final Resources resources){
        this.mResources = resources;
    }

    /**
     * @Title: getApplicationResource
     * @Description: TODO 获取全局资源
     * @param @return
     * @return Resources
     * @exception/throws  description
     */
    public Resources getApplicationResource(){

        return this.mResources;
    }

    /**
     * @Title: setApplicationContext
     * @Description: TODO 设置全局context
     * @param @param context
     * @return void
     * @exception/throws  description
     */
    public void setApplicationContext(final Context context){

        this.mContext = context;
    }

    /**
     * @Title: getApplicationContext
     * @Description: TODO 得到全局context
     * @param @return
     * @return Context
     * @exception/throws  description
     */
    public Context getApplicationContext(){
        return this.mContext;
    }

}

