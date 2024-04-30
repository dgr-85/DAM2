package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Municipi {

    @SerializedName("elements")
    private ArrayList<Element> elements;

    public Municipi(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

}
