package com.example.bluescreen.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static ApiClient instance;

    public ApiClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static synchronized ApiClient getInstance(){
        if(instance==null)
            instance = new ApiClient();
        return instance;
    }

    public ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }
}
