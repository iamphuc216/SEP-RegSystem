package com.uts.iamjinqianyu.regSystem.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.User;

public class SurveyDetailActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private String Uemail;
    private String Upassword;
    private String Uname;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        TextView textViewFirstname = (TextView) findViewById(R.id.textView_firstname);
        TextView textViewLastname = (TextView) findViewById(R.id.textView_lastname);
        TextView textViewEmail = (TextView) findViewById(R.id.textView_email);
        TextView textViewPhone = (TextView) findViewById(R.id.textView_phone);
        TextView textViewKnowledge = (TextView) findViewById(R.id.textView_knowledge);
        TextView textViewRe = (TextView) findViewById(R.id.textView_re);
        TextView textViewIn = (TextView) findViewById(R.id.textView_interest);
        TextView textViewUserType = (TextView) findViewById(R.id.textView_userType);
        TextView textViewNa = (TextView) findViewById(R.id.textView_na);
        Button toA = (Button) findViewById(R.id.toApprove);



        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("bundle");
            textViewFirstname.setText(bundle.getString("firstName"));
            textViewLastname.setText(bundle.getString("lastName"));
            textViewEmail.setText(bundle.getString("email"));
            textViewPhone.setText(bundle.getString("phone"));
            textViewUserType.setText(bundle.getString("userType"));
            textViewKnowledge.setText(bundle.getString("knowledge"));
            textViewRe.setText(bundle.getString("re"));
            textViewIn.setText(bundle.getString("interest"));
            textViewNa.setText(bundle.getString("interest"));
            Uemail = bundle.getString("email");
            Upassword = "123123";
            Uname = bundle.getString("firstName") + " " + bundle.getString("lastName");
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        toA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(Uemail, Upassword);
            }
        });
    }

    private void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    reference = mFirebaseInstance.getReference("user");
                    FirebaseUser user = mAuth.getCurrentUser();
                    String UID = user.getUid();
                    Log.d(TAG, "createUserWithEmail:success");
                    User u = new User(Uname,  Uemail, "student");
                    reference.child(UID).setValue(u);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(SurveyDetailActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}
