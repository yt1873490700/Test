package com.firstelite.ble.manager.data.db;

import android.content.Context;

public class DBHelperTool {
    private MyDBHelper dbHelper;
    public  DBHelperTool(Context context){
        dbHelper = new MyDBHelper(context,1);
    }
}
