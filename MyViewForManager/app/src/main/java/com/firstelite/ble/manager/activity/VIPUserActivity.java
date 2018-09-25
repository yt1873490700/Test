package com.firstelite.ble.manager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.view.TitleTool;

public class VIPUserActivity extends AppCompatActivity {
    private TitleTool titleTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_user);

        titleTool = new TitleTool();
        titleTool.setTitle(this,"VIP普通用户提示",true);
    }
}
