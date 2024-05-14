package com.example.ninja_warrior;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    // Increment estàndard de gir i acceleració
    private static final int INC_TURN_RATE = 5;
    private static final float INC_ACCEL = 0.5f;
    //Cada quant temps volem processar canvis (ms)
    private static int PROCESS_INTERVAL = 50;
    private static int INC_KNIFE_SPEED = 12;
    private List<Graphics> targets;
    private Drawable drawableNinja, drawableKnife, drawableEnemy;
    private Graphics ninja;// Gràfic del ninja
    private int ninjaTurnRate; // Increment de direcció
    private float ninjaAccel; // augment de velocitat
    private GameThread thread = new GameThread();
    // Quan es va realitzar l'últim procés
    private long lastProcess = 0;
    private float mX = 0, mY = 0;
    private boolean knifeThrown = false;
    // //// LLANÇAMENT //////
    private Graphics knife;
    private boolean knifeIsActive = false;
    private int knifeTime;
    private Drawable drawableTarget[] = new Drawable[8];
    private MediaPlayer mpThrow,mpExplosion;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        String selectedNinja = prefs.getString("list_ninjas", "ninja01");
        Integer ninjaId = getResources().getIdentifier(selectedNinja, "drawable", context.getPackageName());
        drawableNinja = context.getResources().getDrawable(ninjaId, null);
        ninja = new Graphics(this, drawableNinja);

        // Creem els objectius o blancs i inicialitzem la seva velocitat, angle i
        // rotació. La posició inicial no la podem obtenir
        // fins a conèixer ample i alt pantalla
        drawableEnemy = context.getResources().getDrawable(R.drawable.ninja_enemic, null);
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

        drawableKnife = context.getResources().getDrawable(R.drawable.ganivet, null);
        knife = new Graphics(this, drawableKnife);

        drawableTarget[0] = context.getResources().
                getDrawable(R.drawable.cap_ninja, null); //cap
        drawableTarget[1] = context.getResources().
                getDrawable(R.drawable.cos_ninja, null); //cos
        drawableTarget[2] = context.getResources().
                getDrawable(R.drawable.cua_ninja, null);

        mpThrow=MediaPlayer.create(context,R.raw.llancament);
        mpExplosion=MediaPlayer.create(context,R.raw.explosio);
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
        lastProcess = System.currentTimeMillis();
        thread.start();
    }

    // Mètode que dibuixa la vista
    @Override
    synchronized protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Graphics target : targets) {
            target.drawGraphic(canvas);
        }
        ninja.drawGraphic(canvas);
        if (knifeIsActive) {
            knife.drawGraphic(canvas);
        }
    }

    synchronized protected void updateMovement() {
        long currentInstant = System.currentTimeMillis();
        // No facis res si el període de procés no s'ha complert.
        if (lastProcess + PROCESS_INTERVAL > currentInstant) {
            return;
        }
        // Per una execució en temps real calculem retard
        double delay = (currentInstant - lastProcess) / PROCESS_INTERVAL;
        lastProcess = currentInstant; // Per a la propera vegada
        // Actualitzem velocitat i direcció del personatge Ninja a partir de
        // girNinja i acceleracioNinja (segons l'entrada del jugador)
        ninja.setAngle((int) (ninja.getAngle() + ninjaTurnRate * delay));
        double nIncX = ninja.getPosX() + ninjaAccel *
                Math.cos(Math.toRadians(ninja.getAngle())) * delay;
        double nIncY = ninja.getPosY() + ninjaAccel *
                Math.sin(Math.toRadians(ninja.getAngle())) * delay;
        // Actualitzem si el módul de la velocitat no és més gran que el màxim
        if (Math.hypot(nIncX, nIncY) <= Graphics.MAX_SPEED) {
            ninja.setPosX(nIncX);
            ninja.setPosY(nIncY);
        }
        // Actualitzem posicions X i Y
        ninja.increasePos(delay);
        for (Graphics target : targets) {
            target.increasePos(delay);
        }

        // Actualitzem posició de ganivet
        if (knifeIsActive) {
            knife.increasePos(delay);
            knifeTime -= delay;
            if (knifeTime < 0) {
                knifeIsActive = false;
            } else {
                for (int i = 0; i < targets.size(); i++)
                    if (knife.verifyCollision(targets.get(i))) {
                        destroyTarget(i);
                        break;
                    }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                knifeThrown = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mX);
                float dy = Math.abs(y - mY);
                if (dy < 6 && dx > 6) {
                    ninjaTurnRate = Math.round((x - mX) / 2);
                    knifeThrown = false;
                } else if (dx < 6 && dy > 6) {
                    ninjaAccel = Math.round((mY - y) / 25);
                    knifeThrown = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                ninjaTurnRate = 0;
                ninjaAccel = 0;
                if (knifeThrown) {
                    throwKnife();
                }
                break;
        }
        mX = x;
        mY = y;
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        // Suposem que processarem la pulsació
        boolean processed = true;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                ninjaAccel = +INC_ACCEL;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                ninjaAccel = -INC_ACCEL;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                ninjaTurnRate = -INC_TURN_RATE;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                ninjaTurnRate = +INC_TURN_RATE;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                throwKnife();
                break;
            default:
                // Si estem aquí, no hi ha pulsació que ens interessi
                processed = false;
                break;
        }
        return processed;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        // Suposem que processarem la pulsació
        boolean processed = true;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
            case KeyEvent.KEYCODE_DPAD_DOWN:
                ninjaAccel = 0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                ninjaTurnRate = 0;
                break;
            default:
            // Si estem aquí, no hi ha pulsació que ens interessi
                processed = false;
                break;
        }
        return processed;
    }

    private void destroyTarget(int i) {
        mpExplosion.start();
        int numParts = 3;
        if(targets.get(i).getDrawable()== drawableEnemy){
            for(int n = 0; n < numParts; n++){
                Graphics target = new Graphics(this, drawableTarget[n]);
                target.setPosX(targets.get(i).getPosX());
                target.setPosY(targets.get(i).getPosY());
                target.setSpeedX(Math.random()*7-3);
                target.setSpeedY(Math.random()*7-3);
                target.setAngle((int)(Math.random()*360));
                target.setRotation((int)(Math.random()*8-4));
                targets.add(target);
            }
        }
        targets.remove(i);
        knifeIsActive = false;
    }

    private void throwKnife() {
        mpThrow.start();
        knife.setPosX(ninja.getPosX() + ninja.getWidth() / 2 - knife.getWidth() / 2);
        knife.setPosY(ninja.getPosY() + ninja.getHeight() / 2 - knife.getHeight() / 2);
        knife.setAngle(ninja.getAngle());
        knife.setSpeedX(Math.cos(Math.toRadians(knife.getAngle())) *
                INC_KNIFE_SPEED);
        knife.setSpeedY(Math.sin(Math.toRadians(knife.getAngle())) *
                INC_KNIFE_SPEED);
        knifeTime = (int) Math.min(this.getWidth() / Math.abs(knife.getSpeedX()),
                this.getHeight() / Math.abs(knife.getSpeedY())) - 2;
        knifeIsActive = true;
    }

    class GameThread extends Thread {
        @Override
        public void run() {
            while (true) {
                updateMovement();
            }
        }
    }
}
