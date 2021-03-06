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

public class addScorecard2 extends Activity {
    private EditText mToss;
    private EditText mFirstInnings;
    private EditText mSecondInnings;
    private EditText mThirdInnings;
    private EditText mFourthInnings;
    private EditText mDate;
    private EditText mLink;
    private Button mSubmitButton;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;

    Scorecard scorecard;
    String ScorecardFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scorecard2);

        mToss =(EditText) findViewById(R.id.addToss);
        mFirstInnings = (EditText) findViewById(R.id.addFirstInnings);
        mDate = (EditText) findViewById(R.id.addDate);
        mSecondInnings = (EditText) findViewById(R.id.addSecondInnings);
        mThirdInnings = (EditText) findViewById(R.id.addThirdInnings);
        mFourthInnings = (EditText) findViewById(R.id.addFourthInnings);
        mLink = (EditText) findViewById(R.id.addLink);
        mSubmitButton=(Button) findViewById(R.id.submitButton);
        mProgress= new ProgressDialog(this);
        ScorecardFormat = getIntent().getStringExtra("ScorecardFormat");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("scorecard").child(ScorecardFormat);

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
        String format_val=ScorecardFormat;
        String link_val=mLink.getText().toString().trim();
        String secondInnings_val=mSecondInnings.getText().toString().trim();
        String thirdInnings_val = mThirdInnings.getText().toString().trim();;
        String fourthInnings_val = mFourthInnings.getText().toString().trim();;


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
