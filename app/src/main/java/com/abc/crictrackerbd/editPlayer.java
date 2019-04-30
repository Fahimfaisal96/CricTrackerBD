package com.abc.crictrackerbd;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class editPlayer extends Activity {

    String Path;
    private final static int PICK_IMAGE_NUM = 107;

    private DatabaseReference editPlayerDatabaseRef;
    private StorageReference editPlayerStorageReference;

    EditText editName;
    EditText editRole;
    EditText editMatch;
    EditText editInnings;
    EditText editRuns;
    EditText editAverage;
    EditText editStrikeRate;
    EditText editHundreds;
    EditText editFifties;
    EditText editBestScore;
    EditText editNotOuts;
    EditText editBalls;
    EditText editRunsConceded;
    EditText editWickets;
    EditText editEconomy;
    EditText editBowlingAverage;
    EditText editBowlingStrikeRate;
    EditText editBestFigure;
    EditText editFifers;
    Button doneEditButton;
    Button selectImage;
    ImageView editPicture;

    String dpURL;
    Uri imageURI;

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        Path = getIntent().getStringExtra("showKey");
        editPlayerDatabaseRef = FirebaseDatabase.getInstance().getReference("players/" + Path);
        editPlayerStorageReference = FirebaseStorage.getInstance().getReference("pictures");

        editName = findViewById(R.id.editName);
        editRole = findViewById(R.id.editRole);
        editMatch = findViewById(R.id.editMatch);
        editInnings = findViewById(R.id.editInnings);
        editRuns = findViewById(R.id.editRuns);
        editAverage = findViewById(R.id.editAverage);
        editStrikeRate = findViewById(R.id.editStrikeRate);
        editHundreds = findViewById(R.id.editHundreds);
        editFifties = findViewById(R.id.editFifties);
        editNotOuts = findViewById(R.id.editNotOuts);
        editBestScore = findViewById(R.id.editBestScore);
        editBalls = findViewById(R.id.editBalls);
        editRunsConceded = findViewById(R.id.editRunsConceded);
        editWickets = findViewById(R.id.editWickets);
        editEconomy = findViewById(R.id.editEconomy);
        editBowlingAverage = findViewById(R.id.editBowlingAverage);
        editBowlingStrikeRate = findViewById(R.id.editBowlingStrikeRate);
        editFifers = findViewById(R.id.editFifers);
        editBestFigure = findViewById(R.id.editBestFigure);
        doneEditButton = findViewById(R.id.editDone);
        selectImage = findViewById(R.id.editSelectButton);
        editPicture = findViewById(R.id.editImage);

        doneEditButton.setVisibility(View.GONE);

        editPlayerDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                player = dataSnapshot.getValue(Player.class);

                editName.setText(player.getName());
                editRole.setText(player.getRole());
                editMatch.setText(player.getMatch());
                editInnings.setText(player.getInnings());
                editRuns.setText(player.getRuns());
                editAverage.setText(player.getAverage());
                editStrikeRate.setText(player.getStrikeRate());
                editHundreds.setText(player.getHundreds());
                editFifties.setText(player.getFifties());
                editNotOuts.setText(player.getNotOuts());
                editBestScore.setText(player.getBestScore());
                editBalls.setText(player.getBalls());
                editRunsConceded.setText(player.getRunsConceded());
                editWickets.setText(player.getWickets());
                editEconomy.setText(player.getEconomy());
                editBowlingAverage.setText(player.getBowlingAverage());
                editBowlingStrikeRate.setText(player.getBowlingStrikeRate());
                editBestFigure.setText(player.getBestFigure());
                editFifers.setText(player.getFifers());

                dpURL = player.getDpUrl();

                System.out.println("$$$$$$$" + player.getDpUrl());
                Glide.with(editPlayer.this)
                        .load(player.getDpUrl())
                        .into(editPicture);

                doneEditButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        doneEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goEditProfile();
            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    public void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_NUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_NUM && resultCode == RESULT_OK && data != null && data.getData()!=null){
            imageURI = data.getData();

            Glide.with(this)
                    .load(imageURI)
                    .into(editPicture);
        }
    }

    public void goEditProfile(){

        final Player changedPlayer = player;

        changedPlayer.setName(editName.getText().toString().trim());
        changedPlayer.setRole(editRole.getText().toString().trim());
        changedPlayer.setMatch(editMatch.getText().toString().trim());
        changedPlayer.setInnings(editInnings.getText().toString().trim());
        changedPlayer.setRuns(editRuns.getText().toString().trim());
        changedPlayer.setAverage(editAverage.getText().toString().trim());
        changedPlayer.setStrikeRate(editStrikeRate.getText().toString().trim());
        changedPlayer.setHundreds(editHundreds.getText().toString().trim());
        changedPlayer.setFifties(editFifties.getText().toString().trim());
        changedPlayer.setNotOuts(editNotOuts.getText().toString().trim());
        changedPlayer.setBestScore(editBestScore.getText().toString().trim());
        changedPlayer.setBalls(editBalls.getText().toString().trim());
        changedPlayer.setRunsConceded(editRunsConceded.getText().toString().trim());
        changedPlayer.setWickets(editWickets.getText().toString().trim());
        changedPlayer.setEconomy(editEconomy.getText().toString().trim());
        changedPlayer.setBowlingAverage(editBowlingAverage.getText().toString().trim());
        changedPlayer.setBowlingStrikeRate(editBowlingStrikeRate.getText().toString().trim());
        changedPlayer.setFifers(editFifers.getText().toString().trim());
        changedPlayer.setDpUrl(dpURL);


        if(imageURI != null){
            doneEditButton.setVisibility(View.GONE);
            final StorageReference fileReference = editPlayerStorageReference.child(player.getKey());

            StorageTask uploadTask = fileReference.putFile(imageURI)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Picture Gese.");
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dpURL = uri.toString();
                                    changedPlayer.setDpUrl(dpURL);
                                    editPlayerDatabaseRef.setValue(changedPlayer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(editPlayer.this,"Update Done",Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                    });
                                }
                            });

                            doneEditButton.setVisibility(View.VISIBLE);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("Picture Hoynai");
                            Toast.makeText(editPlayer.this,"Failed",Toast.LENGTH_LONG).show();
                            doneEditButton.setVisibility(View.VISIBLE);
                            editPlayerDatabaseRef.setValue(changedPlayer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(editPlayer.this,"Update Done",Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            });
                        }
                    });


        }
        else{
            editPlayerDatabaseRef.setValue(changedPlayer).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(editPlayer.this,"Update Done",Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }

    }
}
