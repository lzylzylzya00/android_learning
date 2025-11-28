package com.example.recycleview;

/**
 * @author: laizhiyu
 * @date: 2025/9/5
 * desc:
 */
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.adapter.UserAdapter;
import com.example.recycleview.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_activity);

        initViews();
        initData();
        setupRecyclerView();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initData() {
        userList = new ArrayList<>();
        userList.add(new User("张三", "zhangsan@example.com", 25));
        userList.add(new User("李四", "lisi@example.com", 30));
        userList.add(new User("王五", "wangwu@example.com", 28));
        userList.add(new User("赵六", "zhaoliu@example.com", 35));
        userList.add(new User("钱七", "qianqi@example.com", 22));
        userList.add(new User("孙八", "sunba@example.com", 27));
        userList.add(new User("周九", "zhoujiu@example.com", 32));
        userList.add(new User("吴十", "wushi@example.com", 29));
        userList.add(new User("郑一", "zhengyi@example.com", 26));
        userList.add(new User("王二", "wanger@example.com", 31));
    }

    private void setupRecyclerView() {
        adapter = new UserAdapter(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 设置点击事件监听
        adapter.setOnItemClickListener((user, position) -> {
            Toast.makeText(UserListActivity.this,
                    "点击了: " + user.getName(),
                    Toast.LENGTH_SHORT).show();
        });
    }
}