package com.uts.iamjinqianyu.regSystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RatingBar;

import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.Survey;


/**
 * Created by iamph on 20/09/2017.
 */

public class Q2Activity extends AppCompatActivity {
    //declare items
    private RatingBar ratingBar;
    private Button backBtn;

    Survey nonRegUserSurvey;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        nonRegUserSurvey = Survey.getInstance();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {

                nonRegUserSurvey.knowledge = (int)ratingBar.getRating();
                startActivity(new Intent(Q2Activity.this, Q3Activity.class));
            }
        });

    /*
        if (nonRegUser.religion == "no") {
            ratingBar.setVisibility(View.GONE);
        }
    */

    }

}
