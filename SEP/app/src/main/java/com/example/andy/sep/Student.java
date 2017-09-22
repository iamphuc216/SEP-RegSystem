package com.example.andy.sep;

import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.text.TextUtils;
import android.content.Intent;
import android.widget.Toast;


import com.example.andy.sep.models.ClassAdapter;
import com.example.andy.sep.models.ClassInfo;
import com.example.andy.sep.models.UserDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DatabaseReference mDatabase;
    private DatabaseReference classRef;
    private DatabaseReference enrollment;
    private ListView classListOfStu;
    private ClassAdapter adapter;
    private String UID;
    private TextView hello;
    private static final String TAG = "PostDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        UID = getIntent().getStringExtra("UID");
        hello = (TextView) findViewById(R.id.test1);
        classListOfStu = (ListView) findViewById(R.id.classListForStu);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(UID);
        classRef = FirebaseDatabase.getInstance().getReference().child("classes");
        enrollment = FirebaseDatabase.getInstance().getReference().child("enrollments");

        Button logout = (Button) findViewById(R.id.logoutStu);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Student.this, MainActivity.class);
                intent.putExtra("UID", "Test");
                startActivity(intent);
            }
        });


        hello.setText("Hi" + UID);

        classRef.addListenerForSingleValueEvent(new ValueEventListener() {
            final List<ClassInfo> clist = new ArrayList<>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ClassInfo c = ds.getValue(ClassInfo.class);
                    clist.add(c);
                    //System.out.println(user.getUserType());
                }
                adapter = new ClassAdapter(Student.this, clist);
                classListOfStu.setAdapter(adapter);
                classListOfStu.setOnItemClickListener(Student.this);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        ValueEventListener Listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                UserDetails user = dataSnapshot.getValue(UserDetails.class);
                // [START_EXCLUDE]
                hello.setText("Hi, " + user.first_name + user.last_name);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(Student.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mDatabase.addValueEventListener(Listener);
    }

    @Override
    public void onItemClick(AdapterView<?> ada, View v, int position, long arg){
        TextView textView = v.findViewById(R.id.classID);
        final String selected = textView.getText().toString();

        enrollment.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.child(selected).child(UID).getValue() == null) {
                    mutableData.child(selected).child(UID).setValue(Integer.toString(1));
                } else {
                    int count = mutableData.getValue(Integer.class);
                    mutableData.child(selected).child(UID).setValue(Integer.toString(count + 1));
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean success, DataSnapshot dataSnapshot) {
                // Analyse databaseError for any error during increment
            }
        });
        //enrollment.child(selected).push().setValue(UID);
        Toast.makeText(Student.this, "Enrolled in this class!",
                Toast.LENGTH_SHORT).show();
    }

}
