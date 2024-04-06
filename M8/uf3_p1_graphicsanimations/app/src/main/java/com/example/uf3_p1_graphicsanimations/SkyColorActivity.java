package com.example.uf3_p1_graphicsanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;

public class SkyColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sky_color);


        ValueAnimator skyAnim = ObjectAnimator.ofInt(findViewById(R.id.ivColor), "backgroundColor", Color.rgb(120, 20, 200), Color.rgb(50, 150, 200));

        skyAnim.setDuration(3000);
        skyAnim.setRepeatCount(ValueAnimator.INFINITE);
        skyAnim.setRepeatMode(ValueAnimator.REVERSE);

        skyAnim.setEvaluator(new ArgbEvaluator());

        skyAnim.start();

    }
}