package com.uts.iamjinqianyu.regSystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.Survey;


/**
 * Created by iamph on 20/09/2017.
 */

public class Q1Activity extends AppCompatActivity {
    //declare items
    private RadioButton yesRadBtn;
    private RadioButton noRadBtn;
    private Button backBtn;

    //instantiate user

    Survey nonRegUserSurvey;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        nonRegUserSurvey = Survey.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);
        yesRadBtn = (RadioButton) findViewById(R.id.yesRadBtn);
        noRadBtn = (RadioButton) findViewById(R.id.noRadBtn);
        backBtn = (Button) findViewById(R.id.backBtn);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //return to main acitivity
                startActivity(new Intent(Q1Activity.this, SignInActivity.class));
            }
        });

    }
    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.yesRadBtn:
                if (checked)

                    nonRegUserSurvey.religion = "yes";

                    startActivity(new Intent(Q1Activity.this, Q2Activity.class));
                    break;
            case R.id.noRadBtn:
                if (checked)


                    nonRegUserSurvey.religion = "no";

                    startActivity(new Intent(Q1Activity.this, Q2Activity.class));
                    // I have no religion
                    break;
        }
    }


}
