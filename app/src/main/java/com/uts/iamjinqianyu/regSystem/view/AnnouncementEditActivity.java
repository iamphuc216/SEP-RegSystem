package com.uts.iamjinqianyu.regSystem.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.regSystem.R;

public class AnnouncementEditActivity extends AppCompatActivity {
    private TextView newAnnouncementTv;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference("announcement");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        newAnnouncementTv = (TextView) findViewById(R.id.textView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnnouncement(view);
            }
        });
    }

    private void updateAnnouncement (View view){
        String announcement =  newAnnouncementTv.getText().toString();
        if (TextUtils.isEmpty(announcement)) {
            reference.setValue("No announcement");
            Snackbar.make(view, "Clear Announcement!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            reference.setValue(announcement);
            Snackbar.make(view, "Announcement Update!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

}
