package com.example.uf3_p1_graphicsanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class WheelFortuneActivity extends AppCompatActivity {

    private ImageView wheel;
    private AnimatorSet wheelSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_fortune);

        wheel = findViewById(R.id.ivWheel);
        wheelSet = new AnimatorSet();
        TimeInterpolator interpolator = new LinearInterpolator();

        ValueAnimator wheelRotate = ObjectAnimator.ofFloat(wheel, "rotation", 0, 360);
        wheelRotate.setDuration(8000).setRepeatCount(ValueAnimator.INFINITE);
        wheelRotate.setRepeatMode(ValueAnimator.REVERSE);
        wheelRotate.setInterpolator(interpolator);

        ValueAnimator wheelMove = ObjectAnimator.ofFloat(wheel, "y", -150, 150);
        wheelMove.setDuration(8000).setRepeatCount(ValueAnimator.INFINITE);
        wheelMove.setRepeatMode(ValueAnimator.REVERSE);
        wheelMove.setInterpolator(interpolator);

        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(wheel, "alpha", 0f, 1f);
        fadeAnim.setDuration(4000);
        fadeAnim.setRepeatCount(ValueAnimator.INFINITE);
        fadeAnim.setRepeatMode(ValueAnimator.REVERSE);

        wheelSet.playTogether(wheelRotate, fadeAnim, wheelMove);

        wheelSet.start();
    }
}