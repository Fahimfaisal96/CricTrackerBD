package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.GONE;

public class showSingleLiveScorecard extends Activity {

    private DatabaseReference showSingleScorecardDatabaseRef;

    TextView toss;
    TextView firstInnings;
    TextView secondInnings;
    TextView date;
    Button link;

    Button editScorecardButton;
    Button deleteScorecardButton;

    private FirebaseAuth loginAuthentication;
    private DatabaseReference userDatabase;


    String key;
    String format;
    String nowUser;
    String link_value;

    User user;
    Scorecard scorecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_live_scorecard);

        editScorecardButton = findViewById(R.id.Edit_scorecard_button);
        editScorecardButton.setVisibility(GONE);
        deleteScorecardButton = findViewById(R.id.Delete_scorecard_button);
        deleteScorecardButton.setVisibility(GONE);

        toss = findViewById(R.id.toss_value);
        firstInnings = findViewById(R.id.firstInnings_value);
        secondInnings = findViewById(R.id.secondInnings_value);
        date =findViewById(R.id.date_value);
        link = findViewById(R.id.link_value);



        loginAuthentication = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = loginAuthentication.getCurrentUser();
        nowUser=currentUser.getEmail();
        userDatabase= FirebaseDatabase.getInstance().getReference("users/"+nowUser.replace('.','&'));


        key = getIntent().getStringExtra("showKey");
        format = getIntent().getStringExtra("LivescoreFormat");
        showSingleScorecardDatabaseRef = FirebaseDatabase.getInstance().getReference("livescore/"+ format+ "/" + key);


        showSingleScorecardDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scorecard = dataSnapshot.getValue(Scorecard.class);
                toss.setText(scorecard.getToss());
                firstInnings.setText(scorecard.getFirstInnings());
                secondInnings.setText(scorecard.getSecondInnings());
                date.setText(scorecard.getDate());
                link_value = scorecard.getLink();


                showSingleScorecardDatabaseRef.removeEventListener(this);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                if(user.getIsModerator().equals("true")){
                    System.out.println("habijabi");
                    editScorecardButton.setVisibility(View.VISIBLE);
                    deleteScorecardButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeToLink(v);
            }
        });

        editScorecardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeToEditScorecard(v);
            }

        });

        deleteScorecardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleScorecardDatabaseRef.removeValue();
                finish();
            }
        });
    }

    private void takeToEditScorecard(View v) {
        Intent intent = new Intent(this,editLiveScorecard.class);
        intent.putExtra("showKey",key);
        intent.putExtra("format",format);
        startActivity(intent);
    }
    private void takeToLink(View v) {
        String url = link_value;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
