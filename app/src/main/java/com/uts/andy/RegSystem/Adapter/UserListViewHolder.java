package com.uts.andy.RegSystem.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uts.andy.RegSystem.R;

/**
 * Created by iamji on 2017/9/25.
 */

public class UserListViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TextView mUsernameTextView;
    public TextView mUserEmailTextView;
    public TextView mUserType;
    public UserListViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        mUsernameTextView = (TextView) itemView.findViewById(R.id.userName);
        mUserEmailTextView = (TextView) itemView.findViewById(R.id.userEmail);
        mUserType = (TextView) itemView.findViewById(R.id.userType);
    }

    public void setmUsernameTextView(String userName){
        mUsernameTextView.setText(userName);
    }
    public void setmUserEmailTextView(String email){
        mUserEmailTextView.setText(email);
    }
    public void setmUserType(String type){
        mUserType.setText(type);
    }
}
