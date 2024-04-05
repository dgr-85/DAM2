package com.example.uf3_p1_graphicsanimations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button btnCircle=findViewById(R.id.btnDrawCircle);
            Button btnTween=findViewById(R.id.btnTween);
            Button btnSunMove=findViewById(R.id.btnSunMove);
            Button btnWheel=findViewById(R.id.btnWheelRotate);

            btnCircle.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), DrawCircleActivity.class)));
            btnTween.setOnClickListener(v -> v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tween)));
            btnSunMove.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SunMoveActivity.class)));
            btnWheel.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), WheelFortuneActivity.class)));
        }
}