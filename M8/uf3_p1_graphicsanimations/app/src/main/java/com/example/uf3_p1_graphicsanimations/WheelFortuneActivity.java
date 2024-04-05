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

        wheel=findViewById(R.id.ivWheel);
        wheelSet=new AnimatorSet();
        TimeInterpolator interpolator = new LinearInterpolator();

        ValueAnimator wheelRotate= ObjectAnimator.ofFloat(wheel,"rotation",0,360);
        wheelRotate.setDuration(7000).setRepeatCount(ValueAnimator.INFINITE);
        wheelRotate.setInterpolator(interpolator);
        wheelSet.play(wheelRotate);

        ValueAnimator wheelVanish=ObjectAnimator.of
        wheelSet.start();
    }
}