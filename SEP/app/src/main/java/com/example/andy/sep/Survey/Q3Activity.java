package com.example.andy.sep.Survey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.andy.sep.MainActivity;
import com.example.andy.sep.R;


/**
 * Created by iamph on 20/09/2017.
 */

public class Q3Activity extends AppCompatActivity{

    private RatingBar intrstRtBar;

    //toast debugging declaration
    int duration = Toast.LENGTH_SHORT;
    Context context;
    CharSequence text;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q3);


        intrstRtBar = (RatingBar) findViewById(R.id.intrstRtBar);
        context = getApplicationContext();
        final Intent currIntent = getIntent();

        intrstRtBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            final Intent intent = new Intent(Q3Activity.this, MainActivity.class);
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                startActivity(intent);
            }
        });

    }

}
