package com.example.uf3_p1_graphicsanimations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ConstraintLayout main= findViewById(R.id.cLayout);
         main.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event){
                int motionX= (int) event.getX();
                int motionY=(int) event.getY();
                 return true;
             }
         });
    }
}