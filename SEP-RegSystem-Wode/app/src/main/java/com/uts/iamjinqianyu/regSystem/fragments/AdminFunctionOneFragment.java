package com.uts.iamjinqianyu.regSystem.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.view.AnnouncementEditActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFunctionOneFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference announcementRef = firebaseDatabase.getReference("announcement");

    public AdminFunctionOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_function_one, container, false);
        final TextView announcementTv = (TextView) view.findViewById(R.id.announcementContent_TextView);

        announcementRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String announcement = dataSnapshot.getValue(String.class);
                if (announcement != null) {
                    announcementTv.setText(announcement);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(getContext(), AnnouncementEditActivity.class));
            }
        });
        return view;
    }

}
