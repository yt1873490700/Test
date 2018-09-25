package com.firstelite.ble.manager.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.adapter.UserNameAdapter;
import com.firstelite.ble.manager.adapter.UserNameListAdapter;
import com.firstelite.ble.manager.model.NewUserModel;
import com.firstelite.ble.manager.view.TitleTool;
import com.firstelite.ble.manager.view.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSalectActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.input_name)
    TextView inputName;
    @BindView(R.id.input_time)
    TextView inputTime;
    @BindView(R.id.user_cancle)
    Button userCancle;
    @BindView(R.id.user_next)
    Button userNext;
    @BindView(R.id.btn_ly)
    LinearLayout btnLy;
    private TitleTool titleTool;
    private TimeSelector timeSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);
        ButterKnife.bind(this);
        titleTool = new TitleTool();
        titleTool.setTitle(this, "筛选查询", true);

         timeSelector = new TimeSelector(UserSalectActivity.this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                inputTime.setText(time);
            }
        }, "2018-01-01 00:00", "2050-12-31 23:59:59");
        timeSelector.setIsLoop(false);//设置不循环,true循环
        timeSelector.setMode(TimeSelector.MODE.YMDHM);//显示 年月日时分（默认）


        inputName.setOnClickListener(this);
        inputTime.setOnClickListener(this);
        userCancle.setOnClickListener(this);
        userNext.setOnClickListener(this);

        String intentName = getIntent().getStringExtra("USER_MSG_SEARCH");
        if(!TextUtils.isEmpty(intentName)){
            inputName.setText(intentName);
        }
    }



    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.user_cancle:
                UserSalectActivity.this.finish();
                break;
            case R.id.user_next:
                String msg = inputName.getText().toString();
                String time = inputTime.getText().toString();
                if(!(msg).equals("点击选择用户")){
                    Intent i = new Intent(this,UserDataManagerActivity.class);
                    i.putExtra("USER_NAME",msg);
                    if(!(time).equals("点击选择时间(不选默认查询所有)")){
                        i.putExtra("USER_TIME",time);
                    }
                    UserSalectActivity.this.startActivity(i);
                }else{
                    ToastUtils.show(this,"请选择用户");
                }

                break;
            case R.id.input_name:
                showUserNameList();
                break;
            case R.id.input_time:
                timeSelector.show();
                break;
                default:
                    break;
        }
    }


    public void showUserNameList(){
        SharedPreferences sp = getSharedPreferences("SP_NewUserModel_List", Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
        String peopleListJson = sp.getString("KEY_NewUserModel_LIST_DATA","");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
        List<String> list = new ArrayList<>();
        if(peopleListJson!="")  //防空判断
        {
            Gson gson = new Gson();
            List<NewUserModel> nameList = gson.fromJson(peopleListJson, new TypeToken<List<NewUserModel>>() {}.getType()); //将json字符串转换成List集合
            for(int i = 0; i < nameList.size();i++){
                NewUserModel model = nameList.get(i);
                String name = model.getName();
                String phone = model.getPhone();
                String mString = name + "(" + phone + ")";
                list.add(mString);
            }
            showMyDialog(list);
        }else{
            ToastUtils.show(this,"暂无数据");
        }
    }

    AlertDialog mAlertDialog;
    public void showMyDialog(final List<String> nameList){
        mAlertDialog= new AlertDialog.Builder(this).create();
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setContentView(R.layout.dialog_namelist);
        Button button = (Button) window.findViewById(R.id.button_cencle);
        ListView namelist = (ListView) window.findViewById(R.id.ble_namelist);

        UserNameListAdapter adapter = new UserNameListAdapter(this,nameList);
        namelist.setAdapter(adapter);
        namelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAlertDialog.dismiss();
                inputName.setText(nameList.get(position));
            }
        });
        inputName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyDialog(nameList);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mAlertDialog.dismiss();
            }
        });
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }
}

