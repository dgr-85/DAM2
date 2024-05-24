package com.example.ninja_warrior;

import android.graphics.drawable.Drawable;
import android.view.View;

public class Knife extends Graphics {
    private boolean isActive;
    private int time;
    private boolean thrown;

    public Knife(View view, Drawable drawable, boolean isActive, int time, boolean thrown) {
        super(view, drawable);
        this.isActive = isActive;
        this.time = time;
        this.thrown = thrown;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isThrown() {
        return thrown;
    }

    public void setThrown(boolean thrown) {
        this.thrown = thrown;
    }
}
