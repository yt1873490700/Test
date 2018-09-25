package com.firstelite.ble.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.model.NewUserModel;
import com.firstelite.ble.manager.view.TitleTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_sex)
    EditText userSex;
    @BindView(R.id.user_age)
    EditText userAge;
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.submit_queation)
    Button submitQueation;
    private TitleTool titleTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);
        titleTool = new TitleTool();
        titleTool.setTitle(this, "添加新用户", true);

        submitQueation.setOnClickListener(this);
    }

    @OnClick(R.id.submit_queation)
    public void onViewClicked(View view) {
        String name = userName.getText().toString();
        String sex = userSex.getText().toString();
        String phone = userPhone.getText().toString();
        String age = userAge.getText().toString();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sex) &&
                !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(age)){
            NewUserModel model = new NewUserModel();
            model.setAge(age);
            model.setName(name);
            model.setSex(sex);
            model.setPhone(phone);
            Bundle bundle = new Bundle();
            bundle.putSerializable("RESULT", model);
            Intent intent = new Intent(this,UserManagerActivity.class);
            intent.putExtra("NewUserModel", bundle);
            this.setResult(1, intent);
            NewUserActivity.this.finish();
        }else{
            Toast.makeText(this,"请完善信息",0).show();
        }
    }

    @Override
    public void onClick(View v){
        onViewClicked(submitQueation);
    }
}
