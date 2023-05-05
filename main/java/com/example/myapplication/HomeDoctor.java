package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.databinding.ActivityHomeDoctorBinding;
import com.example.myapplication.fragments.ChatFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;


public class HomeDoctor extends AppCompatActivity {
     ActivityHomeDoctorBinding binding;
     FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeDoctorBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_home_doctor);

        mAuth  = FirebaseAuth.getInstance();



        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment());
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.chat:
                        fragment=new ChatFragment();
                        break;
                    case R.id.profile:
                        fragment=new ProfileFragment() ;
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
                return true;
            }
        });
    }
}