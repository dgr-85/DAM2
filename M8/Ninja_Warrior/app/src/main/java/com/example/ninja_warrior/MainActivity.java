package com.example.ninja_warrior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvTitle = findViewById(R.id.tvtitle);
        tvTitle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.title_animation));

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnScore = findViewById(R.id.btnScore);
        Button btnQuit = findViewById(R.id.btnQuit);

        btnPlay.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_animation));
        btnScore.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_animation));
        btnQuit.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_animation));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}