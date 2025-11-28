package com.example.recycleview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.R;
import com.example.recycleview.adapter.ItemDecorationAdapter;
import com.example.recycleview.itemdecoretion.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: laizhiyu
 * @date: 2025/9/4
 * desc:
 */
public class ItemDecorationActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<HashMap<String,Object>> listItem;
    private ItemDecorationAdapter mItemDecorationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration);

        initData();

        initView();

    }

    private void initData(){
        listItem = new ArrayList<HashMap<String,Object>>();

        HashMap<String,Object> map1 = new HashMap<String,Object>();
        HashMap<String,Object> map2 = new HashMap<String,Object>();
        HashMap<String,Object> map3 = new HashMap<String,Object>();
        HashMap<String,Object> map4 = new HashMap<String,Object>();
        HashMap<String,Object> map5 = new HashMap<String,Object>();
        HashMap<String,Object> map6 = new HashMap<String,Object>();

        map1.put("ItemTitle","美国谷歌公司已发出");
        map1.put("ItemText","发件人:谷歌 CEO xxxx");
        listItem.add(map1);

        map2.put("ItemTitle", "国际顺丰已收入");
        map2.put("ItemText", "等待中转");
        listItem.add(map2);

        map3.put("ItemTitle", "国际顺丰转件中");
        map3.put("ItemText", "下一站中国");
        listItem.add(map3);

        map4.put("ItemTitle", "中国顺丰已收入");
        map4.put("ItemText", "下一站广州华南理工大学");
        listItem.add(map4);

        map5.put("ItemTitle", "中国顺丰派件中");
        map5.put("ItemText", "等待派件");
        listItem.add(map5);

        map6.put("ItemTitle", "华南理工大学已签收");
        map6.put("ItemText", "收件人:Carson");
        listItem.add(map6);

    }


    private void initView(){
        mRecyclerView = findViewById(R.id.rv_item_decoration);
        // 使用线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
      //  mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));

        mItemDecorationAdapter = new ItemDecorationAdapter(this,listItem);
        mRecyclerView.setAdapter(mItemDecorationAdapter);

    }
}

