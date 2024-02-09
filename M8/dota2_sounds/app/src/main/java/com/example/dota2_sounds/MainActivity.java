package com.example.dota2_sounds;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay=findViewById(R.id.btnPlay);
        MediaPlayer mp=MediaPlayer.create(MainActivity.this,R.raw.arena_of_blood);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.stop();
                    try {
                        mp.prepare();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    mp.start();
                }
            }
        });
    }
}