package com.firstelite.ble.manager.adapter;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.activity.MeasureActivity;
import com.firstelite.ble.manager.activity.UserSalectActivity;
import com.firstelite.ble.manager.model.NewUserModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserNameAdapter extends BaseAdapter {
    private List<NewUserModel> questionList;
    private Activity context;
    private static BluetoothManager mBluetoothManager; 	//蓝牙设备管理器
    private BluetoothAdapter mBluetoothAdapter = null; 	//蓝牙适配器
    public List<NewUserModel> getQuestionList() {
        return questionList;
    }

    public UserNameAdapter(Activity context, List<NewUserModel> questionList) {
        // TODO Auto-generated constructor stub
        this.questionList = questionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return questionList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return questionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void refresh() {

        UserNameAdapter.this.notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_add_user, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        NewUserModel model = questionList.get(position);
        holder.textViewName.setText(model.getName());
        holder.textViewSex.setText(model.getSex());
        holder.textViewAge.setText(model.getAge());
        holder.textViewPhone.setText(model.getPhone());

        holder.textDelete.setTag(position);
        holder.textDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = (Integer)v.getTag();
                questionList.remove(currentPosition);
                UserNameAdapter.this.notifyDataSetChanged();
            }
        });

        holder.textMeasure.setTag(position);
        holder.textMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = (Integer)v.getTag();
                Intent intent = new Intent(context, UserSalectActivity.class);
                String msg = questionList.get(currentPosition).getName() + "(" + questionList.get(currentPosition).getPhone()+")";
                intent.putExtra("USER_MSG_SEARCH",msg);
                context.startActivity(intent);
            }
        });

        holder.textBegin.setTag(position);
        holder.textBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentdex = (Integer)v.getTag();
                //获取安卓的蓝牙模块的管理权限
                mBluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
                mBluetoothAdapter = mBluetoothManager.getAdapter();

                //判断本台设备是否支持BLE
                if (mBluetoothAdapter == null) {
                    Toast.makeText(context, "ble not support", Toast.LENGTH_LONG).show();

                }

                //打开蓝牙
                if (!mBluetoothAdapter.isEnabled())
                {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    context.startActivityForResult(enableIntent, 2);
                }else{
                    Intent intent = new Intent(context, MeasureActivity.class);
                    String msg = questionList.get(currentdex).getName() + "(" + questionList.get(currentdex).getPhone()+")";
                    intent.putExtra("USER_MSG_MEASURE",msg);
                    context.startActivity(intent);
                }
            }
        });



        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.textView_name)
        TextView textViewName;
        @BindView(R.id.textView_sex)
        TextView textViewSex;
        @BindView(R.id.textView_age)
        TextView textViewAge;
        @BindView(R.id.textView_phone)
        TextView textViewPhone;
        @BindView(R.id.user_delete)
        TextView textDelete;
        @BindView(R.id.user_measure)
        TextView textMeasure;
        @BindView(R.id.user_measure_begin)
        TextView textBegin;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
