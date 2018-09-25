package com.firstelite.ble.manager.activity;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.adapter.BaseFragmentPagerAdapter;
import com.firstelite.ble.manager.common.ActivityManager;
import com.firstelite.ble.manager.fragment.HomeFragment;
import com.firstelite.ble.manager.fragment.MsgFragment;
import com.firstelite.ble.manager.fragment.MyInfoFragment;
import com.firstelite.ble.manager.view.TitleTool;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private TextView homeTV,msgTV,myinfoTV;
    private ViewPager viewPager;
    private BaseFragmentPagerAdapter pagerAdapter;
    private TitleTool titleTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityManager.getinstance().add(this);

        titleTool = new TitleTool();
        homeTV = (TextView)findViewById(R.id.text_home);
        msgTV = (TextView)findViewById(R.id.text_msg);
        myinfoTV = (TextView)findViewById(R.id.text_info);
        viewPager = (ViewPager)findViewById(R.id.viewpager);

        homeTV.setOnClickListener(this);
        msgTV.setOnClickListener(this);
        myinfoTV.setOnClickListener(this);
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new MsgFragment());
        fragments.add(new MyInfoFragment());

        fragmentManager = getSupportFragmentManager();

        pagerAdapter = new BaseFragmentPagerAdapter(fragmentManager,fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        homeTV.setCompoundDrawables(null, showIconSelected(1), null, null);
        titleTool.setTitle(this,"首页",false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        homeTV.callOnClick();
                        break;
                    case 1:
                        msgTV.callOnClick();
                        break;
                    case 2:
                        myinfoTV.callOnClick();
                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public Drawable showIconSelected(int index){
        Drawable nav_up = null;
        if(index == 1){
            nav_up=getResources().getDrawable(R.mipmap.icon_shouye1);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        }else if(index == 2){
            nav_up=getResources().getDrawable(R.mipmap.icon_message);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        }else if(index == 3){
            nav_up=getResources().getDrawable(R.mipmap.icon_wo1);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        }

        return nav_up;
    }
    public Drawable showIconUnSelected(int index){
        Drawable nav_up = null;
        if(index == 1){
            nav_up=getResources().getDrawable(R.mipmap.icon_shouye);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        }else if(index == 2){
            nav_up=getResources().getDrawable(R.mipmap.icon_message1);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        }else if(index == 3){
            nav_up=getResources().getDrawable(R.mipmap.icon_wo);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        }

        return nav_up;
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.text_home:
                viewPager.setCurrentItem(0);
                titleTool.setTitle(MainActivity.this,"首页",false);
                homeTV.setCompoundDrawables(null, showIconSelected(1), null, null);
                msgTV.setCompoundDrawables(null, showIconUnSelected(2), null, null);
                myinfoTV.setCompoundDrawables(null, showIconUnSelected(3), null, null);
                break;
            case R.id.text_msg:
                viewPager.setCurrentItem(1);
                titleTool.setTitle(MainActivity.this,"信息",false);
                homeTV.setCompoundDrawables(null, showIconUnSelected(1), null, null);
                msgTV.setCompoundDrawables(null, showIconSelected(2), null, null);
                myinfoTV.setCompoundDrawables(null, showIconUnSelected(3), null, null);
                break;
            case R.id.text_info:
                viewPager.setCurrentItem(2);
                titleTool.setTitle(MainActivity.this,"我的",false);
                homeTV.setCompoundDrawables(null, showIconUnSelected(1), null, null);
                msgTV.setCompoundDrawables(null, showIconUnSelected(2), null, null);
                myinfoTV.setCompoundDrawables(null, showIconSelected(3), null, null);
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) ;
            flag = true;
        }
    };

    private  boolean flag = true;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK  && flag) {
            Toast.makeText(this, "再点击一次退出当前应用", Toast.LENGTH_SHORT).show();
            flag = false;
            handler.sendEmptyMessageDelayed(1, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
        ActivityManager.getinstance().remove(this);
    }
}
