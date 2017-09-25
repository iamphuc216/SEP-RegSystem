package com.example.andy.sep.models;

import android.content.Context;
import android.os.TestLooperManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andy.sep.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gintama on 17/09/2017.
 */

public class UserAdapter extends BaseAdapter{
    private  Context mContext;
    private List<UserDetails> mUserList;
    private TextView uName;
    private TextView uType;
    private TextView UID;
    private ImageView uPicture;


    public UserAdapter(Context mContext, List<UserDetails> mUserList) {
        this.mContext = mContext;
        this.mUserList = mUserList;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.user_listformat, null);
        uName = v.findViewById(R.id.dName);
        uType = v.findViewById(R.id.dUserType);
        UID = v.findViewById(R.id.dUID);
        uPicture = v.findViewById(R.id.dPicture);

        uName.setText(mUserList.get(i).getFirst_name() + " " + mUserList.get(i).getLast_name());
        uType.setText("UserType: " + mUserList.get(i).getUserType());
        UID.setText(mUserList.get(i).getUserId());

        v.setTag(mUserList.get(i).getUserId());
        return v;
    }


}
