package com.uts.andy.RegSystem.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uts.andy.RegSystem.R;

/**
 * Created by iamji on 2017/9/24.
 */

public class ClassListViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public TextView mClassNameTextView;
    public TextView mClassSizeTextView;

    public ClassListViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        mClassNameTextView = (TextView) itemView.findViewById(R.id.className);
        mClassSizeTextView = (TextView) itemView.findViewById(R.id.classSize);
    }

    public void setmClassNameTextView (String className) {
        mClassNameTextView.setText(className);
    }

    public void setmClassSizeTextView (String classSize) {
        mClassSizeTextView.setText(classSize);
    }
}
