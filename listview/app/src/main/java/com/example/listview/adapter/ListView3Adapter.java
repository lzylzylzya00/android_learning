package com.example.listview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.listview.bean.GoodsBean;

import java.util.List;

/**
 * @author: laizhiyu
 * @date: 2025/2/17
 * desc:
 */
public class ListView3Adapter extends BaseAdapter {

    private List<GoodsBean> datas;
    private Context context;


    public ListView3Adapter(Context context, List<GoodsBean> datas){
        this.context = context;
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    class ViewHolder{

    }
}
