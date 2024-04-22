package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GetData {
    Call<List<RetroUsers>> getAllUsers();
}
