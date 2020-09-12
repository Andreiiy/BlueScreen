package com.example.bluescreen.ui.popular;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.bluescreen.data.MovieDataSourceFactory;
import com.example.bluescreen.models.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PopularViewModel extends ViewModel {

    private String criteria;
    private LiveData<PagedList<Result>> listLiveData;

    public PopularViewModel(String criteria) {
        this.criteria = criteria;

        Executor executor = Executors.newFixedThreadPool(5);
        MovieDataSourceFactory sourceFactory = new MovieDataSourceFactory(criteria);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(5)
                .setPageSize(10)
                .setPrefetchDistance(50)
                .build();


        listLiveData = new LivePagedListBuilder<>(sourceFactory,config)
                .setFetchExecutor(executor)
                .build();

    }


    public LiveData<PagedList<Result>> getListLiveData(){
        return listLiveData;
    }
}