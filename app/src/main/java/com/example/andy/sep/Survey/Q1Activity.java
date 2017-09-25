package com.example.andy.sep.Survey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.andy.sep.MainActivity;
import com.example.andy.sep.R;


/**
 * Created by iamph on 20/09/2017.
 */

public class Q1Activity extends AppCompatActivity{
    //declare items
    private RadioButton yesRadBtn;
    private RadioButton noRadBtn;
    private Button backBtn;

    //instantiate user

    //toast debugging declaration
    int duration = Toast.LENGTH_SHORT;
    Context context;
    CharSequence text;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);
        yesRadBtn = (RadioButton) findViewById(R.id.yesRadBtn);
        noRadBtn = (RadioButton) findViewById(R.id.noRadBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        //Toast
        context = getApplicationContext();
        text = "Hello, Toast work!!!";
        toast = Toast.makeText(context, text, duration);
        toast.show();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //return to main acitivity
                startActivity(new Intent(Q1Activity.this, MainActivity.class));
            }
        });

    }
    public void onRadioButtonClicked(View view)
    {
        final Intent intent = new Intent(Q1Activity.this, Q2Activity.class);
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.yesRadBtn:
                if (checked)
                    startActivity(intent);
                    break;
            case R.id.noRadBtn:
                if (checked)
                    startActivity(intent);
                    // I have no religion
                    break;
        }
    }


}
