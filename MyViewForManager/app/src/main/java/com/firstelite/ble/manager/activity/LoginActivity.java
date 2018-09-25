package com.firstelite.ble.manager.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.data.UserInfoCache;
import com.firstelite.ble.manager.view.TitleTool;
import com.firstelite.ble.manager.view.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_psd)
    EditText inputPsd;
    @BindView(R.id.input_job_name)
    EditText inputWork;
    @BindView(R.id.input_work_place)
    EditText inputWorkPlace;
    @BindView(R.id.login_submit)
    Button loginSubmit;

    @BindView(R.id.save_psd)
    TextView savePsd;
    private TitleTool titleTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        titleTool = new TitleTool();
        titleTool.setTitle(this, "登录", false);

        savePsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable= getResources().getDrawable(R.mipmap.selected);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                savePsd.setCompoundDrawables(drawable, null, null, null);
                UserInfoCache.getInstance().setSAVE_PSD("save");
            }
        });
    }

    @OnClick(R.id.login_submit)
    public void onViewClicked(View v) {
        String name  = inputName.getText().toString();
        String phone = inputPhone.getText().toString();
        String psd = inputPsd.getText().toString();//医院
        String work = inputWork.getText().toString();
        String workPlace = inputWorkPlace.getText().toString();
       if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(psd) &&
        !TextUtils.isEmpty(work) && !TextUtils.isEmpty(workPlace)){
           UserInfoCache.getInstance().setUSER_NAME(name);
           UserInfoCache.getInstance().setUSER_PHONE(phone);
           UserInfoCache.getInstance().setUSER_PSD(psd);
           UserInfoCache.getInstance().setUSER_WORK(work);
           UserInfoCache.getInstance().setUSER_WORKPLACE(workPlace);
           Intent i = new Intent(this,MainActivity.class);
           this.startActivity(i);
           LoginActivity.this.finish();
        }else{
           Intent i = new Intent(this,MainActivity.class);
           this.startActivity(i);
           LoginActivity.this.finish();
           ToastUtils.show(this,R.string.login_tip);
       }

    }

    @Override
    public void onClick(View v){

        onViewClicked(loginSubmit);
    }
}
