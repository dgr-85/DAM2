package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Municipi {

    @SerializedName("elements")
    @Expose
    private ArrayList elements;

    public Municipi(ArrayList elements) {
        this.elements = elements;
    }

    public ArrayList getElements() {
        return elements;
    }

    public void setElements(ArrayList elements) {
        this.elements = elements;
    }
}
