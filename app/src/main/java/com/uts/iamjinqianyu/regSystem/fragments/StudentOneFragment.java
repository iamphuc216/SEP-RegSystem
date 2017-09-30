package com.uts.iamjinqianyu.regSystem.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.adapter.ClassListViewHolder;
import com.uts.iamjinqianyu.regSystem.bean.Class;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentOneFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference mFirebaseRef;
    private FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
    private String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public StudentOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_one, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mFirebaseRef = mFirebaseInstance.getReference(uID);
        mAdapter = new FirebaseRecyclerAdapter<Class, ClassListViewHolder>(
                Class.class, R.layout.item_class_list, ClassListViewHolder.class, mFirebaseRef) {
            @Override
            protected void populateViewHolder(ClassListViewHolder holder, final Class currentClass, int position) {
                if (currentClass != null) {
                    final String cID = getRef(position).getKey();
                    holder.setmClassNameTextView(currentClass.getClassName());

                    if (mRecyclerView.getVisibility() ==  View.INVISIBLE){
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }

                }
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
