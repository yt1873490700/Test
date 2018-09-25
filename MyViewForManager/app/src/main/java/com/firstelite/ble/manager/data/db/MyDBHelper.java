package com.firstelite.ble.manager.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{
    public MyDBHelper(Context context, int version) {
        super(context, "question_answer.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        String sql = "create table table_qa(_id integer primary key autoincrement, question varchar,answer varchar)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
