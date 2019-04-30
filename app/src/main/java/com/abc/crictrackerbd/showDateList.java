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
import java.util.Collections;

public class showDateList extends Activity {
    private DatabaseReference FixturePageDataBaseRef;
    private DatabaseReference userDatabase;

    private ArrayList<String> mFixtureFormat = new ArrayList<>();
    private ArrayList<String> mFixtureDate = new ArrayList<>();
    private ArrayList<String> mFixtureKey = new ArrayList<>();

    String nowUser,FixtureFormat;

    User user;

    Button AddFixtureButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_date_list);

        AddFixtureButton = findViewById(R.id.AddFixture);
        AddFixtureButton.setVisibility(View.GONE);
        nowUser = getIntent().getStringExtra("current");
        FixtureFormat = getIntent().getStringExtra("FixtureFormat");
        FixturePageDataBaseRef = FirebaseDatabase.getInstance().getReference("fixture/"+FixtureFormat);

        userDatabase=FirebaseDatabase.getInstance().getReference("users/"+nowUser.replace('.','&'));


        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                if(user.getIsModerator().equals("true")){
                    System.out.println("habijabi");
                    AddFixtureButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AddFixtureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showDateList.this,addFixture.class);
                intent.putExtra("FixtureFormat",FixtureFormat);
                startActivity(intent);
            }

        });

        FixturePageDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mFixtureFormat.clear();
                mFixtureDate.clear();
                mFixtureKey.clear();

                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Fixture fixture = ds.getValue(Fixture.class);
                    mFixtureFormat.add(fixture.getFormat());
                    mFixtureDate.add(fixture.getDate());
                    mFixtureKey.add(fixture.getKey());
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
        dateListRecyclerViewAdapter adapter = new dateListRecyclerViewAdapter(this,mFixtureFormat,mFixtureDate,mFixtureKey);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
