package com.example.ninja_warrior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    ListView listView;
    Map<String,Integer> playersScores=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        playersScores.put("Christine",15);
        playersScores.put("Alex",12);
        playersScores.put("Andrew",10);
        playersScores.put("Tony",1);


        listView = findViewById(R.id.list);
        ScoreRowAdapter adapter = new ScoreRowAdapter(getApplicationContext(), playersScores);
        listView.setAdapter(adapter);
    }
}