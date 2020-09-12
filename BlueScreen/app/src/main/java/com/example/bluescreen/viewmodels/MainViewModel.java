package com.example.bluescreen.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.bluescreen.data.MovieDataSource;
import com.example.bluescreen.data.MovieDataSourceFactory;
import com.example.bluescreen.models.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private String location;
    private LiveData<PagedList<Result>> listLiveDataPopularMovies;
    private LiveData<PagedList<Result>> listLiveDataUpcomingMovies;
    private LiveData<PagedList<Result>> listLiveDataTopratedMovies;
    private Executor executor;
    private PagedList.Config config;

    public MainViewModel(String location) {
        this.location = location;

        initData();

    }

    public LiveData<PagedList<Result>> getListLiveDataPopularMovies() {
        return listLiveDataPopularMovies;
    }

    public LiveData<PagedList<Result>> getListLiveDataUpcomingMovies() {
        return listLiveDataUpcomingMovies;
    }

    public LiveData<PagedList<Result>> getListLiveDataTopratedMovies() {
        return listLiveDataTopratedMovies;
    }

    private void initData(){
        executor = Executors.newFixedThreadPool(5);

        config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(5)
                .setPageSize(10)
                .setPrefetchDistance(50)
                .build();
        MovieDataSourceFactory sourceFactoryPopular,sourceFactoryToprated,sourceFactoryUpcoming;
        switch (location){
            case "home":
                sourceFactoryPopular = new MovieDataSourceFactory("popular");
                listLiveDataPopularMovies = new LivePagedListBuilder<>(sourceFactoryPopular,config)
                        .setFetchExecutor(executor)
                        .build();
                sourceFactoryToprated = new MovieDataSourceFactory("top_rated");
                listLiveDataTopratedMovies = new LivePagedListBuilder<>(sourceFactoryToprated,config)
                        .setFetchExecutor(executor)
                        .build();
                sourceFactoryUpcoming = new MovieDataSourceFactory("upcoming");
                listLiveDataUpcomingMovies = new LivePagedListBuilder<>(sourceFactoryUpcoming,config)
                        .setFetchExecutor(executor)
                        .build();
                break;
            case "popular":
                sourceFactoryPopular = new MovieDataSourceFactory("popular");
                listLiveDataPopularMovies = new LivePagedListBuilder<>(sourceFactoryPopular,config)
                        .setFetchExecutor(executor)
                        .build();
                break;
            case "toprated":
                sourceFactoryToprated = new MovieDataSourceFactory("top_rated");
                listLiveDataTopratedMovies = new LivePagedListBuilder<>(sourceFactoryToprated,config)
                        .setFetchExecutor(executor)
                        .build();
                break;
            case "upcoming":
                sourceFactoryUpcoming = new MovieDataSourceFactory("upcoming");
                listLiveDataUpcomingMovies = new LivePagedListBuilder<>(sourceFactoryUpcoming,config)
                        .setFetchExecutor(executor)
                        .build();
                break;
        }
    }
}