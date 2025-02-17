package com.example.listview;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listview.adapter.ListView1Adapter;

import java.util.ArrayList;
import java.util.List;

public class ListView1Activity extends AppCompatActivity {
    private static final String TAG = ListView1Activity.class.getSimpleName();

    private ListView mListView;
    private List<String> datas = new ArrayList<>();
    private ListView1Adapter mListView1Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        
        initListViewData();
    }

    private void initView() {
        mListView = findViewById(R.id.lv_1);
        mListView1Adapter = new ListView1Adapter(datas, this);
        mListView.setAdapter(mListView1Adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: datas = " + datas.get(position));
            }
        });
    }

    private void initListViewData() {
        for (int i = 0;i < 20;i++){
            datas.add("这是第一" + i + "数据");  // 后面加入也行？
        }

        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView1Adapter.notifyDataSetChanged();  // 去掉也行？
            }
        }, 5000);




    }


}