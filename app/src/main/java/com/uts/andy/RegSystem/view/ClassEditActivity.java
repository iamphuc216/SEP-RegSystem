package com.uts.andy.RegSystem.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.andy.RegSystem.R;
import com.uts.andy.RegSystem.model.Class;

public class ClassEditActivity extends AppCompatActivity {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String classID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText classNameEditText = (EditText) findViewById(R.id.editText_addClassName);
        final EditText classSizeEditText = (EditText) findViewById(R.id.editText_addClassSize);

        Button okButton = (Button) findViewById(R.id.button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClass(classNameEditText.getText().toString(), classSizeEditText.getText().toString());
                Snackbar.make(v, "Class created !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                classNameEditText.setText("");
                classSizeEditText.setText("");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void createClass(String className, String ClassSize) {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("class");

        if (TextUtils.isEmpty(classID)) {
            classID = mFirebaseDatabase.push().getKey();

            Class newClass = new Class(className, Integer.parseInt(ClassSize));
            mFirebaseDatabase.child(classID).setValue(newClass);
            mFirebaseDatabase = mFirebaseInstance.getReference();
            //mFirebaseDatabase.child(classID).setValue("");
            /*mFirebaseDatabase.child(classID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });*/

        }

    }

}
