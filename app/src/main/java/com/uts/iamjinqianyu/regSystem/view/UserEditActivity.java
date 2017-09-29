package com.uts.iamjinqianyu.regSystem.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uts.iamjinqianyu.regSystem.R;
import com.uts.iamjinqianyu.regSystem.bean.User;

public class UserEditActivity extends AppCompatActivity {
    private DatabaseReference reference;
    private FirebaseDatabase databaseInstance = FirebaseDatabase.getInstance();
    TextView nameTv;
    TextView emailTv;
    TextView typeTv;
    Spinner spinner;
    String uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameTv = (TextView) findViewById(R.id.editText_Name);
        emailTv = (TextView) findViewById(R.id.editText_Email);
        typeTv = (TextView) findViewById(R.id.editText_userType);

        Bundle bundle = getIntent().getBundleExtra("passBundle");
        if (bundle != null) {
            nameTv.setText(bundle.getString("passName"));
            emailTv.setText(bundle.getString("passEmail"));
            typeTv.setText(bundle.getString("passType"));
        }

        spinner = (Spinner) findViewById(R.id.spinner);
        final String[] userTypes = getResources().getStringArray(R.array.user_type);
        if (bundle.getString("passType").equals(userTypes[0])){
            spinner.setSelection(0, true);
        } else if (bundle.getString("passType").equals(userTypes[1])) {
            spinner.setSelection(1, true);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                typeTv.setText(userTypes[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyUser(nameTv.getText().toString(), emailTv.getText().toString(), typeTv.getText().toString());
                Snackbar.make(view, "Uer Modify Success", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void modifyUser(String name, String email, String type) {
        reference = databaseInstance.getReference("user");
        if (TextUtils.isEmpty(uID)) {
            uID = reference.push().getKey();
            User user = new User(name,  email, type);
            reference.child(uID).setValue(user);


        } else {
            Snackbar.make(findViewById(R.id.activity_editUser), "Under Development", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

}
