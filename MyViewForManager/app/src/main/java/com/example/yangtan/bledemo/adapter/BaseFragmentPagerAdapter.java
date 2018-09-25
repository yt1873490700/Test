package com.example.yangtan.bledemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    public static String PAGE_TITLE = "PAGE_TITLE";
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private String[] tabs;
    private FragmentManager fm;

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    public BaseFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, String[] tabs) {
        super(fm);
        this.fm = fm;
        this.mFragments = mFragments;
        this.tabs = tabs;
    }

    public BaseFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        this.fm = fm;
        this.mFragments = mFragments;
    }

    public void addFragment(Fragment fg) {
        mFragments.add(fg);
        this.notifyDataSetChanged();

    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        return mFragments.get(position);
    }

    @Override
    public long getItemId(int position) {
        int hashCode = getItem(position).hashCode();
        return hashCode;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tabs != null && tabs.length > 0) {
            return tabs[position];
        } else {
            return getItem(position).getArguments().getString(PAGE_TITLE);
        }
    }

}

