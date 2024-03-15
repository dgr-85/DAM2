package com.example.uf3_p1_graphicsanimations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.UserManager;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private int motionX, motionY;
    private CanvasBG canvasBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        motionX = 100;
        motionY = 100;
        ConstraintLayout cLayout = findViewById(R.id.cLayout);
        canvasBG = new CanvasBG(this);
        canvasBG.setOnTouchListener(this);
        cLayout.addView(canvasBG);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        motionX = (int) event.getX();
        motionY = (int) event.getY();
        canvasBG.invalidate();
        return true;
    }

    class CanvasBG extends View {
        public CanvasBG(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255, 255, 255);
            Paint brush = new Paint();
            brush.setARGB(255, 155, 0, 75);
            brush.setStrokeWidth(10);
            brush.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(motionX, motionY, 100, brush);
        }
    }

}