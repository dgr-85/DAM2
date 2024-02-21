package com.example.dota2_sounds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
        DESCRIPCIÓN GENERAL:

         Las unidades básicas que forman el juego se componen de un sonido, una imagen y un texto.

         Para cada fichero de sonido, su imagen (de drawable) y su texto (de strings.xml)
         tienen exactamente el mismo nombre.

         Por tanto, si se crea una lista de Fields a partir de los contenidos de raw, se podrá
         obtener el nombre de cualquier sonido del juego, y por ende, de su imagen y string asociados.

         Todas las búsquedas se hacen por nombre a partir de los nombres obtenidos de raw.

         ========================================

         VARIABLES GLOBALES:

         selectedElements: almacena las posiciones de 3 elementos elegidos al azar para cada ronda.
         listElements: contiene los Fields de todos los ficheros de sonido, excepto "win" y "fail".
         btnPlay: reproduce el sonido de cada ronda cada vez que se pulsa.
         imgs: contiene las ImageView donde pintar las imágenes.
         texts: contiene los TextView asociados a las imágenes.
         toastIsShowing: bloquea todos los onClickListeners durante la aparición de cualquier Toast.
         soundHasBeenPlayed: bloquea los onClickListeners de las ImageView hasta que se pulsa btnPlay
                             por primera vez. Este efecto se reinicia en cada ronda.
     */
    List<Integer> selectedElements;
    List<Field> listElements;
    ImageButton btnPlay;
    ImageView[] imgs;
    TextView[] texts;
    boolean toastIsShowing=false;
    boolean soundHasBeenPlayed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);

        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);
        ImageView img3 = findViewById(R.id.img3);

        selectedElements = new ArrayList<>();
        imgs = new ImageView[]{img1, img2, img3};
        texts = new TextView[]{tv1, tv2, tv3};
        listElements = new ArrayList<>();
        for(Field f:R.raw.class.getFields()){
            if(!f.getName().equals("win") && !f.getName().equals("fail")){
                listElements.add(f);
            }
        }

        newRound();
    }

    public void newRound() {
        soundHasBeenPlayed=false;
        setImages();
        selectSound(btnPlay);
    }

    public void setImages(){
        int totalElements = listElements.size();
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

            Field numberElem = listElements.get(randomNumber);
            String elemName = numberElem.getName();

            int id = getResources().getIdentifier(elemName, "string", getPackageName());
            texts[i].setText(getString(id));

            imgs[i].setTag(elemName);
            imgs[i].setImageDrawable(getDrawable(getResources().getIdentifier(elemName, "drawable", getPackageName())));
        }
    }
    public void selectSound(ImageButton btnPlay){
        int randomSound=(int)(Math.random()*selectedElements.size());
        randomSound=selectedElements.get(randomSound);
        String soundName=listElements.get(randomSound).getName();
        MediaPlayer mp=MediaPlayer.create(getApplicationContext(),getResources().getIdentifier(soundName,"raw",getPackageName()));
        btnPlay.setOnClickListener(v -> {
            if(toastIsShowing){
                return;
            }
            soundHasBeenPlayed=true;
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
        });
        mapSoundResult(soundName,mp);
    }

    public void mapSoundResult(String soundName, MediaPlayer mpSelected){
        for(ImageView iv:imgs){
            iv.setOnClickListener(v -> {
                if(toastIsShowing){
                    return;
                }if(!soundHasBeenPlayed){
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setMessage(getString(R.string.no_sound_played));
                    builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                    return;
                }
                if(iv.getTag().toString().equals(soundName)){
                    guessCorrect(mpSelected);
                }else{
                    guessWrong();
                }
            });
        }
    }

    public void guessCorrect(MediaPlayer mpSelected){
        toastIsShowing=true;
        Toast toast=Toast.makeText(MainActivity.this,getString(R.string.correct),Toast.LENGTH_SHORT);
        toast.show();
        MediaPlayer mp=MediaPlayer.create(MainActivity.this,R.raw.win);
        mp.start();
        mp.setOnCompletionListener(v->{
            toastIsShowing=false;
            mpSelected.stop();
            mpSelected.release();
            mp.release();
            newRound();
        });
    }
    public void guessWrong(){
        toastIsShowing=true;
        Toast toast=Toast.makeText(MainActivity.this,getString(R.string.wrong),Toast.LENGTH_SHORT);
        toast.show();
        MediaPlayer mp=MediaPlayer.create(MainActivity.this,R.raw.fail);
        mp.start();
        mp.setOnCompletionListener(v->{
            toastIsShowing=false;
            mp.release();
        });
    }
}