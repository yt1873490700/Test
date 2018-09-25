package com.example.yangtan.bledemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yangtan.bledemo.R;
import com.example.yangtan.bledemo.activity.AdActivity;
import com.example.yangtan.bledemo.activity.CommonUserActivity;
import com.example.yangtan.bledemo.activity.NewsActivity;
import com.example.yangtan.bledemo.activity.VIPUserActivity;
import com.example.yangtan.bledemo.adapter.BaseFragmentPagerAdapter;
import com.example.yangtan.bledemo.adapter.MyTableAdapter;
import com.example.yangtan.bledemo.view.MyTitleView;
import com.example.yangtan.bledemo.view.TitleTool;

import java.util.ArrayList;

public class MsgFragment extends Fragment implements View.OnClickListener{
    private Button commonUser;
    private Button vipUser;
    private Button commonNews;
    private Button commonAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_msg, null);

        commonUser = (Button) view.findViewById(R.id.common_user);
        vipUser = (Button) view.findViewById(R.id.vip_user);
        commonNews = (Button) view.findViewById(R.id.common_news);
        commonAd = (Button) view.findViewById(R.id.common_ad);

        commonUser.setOnClickListener(this);
        vipUser.setOnClickListener(this);
        commonNews.setOnClickListener(this);
        commonAd.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v){
        doButtonClick(v);
    }

    public void doButtonClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.common_user:
                intent = new Intent(getActivity(), CommonUserActivity.class);
                break;
            case R.id.vip_user:
                intent = new Intent(getActivity(), VIPUserActivity.class);
                break;
            case R.id.common_news:
                intent = new Intent(getActivity(), NewsActivity.class);
                break;
            case R.id.common_ad:
                intent = new Intent(getActivity(), AdActivity.class);
                break;
                default:
                    break;
        }
        if(intent != null){
            getActivity().startActivity(intent);
        }
    }
}
