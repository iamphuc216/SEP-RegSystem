package com.uts.andy.RegSystem.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uts.andy.RegSystem.R;
import com.uts.andy.RegSystem.model.User;

import java.util.ArrayList;

/**
 * Created by iamji on 2017/9/24.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private ArrayList<User> recruiterArrayList = new ArrayList<>();

    public UserListAdapter(ArrayList<User> recruiterArrayList) {
        this.recruiterArrayList = recruiterArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.item_user_list), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User current = recruiterArrayList.get(position);
        holder.mUsernameTextView.setText(current.getName());
        holder.mUserEmailTextView.setText(current.getEmail());
        holder.mUserType.setText(current.getType());

    }

    @Override
    public int getItemCount() {
        return recruiterArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView mUsernameTextView;
        public TextView mUserEmailTextView;
        public TextView mUserType;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            mUsernameTextView = (TextView) itemView.findViewById(R.id.userName);
            mUserEmailTextView = (TextView) itemView.findViewById(R.id.userEmail);
            mUserType = (TextView) itemView.findViewById(R.id.userType);
        }
    }
}
