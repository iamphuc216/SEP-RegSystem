package com.uts.iamjinqianyu.regSystem.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.regSystem.adapter.UserListViewHolder;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.User;
import com.uts.iamjinqianyu.regSystem.view.UserEditActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFunctionTwoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //private ArrayList<Recruiter> mRecruiterArrayList = new ArrayList<Recruiter>();

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
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        /*mRecyclerView = (RecyclerView) view.findViewById(R.id.user_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);*/
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("user");
        SetUpRecyclerView(view);
        //addRecruiterChangeListener();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do something", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        progressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        return view;
    }

    private void SetUpRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.user_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FirebaseRecyclerAdapter<User, UserListViewHolder>(User.class, R.layout.item_user_list, UserListViewHolder.class, mFirebaseDatabase) {
            @Override
            protected void populateViewHolder(UserListViewHolder holder, User current, int position) {
                final User user = current;
                String uID = getRef(position).getKey();
                holder.setmUsernameTextView(current.getName());
                Log.d("DEBUG", current.getName());
                holder.setmUserEmailTextView(current.getEmail());
                holder.setmUserType(current.getType());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), UserEditActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("passName", user.getName());
                        bundle.putString("passEmail", user.getEmail());
                        bundle.putString("passType", user.getType());
                        intent.putExtra("passBundle", bundle);
                        startActivity(intent);
                    }
                });

            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    /*private void addRecruiterChangeListener() {
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
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
