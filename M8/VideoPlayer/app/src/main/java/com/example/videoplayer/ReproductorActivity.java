package com.example.videoplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReproductorActivity extends AppCompatActivity {
    private Button botonReproducir;
    private TextView lbEstado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonReproducir = findViewById(R.id.button1);
        lbEstado = findViewById(R.id.textView1);
//También puedes asociar la propiedad Onclic del botón con el método onClick en vez de utilizar OnClickListener
//botonReproducir.setOnClickListener(new
//View.OnClickListener() {
        botonReproducir.setOnClickListener(arg0 -> {
             MediaPlayer mp =  MediaPlayer.create(ReproductorActivity.this,R.raw.letitbe);
             mp.start();
             lbEstado.setText("Reproduciendo");
             mp.setOnCompletionListener(arg01 -> lbEstado.setText("Fin de la Reproducción"));
        });
    }
}
