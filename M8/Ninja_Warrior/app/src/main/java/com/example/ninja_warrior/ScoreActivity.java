package com.example.ninja_warrior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ScoreActivity extends AppCompatActivity {

    ListView listView;
    String[] players = {"Christine", "Alex", "Andrew", "Tony"};
    int[] scores = {15, 12, 10, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        listView = findViewById(R.id.list);
        ScoreRowAdapter adapter = new ScoreRowAdapter(getApplicationContext(), players, scores);
        listView.setAdapter(adapter);
    }
}