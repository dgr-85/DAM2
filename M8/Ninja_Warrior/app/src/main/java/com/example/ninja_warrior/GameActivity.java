package com.example.ninja_warrior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;

import java.net.ContentHandler;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    private boolean musicPlays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("GameActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = findViewById(R.id.GameView);
        gameView.setParent(this);

        SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
        musicPlays = prefs.getBoolean("checkbox_music", true);
    }

    @Override
    public void onBackPressed() {
        Log.d("GameActivity","onBackpressed");
        onPause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.leave_game_title));
        builder.setMessage(getString(R.string.leave_game_text));
        builder.setPositiveButton(getString(R.string.leave_game_accept), (dialog, which) -> {
            super.onBackPressed();
            gameView.stopMusic();
            gameView.gameLost();
            finish();
        });
        builder.setNegativeButton(getString(R.string.leave_game_deny), (dialog, which) -> {
            onResume();
            dialog.cancel();
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        Log.d("GameActivity","onDestroy");
        super.onDestroy();
        gameView.stopMusic();
    }

    @Override
    protected void onPause() {
        Log.d("GameActivity","onPause");
        gameView.pauseState(true);
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("GameActivity","onResume");
        gameView.pauseState(false);
        gameView.resumeGame();
        super.onResume();
    }
}