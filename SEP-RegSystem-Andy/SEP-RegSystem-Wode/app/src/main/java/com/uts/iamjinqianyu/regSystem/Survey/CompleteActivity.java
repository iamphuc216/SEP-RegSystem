package com.uts.iamjinqianyu.regSystem.Survey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.Survey;


/**
 * Created by iamph on 5/10/2017.
 */

public class CompleteActivity extends AppCompatActivity {

    //declare items
    Button regmemberBtn;
    Button returnBtn;

    Survey nonRegUserSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcomplete);

        nonRegUserSurvey = Survey.getInstance();


        regmemberBtn = (Button)findViewById(R.id.regmemberBtn);

        regmemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompleteActivity.this, EnterDetailActivity.class));
            }
        });
    }
}
