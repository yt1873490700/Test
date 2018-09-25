package com.firstelite.ble.manager.adapter;

import android.view.View;

import java.util.ArrayList;
import java.util.List;


public abstract class MyTitleViewAdapter {
    List<String> tabsList=new ArrayList<String>();

    public abstract View getView(int position);

    public int getCount(){
        return tabsList.size();
    }

    public void add(String name){
        tabsList.add(name);
    }

}