package com.example.yangtan.bledemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.yangtan.bledemo.R;

public class MyTableAdapter extends MyTitleViewAdapter{
    private Activity activity;
    private int mScreenTabCount;
    private DisplayMetrics dm;

    public MyTableAdapter(Activity activity, int screenTabsCount) {
        super();
        // TODO Auto-generated constructor stub
        this.activity=activity;
        mScreenTabCount = screenTabsCount;

        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    }



    @Override
    public View getView(int position) {
        // TODO Auto-generated method stub
        LayoutInflater inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Button button=(Button) inflater.inflate(R.layout.titleview_btn, null);

        button.setWidth(dm.widthPixels/mScreenTabCount);
        button.setText(tabsList.get(position));
        return button;
    }
}

