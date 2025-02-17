package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button listview1 = findViewById(R.id.btn_listview_one);
        listview1.setOnClickListener(this);
        Button listview2 = findViewById(R.id.btn_listview_two);
        listview2.setOnClickListener(this);
        Button listview3 = findViewById(R.id.btn_listview_three);
        listview3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (v.getId() == R.id.btn_listview_one){
            listView1Actiivty();
        }
        if (v.getId() == R.id.btn_listview_two){
            listView2Activity();
        }

    }

    private void listView1Actiivty(){
        Intent listView1Intent = new Intent(this, ListView1Activity.class);
        this.startActivity(listView1Intent);
    }

    private void listView2Activity(){
        Intent listView2Itent = new Intent(this, ListView2Activity.class);
        startActivity(listView2Itent);
    }
}