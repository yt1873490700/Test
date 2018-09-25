package com.firstelite.ble.manager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.adapter.DataListAdapter;
import com.firstelite.ble.manager.adapter.NameListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDataManagerActivity extends AppCompatActivity {

    private List<String> nameList = new ArrayList<>();
    private List<String> dataList = new ArrayList<>();
    private ListView nameListView, dateListView;
    private NameListAdapter nameAdapter;
    private DataListAdapter dataAdapter;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_userdata);
        ButterKnife.bind(this);

        nameListView = (ListView)findViewById(R.id.namelist);
        dateListView = (ListView)findViewById(R.id.datelist);
        exitBtn = (Button)findViewById(R.id.btn_exit);

        String user_name = getIntent().getStringExtra("USER_NAME");
        String user_time = getIntent().getStringExtra("USER_TIME");

        nameList.add(user_name.substring(0, user_name.indexOf("(")));
        nameAdapter = new NameListAdapter(this, nameList);
        nameListView.setAdapter(nameAdapter);
        nameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameAdapter.currentIndex(position);
            }
        });


        if (!TextUtils.isEmpty(user_time)) {

            dataList.add(user_time);
        }
        dataAdapter = new DataListAdapter(this, dataList);

        dateListView.setAdapter(dataAdapter);
        dateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataAdapter.currentIndex(position);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataManagerActivity.this.finish();
            }
        });

    }


}
