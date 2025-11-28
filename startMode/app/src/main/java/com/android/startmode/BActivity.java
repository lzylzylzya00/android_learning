package com.android.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: laizhiyu
 * @date: 2025/11/5
 * desc:
 */
public class BActivity extends AppCompatActivity {

    private static int sCount = 0;
    private int mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mInstance = ++sCount;


        /**
         * 当你启动一个已在栈中的 Activity 并加上 CLEAR_TOP：  FLAG_ACTIVITY_CLEAR_TOP 到底做了什么？
         *步骤 1：系统找到该 Activity 实例（比如 A）
         * 步骤 2：销毁 A 之上的所有 Activity（比如 B、C）
         * 步骤 3：对 A 本身如何处理？这取决于它的 launchMode！
         *
         * launchMode
         *  standard  ❌ 销毁 A，再新建一个 A（调用onCreate）  即使 CLEAR_TOP 找到了旧实例，系统仍会 先 destroy 旧的，再 create 新的,这保证了 standard 的行为一致性：永远不复用
         *  singleTop    复用 A，调用 onNewIntent()
         *  singleTask/singleInstance    复用 A，调用 onNewIntent()
         */
        findViewById(R.id.btn_clear_top_to_a).setOnClickListener(v -> {
            Intent intent = new Intent(this, AActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("BActivity", "onNewIntent called! Instance: " + mInstance);
        // 注意：standard 模式下，onNewIntent 后通常会重建！
    }
}
