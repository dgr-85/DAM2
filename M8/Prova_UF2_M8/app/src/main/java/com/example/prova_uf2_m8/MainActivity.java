package com.example.prova_uf2_m8;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnG;
    Button btnF;
    Button btnS;
    Button btnStop;
    ImageView iv;
    MediaPlayer mp;
    String[] instrument={"guitarra","flauta","saxofon"};
    String currentInstrument="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnG=findViewById(R.id.btnGuitarra);
        btnF=findViewById(R.id.btnFlauta);
        btnS=findViewById(R.id.btnSaxofon);
        btnStop=findViewById(R.id.btnStop);
        iv=findViewById(R.id.imageView);

        btnG.setOnClickListener(v -> {
            setGuitarra();
        });

        btnF.setOnClickListener(v -> {
            setFlauta();
        });

        btnS.setOnClickListener(v -> {
            setSaxofon();
        });

        btnStop.setOnClickListener(v -> {
            stopSong();
        });
    }

    public void setGuitarra(){
        if(currentInstrument.equals(instrument[0])){
            return;
        }
        currentInstrument = instrument[0];
        iv.setImageResource(R.drawable.guitarra);
        if(mp!=null && mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        mp=MediaPlayer.create(MainActivity.this,R.raw.guitarra);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    public void setFlauta(){
        if(currentInstrument.equals(instrument[1])){
            return;
        }
        currentInstrument = instrument[1];
        iv.setImageResource(R.drawable.flauta);
        if(mp!=null && mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        mp=MediaPlayer.create(MainActivity.this,R.raw.flauta);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    public void setSaxofon(){
        if(currentInstrument.equals(instrument[2])){
            return;
        }
        currentInstrument = instrument[2];
        iv.setImageResource(R.drawable.saxofon);
        if(mp!=null && mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        mp=MediaPlayer.create(MainActivity.this,R.raw.saxofon);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    public void stopSong(){
        currentInstrument = "";
        iv.setImageDrawable(null);
        if(mp!=null) {
            mp.stop();
            mp.release();
            mp=null;
        }
    }
}