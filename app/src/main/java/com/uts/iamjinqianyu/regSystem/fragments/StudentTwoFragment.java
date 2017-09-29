package com.uts.iamjinqianyu.regSystem.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.regSystem.adapter.ClassListViewHolder;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.Class;
import com.uts.iamjinqianyu.regSystem.view.ClassDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentTwoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    TextView noClassTv;

    private DatabaseReference mFirebaseRef;
    private FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

    private boolean hasClass = false;

    public StudentTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_two, container, false);
        noClassTv = (TextView) view.findViewById(R.id.NoClassTv);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.classList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mFirebaseRef = mFirebaseInstance.getReference("class");
        mAdapter = new FirebaseRecyclerAdapter<Class, ClassListViewHolder>(
                Class.class, R.layout.item_class_list, ClassListViewHolder.class, mFirebaseRef) {
            @Override
            protected void populateViewHolder(ClassListViewHolder holder, final Class currentClass, int position) {
                //hasClass = false;
                if (currentClass != null) {
                    hasClass = true;
                    final String cId = getRef(position).getKey();
                    holder.setmClassNameTextView(currentClass.getClassName());
                    holder.setmClassSizeTextView(String.valueOf(currentClass.getSize())
                            + " slots remain");
                    holder.starImageView.setVisibility(View.VISIBLE);
                    holder.view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ClassDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("cID", cId);
                            bundle.putString("className", currentClass.getClassName());
                            bundle.putString("classSize", currentClass.getSize());
                            intent.putExtra("bundle", bundle);
                            startActivity(intent);


                        }
                    });

                    if (mRecyclerView.getVisibility() ==  View.INVISIBLE){
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }

                } else {
                    noClassTv.setVisibility(View.VISIBLE);
                }

            }

        };

        mRecyclerView.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
        /*if (hasClass) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else if (!hasClass){
            noClassTv.setVisibility(View.VISIBLE);
        }*/

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
