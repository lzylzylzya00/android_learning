package com.android.startmode;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

/**
 * @author: laizhiyu
 * @date: 2025/11/5
 * desc:
 */
public class SingleTaskTargetActivity extends BaseDebugActivity{

    private static final String TAG = "SingleTaskTarget";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent() called! HashCode: " + this.hashCode());
        // 更新 UI 显示“被复用”
        TextView tvInfo = findViewById(R.id.tv_info);
        if (tvInfo != null) {
            tvInfo.append("\n\n✅ 复用！onNewIntent 被调用");
        }
    }
}
