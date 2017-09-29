package com.uts.iamjinqianyu.regSystem.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.uts.iamjinqianyu.regSystem.adapter.ClassListViewHolder;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.Class;
import com.uts.iamjinqianyu.regSystem.view.ClassEditActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFunctionThreeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private ArrayList<Class> mClassArrayList = new ArrayList<Class>();

    private DatabaseReference databaseReference;
    private FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

    public AdminFunctionThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_class_manager, container, false);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        //setUpClass();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.Admin_classList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        databaseReference = mFirebaseInstance.getReference("class");
        mAdapter = new FirebaseRecyclerAdapter<Class, ClassListViewHolder>(
                Class.class, R.layout.item_class_list, ClassListViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(ClassListViewHolder holder, Class currentClass, int position) {
                final Class c = currentClass;
                final String classID = getRef(position).getKey();
                holder.setmClassNameTextView(c.getClassName());
                holder.setmClassSizeTextView(c.getSize()
                        + " slots remain");
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext() , ClassEditActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("PassClassID", classID);
                        bundle.putString("PassClassName", c.getClassName());
                        bundle.putString("PassClassSize", c.getSize());
                        intent.putExtra("PassBundle", bundle);
                        startActivity(intent);
                    }
                });

            }
        };

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "编辑课程", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(getContext(), ClassEditActivity.class));

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

        fab.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
