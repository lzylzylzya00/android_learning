package com.android.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

        findViewById(R.id.btn_standard).setOnClickListener(v ->
                startActivity(new Intent(this, StandardActivity.class)));

        findViewById(R.id.btn_single_top).setOnClickListener(v ->
                startActivity(new Intent(this, SingleTopActivity.class)));

        findViewById(R.id.btn_single_task).setOnClickListener(v ->
                startActivity(new Intent(this, SingleTaskActivity.class)));

        findViewById(R.id.btn_single_instance).setOnClickListener(v ->
                startActivity(new Intent(this, SingleInstanceActivity.class)));


        findViewById(R.id.btn_new_task).setOnClickListener(v -> {
            Intent intent = new Intent(this, TargetActivity.class);
            // 启动一个新的Task（任务栈）。如果目标 Activity 已存在于某个 Task 中，则直接将该 Task 带到前台，不创建新实例（类似singleTas行为）
            //注意：如果 TargetActivity 的 taskAffinity 和 MainActivity 相同（默认就是包名），系统可能不会创建新 Task
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        findViewById(R.id.btn_clear_top).setOnClickListener(v -> {
            Intent intent = new Intent(this, TargetActivity.class);
            // 如果目标 Activity 已存在于当前 Task 中，则清除它之上的所有 Activity，并复用该实例（调用onNewIntent()）。
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        findViewById(R.id.btn_single_top_flag).setOnClickListener(v -> {
            Intent intent = new Intent(this, TargetActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        findViewById(R.id.btn_clear_task).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        findViewById(R.id.btn_no_history).setOnClickListener(v -> {
            Intent intent = new Intent(this, TargetActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });



        findViewById(R.id.btn_new_task_standard).setOnClickListener(v -> {
            Intent intent = new Intent(this, TargetActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(this, "启动 Standard + NEW_TASK", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btn_new_task_single_task).setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleTaskTargetActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(this, "启动 SingleTask + NEW_TASK", Toast.LENGTH_SHORT).show();
        });


        findViewById(R.id.btn_to_a).setOnClickListener(v ->
        {
            /*Intent intent = new Intent(this, AActivity.class);
            startActivity(intent);*/

            Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.lingxi");
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                // fallback: 显示提示
                Toast.makeText(this, "无法启动应用", Toast.LENGTH_SHORT).show();
            }
        });
    }

}