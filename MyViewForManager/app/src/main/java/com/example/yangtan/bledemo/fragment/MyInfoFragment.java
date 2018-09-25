package com.example.yangtan.bledemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangtan.bledemo.R;
import com.example.yangtan.bledemo.activity.AboutActivity;
import com.example.yangtan.bledemo.activity.MyUserInfoActivity;
import com.example.yangtan.bledemo.activity.QuestionActivity;
import com.example.yangtan.bledemo.activity.SettingActivity;
import com.example.yangtan.bledemo.view.TitleTool;

public class MyInfoFragment extends Fragment implements View.OnClickListener{
    private TextView mMyUsername, mMyCareerInfo, mSetting, mMyUserInfo, mAbout, mQuestion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mRootView=inflater.inflate(R.layout.fragment_my, null);
        mMyUsername = (TextView) mRootView.findViewById(R.id.my_name);
        mMyCareerInfo = (TextView) mRootView.findViewById(R.id.my_career);
        mSetting = (TextView) mRootView.findViewById(R.id.fragmentmy_setting);
        mMyUserInfo = (TextView) mRootView.findViewById(R.id.fragmentmy_account);
        mAbout = (TextView) mRootView.findViewById(R.id.fragmentmy_about);
        mQuestion = (TextView) mRootView.findViewById(R.id.fragmentmy_question);

        mSetting.setOnClickListener(this);
        mMyUserInfo.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mQuestion.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onClick(View v){
        doButtonClick(v);
    }

    public void doButtonClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.fragmentmy_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                break;
            case R.id.fragmentmy_account:
                intent = new Intent(getActivity(), MyUserInfoActivity.class);
                break;
            case R.id.fragmentmy_about:
                intent = new Intent(getActivity(), AboutActivity.class);
                break;
            case R.id.fragmentmy_question:
                intent = new Intent(getActivity(), QuestionActivity.class);
                break;
                default:
                    break;
        }
        if(intent != null){
            getActivity().startActivity(intent);
        }
    }
}
