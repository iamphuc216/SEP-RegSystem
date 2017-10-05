package com.example.andy.sep;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.sep.models.Announcement;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class announcement extends AppCompatActivity {

    public static final int FORMAT_SHOW_TIME = android.text.format.DateUtils.FORMAT_SHOW_TIME;
    public static final int FORMAT_SHOW_DATE = android.text.format.DateUtils.FORMAT_SHOW_DATE;
    int year, month, day;
    static final int DILOG_ID = 0;
    private DatabaseReference mDatabase;
    TextView dateView;
    TextView Title;
    TextView ContentBody;
    DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    Calendar calendar = Calendar.getInstance();
    TimeZone zone = calendar.getTimeZone();
    String timeZone = zone.getID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DateTime now = DateTime.now();
        dateView = (TextView) findViewById(R.id.date);
        Title = (TextView) findViewById(R.id.aTitle);
        ContentBody = (TextView) findViewById(R.id.contentBody);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //dateView.setText(now.toString());
        //String nowDate =DateUtils.formatDateTime(this, now, FORMAT_SHOW_DATE | FORMAT_SHOW_TIME);
        //dateView.setText();
       /* text.add("Show date and time: " + DateUtils.formatDateTime(this, now, DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_SHOW_TIME));*/

        String msgAtTime =  getFormatedTime(System.currentTimeMillis(), timeZone, "MMM dd, hh:mm a");
        dateView.setText(msgAtTime);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.publish);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Title.getText().toString().trim();
                String content = ContentBody.getText().toString().trim();
                String time = dateView.getText().toString().trim();
                Announcement newA = new Announcement(content, title, time);
                mDatabase.child("announcement").child(time).setValue(newA);
                Toast.makeText(announcement.this, "Published",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDialogOnButtonClick(){
        Button btn = (Button) findViewById(R.id.publish);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                    }
                }
        );
    }
 public static String getFormatedTime(long dateTime,String timeZone, String format){
     String time = null;
     try{
         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(dateTime);
         SimpleDateFormat sdf = new SimpleDateFormat(format);
         sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
         time = sdf.format(cal.getTime());
     }
     catch(Exception e){
         //logger.log(Level.SEVERE,"\n ERROR**********, Exception during get formated time: "+e+"\n");
     }
     return time;
 }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DILOG_ID)
            return new DatePickerDialog(this, datepickerListener, year, month, day);
        return null;

    }

    private DatePickerDialog.OnDateSetListener datepickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int yearOfPicker, int monthOfYear, int dayOfPicker){
            year = yearOfPicker;
            month = monthOfYear;
            day = dayOfPicker;
        }
    };
}
