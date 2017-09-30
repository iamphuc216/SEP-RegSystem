package com.uts.iamjinqianyu.regSystem.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.Class;

public class ClassDetailActivity extends AppCompatActivity {
    TextView classNameTv;
    TextView classSizeTv;
    FloatingActionButton fab;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DatabaseReference personalRef;
    String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    String classId;
    Boolean isEnrolled = false;
    String className;
    String classSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_class_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        classNameTv = (TextView) findViewById(R.id.ClassNameTv);
        classSizeTv = (TextView) findViewById(R.id.classSizeTv);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        className = bundle.getString("className");
        classSize = bundle.getString("classSize");
        classNameTv.setText(bundle.getString("className"));
        classSizeTv.setText(bundle.getString("classSize") + " slots remain");
        classId = bundle.getString("cID");
        //isEnrolled();
        new QueueIsEnrolled().execute();


        fab = (FloatingActionButton) findViewById(R.id.fab);
        //toggleFab();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEnrolled();
                Log.d("DEBUG", String.valueOf(isEnrolled));

                if (!isEnrolled) {
                    enrollClass();
                    Snackbar.make(view, "Enroll in !", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d("DEBUG", String.valueOf(isEnrolled));
                    //isEnrolled = true;
                } else {
                    enrollClass();
                    Snackbar.make(view, "Already Enroll in now withdraw!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                toggleFab();
            }
        });
    }

    private void enrollClass() {
        reference = firebaseDatabase.getReference(classId);
        //String enrollID = reference.push().getKey();
        if (isEnrolled) {
            reference.child(uID).setValue(false);
        } else {
            reference.child(uID).setValue(true);
        }


        reference = firebaseDatabase.getReference("class").child(classId).child("size");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currentSize = dataSnapshot.getValue(String.class);
                Log.d("DEBUG", currentSize);
                String newSize;

                if (!isEnrolled) {
                    newSize = plusOne(currentSize);
                    removeClassFromPersonalClassList();
                    isEnrolled = false;
                } else {
                    newSize = sizeMinusOne(currentSize);
                    addClassToPersonalClassList();
                    isEnrolled = true;
                }
                reference.setValue(newSize);
                classSizeTv.setText(newSize);
                //isEnrolled();

            }

            private String sizeMinusOne(String currentSize) {
                int newSize = Integer.parseInt(currentSize) - 1;
                return String.valueOf(newSize);
            }

            private String plusOne(String currentSize) {
                int newSize = Integer.parseInt(currentSize) + 1;
                return String.valueOf(newSize);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void isEnrolled() {
        reference = firebaseDatabase.getReference(classId).child(uID);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(Boolean.class) != null) {
                    isEnrolled = dataSnapshot.getValue(Boolean.class);
                    Log.d("DEBUG", String.valueOf(isEnrolled) + " by queue");
                    toggleFab();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void toggleFab() {
        Log.d("DEBUG", String.valueOf(isEnrolled) + " for now");
        if (!isEnrolled) {
            fab.setImageResource(R.drawable.ic_done_black_24dp);
        } else {
            fab.setImageResource(R.drawable.ic_close_black_24dp);
        }

    }

    private void addClassToPersonalClassList() {
        Class enrolledClass = new Class(className, classSize);
        personalRef = firebaseDatabase.getReference(uID);
        personalRef.child(classId).setValue(enrolledClass);
    }

    private void removeClassFromPersonalClassList() {
        personalRef = firebaseDatabase.getReference(uID);
        personalRef.child(classId).removeValue();
    }

    private class QueueIsEnrolled extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            isEnrolled();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            toggleFab();
        }
    }

}
