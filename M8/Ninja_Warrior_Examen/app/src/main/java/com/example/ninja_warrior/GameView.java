package com.example.ninja_warrior;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameView extends View {
    private static final int INC_TURN_RATE = 5;
    private static final float INC_ACCEL = 0.5f;
    private static final int PROCESS_INTERVAL = 50;
    private static final int INC_KNIFE_SPEED = 18;
    private List<Graphics> targets, totalLives;
    private Drawable drawableNinja, drawableKnife, drawableEnemy, drawableEnemySmall, drawableHeart;
    private Graphics ninja;
    private int ninjaTurnRate, nextKnife = 0;
    private float ninjaAccel, mX = 0, mY = 0;
    private GameThread thread = new GameThread();
    private long lastProcess = 0, pausedInstant = 0;
    private List<Knife> knifeStash;
    private MediaPlayer mpIntro, mpLoop;
    private SoundPool soundPool;
    private int idEnemyDeath, idGibDeath, idThrowKnife, idPlayerDeath;
    private boolean playerIsDead = false, gameIsPaused = false, respawnTimerIsRunning = false;
    private Activity parent;
    private SharedPreferences prefs;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        mpIntro = MediaPlayer.create(context, R.raw.bgm01_intro);
        mpLoop = MediaPlayer.create(context, R.raw.bgm01_loop);

        String selectedNinja = prefs.getString("list_ninjas", "ninja01");
        Integer ninjaId = getResources().getIdentifier(selectedNinja, "drawable", context.getPackageName());
        drawableNinja = context.getResources().getDrawable(ninjaId, null);
        drawableEnemy = context.getResources().getDrawable(R.drawable.ninja_enemic, null);
        drawableEnemySmall = context.getResources().getDrawable(R.drawable.ninja_petit, null);
        drawableHeart = context.getResources().getDrawable(R.drawable.heart, null);
        drawableKnife = context.getResources().getDrawable(R.drawable.ganivet, null);

        totalLives = new ArrayList<>(Arrays.asList(new Graphics(this, drawableHeart), new Graphics(this, drawableHeart), new Graphics(this, drawableHeart)));
        for (Graphics heart : totalLives) {
            heart.setHeight(50);
            heart.setWidth(50);
        }
        ninja = new Graphics(this, drawableNinja);
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

        knifeStash = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            knifeStash.add(new Knife(this, drawableKnife, false, 0, false));
        }

        soundPool = new SoundPool.Builder().setMaxStreams(10).build();
        idEnemyDeath = soundPool.load(context, R.raw.enemy_death, 1);
        idGibDeath = soundPool.load(context, R.raw.gib_death, 1);
        idThrowKnife = soundPool.load(context, R.raw.throw_knife, 1);
        idPlayerDeath = soundPool.load(context, R.raw.player_death, 1);
        setBackGroundMusic();
    }

    private void setBackGroundMusic() {
        if (MainActivity.checkMusicCheckboxGame()) {
            mpIntro.start();
            mpIntro.setOnCompletionListener(mediaPlayer -> {
                mpLoop.start();
                mpLoop.setOnCompletionListener(mediaPlayer1 -> mediaPlayer1.start());
            });
        } else {
            if (mpIntro.isPlaying()) {
                mpIntro.stop();
                mpIntro.release();
            }
            if (mpLoop.isPlaying()) {
                mpLoop.stop();
                mpLoop.release();
            }
        }
    }

    public void pauseState(boolean state) {
        Log.d("View", "pauseState: " + state);
        gameIsPaused = state;
    }

    public void setParent(Activity parent) {
        this.parent = parent;
    }

    @Override
    protected void onSizeChanged(int width, int height,
                                 int width_old, int height_old) {
        super.onSizeChanged(width, height, width_old, height_old);
        for (Graphics target : targets) {
            do {
                target.setPosX(Math.random() * (width - target.getWidth()));
                target.setPosY(Math.random() * (height - target.getHeight()));
            } while (target.distance(ninja) < (height + width) / 2);
        }
        ninja.setPosX(width / 2);
        ninja.setPosY(height / 2);

        for (int i = 0; i < totalLives.size(); i++) {
            totalLives.get(i).setPosX(10 * (i + 1) + (50 * i));
            totalLives.get(i).setPosY(height - 50);
        }

        lastProcess = System.currentTimeMillis();
        if (!thread.isAlive()) {
            thread.start();
        }

    }

    @Override
    synchronized protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Graphics target : targets) {
            target.drawGraphic(canvas);
        }
        ninja.drawGraphic(canvas);
        for (Graphics life : totalLives) {
            life.drawGraphic(canvas);
        }
        for (Knife knife : knifeStash) {
            if (knife.isActive()) {
                knife.drawGraphic(canvas);
            }
        }
    }

    protected void updateMovement() {
        long currentInstant;
        if (gameIsPaused) {
            if (pausedInstant == 0) {
                pausedInstant = System.currentTimeMillis();
            }
            return;
        } else if (pausedInstant != 0) {
            currentInstant = pausedInstant;
            pausedInstant = 0;
        } else {
            currentInstant = System.currentTimeMillis();
        }
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
            if (target.verifyCollision(ninja) && target.getDrawable() != drawableEnemySmall) {
                destroyPlayer();
                break;
            }
        }
        for (Knife knife : knifeStash) {
            if (knife.isActive()) {
                knife.increasePos(delay);
                knife.setTime((int) (knife.getTime() - delay));
                if (knife.getTime() < 0) {
                    knife.setActive(false);
                } else {
                    for (int i = 0; i < targets.size(); i++) {
                        if (knife.verifyCollision((targets.get(i)))) {
                            targets.get(i).setDead(true);
                            destroyTarget(i, knife);
                            break;
                        }
                    }
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
                knifeStash.get(nextKnife).setThrown(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mX);
                float dy = Math.abs(y - mY);
                if (dy < 6 && dx > 6) {
                    ninjaTurnRate = Math.round((x - mX) / 2);
                    knifeStash.get(nextKnife).setThrown(false);
                } else if (dx < 6 && dy > 6) {
                    ninjaAccel = Math.round((mY - y) / 25);
                    knifeStash.get(nextKnife).setThrown(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                ninjaTurnRate = 0;
                ninjaAccel = 0;
                if (knifeStash.get(nextKnife).isThrown()) {
                    throwKnife(knifeStash.get(nextKnife));
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
                throwKnife(knifeStash.get(nextKnife));
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
        if (!playerIsDead) {
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
        return false;
    }

    private void destroyTarget(int i, Knife knife) {
        int numParts = Integer.parseInt(prefs.getString("text_num_enemies_small", prefs.getString("defaultValue", "3")));;
        if (knife != null) {
            knife.setActive(false);
        }
        if (targets.get(i).getDrawable() == drawableEnemy) {
            soundPool.play(idEnemyDeath, 1, 1, 0, 0, 1);
            for (int n = 0; n < numParts; n++) {
                Graphics target = new Graphics(this, drawableEnemySmall);
                target.setPosX(targets.get(i).getPosX());
                target.setPosY(targets.get(i).getPosY());
                target.setSpeedX(Math.random() * 7 - 3);
                target.setSpeedY(Math.random() * 7 - 3);
                target.setAngle((int) (Math.random() * 360));
                target.setRotation((int) (Math.random() * 8 - 4));
                targets.add(target);
            }
        } else {
            soundPool.play(idGibDeath, 1, 1, 0, 0, 1);
        }
        targets.remove(i);
    }

    private void destroyPlayer() {
        soundPool.play(idPlayerDeath, 0.7F, 0.7F, 0, 0, 1);
        for (int x = 0; x < targets.size(); x++) {
            if (targets.get(x).distance(ninja) < 100) {
                targets.remove(x);
            }
        }
        totalLives.remove(totalLives.size() - 1);
    }

    private void throwKnife(Knife knife) {
        if (!knife.isActive()) {
            soundPool.play(idThrowKnife, 1, 1, 0, 0, 1);
            knife.setPosX(ninja.getPosX() + ninja.getWidth() / 2 - knife.getWidth() / 2);
            knife.setPosY(ninja.getPosY() + ninja.getHeight() / 2 - knife.getHeight() / 2);
            knife.setAngle(ninja.getAngle());
            knife.setSpeedX(Math.cos(Math.toRadians(knife.getAngle())) *
                    INC_KNIFE_SPEED);
            knife.setSpeedY(Math.sin(Math.toRadians(knife.getAngle())) *
                    INC_KNIFE_SPEED);
            knife.setTime((int) Math.min(this.getWidth() / Math.abs(knife.getSpeedX()),
                    this.getHeight() / Math.abs(knife.getSpeedY())) - 6);
            knife.setActive(true);
            nextKnife = (nextKnife + 1) % knifeStash.size();
        }
    }

    public void gameLost() {
        for (Graphics target : targets) {
            target.setRotation(0);
            target.setSpeedX(0);
            target.setSpeedY(0);
        }
        playerIsDead = true;
        gameIsPaused = false;
    }

    public void stopMusic() {
        mpIntro.release();
        mpLoop.release();
    }

    class GameThread extends Thread {
        @Override
        public void run() {
            Log.d("View", "thread.run");
            Looper.prepare();
            while (totalLives.size() > 0) {
                updateMovement();
            }
            parent.finish();
            Looper.loop();
        }
    }

}
