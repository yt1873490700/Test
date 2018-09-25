package com.firstelite.ble.manager.view;


import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.firstelite.ble.manager.R;


public class TitleTool {
    private Activity mActivity;
    public void setTitle( Activity activity,String title,boolean isShow){
        mActivity = activity;
        TextView titleTV = (TextView)mActivity.findViewById(R.id.title);
        titleTV.setText(title);

        TextView leftTV =(TextView)mActivity.findViewById(R.id.back);
        if(isShow){
            leftTV.setVisibility(View.VISIBLE);
        }else{
            leftTV.setVisibility(View.INVISIBLE);
        }
        leftTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }
}
