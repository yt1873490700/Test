package com.firstelite.ble.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.adapter.UserNameAdapter;
import com.firstelite.ble.manager.model.NewUserModel;
import com.firstelite.ble.manager.view.TitleTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserManagerActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.user_list)
    ListView userList;
    @BindView(R.id.create_new)
    Button createNew;
    private List<NewUserModel> nameList = new ArrayList<>();
    private UserNameAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanager);
        ButterKnife.bind(this);
        TitleTool title = new TitleTool();
        title.setTitle(this, "用户管理", false);

        SharedPreferences sp = getSharedPreferences("SP_NewUserModel_List",Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
        String peopleListJson = sp.getString("KEY_NewUserModel_LIST_DATA","");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
        if(peopleListJson!="")  //防空判断
        {
            Gson gson = new Gson();
            nameList = gson.fromJson(peopleListJson, new TypeToken<List<NewUserModel>>() {}.getType()); //将json字符串转换成List集合
        }
        mAdapter = new UserNameAdapter(this,nameList);
        userList.setAdapter(mAdapter);
    }

    @OnClick(R.id.create_new)
    public void onViewClicked(View v) {
        Intent intent = new Intent(this,NewUserActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode ==1){
            Bundle bundle = data.getBundleExtra("NewUserModel");
            if(bundle != null){
                NewUserModel model = (NewUserModel)bundle.getSerializable("RESULT");
                nameList.add(model);
                mAdapter.notifyDataSetChanged();
            }
        }
        if(requestCode == 2){//打开成功
            Intent intent = new Intent(this, MeasureActivity.class);
            this.startActivity(intent);
        }
    }

    @Override
    public void onClick(View v){
        onViewClicked(createNew);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveAllUserDate();
    }
    public void saveAllUserDate(){
        if(nameList != null){
            SharedPreferences sp = getSharedPreferences("SP_NewUserModel_List", Activity.MODE_PRIVATE);//创建sp对象
            Gson gson = new Gson();
            String jsonStr=gson.toJson(nameList); //将List转换成Json
            SharedPreferences.Editor editor = sp.edit() ;
            editor.putString("KEY_NewUserModel_LIST_DATA", jsonStr) ; //存入json串
            editor.commit() ;  //提交
        }
    }


}
