package com.example.p7_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText idCard=findViewById(R.id.etIdCard);
        EditText etName = findViewById(R.id.etName);
        EditText etSurname = findViewById(R.id.etSurname);
        Spinner spCycles = findViewById(R.id.spCycles);
        RadioGroup rgCourses = findViewById(R.id.rgCourses);
        RadioButton rbFirst = findViewById(R.id.rbFirst);
        RadioButton rbSecond = findViewById(R.id.rbSecond);

        String[] cycles={"ASIX","DAM","DAW"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,cycles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCycles.setAdapter(adapter);


    }
}