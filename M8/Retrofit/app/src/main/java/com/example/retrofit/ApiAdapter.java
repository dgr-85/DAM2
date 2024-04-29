package com.example.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    private static ApiService apiService;
    private static final String BASE_URL = "https://do.diba.cat/api/dataset/";

    public static ApiService getApiServiceInstance() {

        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    /*.client()*/
                    .build();
            apiService=retrofitSingleton.create(ApiService.class);
        }

    }
}
