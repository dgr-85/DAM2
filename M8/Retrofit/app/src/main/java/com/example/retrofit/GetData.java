package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetData {
    @GET("municipis")
    Call<Municipi> getAllUsers();
}
