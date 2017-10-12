package com.uts.iamjinqianyu.regSystem.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uts.iamjinqianyu.regSystem.R;


public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "EmailPassword";
    public static final String USER_TYPE_ADMIN = "admin";
    public static final String USER_TYPE_STUDENT = "student";
    private TextView msg;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mReligion;
    private EditText mType;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailField = (EditText) findViewById(R.id.AccountField);
        mPasswordField = (EditText) findViewById(R.id.PasswordField);
        mFirstName = (EditText) findViewById(R.id.firstName);
        mLastName = (EditText) findViewById(R.id.lastName);
        mReligion = (EditText) findViewById(R.id.religionField);
        mType = (EditText) findViewById(R.id.userType);
        //msg = (TextView) findViewById(R.id.displayMsg);

        mFirstName.setVisibility(View.INVISIBLE);
        mLastName.setVisibility(View.INVISIBLE);
        mReligion.setVisibility(View.INVISIBLE);
        mType.setVisibility(View.INVISIBLE);


        Button survey = (Button) findViewById(R.id.toSurvey);
        Button demo = (Button) findViewById(R.id.demo);
        Button forgot = (Button) findViewById(R.id.fPW);

        //[START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        //
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl(url);

        // [END initialize_database_ref]

        Button signIn = (Button) findViewById(R.id.signIn);
        Button signUp = (Button) findViewById(R.id.signUp);

        final Button update = (Button) findViewById(R.id.updateBtr);

        /*demo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if (mFirstName.isShown()){
                    mFirstName.setVisibility(View.INVISIBLE);
                    mLastName.setVisibility(View.INVISIBLE);
                    mReligion.setVisibility(View.INVISIBLE);
                    mType.setVisibility(View.INVISIBLE);
                }
                else{
                    mFirstName.setVisibility(View.VISIBLE);
                    mLastName.setVisibility(View.VISIBLE);
                    mReligion.setVisibility(View.VISIBLE);
                    mType.setVisibility(View.VISIBLE);
                }

            }
        });*/

        forgot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SignIn.this, pwReset.class);
                startActivity(intent);
            }
        });

        /*survey.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SignIn.this, Q1Activity.class);
                startActivity(intent);
            }
        });*/

        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signUp(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });

        /*update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateInformation();
            }
        });*/
    }

    private void signIn(String email, String password) {
        Log.v(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            final String userId = user.getUid();

                            mDatabase = mDatabase.child("user").child(userId).child("type");
                            ValueEventListener userListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Get Post object and use the values to update the UI
                                    String userType = dataSnapshot.getValue(String.class);
                                    //String u = dataSnapshot.getValue().toString();


                                    if(userType.equals("admin")){
                                        Intent intent = new Intent(SignIn.this, AdminActivity.class);
                                        intent.putExtra("UID", userId);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if(userType.equals("student")){
                                        Intent intent = new Intent(SignIn.this, StudentActivity.class);
                                        intent.putExtra("UID", userId);
                                        startActivity(intent);
                                        finish();
                                    }
                                    /*else if(userType.equals("rec")){
                                        Intent intent = new Intent(SignIn.this, Recruiter.class);
                                        intent.putExtra("UID", userId);
                                        startActivity(intent);
                                    }*/
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Getting Post failed, log a message
                                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                                    // [START_EXCLUDE]
                                    Toast.makeText(SignIn.this, "Failed to load post.",
                                            Toast.LENGTH_SHORT).show();
                                    // [END_EXCLUDE]
                                }
                            };
                            mDatabase.addValueEventListener(userListener);

                            Toast.makeText(SignIn.this, "Authentication succeed.",
                                    Toast.LENGTH_SHORT).show();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
        // [END sign_in_with_email]
    }

    private void signUp(String email, String password) {
        Log.v(TAG, "signUp:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(SignIn.this, "New account created.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        // [END create_user_with_email]

    }

    /*private void updateInformation(){
        String first_name = mFirstName.getText().toString().trim();
        String last_name = mLastName.getText().toString().trim();
        String religion = mReligion.getText().toString().trim();
        String user_type = mType.getText().toString().trim();
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();

        UserDetails ud = new UserDetails(userId, first_name, last_name, religion, user_type);

        mDatabase.child("users").child(user.getUid()).setValue(ud);
        Toast.makeText(SignIn.this, "Updated.",
                Toast.LENGTH_SHORT).show();
    }*/



    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }
}