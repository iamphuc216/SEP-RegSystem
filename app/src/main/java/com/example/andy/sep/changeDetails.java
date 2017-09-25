package com.example.andy.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.sep.models.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class changeDetails extends AppCompatActivity {

    private Spinner typelist;
    private String uID;
    private DatabaseReference mDatabase;
    private EditText uFirst;
    private EditText uLast;
    private EditText uReligion;
    private EditText uType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_details);

        final List<String> list = new ArrayList<String>();
        list.add("stu");
        list.add("rec");
        list.add("admin");

        uID = getIntent().getStringExtra("selectedID");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uID);
        uFirst = (EditText) findViewById(R.id.uFirstName);
        uLast = (EditText) findViewById(R.id.uLastName);
        uReligion = (EditText) findViewById(R.id.uReligion);

        Button update = (Button) findViewById(R.id.toUpdate);
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                changeInfo(uID);
            }
        });

        typelist = (Spinner) findViewById(R.id.uType);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typelist.setAdapter(adp1);

        typelist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
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
                uFirst.setText(user.first_name);
                uLast.setText(user.last_name);
                uReligion.setText(user.religion);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(Listener);
    }

    private void changeInfo(String userID){
        String first_name = uFirst.getText().toString().trim();
        String last_name = uLast.getText().toString().trim();
        String religion = uReligion.getText().toString().trim();
        String user_type = typelist.getSelectedItem().toString().trim();

        //UserDetails ud = new UserDetails(userID, first_name, last_name, religion, user_type);
        /*String key = mDatabase.child("users").push().getKey();
        Map<String, Object> Values = ud.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + key, Values);
        childUpdates.put("/users/" + uID + "/" + key, Values);
        mDatabase.updateChildren(childUpdates);*/
        mDatabase.child("first_name").setValue(first_name);
        mDatabase.child("last_name").setValue(last_name);
        mDatabase.child("religion").setValue(religion);
        mDatabase.child("userType").setValue(user_type);
        Toast.makeText(changeDetails.this, "Updated.",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(changeDetails.this, Admin.class);
        intent.putExtra("UID", "Test");
        startActivity(intent);
    }

}
