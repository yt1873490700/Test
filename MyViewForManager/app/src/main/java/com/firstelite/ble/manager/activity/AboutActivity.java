package com.firstelite.ble.manager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.view.TitleTool;

import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    private TitleTool titleTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        titleTool = new TitleTool();
        titleTool.setTitle(this,"关于我们",true);
    }
}
