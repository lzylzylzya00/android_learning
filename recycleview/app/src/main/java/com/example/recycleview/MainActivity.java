package com.example.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnItemDecoration;
    private Button btnShowUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setClickListeners();


    }

    private void initViews() {
        btnItemDecoration = findViewById(R.id.btn_item_decoretion);
        btnShowUser = findViewById(R.id.btn_show_user);

    }

    private void setClickListeners() {
        btnItemDecoration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemDecorationActivity.class);
                startActivity(intent);
            }
        });

        btnShowUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });

    }
}