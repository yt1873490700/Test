package com.firstelite.ble.manager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.data.UserInfoCache;
import com.firstelite.ble.manager.view.TitleTool;
import com.firstelite.ble.manager.view.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyUserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_sex)
    EditText userSex;
    @BindView(R.id.user_work)
    EditText userWork;
    @BindView(R.id.user_work_place)
    EditText userWorkPlace;
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.submit_queation)
    Button submitQueation;
    private TitleTool titleTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myuserinfo);
        ButterKnife.bind(this);

        titleTool = new TitleTool();
        titleTool.setTitle(this, "我的信息", true);

        String sex = UserInfoCache.getInstance().getUSER_SEX();;
        String work = UserInfoCache.getInstance().getUSER_WORK();
        String work_place = UserInfoCache.getInstance().getUSER_WORKPLACE();
        String name = UserInfoCache.getInstance().getUSER_NAME();
        String phone = UserInfoCache.getInstance().getUSER_PHONE();
        if( !TextUtils.isEmpty(sex) || !TextUtils.isEmpty(work) || !TextUtils.isEmpty(work_place)
                || !TextUtils.isEmpty(name) || !TextUtils.isEmpty(phone)){
            userName.setText(name);
            userSex.setText(sex);
            userWork.setText(work);
            userWorkPlace.setText(work_place);
            userPhone.setText(phone);
        }
        submitQueation.setOnClickListener(this);
    }

    @OnClick(R.id.submit_queation)
    public void onViewClicked(View v) {
        String sex = userSex.getText().toString();
        String work = userWork.getText().toString();
        String work_place = userWorkPlace.getText().toString();
        if( !TextUtils.isEmpty(sex) &&!TextUtils.isEmpty(work) && !TextUtils.isEmpty(work_place)){
            UserInfoCache.getInstance().setUSER_SEX(sex);
            UserInfoCache.getInstance().setUSER_WORK(work);
            UserInfoCache.getInstance().setUSER_WORKPLACE(work_place);
            submitQueation.setVisibility(View.INVISIBLE);
            ToastUtils.show(this,"保存成功");
            userName.setEnabled(false);
            userSex.setEnabled(false);
            userWork.setEnabled(false);
            userWorkPlace.setEnabled(false);
            userPhone.setEnabled(false);
        }else{
            ToastUtils.show(this,"请完善信息");
        }
    }

    @Override
    public void onClick(View v){
        onViewClicked(submitQueation);
    }
}

