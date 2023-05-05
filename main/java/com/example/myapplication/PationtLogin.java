package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PationtLogin extends AppCompatActivity {
      TextView donthaveAccount;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    EditText emailP;
    EditText passP;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pationt_login);

         donthaveAccount = findViewById(R.id.donthaveAccount);
         donthaveAccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent (PationtLogin.this,PationtSign.class));
             }
         });


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        emailP = findViewById(R.id.emailLogin);
        passP = findViewById(R.id.passLogin);
        btn_login = findViewById(R.id.login);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        emailP.setText(name);
        passP.setText(password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PationtLogin.this,ChoosePatientsTopics.class));

            }
        });

    }

    private void loginUser(){
        String email = emailP.getText().toString();
        String password = passP.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailP.setError("Email cannot be empty");
            emailP.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passP.setError("Password cannot be empty");
            passP.requestFocus();
        }else{
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(PationtLogin.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PationtLogin.this,ChoosePatientsTopics.class));
                    }else{
                        Toast.makeText(PationtLogin.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}