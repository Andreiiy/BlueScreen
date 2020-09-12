package com.example.bluescreen.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.bluescreen.models.Result;

import org.jetbrains.annotations.NotNull;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Result> {

    private String criteria;
    private MutableLiveData<MovieDataSource> liveData;
    private MovieDataSource dataSource;

    public MovieDataSourceFactory(String criteria) {
        this.criteria = criteria;
        liveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource<Integer, Result> create() {
        dataSource = new MovieDataSource(criteria);
        liveData = new MutableLiveData<>();
        liveData.postValue(dataSource);
        return dataSource;
    }
}
