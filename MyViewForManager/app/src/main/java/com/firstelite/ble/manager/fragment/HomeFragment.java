package com.firstelite.ble.manager.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firstelite.ble.manager.R;
import com.firstelite.ble.manager.activity.MeasureActivity;
import com.firstelite.ble.manager.activity.UserManagerActivity;
import com.firstelite.ble.manager.activity.UserSalectActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button userManagerBtn,dataManagerBtn,measureBtn;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private int imagesid[];
    private List<ImageView> imageViewsList;//存放资源图片
    private int currentId = 300;// 当前页面的下标
    private static BluetoothManager mBluetoothManager; 	//蓝牙设备管理器
    private BluetoothAdapter mBluetoothAdapter = null; 	//蓝牙适配器
    private Handler handler = new Handler();
    private long lastTime;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            // 在主线程          每隔几秒刷新
            if (System.currentTimeMillis() - lastTime > 2000)
            {
                currentId++;
                viewHandler.sendEmptyMessage(currentId);
                lastTime = System.currentTimeMillis();
            }
            handler.postDelayed(mRunnable, 2000);
        }
    };

    final Handler viewHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            //此处的 msg.what 其实就是 currentId ：表示当前轮播中显示图片的id
            viewPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        initViewPagerData();
        return view;
    }
    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        linearLayout = (LinearLayout)view.findViewById(R.id.paintview);
        userManagerBtn = (Button)view.findViewById(R.id.user_manager);
        dataManagerBtn = (Button)view.findViewById(R.id.vip_user);
        measureBtn  = (Button)view.findViewById(R.id.home_measure);

        measureBtn.setOnClickListener(this);
        dataManagerBtn.setOnClickListener(this);
        userManagerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent =null;
        switch (v.getId()){
            case R.id.user_manager:
                intent = new Intent(getActivity(), UserManagerActivity.class);
                break;
            case R.id.vip_user:
                intent = new Intent(getActivity(), UserSalectActivity.class);
                break;
            case R.id.home_measure:
                //获取安卓的蓝牙模块的管理权限
                mBluetoothManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
                mBluetoothAdapter = mBluetoothManager.getAdapter();

                //判断本台设备是否支持BLE
                if (mBluetoothAdapter == null) {
                    Toast.makeText(getActivity(), "ble not support", Toast.LENGTH_LONG).show();

                }

                //打开蓝牙
                if (!mBluetoothAdapter.isEnabled())
                {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, 2);
                }else{
                    intent = new Intent(getActivity(), MeasureActivity.class);
                }
                break;
                default:
                    break;
        }
        if(intent != null){
            getActivity().startActivity(intent);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){//打开成功
            Intent intent = new Intent(getActivity(), MeasureActivity.class);
            getActivity().startActivity(intent);
        }
    }

    @SuppressWarnings("deprecation")
    private void initViewPagerData(){
        //初始化图片资源
        imagesid = new int[]
                {
                        R.mipmap.banner_1, R.mipmap.banner_1,
                        R.mipmap.banner_1
                };
        imageViewsList = new ArrayList<ImageView>();

        // 添加图片
        for (int i = 0; i < imagesid.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imagesid[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewsList.add(imageView);
        }

        // 添加圆圈
        for (int i = 0; i < imagesid.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(R.drawable.not_pitch_viewpage_paint);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 5, 10, 10);
            imageView.setLayoutParams(params);
            linearLayout.addView(imageView);
        }

        // ViewPager 添加适配器
        viewPager.setAdapter(new MyViewPageAdapter());
        viewPager.setCurrentItem(300);
        // 设置滚动监听器
        viewPager.setOnPageChangeListener(new MyViewPagerListener());

        handler.postDelayed(mRunnable, 2000);
    }

    /**
     * ViewPage的适配器
     */
    class MyViewPageAdapter extends PagerAdapter {

        //返回总数量
        @Override
        public int getCount() {
            //此处的返回值必须是 Integer.MAX_VALUE:无限大，因为ViwePager是无限的在循环
            //设置成最大，用户看不到边界
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            //对ViewPager页号求模取出View列表中要显示的项的索引id
            int index = position % imageViewsList.size();
            try {
                ((ViewPager) container).addView((View) imageViewsList.get(index), 0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return imageViewsList.get(index);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            int index = position % imageViewsList.size();
            try {
                ((ViewPager) container).removeView((View) imageViewsList.get(index));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }


    /**
     * 给ViewPager添加监听器
     */
    class MyViewPagerListener implements ViewPager.OnPageChangeListener {

        // 当滑动状态改变的时候调用
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        // 当页面滑动的时候调用
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        // 单页面被选中的时候调用
        @Override
        public void onPageSelected(int position) {
            //ToastUtil.showMsg(getActivity(), "这是第" + position + "张轮播图片");
            currentId = position;
            position = position % imageViewsList.size();
            // 设置当前页
            setCurrentSelector(position);
            lastTime = System.currentTimeMillis();
        }

        public void setCurrentSelector(int index) {
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                ImageView temp = (ImageView) linearLayout.getChildAt(i);
                if (i == index) {
                    // 选中的时候下面的点显示的颜色
                    temp.setBackgroundResource(R.drawable.pitch_viewpage_paint);
                } else {
                    temp.setBackgroundResource(R.drawable.not_pitch_viewpage_paint);
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(mRunnable);
    }

}
