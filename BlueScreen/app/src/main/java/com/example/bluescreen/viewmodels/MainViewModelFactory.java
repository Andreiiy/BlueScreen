package com.example.bluescreen.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bluescreen.viewmodels.MainViewModel;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private String criteria;

    public MainViewModelFactory(String criteria) {
        this.criteria = criteria;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(criteria);
    }
}
