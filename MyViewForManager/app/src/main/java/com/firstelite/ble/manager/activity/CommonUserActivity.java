package com.firstelite.ble.manager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.fragment.SaveMsgFragment;
import com.firstelite.ble.manager.fragment.SendMsgFragment;
import com.firstelite.ble.manager.fragment.SendingMsgFragment;
import com.firstelite.ble.manager.view.TitleTool;

import java.util.ArrayList;
import java.util.List;

public class CommonUserActivity extends AppCompatActivity implements  RadioGroup.OnCheckedChangeListener {

    private TitleTool titleTool;
    private RadioGroup tzggRadiogroup;
    private RadioButton tzggRadiobtnLeft;
    private RadioButton tzggRadiobtnCenter;
    private RadioButton tzggRadiobtnRight;
    private FrameLayout tzggParentContent;
    private List<Fragment> fragments;
    private String ZERO="zero",ONE="one",TWO="two";
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_user);

        titleTool = new TitleTool();
        titleTool.setTitle(this,"普通用户提示",true);

        tzggRadiogroup = (RadioGroup) findViewById(R.id.tzgg_radiogroup);
        tzggRadiobtnLeft = (RadioButton) findViewById(R.id.tzgg_radiobtn_left);
        tzggRadiobtnCenter = (RadioButton) findViewById(R.id.tzgg_radiobtn_center);
        tzggRadiobtnRight = (RadioButton) findViewById(R.id.tzgg_radiobtn_right);
        tzggParentContent = (FrameLayout) findViewById(R.id.tzgg_parent_content);

        tzggRadiogroup.setOnCheckedChangeListener(this);

        SendingMsgFragment sendingMsgFragment = new SendingMsgFragment();
        fragments = new ArrayList<>();
        fragments.add(sendingMsgFragment);
        fragments.add(new SendMsgFragment());
        fragments.add(new SaveMsgFragment());

        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().add(tzggParentContent.getId(), fragments.get(0),ZERO).commit();
        fragmentManager.beginTransaction().add(tzggParentContent.getId(), fragments.get(1),ONE).commit();
        fragmentManager.beginTransaction().add(tzggParentContent.getId(), fragments.get(2),TWO).commit();
        fragmentManager.beginTransaction().hide(fragments.get(1)).commit();
        fragmentManager.beginTransaction().hide(fragments.get(2)).commit();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.tzgg_radiobtn_left:
                if (fragmentManager.findFragmentByTag(ONE) != null) {
                    fragmentManager.beginTransaction().hide(fragments.get(1)).commit();
                }
                if (fragmentManager.findFragmentByTag(TWO) != null) {
                    fragmentManager.beginTransaction().hide(fragments.get(2)).commit();
                }
                fragmentManager.beginTransaction().show( fragments.get(0))
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.tzgg_radiobtn_center:
                fragmentManager.beginTransaction().hide(fragments.get(0)).commit();
                if (fragmentManager.findFragmentByTag(TWO) != null) {
                    fragmentManager.beginTransaction().hide(fragments.get(2)).commit();
                }
                fragmentManager.beginTransaction().show(fragments.get(1))
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.tzgg_radiobtn_right:
                fragmentManager.beginTransaction().hide(fragments.get(0)).commit();
                fragmentManager.beginTransaction().hide(fragments.get(1)).commit();
                fragmentManager.beginTransaction().show(fragments.get(2))
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            default:
                break;
        }
    }
}
