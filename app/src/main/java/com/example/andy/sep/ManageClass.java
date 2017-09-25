package com.example.andy.sep;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andy.sep.models.ClassAdapter;
import com.example.andy.sep.models.ClassInfo;
import com.example.andy.sep.models.UserAdapter;
import com.example.andy.sep.models.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageClass extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView classList;
    private ClassAdapter adapter;
    private DatabaseReference mDatabase;
    private DatabaseReference classRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_class);
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

        classList = (ListView) findViewById(R.id.classListView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        classRef = mDatabase.child("classes");

        classRef.addListenerForSingleValueEvent(new ValueEventListener() {
            final List<ClassInfo> clist = new ArrayList<>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ClassInfo c = ds.getValue(ClassInfo.class);
                    clist.add(c);
                    //System.out.println(user.getUserType());
                }
                adapter = new ClassAdapter(ManageClass.this, clist);
                classList.setAdapter(adapter);
                classList.setOnItemClickListener(ManageClass.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> ada, View v, int position, long arg){
        TextView textView = v.findViewById(R.id.classID);
        String selected = textView.getText().toString();
        Intent intent = new Intent(ManageClass.this, changeClassDetails.class);
        intent.putExtra("selectedID", selected);
        startActivity(intent);
    }
}
