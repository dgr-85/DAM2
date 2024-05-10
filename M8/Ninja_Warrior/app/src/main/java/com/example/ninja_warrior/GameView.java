package com.example.ninja_warrior;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.preference.PreferenceManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GameView extends View {
    // Increment estàndard de gir i acceleració
    private static final int INC_GIR = 5;
    private static final float INC_ACCELERACIO = 0.5f;
    private List<Graphics> targets;
    private Drawable drawableNinja, drawableKnife, drawableEnemy;
    private Graphics ninja;// Gràfic del ninja
    private int girNinja; // Increment de direcció
    private float acceleracioNinja; // augment de velocitat

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        drawableEnemy = context.getResources().getDrawable(R.drawable.ninja_enemic, null);

        String selectedNinja = prefs.getString("list_ninjas", "ninja01");
        Integer ninjaId = getResources().getIdentifier(selectedNinja, "drawable", context.getPackageName());
        drawableNinja = context.getResources().getDrawable(ninjaId, null);
        ninja = new Graphics(this, drawableNinja);

        // Creem els objectius o blancs i inicialitzem la seva velocitat, angle i
        // rotació. La posició inicial no la podem obtenir
        // fins a conèixer ample i alt pantalla
        targets = new ArrayList<>();
        Integer numTargets = Integer.parseInt(prefs.getString("text_num_enemies", prefs.getString("defaultValue", "3")));
        for (int i = 0; i < numTargets; i++) {
            Graphics target = new Graphics(this, drawableEnemy);
            target.setSpeedY(Math.random() * 4 - 2);
            target.setSpeedX(Math.random() * 4 - 2);
            target.setAngle((int) (Math.random() * 360));
            target.setRotation((int) (Math.random() * 8 - 4));
            targets.add(target);
        }
    }

    // Obtenim ample i alt de la pantalla
    @Override
    protected void onSizeChanged(int width, int height,
                                 int width_old, int height_old) {
        super.onSizeChanged(width, height, width_old, height_old);
        // Sabent ample i alt situem objectius de forma aleatòria
        for (Graphics target : targets) {
            target.setPosX(Math.random() * (width - target.getWidth()));
            target.setPosY(Math.random() * (height - target.getHeight()));
        }
        ninja.setPosX(width / 2);
        ninja.setPosY(height / 2);
    }

    // Mètode que dibuixa la vista
    @Override
    synchronized protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Graphics target : targets) {
            target.drawGraphic(canvas);
        }
        ninja.drawGraphic(canvas);
    }
}
