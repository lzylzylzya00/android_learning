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
import java.util.concurrent.TimeUnit;

/**
 * 通过匿名内部类创建handler对象
 */

public class HandlerActivity2 extends AppCompatActivity {
    private static final String TAG = "HandlerActivity2";
    private TextView textView;
    private Button startButton;
    private Button startButton2;
    private ExecutorService executorService;
    private Handler mHandler;
    

    

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler2);
        
        initView();
        

    }
    
    private void initView() {
        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);
        startButton2 = findViewById(R.id.startButton2);


        // 步骤1：在主线程中 通过匿名内部类 创建Handler类对象
        mHandler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        Log.d(TAG, "handleMessage: 1" + Thread.currentThread().getName());  // 显示在主线程 main
                        textView.setText("handler消息已经处理 ： " + msg.what);

                    case 2:
                        Log.d(TAG, "handleMessage: 2" + Thread.currentThread().getName()); // 显示在主线程 main
                        textView.setText("handler消息已经处理 ： " + msg.what);
                    default:
                        break;
                }
            }
        };
        
        // 创建固定大小的线程池
        executorService = Executors.newFixedThreadPool(2);
        
        // 设置第一个按钮点击事件 - 使用MyHandler
        startButton.setOnClickListener(v -> {
            // 禁用按钮，防止重复点击
            startButton.setEnabled(false);
            
            // 使用线程池执行任务1
            executorService.execute(() -> {
                try {
                    Log.d(TAG, "任务1开始: " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    // 步骤2 ： 在子线程中发送消息
                    // 创建消息并发送
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = "线程1";
                    mHandler.sendMessage(msg);
                    
                    // 延迟2秒后发送第二条消息
                    Thread.sleep(2000);
                    Message msg2 = Message.obtain();
                    msg2.what = 2;
                    msg2.obj = "线程1-延迟";
                    mHandler.sendMessage(msg2);
                } catch (InterruptedException e) {
                    Log.e(TAG, "任务1被中断", e);
                } finally {
                    // 恢复按钮状态
                    runOnUiThread(() -> startButton.setEnabled(true));
                }
            });
        });
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        // 清除所有消息和回调，防止内存泄漏
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        
        // 优雅关闭线程池
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
            try {
                // 等待任务完成，最多等待500毫秒
                if (!executorService.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }
} 