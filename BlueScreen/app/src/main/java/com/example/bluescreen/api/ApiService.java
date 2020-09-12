package com.example.bluescreen.api;

import com.example.bluescreen.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/{criterial}")
    Call<MovieResponse> getAllMovie(
            @Path("criterial")String criterial,
            @Query("api_key")String api_key,
            @Query("language")String language,
            @Query("page")int page
    );

}
