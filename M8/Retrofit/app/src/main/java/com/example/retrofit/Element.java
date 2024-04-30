package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Element {
    @SerializedName("municipi_nom")
    private String municipiNom;

    public String getMunicipiNom() {
        return municipiNom;
    }

}
