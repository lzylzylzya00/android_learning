package com.example.androidthread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "AsyncTaskActivity";
    private TextView statusTextView;
    private ProgressBar progressBar;
    private Button startTaskButton;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        initView();
    }

    private void initView() {
        statusTextView = findViewById(R.id.statusTextView);
        progressBar = findViewById(R.id.progressBar);
        startTaskButton = findViewById(R.id.startTaskButton);

        startTaskButton.setOnClickListener(v -> {
            // 禁用按钮，防止多次点击
            startTaskButton.setEnabled(false);
            // 执行AsyncTask
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(100);
        });
    }

    /**
     * AsyncTask<Params, Progress, Result>
     * Params: 执行时传入的参数类型
     * Progress: 进度更新的参数类型
     * Result: 执行结果的类型
     */
    private class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
        
        @Override
        protected void onPreExecute() {
            // 在主线程执行，任务开始前调用
            statusTextView.setText("任务开始执行");
            progressBar.setProgress(0);
        }

        @Override
        protected String doInBackground(Integer... params) {
            // 在工作线程执行，执行耗时操作
            int taskLength = params[0];
            int step = taskLength / 20; // 将任务分为20步
            
            try {
                for (int i = 0; i <= taskLength; i += step) {
                    // 模拟耗时操作
                    Thread.sleep(200);
                    // 发布进度更新
                    publishProgress(i);
                    
                    if (isCancelled()) {
                        return "任务被取消";
                    }
                }
            } catch (InterruptedException e) {
                return "任务被中断: " + e.getMessage();
            }
            
            return "任务完成";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 在主线程执行，更新UI进度
            int progress = values[0];
            progressBar.setProgress(progress);
            statusTextView.setText("进度: " + progress + "%");
        }

        @Override
        protected void onPostExecute(String result) {
            // 在主线程执行，任务完成后调用
            statusTextView.setText(result);
            startTaskButton.setEnabled(true);
        }

        @Override
        protected void onCancelled(String result) {
            // 在主线程执行，任务取消时调用
            statusTextView.setText(result);
            startTaskButton.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消正在执行的AsyncTask，防止内存泄漏
        if (myAsyncTask != null && myAsyncTask.getStatus() != AsyncTask.Status.FINISHED) {
            // 如果任务正在执行且未完成，取消任务
            myAsyncTask.cancel(true);
        }
    }
} 