package com.uts.iamjinqianyu.regSystem.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.Survey.CompleteActivity;
import com.uts.iamjinqianyu.regSystem.bean.Survey;


/**
 * Created by iamph on 20/09/2017.
 */

public class Q3Activity extends AppCompatActivity {

    private RatingBar intrstRtBar;

    Survey nonRegUserSurvey;
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
        nonRegUserSurvey = Survey.getInstance();


        intrstRtBar = (RatingBar) findViewById(R.id.intrstRtBar);

        intrstRtBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                nonRegUserSurvey.interest = (int) intrstRtBar.getRating();

                startActivity(new Intent(Q3Activity.this, CompleteActivity.class));
            }
        });

    }

}
