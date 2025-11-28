package com.android.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: laizhiyu
 * @date: 2025/11/4
 * desc:
 */
public class BaseDebugActivity extends AppCompatActivity {

    private TextView tvInfo;
    private Button btnLaunchOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        tvInfo = findViewById(R.id.tv_info);
        btnLaunchOther = findViewById(R.id.btn_launch_other);

        displayInfo();

        // 点击按钮可再次启动自己（用于测试栈行为）
       /* btnLaunchOther.setOnClickListener(v -> {
            Intent intent = new Intent(this, this.getClass());
            startActivity(intent);
        });*/

        // 点击可再次启动 TargetActivity（用于测试栈）
        btnLaunchOther.setOnClickListener(v -> {
            Intent intent = new Intent(this, TargetActivity.class);
            startActivity(intent);
        });

    }

    private void displayInfo() {
        int taskId = getTaskId();
        String mode = this.getClass().getSimpleName().replace("Activity", "");
        String info = String.format(
                "Launch Mode: %s\nTask ID: %d\nInstance Hash: %d",
                mode, taskId, this.hashCode()
        );
        tvInfo.setText(info);


        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("LaunchFlagDemo", getClass().getSimpleName() + " onNewIntent called");
    }
}
