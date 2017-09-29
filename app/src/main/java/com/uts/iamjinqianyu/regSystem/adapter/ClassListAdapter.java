package com.uts.iamjinqianyu.regSystem.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.Views;
import com.uts.iamjinqianyu.regSystem.bean.Class;

import java.util.ArrayList;

/**
 * Created by iamji on 2017/9/24.
 */

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> implements Views {
    private ArrayList<Class> classes;
    private String currentView;

    public ClassListAdapter(ArrayList<Class> classes, String currentView) {
        this.classes = classes;
        this.currentView = currentView;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (currentView.equals(ADMIN_CLASSMANAGER)) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate((R.layout.item_class_list), parent, false);


        } else if (currentView.equals(STUDENT_CLASSLIST)) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate((R.layout.item_class_list), parent, false);
            //return new ViewHolder(view);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Class currentClass = classes.get(position);
        if (currentClass == null) {
            Log.d("List DEBUG", "no class here, position is " + position);
            //Log.d("List DEBUG", currentClass.getClassName());
        } else {
            holder.mClassNameTextView.setText(currentClass.getClassName());
            holder.mClassSizeTextView.setText(String.valueOf(currentClass.getSize()) + " slots remain");
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentView.equals(ADMIN_CLASSMANAGER)) {

                    } else if (currentView.equals(STUDENT_CLASSLIST)) {

                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView mClassNameTextView;
        public TextView mClassSizeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            mClassNameTextView = (TextView) itemView.findViewById(R.id.className);
            mClassSizeTextView = (TextView) itemView.findViewById(R.id.classSize);
        }
    }
}
