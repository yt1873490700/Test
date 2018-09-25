package com.firstelite.ble.manager.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.adapter.BLENameListAdapter;
import com.firstelite.ble.manager.data.UserInfoCache;
import com.firstelite.ble.manager.model.NewUserModel;
import com.firstelite.ble.manager.view.MyDialog;
import com.firstelite.ble.manager.view.TitleTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeasureActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.demo_content)
    TextView demoContent;
    @BindView(R.id.switch_btn)
    Switch switchBtn;
    @BindView(R.id.text_data)
    TextView textData;
    @BindView(R.id.select_user)
    TextView userName;
    @BindView(R.id.measure_begin)
    Button measureBegin;
    @BindView(R.id.measure_stop)
    Button measureStop;
    @BindView(R.id.measure_save)
    Button measureSave;
    private TitleTool titleTool;
    private BluetoothAdapter mBluetoothAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        ButterKnife.bind(this);

        titleTool = new TitleTool();
        titleTool.setTitle(this, "数据采集", true);

        measureBegin.setOnClickListener(this);
        measureStop.setOnClickListener(this);
        measureSave.setOnClickListener(this);

        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){//开启
                    List<String> bleName = new ArrayList<>();
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                    if (pairedDevices.size() > 0) {
                        for (BluetoothDevice device : pairedDevices) {
                            String nameStrirng = device.getName() + ":" + device.getAddress();
                            bleName.add(nameStrirng);
                        }
                        showBleName(bleName);
                    }

                }else {
                    demoContent.setText(R.string.measure_disconnect);
                    demoContent.setClickable(false);
                }
            }
        });

        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> nameList = new ArrayList<>();

                SharedPreferences sp = getSharedPreferences("SP_NewUserModel_List", Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
                String peopleListJson = sp.getString("KEY_NewUserModel_LIST_DATA","");  //取出key为"KEY_PEOPLE_DATA"的值，如果值为空，则将第二个参数作为默认值赋值
                if(peopleListJson!="")  //防空判断
                {
                    Gson gson = new Gson();
                    List<NewUserModel> list = gson.fromJson(peopleListJson, new TypeToken<List<NewUserModel>>() {}.getType()); //将json字符串转换成List集合
                    for (int i = 0 ; i < list.size();i++) {
                        NewUserModel model = list.get(i);
                        String name = model.getName();
                        String phone = model.getPhone();
                        String total = name + "(" + phone + ")";
                        nameList.add(total);
                    }
                }
                showDialog(nameList);
            }
        });

        String intentName = getIntent().getStringExtra("USER_MSG_MEASURE");
        userName.setText(intentName);
    }

    AlertDialog alertDialog;
    public void showBleName(final List<String> name){
        alertDialog= new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_namelist);
        Button button = (Button) window.findViewById(R.id.button_cencle);
        ListView namelist = (ListView) window.findViewById(R.id.ble_namelist);

        BLENameListAdapter adapter = new BLENameListAdapter(this,name);
        namelist.setAdapter(adapter);
        namelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog.dismiss();
                String msg = "连接到：(点击切换设备)" +"\n"+ name.get(position);
                demoContent.setText(msg);
                demoContent.setClickable(true);
            }
        });
        demoContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBleName(name);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    AlertDialog mAlertDialog;
    public void showDialog(final List<String> nameList){
        mAlertDialog= new AlertDialog.Builder(this).create();
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setContentView(R.layout.dialog_namelist);
        Button button = (Button) window.findViewById(R.id.button_cencle);
        ListView namelist = (ListView) window.findViewById(R.id.ble_namelist);

        BLENameListAdapter adapter = new BLENameListAdapter(this,nameList);
        namelist.setAdapter(adapter);
        namelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAlertDialog.dismiss();
                userName.setText(nameList.get(position));
            }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(nameList);
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

    public void showSaveDialog() {
        MyDialog myDialog = new MyDialog();
        myDialog.initDialog(this,"提示:是否保存该次测量数据？");
        myDialog.buttonClickEvent(new MyDialog.DialogButtonClick(){
            @Override
            public void cilckComfirmButton(View view ){

            }
            @Override
            public void cilckCancleButton(View view ){

            }
        });
    }

    public void showExitDialog(){
        MyDialog myDialog = new MyDialog();
        myDialog.initDialog(this,"提示：退出本次测量");
        myDialog.buttonClickEvent(new MyDialog.DialogButtonClick(){
            @Override
            public void cilckComfirmButton(View view ){
                MeasureActivity.this.finish();
            }
            @Override
            public void cilckCancleButton(View view ){

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.measure_begin:
                String stautsMsg =   demoContent.getText().toString();
                if(stautsMsg.equals("已断开连接...")){
                    Toast.makeText(this,"尚未连接设备，请先连接。",0).show();
                }else if(TextUtils.isEmpty(userName.getText().toString())){
                    Toast.makeText(this,"尚未选择用户，请先选择。",0).show();
                } else if(!stautsMsg.equals("已断开连接...") && !TextUtils.isEmpty(userName.getText().toString())){
                    measureStop.setTextColor(Color.parseColor("#7b7b7b"));
                    measureSave.setTextColor(Color.parseColor("#7b7b7b"));
                    measureBegin.setTextColor(Color.parseColor("#FF4081"));
                }
                break;
            case R.id.measure_stop:
                measureSave.setTextColor(Color.parseColor("#7b7b7b"));
                measureBegin.setTextColor(Color.parseColor("#7b7b7b"));
                measureStop.setTextColor(Color.parseColor("#FF4081"));
                showExitDialog();
                break;
            case R.id.measure_save:
                measureStop.setTextColor(Color.parseColor("#7b7b7b"));
                measureBegin.setTextColor(Color.parseColor("#7b7b7b"));
                measureSave.setTextColor(Color.parseColor("#FF4081"));
                showSaveDialog();
                break;
        }

    }

}
