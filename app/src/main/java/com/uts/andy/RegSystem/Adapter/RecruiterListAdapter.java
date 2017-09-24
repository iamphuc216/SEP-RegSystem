package com.uts.andy.RegSystem.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uts.andy.RegSystem.R;
import com.uts.andy.RegSystem.model.Recruiter;

import java.util.ArrayList;

/**
 * Created by iamji on 2017/9/24.
 */

public class RecruiterListAdapter extends RecyclerView.Adapter<RecruiterListAdapter.ViewHolder> {
    private ArrayList<Recruiter> recruiterArrayList = new ArrayList<>();

    public RecruiterListAdapter(ArrayList<Recruiter> recruiterArrayList) {
        this.recruiterArrayList = recruiterArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.item_class_list), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recruiter current = recruiterArrayList.get(position);
        holder.mClassNameTextView.setText(current.getName());
        holder.mClassSizeTextView.setText(current.getEmail());

    }

    @Override
    public int getItemCount() {
        return recruiterArrayList.size();
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
