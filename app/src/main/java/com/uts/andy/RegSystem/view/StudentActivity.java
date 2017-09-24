package com.uts.andy.RegSystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.uts.andy.RegSystem.Adapter.ClassListAdapter;
import com.uts.andy.RegSystem.R;
import com.uts.andy.RegSystem.Views;
import com.uts.andy.RegSystem.model.Class;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity implements Views{
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Class> mClassArrayList = new ArrayList<Class>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpClass();

        mRecyclerView = (RecyclerView) findViewById(R.id.classList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ClassListAdapter(mClassArrayList, STUDENT_CLASSLIST);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "sign out", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                firebaseAuth.signOut();
                startActivity(new Intent(StudentActivity.this, EntryActivity.class));

            }
        });
    }

    private void setUpClass() {
        Class classOne = new Class("Coding Class", 10);
        Class classTwo = new Class("Math class", 5);
        mClassArrayList.add(classOne);
        mClassArrayList.add(classTwo);
    }

}
