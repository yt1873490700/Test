package com.example.yangtan.bledemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yangtan.bledemo.R;
import com.example.yangtan.bledemo.view.TitleTool;

public class SettingActivity extends AppCompatActivity {
    private TitleTool titleTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        titleTool = new TitleTool();
        titleTool.setTitle(this,"系统消息",true);
    }
}
