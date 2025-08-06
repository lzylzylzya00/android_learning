package com.example.androidthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler3Activity extends AppCompatActivity {
    private static final String TAG = "Handler3Activity";
    private TextView textView;
    private Button startButton;
    private ExecutorService executorService;
    

    // 没有指定looper 自动绑定主线程looper
    private Handler mHandler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler3);
        
        initView();
        
        // 创建Handler实例

    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭线程池
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
        
        // 移除所有消息和回调，防止内存泄漏
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
    
    private void initView() {
        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);
        // 创建固定大小的线程池
        executorService = Executors.newFixedThreadPool(3);

        // 步骤1：在主线程中创建Handler实例
        //   mHandler = new Handler();

        // 步骤2：在工作线程中 发送消息到消息队列中 & 指定操作UI内容
        startButton.setOnClickListener(v -> {
            // 线程1
            executorService.execute(() -> {
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("使用post形式 延迟2秒执行");

                    }
                });
            });
            
            // 线程2 - 使用postDelayed方法
            executorService.execute(() -> {
                Log.d(TAG, "thread 2: " + Thread.currentThread().getName());
                
                // 使用post方法在主线程执行Runnable
                mHandler.postDelayed(() -> {
                    textView.setText("使用Handler.postDelayed方法延迟3秒执行");
                    Log.d(TAG, "postDelayed: " + Thread.currentThread().getName());
                }, 3000);
            });
            

        });


    }
} 