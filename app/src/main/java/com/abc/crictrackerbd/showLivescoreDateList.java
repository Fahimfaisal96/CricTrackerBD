package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showLivescoreDateList extends Activity {

    private DatabaseReference LiveScorecardPageDataBaseRef;
    private DatabaseReference userDatabase;

    private ArrayList<String> mLiveScorecardFormat = new ArrayList<>();
    private ArrayList<String> mLiveScorecardDate = new ArrayList<>();
    private ArrayList<String> mLiveScorecardKey = new ArrayList<>();

    String nowUser,LiveScorecardFormat;

    User user;

    Button AddLiveScorecardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_livescore_date_list);

        AddLiveScorecardButton = findViewById(R.id.AddRecord);
        AddLiveScorecardButton.setVisibility(View.GONE);
        nowUser = getIntent().getStringExtra("current");
        LiveScorecardFormat = getIntent().getStringExtra("LivescoreFormat");
        LiveScorecardPageDataBaseRef = FirebaseDatabase.getInstance().getReference("livescore/"+LiveScorecardFormat);

        userDatabase=FirebaseDatabase.getInstance().getReference("users/"+nowUser.replace('.','&'));


        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                if(user.getIsModerator().equals("true")){
                    System.out.println("habijabi");
                    AddLiveScorecardButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AddLiveScorecardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LiveScorecardFormat.equals("test")) {
                    Intent intent = new Intent(showLivescoreDateList.this, addLiveScorecard2.class);
                    intent.putExtra("LivescoreFormat", LiveScorecardFormat);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(showLivescoreDateList.this, addLiveScorecard.class);
                    intent.putExtra("LivescoreFormat", LiveScorecardFormat);
                    startActivity(intent);
                }
            }

        });

        LiveScorecardPageDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mLiveScorecardFormat.clear();
                mLiveScorecardDate.clear();
                mLiveScorecardKey.clear();

                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Scorecard scorecard = ds.getValue(Scorecard.class);
                    mLiveScorecardFormat.add(scorecard.getFormat());
                    mLiveScorecardDate.add(scorecard.getDate());
                    mLiveScorecardKey.add(scorecard.getKey());
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
        LiveScorecardDateListRecyclerViewAdapter adapter = new LiveScorecardDateListRecyclerViewAdapter(this,mLiveScorecardFormat,mLiveScorecardDate,mLiveScorecardKey);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
