package com.example.andy.sep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.andy.sep.models.ClassInfo;
import com.example.andy.sep.models.UserAdapter;
import com.example.andy.sep.models.UserDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Admin extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference classRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        ClassInfo class1 = new ClassInfo("30", "13:00", "Yoga", "class1");
        ClassInfo class2 = new ClassInfo("25", "15:00", "Bible", "class2");
        ClassInfo class3 = new ClassInfo("15", "17:00", "Gaming", "class3");

        mDatabase.child("classes").child("class1").setValue(class1);
        mDatabase.child("classes").child("class2").setValue(class2);
        mDatabase.child("classes").child("class3").setValue(class3);*/

        Button logout = (Button) findViewById(R.id.logoutAdmin);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Admin.this, MainActivity.class);
                intent.putExtra("UID", "Test");
                startActivity(intent);
            }
        });

        Button toCreate = (Button) findViewById(R.id.toCreateClass);
        toCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Admin.this, ManageClass.class);
                intent.putExtra("UID", "Test");
                startActivity(intent);
            }
        });


        Button toList = (Button) findViewById(R.id.toUserList);
        toList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Admin.this, userlist.class);
                intent.putExtra("UID", "Test");
                startActivity(intent);
            }
        });

        Button toA = (Button) findViewById(R.id.toAnnouncement);
        toA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Admin.this, announcement.class);
                intent.putExtra("UID", "Test");
                startActivity(intent);
            }
        });
    }
}
