package com.firstelite.ble.manager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.view.TitleTool;

public class AdActivity extends AppCompatActivity {

    private TitleTool titleTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_ad);

        titleTool = new TitleTool();
        titleTool.setTitle(this,"广告推送",true);
    }

}
