package com.example.bluescreen.ui.upcoming;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpComingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UpComingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is upcoming fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}