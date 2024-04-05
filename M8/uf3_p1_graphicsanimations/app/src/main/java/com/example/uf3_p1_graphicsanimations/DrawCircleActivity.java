package com.example.uf3_p1_graphicsanimations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DrawCircleActivity extends AppCompatActivity implements View.OnTouchListener {
    private int coordX, coordY;
    private boolean hasCanvasBeenClicked = false;
    private CanvasBG canvasBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawcircle);

        ConstraintLayout cLayout = findViewById(R.id.cLayout);
        canvasBG = new CanvasBG(this);
        canvasBG.setOnTouchListener(this);
        cLayout.addView(canvasBG);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        hasCanvasBeenClicked = true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            coordX = (int) event.getX();
            coordY = (int) event.getY();
            canvasBG.invalidate();
        }
        return false;
    }

    class CanvasBG extends View {

        private Paint brush;

        public CanvasBG(Context context) {
            super(context);
            brush = new Paint();
            brush.setARGB(255, 155, 0, 75);
            brush.setStrokeWidth(10);
            brush.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255, 255, 255);
            if (hasCanvasBeenClicked) {
                canvas.drawCircle(coordX, coordY, 100, brush);
            }
            hasCanvasBeenClicked = false;
        }
    }

}
