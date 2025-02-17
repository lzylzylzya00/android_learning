package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listview.adapter.ListView2Adapter;
import com.example.listview.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListView2Activity extends AppCompatActivity {

    private ListView listView;
    private ListView2Adapter listView1Adapter;
    private List<NewsBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.list_view2_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.lv_listview2);
        listView1Adapter = new ListView2Adapter(this,datas);
        listView.setAdapter(listView1Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        initData();
    }

    private void initData() {

        for (int i=0;i<48;i++){
            NewsBean newsBean = new NewsBean();

            Random random = new Random();
            int number = random.nextInt(6);
            if (number%2 == 0){
                newsBean.setItemType(1);
            }else if (number%3 == 0){
                newsBean.setItemType(0);
            }else {
                newsBean.setItemType(2);
            }


            datas.add(newsBean);
        }

        listView1Adapter.notifyDataSetChanged();

    }
}