package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
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

public class showSingleFixture extends Activity {

    private DatabaseReference showSingleFixtureDatabaseRef;

    TextView opponent;
    TextView venue;
    TextView date;
    TextView time;

    Button editFixtureButton;
    Button deleteFixtureButton;

    private FirebaseAuth loginAuthentication;
    private DatabaseReference userDatabase;


    String key;
    String format;
    String nowUser;

    User user;
    Fixture fixture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_fixture);

        editFixtureButton = findViewById(R.id.Edit_fixture_button);
        editFixtureButton.setVisibility(GONE);
        deleteFixtureButton = findViewById(R.id.Delete_fixture_button);
        deleteFixtureButton.setVisibility(GONE);

        opponent = findViewById(R.id.opponent_value);
        venue = findViewById(R.id.venue_value);
        date =findViewById(R.id.date_value);
        time = findViewById(R.id.time_value);


        loginAuthentication = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = loginAuthentication.getCurrentUser();
        nowUser=currentUser.getEmail();
        userDatabase= FirebaseDatabase.getInstance().getReference("users/"+nowUser.replace('.','&'));


        key = getIntent().getStringExtra("showKey");
        format = getIntent().getStringExtra("FixtureFormat");
        showSingleFixtureDatabaseRef = FirebaseDatabase.getInstance().getReference("fixture/"+ format+ "/" + key);


        showSingleFixtureDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fixture = dataSnapshot.getValue(Fixture.class);
                opponent.setText(fixture.getOpponent());
                venue.setText(fixture.getVenue());
                date.setText(fixture.getDate());
                time.setText(fixture.getTime());


                showSingleFixtureDatabaseRef.removeEventListener(this);


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
                    editFixtureButton.setVisibility(View.VISIBLE);
                    deleteFixtureButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editFixtureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeToEditFixture(v);
            }

        });

        deleteFixtureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleFixtureDatabaseRef.removeValue();
                finish();
            }
        });
    }

    private void takeToEditFixture(View v) {
        Intent intent = new Intent(this,editFixture.class);
        intent.putExtra("showKey",key);
        intent.putExtra("format",format);
        startActivity(intent);
    }
}
