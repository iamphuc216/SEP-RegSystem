package com.uts.andy.RegSystem.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uts.andy.RegSystem.R;
import com.uts.andy.RegSystem.model.Recruiter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFunctionTwoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Recruiter> mRecruiterArrayList = new ArrayList<Recruiter>();

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    public AdminFunctionTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_function_two, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recruiter_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("user");
        addRecruiterChangeListener();
        return view;
    }

    private void addRecruiterChangeListener() {
        mFirebaseDatabase.orderByChild("type").equalTo("recruiter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot classSnapShot : dataSnapshot.getChildren()) {
                    Recruiter current = classSnapShot.getValue(Recruiter.class);
                    mRecruiterArrayList.clear();
                    //Log.d("DEBUG", current.getClassName());
                    //Log.d("DEBUG", String.valueOf(current.getSize()));
                    mRecruiterArrayList.add(current);
                    mAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", String.valueOf(mRecruiterArrayList.size()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
