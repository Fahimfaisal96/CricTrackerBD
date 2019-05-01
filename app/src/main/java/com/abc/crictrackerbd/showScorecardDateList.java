package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.apg.mobile.roundtextview.BadgeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showScorecardDateList extends Activity {

    private DatabaseReference ScorecardPageDataBaseRef;
    private DatabaseReference userDatabase;

    private ArrayList<String> mScorecardFormat = new ArrayList<>();
    private ArrayList<String> mScorecardDate = new ArrayList<>();
    private ArrayList<String> mScorecardKey = new ArrayList<>();

    String nowUser,ScorecardFormat;

    User user;

    BadgeView AddScorecardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scorecard_date_list);

        AddScorecardButton = findViewById(R.id.AddRecord);
        AddScorecardButton.setVisibility(View.GONE);
        nowUser = getIntent().getStringExtra("current");
        ScorecardFormat = getIntent().getStringExtra("ScorecardFormat");
        ScorecardPageDataBaseRef = FirebaseDatabase.getInstance().getReference("scorecard/"+ScorecardFormat);

        userDatabase=FirebaseDatabase.getInstance().getReference("users/"+nowUser.replace('.','&'));


        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                if(user.getIsModerator().equals("true")){
                    System.out.println("habijabi");
                    AddScorecardButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AddScorecardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ScorecardFormat.equals("test")) {
                    Intent intent = new Intent(showScorecardDateList.this, addScorecard2.class);
                    intent.putExtra("ScorecardFormat", ScorecardFormat);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(showScorecardDateList.this, addScorecard.class);
                    intent.putExtra("ScorecardFormat", ScorecardFormat);
                    startActivity(intent);
                }
            }

        });

        ScorecardPageDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mScorecardFormat.clear();
                mScorecardDate.clear();
                mScorecardKey.clear();

                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Scorecard scorecard = ds.getValue(Scorecard.class);
                    mScorecardFormat.add(scorecard.getFormat());
                    mScorecardDate.add(scorecard.getDate());
                    mScorecardKey.add(scorecard.getKey());
                    i++;
                }

                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        ScorecardDateListRecyclerViewAdapter adapter = new ScorecardDateListRecyclerViewAdapter(this,mScorecardFormat,mScorecardDate,mScorecardKey);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
