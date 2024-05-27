package com.example.ninja_warrior;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Graphics {
    private Drawable drawable; //Imatge que dibuixarem
    private double posX, posY; //Posicio
    private double speedX, speedY; //Velocitat desplacament
    private int angle, rotation; //Angle i velocitat rotacio
    private int width, height; //Dimensions de la imatge
    private int collisionRadius; //Per determinar col.lisio
    private boolean isDead;
    private View view;
    public static final int MAX_SPEED = 20;

    public Graphics(View view, Drawable drawable) {
        this.view = view;
        this.drawable = drawable;
        width = drawable.getIntrinsicWidth();
        height = drawable.getIntrinsicHeight();
        collisionRadius = (height + width) / 4;
        isDead = false;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCollisionRadius() {
        return collisionRadius;
    }

    public void setCollisionRadius(int collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void drawGraphic(Canvas canvas) {
        canvas.save();
        int x = (int) (posX + width / 2);
        int y = (int) (posY + height / 2);
        canvas.rotate((float) angle, (float) x, (float) y);
        drawable.setBounds((int) posX, (int) posY,
                (int) posX + width, (int) posY + height);
        drawable.draw(canvas);
        canvas.restore();
        view.invalidate();
    }
    public void increasePos(double factor) {
        posX += speedX * factor;
        // Si sortim de la pantalla, corregim posició
        if (posX < -width / 2) {
            posX = view.getWidth() - width / 2;
        }
        if (posX > view.getWidth() - width / 2) {
            posX = -width / 2;
        }
        posY += speedY * factor;
        if (posY < -height / 2) {
            posY = view.getHeight() - height / 2;
        }
        if (posY > view.getHeight() - height / 2) {
            posY = -height / 2;
        }
        angle += rotation * factor; //Actualitzem angle
    }
    public double distance(Graphics g) {
        return Math.hypot(posX-g.posX, posY-g.posY);
    }
    public boolean verifyCollision(Graphics g) {
        return(distance(g) < (collisionRadius +g.collisionRadius));
    }
}
