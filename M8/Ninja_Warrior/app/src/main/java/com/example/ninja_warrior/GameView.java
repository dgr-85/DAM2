package com.example.ninja_warrior;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Vector;

public class GameView extends View {
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableNinja, drawableKnife, drawableEnemy;
        // Obtenim referència al recurs ninja_enemic guardat en carpeta Res
        drawableEnemy = context.getResources().
                getDrawable(R.drawable.ninja_enemic, null);
        // Creem els objectius o blancs i inicialitzem la seva velocitat, angle i
        // rotació. La posició inicial no la podem obtenir
        // fins a conèixer ample i alt pantalla
        Vector<Graphics> objectius = new Vector<Graphics>();
        for (int i = 0; i < objectius.size(); i++) {
            Graphics objectiu = new Graphics(this, drawableEnemy);
            objectiu.setIncY(Math.random() * 4 - 2);
            objectiu.setIncX(Math.random() * 4 - 2);
            objectiu.setAngle((int) (Math.random() * 360));
            objectiu.setRotacio((int) (Math.random() * 8 - 4));
            objectius.add(objectiu);
        }
    }

    // Métode que ens dóna ample i alt pantalla
    @Override
    protected void onSizeChanged(int ancho, int alto,
                                 int ancho_anter, int alto_anter) {
        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);
        // Una vegada que coneixem el nostre ample i alt situem els objectius de
        // forma aleatória
        for (Graphics objectiu : objectius) {
            objectiu.setPosX(Math.random() * (ancho - objectiu.getAmplada()));
            objectiu.setPosY(Math.random() * (alto - objectiu.getAltura()));
        }
    }

    // Métode que dibuixa la vista
    @Override
    synchronized protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Graphics objetiu : objectius) {
            objetiu.dibuixaGrafic(canvas);
        }
    }
}
