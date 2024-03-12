package com.example.uf3_circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VistaSimple(this));
    }

    private static class VistaSimple extends View {
        private ShapeDrawable circle = new ShapeDrawable();

        public VistaSimple(Context context) {
            super(context);
            setFocusable(true);
            this.circle = new ShapeDrawable(new OvalShape());
            this.circle.getPaint().setColor(0xFF00FF00);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int posicionX = 100;
            int posicionY = 10;
            int ancho = 300;
            int alto = 300;
            this.circle.setBounds(posicionX, posicionY, posicionX
                    +
                    ancho, posicionY + alto);
            this.circle.draw(canvas);

            Paint pincel =new Paint();
            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(8);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(150, 150, 100, pincel);

            Path trazo =new Path();
            trazo.addCircle(150, 150, 100, Path.Direction.CCW);
            canvas.drawColor(Color.WHITE);
            pincel = new Paint();
            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(8);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawPath(trazo, pincel);
            pincel.setStrokeWidth(1);
            pincel.setStyle(Paint.Style.FILL);
            pincel.setTextSize(20);
            pincel.setTypeface(Typeface.SANS_SERIF);
            canvas.drawTextOnPath("Desarrollo de aplicaciones para móviles con Android", trazo, 10, 40, pincel);
        }
    }
}