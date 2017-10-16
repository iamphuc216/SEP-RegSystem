package com.uts.iamjinqianyu.regSystem.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.adapter.ClassListViewHolder;
import com.uts.iamjinqianyu.regSystem.bean.Class;
import com.uts.iamjinqianyu.regSystem.view.ClassDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentOneFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView announcementTv;

    private FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
    private String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String classID;

    public StudentOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_one, container, false);
        announcementTv = (TextView) view.findViewById(R.id.announcementContent_rec);
        setUpAnnouncement();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference classReference = mFirebaseInstance.getReference(uID);
        mAdapter = new FirebaseRecyclerAdapter<Class, ClassListViewHolder>(
                Class.class, R.layout.item_class_list, ClassListViewHolder.class, classReference) {
            @Override
            protected void populateViewHolder(ClassListViewHolder holder, final Class currentClass, int position) {
                if (currentClass != null) {
                    classID = getRef(position).getKey();
                    holder.setmClassNameTextView(currentClass.getClassName());
                    holder.setmClassSizeTextView(currentClass.getSize() + " slots remain");
                    if (mRecyclerView.getVisibility() == View.INVISIBLE) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }

                }
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ClassDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("cID", classID);
                        bundle.putString("className", currentClass.getClassName());
                        bundle.putString("classSize", currentClass.getSize());
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void setUpAnnouncement(){
        DatabaseReference announcementRef = mFirebaseInstance.getReference("announcement");
        announcementRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String announcement = dataSnapshot.getValue(String.class);
                if (TextUtils.isEmpty(announcement) || announcement.length() == 0) {
                    announcementTv.setText("No announcement");
                } else {
                    announcementTv.setText(announcement);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
