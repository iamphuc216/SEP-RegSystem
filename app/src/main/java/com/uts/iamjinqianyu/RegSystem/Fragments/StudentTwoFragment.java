package com.uts.iamjinqianyu.RegSystem.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.RegSystem.Adapter.ClassListViewHolder;
import com.uts.iamjinqianyu.RegSystem.R;
import com.uts.iamjinqianyu.RegSystem.model.Class;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentTwoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference mFirebaseRef;
    private FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

    public StudentTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_two, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.classList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mFirebaseRef = mFirebaseInstance.getReference("class");
        mAdapter = new FirebaseRecyclerAdapter<Class, ClassListViewHolder>(
                Class.class, R.layout.item_class_list, ClassListViewHolder.class, mFirebaseRef) {
            @Override
            protected void populateViewHolder(ClassListViewHolder holder, Class currentClass, int position) {

                holder.setmClassNameTextView(currentClass.getClassName());
                holder.setmClassSizeTextView(String.valueOf(currentClass.getSize())
                        + " slots remain");
                progressBar.setVisibility(View.GONE);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
