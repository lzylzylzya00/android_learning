package com.example.listview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listview.R;

import java.util.List;

/**
 * @author: laizhiyu
 * @date: 2025/2/17
 * desc:
 */
public class ListView1Adapter extends BaseAdapter {
    private List<String> datas;
    private Context mContext;

    public ListView1Adapter(List<String> datas, Context context) {
        this.datas = datas;
        Log.d("TAG", "ListView1Adapter: 拿数据 ");
        mContext = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview1_list_item, parent, false);
            viewHolder.name = convertView.findViewById(R.id.ad_listview1_name);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(datas.get(position));

        return convertView;
    }


    class ViewHolder {
        TextView name;
    }
}
