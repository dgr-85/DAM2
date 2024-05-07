package com.example.ninja_warrior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_menu_title_animation));

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnScore = findViewById(R.id.btnScore);
        Button btnQuit = findViewById(R.id.btnQuit);

        btnPlay.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_menu_button_animation));
        btnScore.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_menu_button_animation));
        btnQuit.startAnimation(AnimationUtils.loadAnimation(this, R.anim.main_menu_button_animation));

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScoreList();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_info) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.info_title));
            builder.setMessage(getString(R.string.info_instructions) + System.lineSeparator() + getString(R.string.info_made_by));
            builder.setPositiveButton(getString(R.string.info_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        if (item.getItemId() == R.id.menu_config) {
            startActivity(new Intent(this, PreferencesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void showScoreList() {
        startActivity(new Intent(this, ScoreActivity.class));
    }
}