package com.example.andy.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andy.sep.models.ClassInfo;
import com.example.andy.sep.models.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class changeClassDetails extends AppCompatActivity {
    private String cID;
    private DatabaseReference mDatabase;
    private EditText cName;
    private EditText cTime;
    private EditText cSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_class_details);

        cID = getIntent().getStringExtra("selectedID");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("classes").child(cID);
        cTime = (EditText) findViewById(R.id.eCTime);
        cName = (EditText) findViewById(R.id.eCName);
        cSize = (EditText) findViewById(R.id.eCSize);

        Button update = (Button) findViewById(R.id.toUpdateClass);
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                changeInfo(cID);
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
                ClassInfo c = dataSnapshot.getValue(ClassInfo.class);
                // [START_EXCLUDE]
                cTime.setText(c.getClassTime());
                cSize.setText(c.getClassSize());
                cName.setText(c.getClassName());
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(Listener);
    }
    private void changeInfo(String classID){
        String time = cTime.getText().toString().trim();
        String name = cName.getText().toString().trim();
        String size = cSize.getText().toString().trim();

        mDatabase.child("className").setValue(name);
        mDatabase.child("classSize").setValue(size);
        mDatabase.child("classTime").setValue(time);

        Toast.makeText(changeClassDetails.this, "Updated.",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(changeClassDetails.this, Admin.class);
        intent.putExtra("UID", "Test");
        startActivity(intent);
    }
}
