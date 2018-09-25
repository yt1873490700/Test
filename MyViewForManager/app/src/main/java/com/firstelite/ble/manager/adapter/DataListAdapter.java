package com.firstelite.ble.manager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.firstelite.ble.manager.R;

import java.util.List;


public class DataListAdapter extends BaseAdapter {
    private List<String> questionList;
    private Context context;
    private int defItem;
    public List<String> getQuestionList() {
        return questionList;
    }

    public DataListAdapter(Context context, List<String> questionList) {
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
        notifyDataSetChanged();
    }

    public void currentIndex(int index){
        defItem = index;
        refresh();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_date, null);

            holder.item1 = (TextView) convertView.findViewById(R.id.item_data);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(defItem == position ){
            holder.item1.setTextColor(Color.parseColor("#ffff0000"));
        }else{
            holder.item1.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.item1.setText(questionList.get(position));


        return convertView;
    }
    class ViewHolder {
        TextView item1;
    }
}
