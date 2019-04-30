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

public class editFixture extends Activity {
    private EditText editOpponent;
    private EditText editVenue;
    private EditText editDate;
    private EditText editFormat;
    private EditText editTime;
    private Button editFixtureSubmitButton;

    private DatabaseReference fixtureDatabaseRef;

    Fixture fixture;
    String key;
    String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fixture);

        editOpponent =(EditText) findViewById(R.id.editOpponent);
        editVenue = (EditText) findViewById(R.id.editVenue);
        editDate = (EditText) findViewById(R.id.editDate);
        editTime = (EditText) findViewById(R.id.editTime);
        editFixtureSubmitButton = (Button) findViewById(R.id.editFixtureSubmitButton);
        editFixtureSubmitButton.setVisibility(GONE);

        key = getIntent().getStringExtra("showKey");
        format = getIntent().getStringExtra("format");
        fixtureDatabaseRef = FirebaseDatabase.getInstance().getReference("fixture/"+ format+ "/" + key);

        fixtureDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fixture = dataSnapshot.getValue(Fixture.class);

                editOpponent.setText(fixture.getOpponent());
                editVenue.setText(fixture.getVenue());
                editDate.setText(fixture.getDate());
                //editTeacherDesignation.setText(teacher.getDesignation());
                editTime.setText(fixture.getTime());
                editFixtureSubmitButton.setVisibility(View.VISIBLE);
                fixtureDatabaseRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editFixtureSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditFixture();
            }
        });
    }

    private void goToEditFixture() {
        Fixture changedFixture = fixture;
        changedFixture.setOpponent(editOpponent.getText().toString().trim());
        changedFixture.setVenue(editVenue.getText().toString().trim());
        changedFixture.setDate(editDate.getText().toString().trim());
        //changedTeacher.setDesignation(editTeacherDesignation.getText().toString().trim());
        changedFixture.setTime(editTime.getText().toString().trim());
        fixtureDatabaseRef.setValue(changedFixture).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(editFixture.this,"Update Done",Toast.LENGTH_LONG).show();
            }
        });
        Intent intent = new Intent(this, showSingleFixture.class);
        intent.putExtra("showKey",key);
        intent.putExtra("format",format);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
