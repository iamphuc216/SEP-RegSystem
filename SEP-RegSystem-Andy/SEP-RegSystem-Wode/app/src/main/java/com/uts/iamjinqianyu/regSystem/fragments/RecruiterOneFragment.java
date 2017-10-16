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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.adapter.SurveyListViewHolder;
import com.uts.iamjinqianyu.regSystem.bean.Survey;
import com.uts.iamjinqianyu.regSystem.view.SurveyDetailActivity;

public class RecruiterOneFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView announcementTv;

    private FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
    private String newUID;

    public RecruiterOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recruiter_one, container, false);
        announcementTv = (TextView) view.findViewById(R.id.announcementContent_rec);
        setUpAnnouncement();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_survey);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference surveyReference = mFirebaseInstance.getReference("newUsers");

        mAdapter = new FirebaseRecyclerAdapter<Survey, SurveyListViewHolder>(
                Survey.class, R.layout.item_survey_list, SurveyListViewHolder.class, surveyReference) {
            @Override
            protected void populateViewHolder(SurveyListViewHolder holder, final Survey currentSurvey, int position) {
                if (currentSurvey != null) {
                    newUID = getRef(position).getKey();
                    holder.setApplicantTextView(currentSurvey.getFirstname() + " " + currentSurvey.getLastname());

                    if (mRecyclerView.getVisibility() == View.INVISIBLE) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }

                }
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), SurveyDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("firstName", currentSurvey.getFirstname());
                        bundle.putString("lastName", currentSurvey.getLastname());
                        bundle.putString("phone", currentSurvey.getPhone());
                        bundle.putString("userType", currentSurvey.getUserType());
                        bundle.putString("na", currentSurvey.getNationality());
                        bundle.putString("re", currentSurvey.getReligion());
                        bundle.putString("interest", String.valueOf(currentSurvey.getInterest()));
                        bundle.putString("email", currentSurvey.getEmail());
                        bundle.putString("knowledge", String.valueOf(currentSurvey.getKnowledge()));
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void setUpAnnouncement() {
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