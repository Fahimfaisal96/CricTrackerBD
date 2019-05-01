package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.GONE;

public class editScorecard2 extends Activity {

    private EditText editToss;
    private EditText editFirstInnings;
    private EditText editSecondInnings;
    private EditText editThirdInnings;
    private EditText editFourthInnings;
    private EditText editDate;
    private EditText editFormat;
    private EditText editLink;
    private Button editScorecardSubmitButton;

    private DatabaseReference scorecardDatabaseRef;

    Scorecard scorecard;
    String key;
    String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_scorecard2);

        editToss =(EditText) findViewById(R.id.editToss);
        editFirstInnings = (EditText) findViewById(R.id.editFirstInnings);
        editSecondInnings = (EditText) findViewById(R.id.editSecondInnings);
        editThirdInnings = (EditText) findViewById(R.id.editThirdInnings);
        editFourthInnings = (EditText) findViewById(R.id.editFourthInnings);
        editDate = (EditText) findViewById(R.id.editDate);
        editLink = (EditText) findViewById(R.id.editLink);
        editScorecardSubmitButton = (Button) findViewById(R.id.editScorecardSubmitButton);
        editScorecardSubmitButton.setVisibility(GONE);

        key = getIntent().getStringExtra("showKey");
        format = getIntent().getStringExtra("format");
        scorecardDatabaseRef = FirebaseDatabase.getInstance().getReference("scorecard/"+ format+ "/" + key);

        scorecardDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scorecard = dataSnapshot.getValue(Scorecard.class);

                editToss.setText(scorecard.getToss());
                editFirstInnings.setText(scorecard.getFirstInnings());
                editSecondInnings.setText(scorecard.getSecondInnings());
                editThirdInnings.setText(scorecard.getThirdInnings());
                editFourthInnings.setText(scorecard.getFourthInnings());
                editDate.setText(scorecard.getDate());
                //editTeacherDesignation.setText(teacher.getDesignation());
                editLink.setText(scorecard.getLink());
                editScorecardSubmitButton.setVisibility(View.VISIBLE);
                scorecardDatabaseRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editScorecardSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditScorecard();
            }
        });
    }

    private void goToEditScorecard() {
        Scorecard changedScorecard = scorecard;
        changedScorecard.setToss(editToss.getText().toString().trim());
        changedScorecard.setFirstInnings(editFirstInnings.getText().toString().trim());
        changedScorecard.setSecondInnings(editSecondInnings.getText().toString().trim());
        changedScorecard.setThirdInnings(editThirdInnings.getText().toString().trim());
        changedScorecard.setFourthInnings(editFourthInnings.getText().toString().trim());
        changedScorecard.setDate(editDate.getText().toString().trim());
        //changedTeacher.setDesignation(editTeacherDesignation.getText().toString().trim());
        changedScorecard.setLink(editLink.getText().toString().trim());
        scorecardDatabaseRef.setValue(changedScorecard).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(editScorecard2.this,"Update Done",Toast.LENGTH_LONG).show();
            }
        });
        Intent intent = new Intent(this, showSingleLiveScorecard2.class);
        intent.putExtra("showKey",key);
        intent.putExtra("ScorecardFormat",format);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
