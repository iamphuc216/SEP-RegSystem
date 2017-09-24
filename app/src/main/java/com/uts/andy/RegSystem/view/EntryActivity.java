package com.uts.andy.RegSystem.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uts.andy.RegSystem.R;

public class EntryActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    public static final String USER_TYPE_ADMIN = "admin";
    public static final String USER_TYPE_STUDENT = "student";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (firebaseAuth.getCurrentUser() == null) {
            signIn();
        } else {
            startActivity(new Intent(EntryActivity.this, AdminActivity.class));
            finish();
            //signIn();
        }

        Button signInButton = (Button) findViewById(R.id.buttonSignIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "我登陆了", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                signIn();
            }
        });
    }

    private void signIn() {
        startActivityForResult(
                // Get an instance of AuthUI based on the default app
                AuthUI.getInstance().createSignInIntentBuilder().build(),
                RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                //Log.d("DEBUG", "Meiyouyonghu");

                if (firebaseAuth.getCurrentUser() != null) {
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Personalized Your Experience");
                    progressDialog.show();
                    Log.d("DEBUG", "youyonghu");
                    //User currentUser = new User(firebaseAuth.getCurrentUser().getUid(), firebaseAuth.getCurrentUser().getEmail());
                    FirebaseDatabase databaseInstance = FirebaseDatabase.getInstance();
                    final String uID = firebaseAuth.getCurrentUser().getUid();

                    ref = databaseInstance.getReference("user").child(uID).child("type");

                    final String currentUserEmail = firebaseAuth.getCurrentUser().getEmail();
                    Log.d("DEBUG", currentUserEmail);
                    Log.d("DEBUG", uID);

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String userType = dataSnapshot.getValue(String.class);
                            Log.d("DEBUG", userType);
                            if (userType.equals(USER_TYPE_ADMIN)) {
                                startActivity(new Intent(EntryActivity.this, AdminActivity.class));
                                progressDialog.dismiss();
                                finish();
                            } else if (userType.equals(USER_TYPE_STUDENT)) {
                                startActivity(new Intent(EntryActivity.this, StudentActivity.class));
                                progressDialog.dismiss();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }


                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    showSnackbar("Sign in cancelled");
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackbar("no internet");
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackbar("What happened ?");
                    return;
                }
            }

            showSnackbar("unknown_sign_in_response");
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.coordinatorLayout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}
