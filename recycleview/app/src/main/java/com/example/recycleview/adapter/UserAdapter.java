package com.example.recycleview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.R;
import com.example.recycleview.bean.User;

import java.util.List;

/**
 * @author: laizhiyu
 * @date: 2025/9/5
 * desc:
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(User user, int position);
    }

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_recycle_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(user, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textEmail, textAge;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            textAge = itemView.findViewById(R.id.textAge);
        }

        public void bind(User user) {
            textName.setText(user.getName());
            textEmail.setText(user.getEmail());
            textAge.setText("年龄: " + user.getAge());
        }
    }
}
