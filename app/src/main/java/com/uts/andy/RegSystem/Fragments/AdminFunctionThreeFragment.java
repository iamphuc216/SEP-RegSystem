package com.uts.andy.RegSystem.Fragments;


import android.app.ProgressDialog;
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
import com.uts.andy.RegSystem.Adapter.ClassListViewHolder;
import com.uts.andy.RegSystem.R;
import com.uts.andy.RegSystem.model.Class;
import com.uts.andy.RegSystem.view.ClassEditActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFunctionThreeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Class> mClassArrayList = new ArrayList<Class>();

    private DatabaseReference databaseReference;
    private FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

    public AdminFunctionThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
                holder.setmClassNameTextView(currentClass.getClassName());
                holder.setmClassSizeTextView(String.valueOf(currentClass.getSize())
                        + " slots remain");

            }
        };

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot classSnapShot : dataSnapshot.getChildren()) {
                    Class current = classSnapShot.getValue(Class.class);
                    Log.d("DEBUG", current.getClassName());
                    Log.d("DEBUG", String.valueOf(current.getSize()));
                    mClassArrayList.add(current);
                    mAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", String.valueOf(mClassArrayList.size()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        //mAdapter = new ClassListAdapter(mClassArrayList, ADMIN_CLASSMANAGER);
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

    /*private void setUpClass() {
        Class classOne = new Class("Coding Class", 10);
        Class classTwo = new Class("Math class", 5);
        Class classThree = new Class("Music", 10);
        mClassArrayList.add(classOne);
        mClassArrayList.add(classTwo);
        mClassArrayList.add(classThree);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
