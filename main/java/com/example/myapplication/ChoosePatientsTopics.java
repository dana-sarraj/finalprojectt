package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ChoosePatientsTopics extends AppCompatActivity {
    ListView listView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_patients_topics);

        button = findViewById(R.id.choose_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChoosePatientsTopics.this,HomePatient.class));
            }
        });

        listView = findViewById(R.id.ChooseList);
        ArrayList<String> arrayList = getList();
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<>(ChoosePatientsTopics.this, android.R.layout.simple_list_item_multiple_choice,
                arrayList);
    }

    private ArrayList<String> getList() {
        ArrayList<String> arrayList = new ArrayList<>();



              return arrayList;
    }
}