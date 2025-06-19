package com.example.androidthread;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Button btnHandlerDemo = findViewById(R.id.btnHandlerDemo);
        btnHandlerDemo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HandlerActivity.class);
            startActivity(intent);
        });
        
        Button btnHandlerDemo2 = findViewById(R.id.btnHandlerDemo2);
        btnHandlerDemo2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HandlerActivity2.class);
            startActivity(intent);
        });
        
        Button btnHandlerDemo3 = findViewById(R.id.btnHandlerDemo3);
        btnHandlerDemo3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Handler3Activity.class);
            startActivity(intent);
        });
        
        Button btnAsyncTaskDemo = findViewById(R.id.btnAsyncTaskDemo);
        btnAsyncTaskDemo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
            startActivity(intent);
        });
    }
}