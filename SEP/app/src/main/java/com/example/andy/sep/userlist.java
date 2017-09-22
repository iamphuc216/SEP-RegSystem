package com.example.andy.sep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.sep.models.UserAdapter;
import com.example.andy.sep.models.UserDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.android.gms.tasks.OnCompleteListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class userlist extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView userList;
    private UserAdapter adapter;
    ArrayList<UserDetails> mUserList;
    private DatabaseReference mDatabase;
    private DatabaseReference usersRef;
    Callable ref;
    private final ArrayList<UserDetails> temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        userList = (ListView) findViewById(R.id.dUserList);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        usersRef = mDatabase.child("users");
        mUserList = new ArrayList<>();

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            final List<UserDetails> ulist = new ArrayList<UserDetails>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserDetails user = ds.getValue(UserDetails.class);
                    ulist.add(user);
                    //System.out.println(user.getUserType());
                }
                adapter = new UserAdapter(userlist.this, ulist);
                userList.setAdapter(adapter);
                userList.setOnItemClickListener(userlist.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    /*    ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersRef.addChildEventListener(childEventListener);*/

        /*if (mUserList.size() > 0) {
            adapter = new UserAdapter(this, mUserList);
            userList.setAdapter(adapter);
        } else {
            Toast.makeText(userlist.this, "No data: ", Toast.LENGTH_SHORT).show();
        }*/

    }

    @Override
    public void onItemClick(AdapterView<?> ada, View v, int position, long arg){
        TextView textView = v.findViewById(R.id.dUID);
        String selected = textView.getText().toString();
        Intent intent = new Intent(userlist.this, changeDetails.class);
        intent.putExtra("selectedID", selected);
        startActivity(intent);
    }


}
