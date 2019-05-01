package com.abc.crictrackerbd;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addPlayer extends Activity {

    EditText name;
    EditText role;
    EditText match;
    EditText innings;
    EditText runs;
    EditText average;
    EditText strikeRate;
    EditText hundreds;
    EditText fifties;
    EditText notOuts;
    EditText bestScore;
    EditText balls;
    EditText runsConceded;
    EditText wickets;
    EditText economy;
    EditText bowlingAverage;
    EditText bowlingStrikeRate;
    EditText bestFigure;
    EditText fifers;
    String format;
    Button doneButton;



    private DatabaseReference databaseReference;
    private FirebaseAuth userAuthentication;

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        format = getIntent().getStringExtra("PlayerFormat");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("players").child(format);
        userAuthentication = FirebaseAuth.getInstance();

        name = findViewById(R.id.addName);
        role = findViewById(R.id.addRole);
        match = findViewById(R.id.addMatch);
        innings = findViewById(R.id.addInnings);
        runs = findViewById(R.id.addRuns);
        average = findViewById(R.id.addAverage);
        strikeRate = findViewById(R.id.addStrikeRate);
        hundreds = findViewById(R.id.addHundreds);
        fifties = findViewById(R.id.addFifties);
        notOuts = findViewById(R.id.addNotOuts);
        bestScore = findViewById(R.id.addBestScore);
        balls = findViewById(R.id.addBalls);
        runsConceded = findViewById(R.id.addRunsConceded);
        wickets = findViewById(R.id.addWickets);
        economy = findViewById(R.id.addEconomy);
        bowlingAverage = findViewById(R.id.addBowlingAverage);
        bowlingStrikeRate = findViewById(R.id.addBowlingStrikeRate);
        fifers = findViewById(R.id.addFifers);
        bestFigure = findViewById(R.id.addBestFigure);
        doneButton = findViewById(R.id.doneButton);


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdding();
            }
        });
    }

    private  void startAdding(){
        String name_val=name.getText().toString().trim();
        String role_val=role.getText().toString().trim();
        String match_val=match.getText().toString().trim();
        String innings_val=innings.getText().toString().trim();
        String runs_val=runs.getText().toString().trim();
        String average_val=average.getText().toString().trim();
        String strikeRate_val=strikeRate.getText().toString().trim();
        String hundreds_val=hundreds.getText().toString().trim();
        String fifties_val=fifties.getText().toString().trim();
        String notOuts_val=notOuts.getText().toString().trim();
        String bestScore_val=bestScore.getText().toString().trim();
        String balls_val=balls.getText().toString().trim();
        String runsConceded_val=runsConceded.getText().toString().trim();
        String wickets_val=wickets.getText().toString().trim();
        String economy_val=economy.getText().toString().trim();
        String bowlingAverage_val=bowlingAverage.getText().toString().trim();
        String bowlingStrikeRate_val=bowlingStrikeRate.getText().toString().trim();
        String fifers_val=fifers.getText().toString().trim();
        String bestFigure_val=bestFigure.getText().toString().trim();


        if(!TextUtils.isEmpty(name_val)&& !TextUtils.isEmpty(role_val) ){
            DatabaseReference newPlayer=databaseReference.push();
             player = new Player(name_val,role_val,match_val,innings_val,runs_val,average_val,strikeRate_val,hundreds_val,fifties_val,notOuts_val,bestScore_val,balls_val,runsConceded_val,wickets_val,economy_val,bowlingAverage_val,bowlingStrikeRate_val,fifers_val,bestFigure_val,"https://i.kym-cdn.com/entries/icons/original/000/003/619/ForeverAlone.jpg",format,newPlayer.getKey());
             databaseReference.child(newPlayer.getKey()).setValue(player);
            //startActivity(new Intent(addPost.this,noticeShowPage.class));
            finish();

        }

    }
}
