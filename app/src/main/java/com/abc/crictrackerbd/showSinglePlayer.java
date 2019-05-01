package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showSinglePlayer extends Activity {
    private DatabaseReference showSinglePlayerDatabaseRef;
    private DatabaseReference userDatabase;
    private FirebaseAuth loginAuthentication;

    TextView name;
    TextView role;
    TextView match;
    TextView innings;
    TextView runs;
    TextView average;
    TextView strikeRate;
    TextView hundreds;
    TextView fifties;
    TextView notOuts;
    TextView bestScore;
    TextView balls;
    TextView runsConceded;
    TextView wickets;
    TextView economy;
    TextView bowlingAverage;
    TextView bowlingStrikeRate;
    TextView bestFigure;
    TextView fifers;

    ImageView profilePicture;
    Button edit_profilePhoto_button;
    Button deleteButton;

    String nowUser;
    Player player;
    String key,format;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_player);

        name = findViewById(R.id.name_value);
        role = findViewById(R.id.role_value);
        match = findViewById(R.id.match_value);
        innings = findViewById(R.id.innings_value);
        runs = findViewById(R.id.runs_value);
        average = findViewById(R.id.average_value);
        strikeRate = findViewById(R.id.strikeRate_value);
        hundreds = findViewById(R.id.hundreds_value);
        fifties = findViewById(R.id.fifties_value);
        notOuts = findViewById(R.id.notOuts_value);
        bestScore = findViewById(R.id.bestScore_value);
        balls = findViewById(R.id.balls_value);
        runsConceded = findViewById(R.id.runsConceded_value);
        wickets = findViewById(R.id.wickets_value);
        economy = findViewById(R.id.economy_value);
        bowlingAverage = findViewById(R.id.bowlingAverage_value);
        bowlingStrikeRate = findViewById(R.id.bowlingStrikeRate_value);
        fifers = findViewById(R.id.fifers_value);
        bestFigure = findViewById(R.id.bestFigure_value);
        deleteButton = findViewById(R.id.deleteProfile);
        edit_profilePhoto_button = findViewById(R.id.Edit_photo_button);
        profilePicture = findViewById(R.id.singleUser_profile_image);


        loginAuthentication = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = loginAuthentication.getCurrentUser();
        nowUser=currentUser.getEmail();
        userDatabase= FirebaseDatabase.getInstance().getReference("users/"+nowUser.replace('.','&'));


        key = getIntent().getStringExtra("showKey");
        format = getIntent().getStringExtra("PlayerFormat");
        showSinglePlayerDatabaseRef = FirebaseDatabase.getInstance().getReference("players/"+ format+ "/" + key);

        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user=dataSnapshot.getValue(User.class);
                if(user.getIsModerator().equals("true")){
                    System.out.println("habijabi");
                    edit_profilePhoto_button.setVisibility(View.VISIBLE);
                    deleteButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        showSinglePlayerDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player = dataSnapshot.getValue(Player.class);
                name.setText(player.getName());
                role.setText(player.getRole());
                match.setText(player.getMatch());
                innings.setText(player.getInnings());
                runs.setText(player.getRuns());
                average.setText(player.getAverage());
                strikeRate.setText(player.getStrikeRate());
                hundreds.setText(player.getHundreds());
                fifties.setText(player.getFifties());
                notOuts.setText(player.getNotOuts());
                bestScore.setText(player.getBestScore());
                balls.setText(player.getBalls());
                runsConceded.setText(player.getRunsConceded());
                wickets.setText(player.getWickets());
                economy.setText(player.getEconomy());
                bowlingAverage.setText(player.getBowlingAverage());
                bowlingStrikeRate.setText(player.getBowlingStrikeRate());
                fifers.setText(player.getFifers());
                bestFigure.setText(player.getBestFigure());

                Glide.with(showSinglePlayer.this)
                        .load(player.getDpUrl())
                        .into(profilePicture);


                showSinglePlayerDatabaseRef.removeEventListener(this);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        edit_profilePhoto_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEditProfilePicture();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSinglePlayerDatabaseRef.removeValue();
                finish();
            }
        });
    }

    public void toEditProfilePicture(){
        Intent intent = new Intent(this,editPlayer.class);
        intent.putExtra("showKey",key);
        intent.putExtra("PlayerFormat",format);
        startActivity(intent);
    }
}
