package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class FrontPage extends Activity {

    FirebaseAuth currentlyLoggedIn;
    DatabaseReference userDatabase;

    Button toFixtures;
    Button toScorecard;
    Button toPlayers;
    Button toLivescore;

    String nowUser,token;

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        currentlyLoggedIn = FirebaseAuth.getInstance();
        userDatabase = FirebaseDatabase.getInstance().getReference("users").child(currentlyLoggedIn.getCurrentUser().getEmail().replace('.','&')).child("token");
        //FirebaseUser currentUser = currentlyLoggedIn.getCurrentUser();

        toFixtures = findViewById(R.id.fixtures);
        toScorecard = findViewById(R.id.Scorecard);
        toPlayers = findViewById(R.id.Players);
        toLivescore = findViewById(R.id.Livescore);
        logout = findViewById(R.id.Logout);

        nowUser = getIntent().getStringExtra("current");

        toFixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTofixtures();
            }
        });
        toScorecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeToScorecard();
            }
        });
        toPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeToPlayers();
            }
        });
        toLivescore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeToLivescore(v);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentlyLoggedIn.signOut();
                Intent intent = new Intent(FrontPage.this,LoginPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        token = FirebaseInstanceId.getInstance().getToken();
        userDatabase.setValue(token);

    }


    public void takeTofixtures(){
        Intent intent = new Intent(this,showFixtureFormat.class);
        intent.putExtra("current",nowUser);
        startActivity(intent);
    }
    public void takeToScorecard(){
        Intent intent = new Intent(this,showScorecard.class);
        intent.putExtra("current",nowUser);
        startActivity(intent);
    }

    public void takeToPlayers(){
        Intent intent = new Intent(this,showPlayers.class);
        intent.putExtra("current",nowUser);
        startActivity(intent);
    }


    private void takeToLivescore(View view) {
        Intent intent = new Intent(this,showLivescore.class);
        intent.putExtra("current",nowUser);
        startActivity(intent);
    }
}
