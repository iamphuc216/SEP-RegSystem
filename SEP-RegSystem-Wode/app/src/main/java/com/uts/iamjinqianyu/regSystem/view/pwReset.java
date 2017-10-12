package com.uts.iamjinqianyu.regSystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.uts.iamjinqianyu.regSystem.R;

public class pwReset extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView email;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_reset);

        mAuth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.eAddress);
        Button back = (Button) findViewById(R.id.button3);
        Button sendE = (Button) findViewById(R.id.sce);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(pwReset.this, SignIn.class);
                startActivity(intent);
            }
        });
        sendE.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String emailAddress = email.getText().toString();
                mAuth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                                else{
                                    Log.d(TAG, "Email address is wrong.");
                                }
                            }
                        });
            }
        });
    }
}
