package com.uts.iamjinqianyu.regSystem.Survey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.Survey;
import com.uts.iamjinqianyu.regSystem.view.SignInActivity;

/**
 * Created by iamph on 5/10/2017.
 */

public class EnterDetailActivity extends AppCompatActivity {
    //declare items
    DatabaseReference mDatabase;
    Button regBtn;
    Button cancelBtn;
    EditText firstnameTxt;
    EditText lastnameTxt;
    EditText phoneTxt;
    EditText nationTxt;
    EditText emailTxt;
    Survey nonRegUserSurvey;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        mDatabase = FirebaseDatabase.getInstance().getReference("newUsers");


        nonRegUserSurvey = Survey.getInstance();
        //Connect layout nodes to activity
        regBtn = (Button)findViewById(R.id.regBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        firstnameTxt = (EditText)findViewById(R.id.firstnameTxt);
        lastnameTxt = (EditText)findViewById(R.id.lastnameTxt);
        phoneTxt = (EditText)findViewById(R.id.phoneTxt);
        nationTxt = (EditText)findViewById(R.id.nationTxt);
        emailTxt = (EditText)findViewById(R.id.emailText);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterDetailActivity.this, CompleteActivity.class));

            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nonRegUserSurvey.firstname = firstnameTxt.getText().toString();
                nonRegUserSurvey.lastname = lastnameTxt.getText().toString();
                nonRegUserSurvey.phone = phoneTxt.getText().toString();
                nonRegUserSurvey.nationality = nationTxt.getText().toString();
                nonRegUserSurvey.email = emailTxt.getText().toString();
                nonRegUserSurvey.userType = "member";

                addUserToFbase();
                startActivity(new Intent(EnterDetailActivity.this, SignInActivity.class));
            }
        });
    }

    void addUserToFbase()
    {
        String id = mDatabase.push().getKey();

        //Toast.makeText(getApplicationContext(),nonRegUserSurvey.toString(),Toast.LENGTH_SHORT).show();
        // Write a message to the database
        mDatabase.child(id).setValue(nonRegUserSurvey);

    }
}
