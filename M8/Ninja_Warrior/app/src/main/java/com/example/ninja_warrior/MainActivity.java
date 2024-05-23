package com.example.ninja_warrior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static MediaPlayer mpIntro;
    private static MediaPlayer mpLoop;
    private static Animation animTitle;
    private TextView tvTitle;
    private static boolean firstTime = true;
    private static Button btnPlay, btnScore, btnQuit;
    private static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Main", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mpIntro = MediaPlayer.create(MainActivity.this, R.raw.bgm_title_intro);
        mpLoop = MediaPlayer.create(MainActivity.this, R.raw.bgm_title_loop);

        tvTitle = findViewById(R.id.tvTitle);
        animTitle = AnimationUtils.loadAnimation(this, R.anim.main_menu_title_animation);
        animTitle.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                shakeTitleScreen(findViewById(R.id.cLayout), 10, 10);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        if (firstTime) {
            tvTitle.startAnimation(animTitle);
        }

        prefs = getPreferences(Context.MODE_PRIVATE);

        btnPlay = findViewById(R.id.btnPlay);
        btnScore = findViewById(R.id.btnScore);
        btnQuit = findViewById(R.id.btnQuit);
        btnPlay.setVisibility(View.INVISIBLE);
        btnScore.setVisibility(View.INVISIBLE);
        btnQuit.setVisibility(View.INVISIBLE);

        btnPlay.setOnClickListener(v -> startGame());

        btnScore.setOnClickListener(v -> showScoreList());

        btnQuit.setOnClickListener(v -> finish());
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
            builder.setPositiveButton(getString(R.string.info_ok), (dialog, which) -> dialog.cancel());
            builder.show();
        }
        if (item.getItemId() == R.id.menu_config) {
            startActivity(new Intent(this, PreferencesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setBackgroundMusic() {
        if (checkMusicCheckbox()) {
            mpIntro.start();
            mpIntro.setOnCompletionListener(mediaPlayer -> {
                mpLoop.start();
                mpLoop.setOnCompletionListener(mediaPlayer1 -> mediaPlayer1.start());
            });
        } else {
            if (mpIntro.isPlaying()) mpIntro.stop();
            if (mpLoop.isPlaying()) mpLoop.stop();
            mpIntro.release();
            mpLoop.release();
        }
    }

    public static boolean checkMusicCheckbox() {
        return prefs.getBoolean("checkbox_music", true);
    }

    public void shakeTitleScreen(View view, int duration, int offset) {
        setBackgroundMusic();
        Animation anim = new TranslateAnimation(0, 0, -offset, offset);
        anim.setDuration(duration);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(20);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnPlay.setVisibility(View.VISIBLE);
                btnScore.setVisibility(View.VISIBLE);
                btnQuit.setVisibility(View.VISIBLE);
                btnPlay.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.main_menu_button_animation));
                btnScore.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.main_menu_button_animation));
                btnQuit.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.main_menu_button_animation));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(anim);
        firstTime = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!firstTime) {
            setBackgroundMusic();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mpIntro.stop();
        mpLoop.stop();
        mpIntro.release();
        mpLoop.release();
    }

    public void showScoreList() {
        startActivity(new Intent(this, ScoreActivity.class));
    }

    public void startGame() {
        try {
            if (mpIntro.isPlaying()) {
                mpIntro.stop();
                mpIntro.prepare();
            }
            if (mpLoop.isPlaying()) {
                mpLoop.stop();
                mpLoop.prepare();
            }
        } catch (IOException e) {
        }
        startActivity(new Intent(this, GameActivity.class));
    }
}