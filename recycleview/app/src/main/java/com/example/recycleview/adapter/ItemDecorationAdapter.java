package com.example.recycleview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: laizhiyu
 * @date: 2025/9/4
 * desc:
 */
public class ItemDecorationAdapter extends RecyclerView.Adapter<ItemDecorationAdapter.Viewholder> {
    private LayoutInflater mInflater;
    private ArrayList<HashMap<String,Object>> mList;


    //构造函数，传入数据
    public ItemDecorationAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        mInflater = LayoutInflater.from(context);
        this.mList = listItem;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(mInflater.inflate(R.layout.item_decoration_recycle_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Viewholder viewholder = holder;
        // 绑定数据到ViewHolder里面
        viewholder.Title.setText((String) mList.get(position).get("ItemTitle"));
        viewholder.Text.setText((String) mList.get(position).get("ItemText"));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //定义Viewholder
    class Viewholder extends RecyclerView.ViewHolder  {
        private TextView Title, Text;

        public Viewholder(View root) {
            super(root);
            Title = (TextView) root.findViewById(R.id.Itemtitle);
            Text = (TextView) root.findViewById(R.id.Itemtext);

        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText() {
            return Text;
        }

    }
}
