package com.example.listview.bean;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listview.R;
import com.example.listview.adapter.ListView3Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListView3Activity extends AppCompatActivity {

    private ListView listView;
    private ListView3Adapter listView3Adapter;
    private List<GoodsBean> datas = new ArrayList<>();

    private TextView numberTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view3);

        listView = findViewById(R.id.ac_listview3);
        numberTxt = findViewById(R.id.ac_listview3_number);

        listView3Adapter = new ListView3Adapter(this,datas);
        listView.setAdapter(listView3Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        initData();


    }

    private void initData() {

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setPrice(1 + random.nextInt(20));
            datas.add(goodsBean);

        }

        listView3Adapter.notifyDataSetChanged();
    }

    private void initListener() {

    }


}