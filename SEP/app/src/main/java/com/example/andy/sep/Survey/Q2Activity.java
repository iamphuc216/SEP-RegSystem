package com.example.andy.sep.Survey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.andy.sep.R;


/**
 * Created by iamph on 20/09/2017.
 */

public class Q2Activity extends AppCompatActivity{
    //declare items
    private RatingBar ratingBar;
    private Button backBtn;

    //toast debugging declaration
    int duration = Toast.LENGTH_SHORT;
    Context context;
    CharSequence text;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //Toast
        context = getApplicationContext();
        //toast = Toast.makeText(context, "Question 2", duration);
        //toast.show();
        final Intent intent = new Intent(Q2Activity.this, Q3Activity.class);

        //text = nonRegUser.religion;
        //toast = Toast.makeText(context, text, duration);
        //toast.show();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                startActivity(intent);
            }
        });

    }

}
