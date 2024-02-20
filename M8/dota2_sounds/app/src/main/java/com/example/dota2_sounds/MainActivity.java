package com.example.dota2_sounds;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Integer> selectedElements;
    Field[] listElements;
    Button btnRetry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnPlay = findViewById(R.id.btnPlay);
        btnRetry=findViewById(R.id.btnRetry);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);

        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);
        ImageView img3 = findViewById(R.id.img3);

        selectedElements = new ArrayList<>();
        ImageView[] imgs = {img1, img2, img3};
        TextView[] texts = {tv1, tv2, tv3};
        listElements = R.raw.class.getFields();

        newRound(imgs, texts, btnPlay);
    }

    public void newRound(ImageView[] imgs, TextView[] texts, ImageButton btnPlay) {

        /*Descripción general:

         Las unidades básicas que forman el juego se componen de un sonido, una imagen y un texto.

         La carpeta raw contiene únicamente los sonidos correspondientes a estas unidades del juego
         (los sonidos de win y fail están en una subcarpeta). Por otro lado, para cada fichero de
         sonido, su imagen (de drawable) y su texto (de strings.xml) tienen exactamente el mismo nombre.

         Por tanto, si se crea un array de Fields a partir de los contenidos de raw, éste contendrá únicamente
         los sonidos del juego, pudiendo obtener el nombre de cualquiera de ellos, y por ende,
         de cualquier imagen y string asociados.

         Todas las búsquedas se hacen por nombre a partir de los nombres obtenidos de raw.*/

        setImages(imgs,texts);
        selectSound(btnPlay,imgs,texts);
    }

    public void setImages(ImageView[] imgs, TextView[] texts){
        int totalElements = listElements.length;
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
            texts[i].setText(String.valueOf(getString(id)));

            imgs[i].setTag(elemName);
            imgs[i].setImageDrawable(getDrawable(getResources().getIdentifier(elemName, "drawable", getPackageName())));
        }
    }
    public void selectSound(ImageButton btnPlay,ImageView[] imgs, TextView[] texts){
        int randomSound=(int)(Math.random()*selectedElements.size());
        randomSound=selectedElements.get(randomSound);
        String soundName=listElements[randomSound].getName();
        MediaPlayer mp=MediaPlayer.create(getApplicationContext(),getResources().getIdentifier(soundName,"raw",getPackageName()));
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
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.release();
                newRound(imgs,texts,btnPlay);
            }
        });
        mapSoundResult(soundName,imgs);
    }

    public void mapSoundResult(String soundName, ImageView[] imgs){
        for(ImageView iv:imgs){
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(iv.getTag().equals(soundName)){
                        guessOk();
                    }else{
                        guessWrong();
                    }
                }
            });
        }
    }

    public void guessOk(){

    }
    public void guessWrong(){

    }
}