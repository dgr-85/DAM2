package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private TextView tvPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay=findViewById(R.id.btnPlay);
        tvPlay=findViewById(R.id.tvPlay);

        MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.letitbe);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                    mediaPlayer.start();

                tvPlay.setText("Playing...");
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        tvPlay.setText("Finished.");
                    }
                });
            }
        });
    }

}