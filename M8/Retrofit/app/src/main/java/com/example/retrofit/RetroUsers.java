package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class RetroUsers {

    private String municipi;

    public RetroUsers(String municipi) {
        this.municipi = municipi;
    }

    public String getUser() {
        return municipi;
    }

    public void setUser(String name) {
        this.municipi = name;
    }
}
