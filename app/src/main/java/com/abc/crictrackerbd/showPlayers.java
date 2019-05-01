package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class showPlayers extends Activity {

    private DatabaseReference playerPageDataBaseRef;
    private DatabaseReference userDatabase;
    private FirebaseAuth userPageAuth;

    private ArrayList<String> mImageURL = new ArrayList<>();
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImageKeys = new ArrayList<>();
    private ArrayList<String> mImageFormat = new ArrayList<>();

    String nowUser;
    String filter;
    String format;

    EditText filterBox;
    Button filterDone;
    Button AddPlayerButton;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players);

        format = getIntent().getStringExtra("PlayerFormat");

        playerPageDataBaseRef = FirebaseDatabase.getInstance().getReference("players/"+format );
        userPageAuth = FirebaseAuth.getInstance();
        AddPlayerButton = findViewById(R.id.addPlayer);
        AddPlayerButton.setVisibility(View.GONE);

        nowUser = getIntent().getStringExtra("current");
        filter = getIntent().getStringExtra("filter");

        filterBox = findViewById(R.id.FilterBoxPlayer);
        filterDone = findViewById(R.id.FilterDonePlayer);
        userDatabase=FirebaseDatabase.getInstance().getReference("users/"+nowUser.replace('.','&'));


        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                if(user.getIsModerator().equals("true")){
                    System.out.println("habijabi");
                    AddPlayerButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AddPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showPlayers.this,addPlayer.class);
                intent.putExtra("PlayerFormat",format);
                startActivity(intent);
            }

        });

        filterDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filterBox.getText().toString().trim() != null && !filterBox.getText().toString().trim().equals("")){
                    Intent intent = new Intent(showPlayers.this,showPlayers.class);
                    intent.putExtra("current",nowUser);
                    intent.putExtra("filter",filterBox.getText().toString().trim());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    filterDone.setOnClickListener(null);
                }
            }
        });




        playerPageDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mImageKeys.clear();
                mImageNames.clear();
                mImageURL.clear();
                mImageFormat.clear();

                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Player player = ds.getValue(Player.class);
                    if(filter != null && !filter.equals("")){
                        if(player.getName().toLowerCase().contains(filter.toLowerCase())){
                            mImageNames.add(player.getName());
                            mImageURL.add(player.getDpUrl());
                            mImageKeys.add(player.getKey());
                            mImageFormat.add(player.getFormat());
                            i++;
                        }
                    }
                    else{
                        mImageNames.add(player.getName());
                        mImageURL.add(player.getDpUrl());
                        mImageKeys.add(player.getKey());
                        mImageFormat.add(player.getFormat());
                        i++;
                    }
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
        playerListRecyclerViewAdapter adapter = new playerListRecyclerViewAdapter(this,mImageURL,mImageNames,mImageFormat,mImageKeys);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
