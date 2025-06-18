package com.example.androidthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerActivity extends AppCompatActivity {
    private TextView textView;
    private Button startButton;
    private Handler handler;
    private static final int UPDATE_TEXT = 1;
    private static final int SHOW_TOAST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);

        // 创建Handler，使用主线程的Looper
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_TEXT:
                        textView.setText("Handler消息已处理: " + msg.arg1);
                        break;
                    case SHOW_TOAST:
                        Toast.makeText(HandlerActivity.this,
                                "任务完成！", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        startButton.setOnClickListener(v -> {
            // 启动新线程执行耗时操作
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    try {
                        Thread.sleep(1000); // 模拟耗时操作

                        // 发送消息更新UI
                        Message message = handler.obtainMessage(UPDATE_TEXT, i, 0);
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 发送完成消息
                handler.sendEmptyMessage(SHOW_TOAST);
            }).start();
        });
    }
}