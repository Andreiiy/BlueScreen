package com.example.bluescreen.data;

import androidx.paging.PageKeyedDataSource;

import com.example.bluescreen.BuildConfig;
import com.example.bluescreen.api.ApiClient;
import com.example.bluescreen.models.MovieResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource {

    private String criteria;

    public MovieDataSource(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public void loadAfter(@NotNull LoadParams loadParams, @NotNull final LoadCallback loadCallback) {

        final int currentPage = (int)loadParams.key;
        //Retrofit request
        ApiClient.getInstance().getInstance().getApiService().getAllMovie(criteria, BuildConfig.API_KEY,"en-IN",currentPage)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if(response.isSuccessful()){
                            int nextPage = currentPage+1;
                            loadCallback.onResult(response.body().getResults(),
                                    nextPage);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NotNull LoadParams loadParams, @NotNull LoadCallback loadCallback) {

    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams loadInitialParams, @NotNull final LoadInitialCallback loadInitialCallback) {
        //First Retrofit request
        ApiClient.getInstance().getInstance().getApiService().getAllMovie(criteria, BuildConfig.API_KEY,"en-IN",1)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if(response.isSuccessful()){
                            loadInitialCallback.onResult(response.body().getResults(),
                                    1,2);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }
}
