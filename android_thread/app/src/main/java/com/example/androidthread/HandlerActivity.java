package com.example.androidthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * handler内存泄漏
 * 原因： 由于handler的使用 是通过实例化一个内部类或者是 匿名内部类。
 * 由于内部类会持有外部类的引用，当activity在其它线程执行时，activity销毁时，activity回收不了
 *
 * 解决方案 ：
 * 1.使用静态内部类 + 弱引用方式
 * 2.activity销毁的时候，清除消息队列中所有消息和回调
 */
public class HandlerActivity extends AppCompatActivity {
    private static String TAG = "HandlerActivity";
    private TextView textView;
    private Button startButton;
    private ExecutorService executorService;

    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initView();


        //方式1  步骤2：在主线程中创建Handler实例
        mHandler = new Myhandler(this);



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭线程池
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }

        // 解决内存泄漏方式2
        if (mHandler != null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }


    // handler使用  handler.sendMessage()
    // 方式1 ： 创建handler子类
    // 方式1 步骤1：（自定义）新创建Handler子类(继承Handler类) & 复写handleMessage（）方法
    static class  Myhandler extends Handler {
        private final WeakReference<Activity> mWeakReference;

        public Myhandler(Activity activity){
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            HandlerActivity activity = (HandlerActivity) mWeakReference.get();
            switch (msg.what) {
                case 1:
                    Log.d(TAG, "handleMessage: 1" + Thread.currentThread().getName());  // 显示在主线程 main
                    activity.textView.setText("handler消息已经处理 ： " + msg.what);

                case 2:
                    Log.d(TAG, "handleMessage: 2" + Thread.currentThread().getName()); // 显示在主线程 main
                    activity.textView.setText("handler消息已经处理 ： " + msg.what);
                default:
                    break;
            }
        }
    }


    private void initView() {
        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.startButton);
        // 创建固定大小的线程池
        executorService = Executors.newFixedThreadPool(3);

        startButton.setOnClickListener(v ->

        {
            //  实现多线程演示 线程1
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(6*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d(TAG, "thread 1" + Thread.currentThread().getName()); // pool-3-thread-1
                    // 方式1  步骤3 : 创建所需的消息对象
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = "A";
                    mHandler.sendMessage(msg);
                }

            });

            //  实现多线程演示 线程2
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(6*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d(TAG, "thread 2" + Thread.currentThread().getName()); // pool-3-thread-1
                    // 方式1  步骤3 : 创建所需的消息对象
                    Message msg = Message.obtain();
                    msg.what = 2;
                    msg.obj = "B";
                    mHandler.sendMessage(msg);
                }

            });



        }

        );

    }
}