package com.abc.crictrackerbd;

import android.app.Activity;
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

public class registerUser extends Activity {

    EditText password;
    EditText fullname;
    EditText email;
    Button doneButton;

    private DatabaseReference databaseReference;
    private FirebaseAuth userAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userAuthentication = FirebaseAuth.getInstance();

        password = findViewById(R.id.regiPasswordField);
        fullname = findViewById(R.id.fullNameField);
        email = findViewById(R.id.emailField);
        doneButton = findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser(){
        final FirebaseUser hmmuser = userAuthentication.getCurrentUser();
        //System.out.println("asd sfg sfdg sdfg sd " + hmmuser.getEmail());

        final String spassword = password.getText().toString().trim();
        final String sfullname = fullname.getText().toString().trim();
        final String semail = email.getText().toString().trim();

        userAuthentication.createUserWithEmailAndPassword(semail,spassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(semail, sfullname, "false","-1");
                            databaseReference.child(semail.replace('.','&')).setValue(user);
                            FirebaseUser hmmttuser = userAuthentication.getCurrentUser();
                            hmmttuser.sendEmailVerification();
                            Toast.makeText(registerUser.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();

                            email.setText("");
                            password.setText("");
                            fullname.setText("");

                            Intent intent = new Intent(registerUser.this, LoginPage.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            userAuthentication.signOut();
                            startActivity(intent);
                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(registerUser.this, "Email already taken.", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(registerUser.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                });


        
    }
}
