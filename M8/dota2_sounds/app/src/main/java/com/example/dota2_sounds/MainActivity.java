package com.example.dota2_sounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Integer> selectedElements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = findViewById(R.id.btnPlay);
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.primal_roar);


        int soundsLength = R.raw.class.getFields().length;

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);

        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);
        ImageView img3 = findViewById(R.id.img3);

        ImageView[] imgs = {img1, img2, img3};
        TextView[] texts = {tv1, tv2, tv3};

        newRound(imgs, texts);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(mp.isPlaying()){
                    mp.stop();
                    try {
                        mp.prepare();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    mp.start();
                }*/
                newRound(imgs, texts);
            }
        });
    }

    public void newRound(ImageView[] imgs, TextView[] texts) {

        Field[] listElements = R.raw.class.getFields();
        int totalElements = listElements.length;
        Field[] listImages = R.drawable.class.getFields();
        int randomNumber = (int) (Math.random() * totalElements);

        for (int i = 0; i < imgs.length; i++) {
            while (selectedElements.contains(randomNumber)) {
                randomNumber = (int) (Math.random() * totalElements);
            }
            if (selectedElements.size() < 3) {
                selectedElements.add(randomNumber);
            } else {
                selectedElements.set(i, randomNumber);
            }

            Field numberElem = listElements[randomNumber];
            String elemName = numberElem.getName();

            int id = getResources().getIdentifier(elemName, "string", getPackageName());
            String str = String.valueOf(getString(id));
            texts[i].setText(str);

            imgs[i].setImageDrawable(getDrawable(getResources().getIdentifier(elemName, "drawable", getPackageName())));
        }
    }

}