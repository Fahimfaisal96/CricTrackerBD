package com.abc.crictrackerbd;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addFixture extends Activity {
    private EditText mOpponent;
    private EditText mVenue;
    private EditText mDate;
    private EditText mTime;
    private Button mSubmitButton;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;

    Fixture fixture;
    String FixtureFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixture);

        mOpponent =(EditText) findViewById(R.id.addOpponent);
        mVenue = (EditText) findViewById(R.id.addVenue);
        mDate = (EditText) findViewById(R.id.addDate);
        mTime = (EditText) findViewById(R.id.addTime);
        mSubmitButton=(Button) findViewById(R.id.submitButton);
        mProgress= new ProgressDialog(this);
        FixtureFormat = getIntent().getStringExtra("FixtureFormat");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("fixture").child(FixtureFormat);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdding();
            }
        });
    }

    private void startAdding() {
        mProgress.setMessage("Adding");
        mProgress.show();
        String opponent_val=mOpponent.getText().toString().trim();
        String venue_val=mVenue.getText().toString().trim();
        String date_val=mDate.getText().toString().trim();
        String format_val=FixtureFormat;
        String time_val=mTime.getText().toString().trim();


        if(!TextUtils.isEmpty(opponent_val)&& !TextUtils.isEmpty(venue_val)&& !TextUtils.isEmpty(date_val)&& !TextUtils.isEmpty(format_val)&& !TextUtils.isEmpty(time_val) ){
            DatabaseReference newFixture=mDatabase.push();
            fixture = new Fixture(opponent_val,venue_val,date_val,time_val,format_val,newFixture.getKey());
            mDatabase.child(newFixture.getKey()).setValue(fixture);
            mProgress.dismiss();
            //startActivity(new Intent(addPost.this,noticeShowPage.class));
            finish();

        }
    }
}
