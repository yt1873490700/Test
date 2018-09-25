package com.firstelite.ble.manager.common;


import android.app.Activity;

import java.util.Stack;

public class ActivityManager {

    private ActivityManager() {

    }

    private static ActivityManager activityManager = new ActivityManager();

    public static ActivityManager getinstance() {
        return activityManager;
    }

    private Stack<Activity> activityStack = new Stack<>();

    public void add(Activity activity){
        if (activity != null){
            activityStack.add(activity);
        }
    }

    public void remove(Activity activity){
        if (activity != null){
            for (Activity curAV :   activityStack) {
                if (curAV.getClass().equals(activity.getClass())){
                    curAV.finish();
                    activityStack.remove(curAV);
                }
            }
        }
    }

    public void removeCurrent(){
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);

    }

    public void removeAll(){
        for (int i = activityStack.size();i>= 0 ; i --){
            Activity activity = activityStack.get(i);
            activity.finish();;
            activityStack.remove(activity);
        }
    }

    public int size(){
        return activityStack.size();
    }


}


