package com.example.listview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listview.adapter.ListView3Adapter;
import com.example.listview.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListViewActivity3 extends AppCompatActivity {
    private final static String TAG = ListViewActivity3.class.getSimpleName();

    private List<GoodsBean> datas = new ArrayList<>();
    private Context mContext;
    private ListView3Adapter mListView3Adapter;

    private TextView numberTxt;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view2);

        listView = findViewById(R.id.lv_3);
        numberTxt = findViewById(R.id.tv_summer);

        mListView3Adapter = new ListView3Adapter(this,datas);
        listView.setAdapter(mListView3Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: ");
            }
        });

        Random random = new Random();

        for (int i = 0;i < 10;i++){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setPrice(1+random.nextInt(20));
            datas.add(goodsBean);
        }

        mListView3Adapter.notifyDataSetChanged();


        mListView3Adapter.setPricesListener(new ListView3Adapter.PricesListener() {
            @Override
            public void totalPrices() {
                int totalNumber = 0;
                for(GoodsBean bean : datas){
                    if (bean.isChoosed()){
                        totalNumber = totalNumber + bean.getNumber() * bean.getPrice();
                    }
                }

                numberTxt.setText("总计："+totalNumber);
            }
        });

    }
}