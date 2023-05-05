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

import com.example.myapplication.modules.DoctorModule;
import com.example.myapplication.modules.PationtsModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signup_Doctor extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    Button Sign_btn;
    EditText fullname;
    EditText birth_date;
    EditText address1;
    EditText emailaddress;
    TextView haveAccountdoc;
    EditText phone_number;
    EditText pass;
    EditText confirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_doctor);

        Sign_btn = findViewById(R.id.registedocr);
        fullname = findViewById(R.id.nameTxtdoc);
        birth_date = findViewById(R.id.birthTxtdoc);
        address1 = findViewById(R.id.addressTxtdoc);
        emailaddress = findViewById(R.id.emailTxtdoc);
        phone_number = findViewById(R.id.ponetxtdoc);
        pass = findViewById(R.id.passworddoc);
        confirmpass = findViewById(R.id.confirmPassdoc);
        haveAccountdoc = findViewById(R.id.haveAccountdoc);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Patients");
        progressDialog = new ProgressDialog(this);
     haveAccountdoc.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View view) {
             startActivity(new Intent(Signup_Doctor.this,Login_doctor.class));
         }
     });

        Sign_btn.setOnClickListener(view -> {
            createUser();
        });

    }

    private void createUser() {
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
                        DoctorModule doctorModule = new DoctorModule(name,address,birthdate,email,phone,password,confrimpassword);
                        databaseReference.child(name).setValue(doctorModule);

                        Toast.makeText(Signup_Doctor.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                        firebaseFirestore.collection("doctors").document(FirebaseAuth.getInstance().getUid()).set(
                                new PationtsModule(name,address,birthdate,email,phone,password,confrimpassword)
                        );

                        Intent intent = new Intent(Signup_Doctor.this,Login_doctor.class);
                        intent.putExtra("name",email);
                        intent.putExtra("password",password);
                        startActivity(intent);


                    }else{
                        Toast.makeText(Signup_Doctor.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }


}