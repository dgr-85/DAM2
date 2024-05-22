package com.example.ninja_warrior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameView extends View {
    private static final int INC_TURN_RATE = 5;
    private static final float INC_ACCEL = 0.5f;
    private static final int PROCESS_INTERVAL = 50;
    private static final int INC_KNIFE_SPEED = 12;
    private List<Graphics> targets, totalLives;
    private Drawable drawableNinja, drawableKnife, drawableEnemy, drawableBlood, drawableGibBlood, drawableKatana, drawableSlash, drawableHeart, drawableSmoke;
    private Graphics ninja, smoke;
    private int ninjaTurnRate, nextKnife = 0;
    private float ninjaAccel, mX = 0, mY = 0;
    private GameThread thread = new GameThread();
    private long lastProcess = 0;
    private List<Knife> knifeStash;
    private Drawable[] drawableTarget = new Drawable[8];
    private MediaPlayer mpIntro, mpLoop;
    private SoundPool soundPool;
    private int idEnemyDeath, idGibDeath, idThrowKnife, idPlayerDeath, idPlayerSpawn, idKatanaReady, idKatanaCinematics, idKatanaDraw, idKatanaSheathe, idKatanaClimax;
    private boolean playerIsDead = false, gameIsPaused = false, respawnTimerIsRunning = false;
    private Activity parent;
    private CountDownTimer timer;
    private ObjectAnimator fadeOut;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        mpIntro = MediaPlayer.create(context, R.raw.bgm01_intro);
        mpLoop = MediaPlayer.create(context, R.raw.bgm01_loop);

        String selectedNinja = prefs.getString("list_ninjas", "ninja01");
        Integer ninjaId = getResources().getIdentifier(selectedNinja, "drawable", context.getPackageName());
        drawableNinja = context.getResources().getDrawable(ninjaId, null);
        drawableBlood = context.getResources().getDrawable(R.drawable.blood_splat, null);
        drawableGibBlood = context.getResources().getDrawable(R.drawable.gib_splat, null);
        drawableKatana = context.getResources().getDrawable(R.drawable.katana, null);
        drawableSlash = context.getResources().getDrawable(R.drawable.katana_slash, null);
        drawableEnemy = context.getResources().getDrawable(R.drawable.ninja_enemic, null);
        drawableHeart = context.getResources().getDrawable(R.drawable.heart, null);
        drawableSmoke = context.getResources().getDrawable(R.drawable.smoke, null);

        totalLives = new ArrayList<>(Arrays.asList(new Graphics(this, drawableHeart), new Graphics(this, drawableHeart), new Graphics(this, drawableHeart)));
        for (Graphics heart : totalLives) {
            heart.setHeight(50);
            heart.setWidth(50);
        }
        ninja = new Graphics(this, drawableNinja);
        smoke = new Graphics(this, drawableSmoke);
        smoke.setHeight(200);
        smoke.setWidth(200);
        smoke.setAngle(0);
        smoke.setPosX(-1);
        smoke.setPosY(-1);
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
        knifeStash = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            knifeStash.add(new Knife(this, drawableKnife, false, 0, false));
        }
        drawableTarget[0] = context.getResources().
                getDrawable(R.drawable.cap_ninja, null); //cap
        drawableTarget[1] = context.getResources().
                getDrawable(R.drawable.cos_ninja, null); //cos
        drawableTarget[2] = context.getResources().
                getDrawable(R.drawable.cua_ninja, null);

        soundPool = new SoundPool.Builder().setMaxStreams(10).build();
        idEnemyDeath = soundPool.load(context, R.raw.enemy_death, 1);
        idGibDeath = soundPool.load(context, R.raw.gib_death, 1);
        idThrowKnife = soundPool.load(context, R.raw.throw_knife, 1);
        idPlayerDeath = soundPool.load(context, R.raw.player_death, 1);
        idPlayerSpawn = soundPool.load(context, R.raw.player_spawn, 1);
        idKatanaReady = soundPool.load(context, R.raw.katana_ready, 2);
        idKatanaCinematics = soundPool.load(context, R.raw.katana_cinematics, 1);
        idKatanaDraw = soundPool.load(context, R.raw.katana_draw, 1);
        idKatanaSheathe = soundPool.load(context, R.raw.katana_sheathe, 1);
        idKatanaClimax = soundPool.load(context, R.raw.katana_climax, 1);

        setBackGroundMusic();
    }

    private void setBackGroundMusic() {
        mpIntro.start();
        mpIntro.setOnCompletionListener(mediaPlayer -> {
            mpLoop.start();
            mpLoop.setOnCompletionListener(mediaPlayer1 -> mediaPlayer1.start());
        });
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
        if (smoke.getPosX() != -1 && smoke.getPosY() != -1) {
            smoke.drawGraphic(canvas);
        }
    }

    synchronized protected void updateMovement() {
        if (!gameIsPaused && !playerIsDead) {
            Log.d("View", "updateMovement");
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
                if (target.verifyCollision(ninja)) {
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
                                destroyTarget(i, knife);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!playerIsDead) {
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
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!playerIsDead) {
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
        return false;
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
        soundPool.play(idEnemyDeath, 1, 1, 0, 0, 1);
        int numParts = 3;
        if (targets.get(i).getDrawable() == drawableEnemy) {
            targets.get(i).setDrawable(drawableBlood);
            for (int n = 0; n < numParts; n++) {
                Graphics target = new Graphics(this, drawableTarget[n]);
                target.setPosX(targets.get(i).getPosX());
                target.setPosY(targets.get(i).getPosY());
                target.setSpeedX(Math.random() * 7 - 3);
                target.setSpeedY(Math.random() * 7 - 3);
                target.setAngle((int) (Math.random() * 360));
                target.setRotation((int) (Math.random() * 8 - 4));
                targets.add(target);
            }
        } else {
            targets.get(i).setDrawable(drawableGibBlood);
        }
        targets.remove(i);
        if (knife != null) {
            knife.setActive(false);
        }

    }

    private void destroyPlayer() {
        playerIsDead = true;
        soundPool.play(idPlayerDeath, 0.7F, 0.7F, 0, 0, 1);
        ninja.setAngle(0);
        ninjaAccel = 0;
        ninjaTurnRate = 0;
        Drawable alive = ninja.getDrawable();
        ninja.setDrawable(drawableBlood);
        for (int x = 0; x < targets.size(); x++) {
            if (targets.get(x).distance(ninja) < 100) {
                targets.remove(x);
            }
        }
        fadeOutAnimation(ninja, alive);
    }

    private void fadeOutAnimation(Graphics graphics, Drawable alive) {
        fadeOut = ObjectAnimator.ofInt(graphics.getDrawable(), "alpha", 255, 0);
        fadeOut.setDuration(3000);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (playerIsDead && totalLives.size() > 0) {
                    respawnPlayer(alive);
                }
            }
        });
        Thread t = new Thread(() -> fadeOut.start());
        t.run();
    }

    private void respawnPlayer(Drawable alive) {
        smoke.setPosX(ninja.getPosX());
        smoke.setPosY(ninja.getPosY());
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.gameview_smoke_bomb);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ninja.setDrawable(alive);
                ninja.getView().setVisibility(VISIBLE);
                totalLives.remove(totalLives.size() - 1);
                playerIsDead = false;
                gameIsPaused = false;
                resumeGame();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        smoke.getView().startAnimation(animation);
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

    public void resumeGame() {
        Log.d("View", "resumeGame");
        synchronized (thread) {
            thread.notify();
        }
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
                if (gameIsPaused) {
                    synchronized (this) {
                        try {
                            Log.d("View", "thread.wait");
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                updateMovement();
            }
            Looper.loop();
        }
    }

}
