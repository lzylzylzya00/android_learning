package com.android.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: laizhiyu
 * @date: 2025/11/5
 * desc:
 */
public class AActivity extends AppCompatActivity {

    private static int sCount = 0;
    private int mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        mInstance = ++sCount;



        findViewById(R.id.btn_to_b).setOnClickListener(v ->
                startActivity(new Intent(this, BActivity.class)));

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("AActivity", "onNewIntent called! Instance: " + mInstance);
        // 注意：standard 模式下，onNewIntent 后通常会重建！
    }


}
