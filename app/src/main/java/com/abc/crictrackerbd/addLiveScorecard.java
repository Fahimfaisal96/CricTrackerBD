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

public class addLiveScorecard extends Activity {

    private EditText mToss;
    private EditText mFirstInnings;
    private EditText mSecondInnings;
    private EditText mDate;
    private EditText mLink;
    private Button mSubmitButton;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;

    Scorecard scorecard;
    String LiveScorecardFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_live_scorecard);

        mToss =(EditText) findViewById(R.id.addToss);
        mFirstInnings = (EditText) findViewById(R.id.addFirstInnings);
        mDate = (EditText) findViewById(R.id.addDate);
        mSecondInnings = (EditText) findViewById(R.id.addSecondInnings);
        mLink = (EditText) findViewById(R.id.addLink);
        mSubmitButton=(Button) findViewById(R.id.submitButton);
        mProgress= new ProgressDialog(this);
        LiveScorecardFormat = getIntent().getStringExtra("LivescoreFormat");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("livescore").child(LiveScorecardFormat);

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
        String toss_val=mToss.getText().toString().trim();
        String firstInnings_val=mFirstInnings.getText().toString().trim();
        String date_val=mDate.getText().toString().trim();
        String format_val=LiveScorecardFormat;
        String link_val=mLink.getText().toString().trim();
        String secondInnings_val=mSecondInnings.getText().toString().trim();
        String thirdInnings_val = "N/A";
        String fourthInnings_val = "N/A";


        if(!TextUtils.isEmpty(toss_val)&& !TextUtils.isEmpty(firstInnings_val)&& !TextUtils.isEmpty(secondInnings_val)&& !TextUtils.isEmpty(format_val)&& !TextUtils.isEmpty(date_val) && !TextUtils.isEmpty(link_val) ){
            DatabaseReference newScorecard=mDatabase.push();
            scorecard = new Scorecard(toss_val,firstInnings_val,secondInnings_val,thirdInnings_val,fourthInnings_val,format_val,date_val,link_val,newScorecard.getKey());
            mDatabase.child(newScorecard.getKey()).setValue(scorecard);
            mProgress.dismiss();
            //startActivity(new Intent(addPost.this,noticeShowPage.class));
            finish();

        }
    }
}
