package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.modules.PationtsModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PationtSign extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    Button Sign_btn;
    EditText fullname;
    EditText birth_date;
    EditText address1;
    EditText emailaddress;
    TextView haveAccount;
    EditText phone_number;
    EditText pass;
    EditText confirmpass;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pationt_sign);

        Sign_btn = findViewById(R.id.register);
        fullname = findViewById(R.id.nameTxt);
        birth_date = findViewById(R.id.birthTxt);
        address1 = findViewById(R.id.addressTxt);
        emailaddress = findViewById(R.id.emailTxt);
        haveAccount = findViewById(R.id.haveAccount);

       phone_number = findViewById(R.id.ponetxt);
        pass = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirmPass);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);

        Sign_btn.setOnClickListener(view -> {
            creatUser();
        });

    }

    private void creatUser() {

        String email = emailaddress.getText().toString();
        String password = pass.getText().toString();
        String address = address1.getText().toString();
        String birthdate= birth_date.getText().toString();
        String name = fullname.getText().toString();
        String confrimpassword = confirmpass.getText().toString();
        String phone = phone_number.getText().toString();
        if (TextUtils.isEmpty(email)){
            emailaddress.setError("Email cannot be empty");
            emailaddress.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            pass.setError("Password cannot be empty");
            pass.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            fullname.setError("Password cannot be empty");
            fullname.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            address1.setError("Password cannot be empty");
            address1.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            phone_number.setError("Password cannot be empty");
            phone_number.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            confirmpass.setError("Password cannot be empty");
            confirmpass.requestFocus();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(PationtSign.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PationtSign.this, PationtLogin.class));
                        firebaseFirestore.collection("Patients").document(FirebaseAuth.getInstance().getUid()).set(
                                new PationtsModule(name,address,birthdate,email,phone,password,confrimpassword));
                        progressDialog.cancel(); } else{
                        Toast.makeText(PationtSign.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.cancel(); }
                }
            });
        }

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PationtSign.this , PationtLogin.class));
            }
        });



    }




}