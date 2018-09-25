package com.firstelite.ble.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.firstelite.ble.manager.R;
import java.util.List;

public class NameListAdapter extends BaseAdapter {
    private List<String> questionList;
    private Context context;
    private int defItem;
    public List<String> getQuestionList() {
        return questionList;
    }

    public NameListAdapter(Context context, List<String> questionList) {
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

        NameListAdapter.this.notifyDataSetChanged();
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
                    .inflate(R.layout.list_parent_item, null);

            holder.item1 = (TextView) convertView.findViewById(R.id.item1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(defItem == position ){
            holder.item1.setBackgroundResource(R.mipmap.item_click);
        }else{
            holder.item1.setBackgroundResource(android.R.color.transparent);
        }

        holder.item1.setText(questionList.get(position));


        return convertView;
    }
    class ViewHolder {
        TextView item1;
    }
}
