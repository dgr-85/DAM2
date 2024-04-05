package com.example.uf3_p1_graphicsanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.widget.ImageView;

public class SunMoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun_move);

        ImageView sun = this.findViewById(R.id.sun);
        AnimatorSet sunSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.sun_movement);
        sunSet.setTarget(sun);
        sunSet.start();
    }
}