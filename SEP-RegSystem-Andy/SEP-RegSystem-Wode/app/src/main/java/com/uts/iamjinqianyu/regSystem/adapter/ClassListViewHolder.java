package com.uts.iamjinqianyu.regSystem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uts.iamjinqianyu.regSystem.R;

/**
 * Created by iamji on 2017/9/24.
 */

public class ClassListViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TextView mClassNameTextView;
    public TextView mClassSizeTextView;
    public ImageView starImageView;

    public ClassListViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        mClassNameTextView = (TextView) itemView.findViewById(R.id.className);
        mClassSizeTextView = (TextView) itemView.findViewById(R.id.classSize);
        starImageView = (ImageView) itemView.findViewById(R.id.imageView_star);
    }

    public void setmClassNameTextView(String className) {
        mClassNameTextView.setText(className);
    }

    public void setmClassSizeTextView(String classSize) {
        mClassSizeTextView.setText(classSize);
    }
}
