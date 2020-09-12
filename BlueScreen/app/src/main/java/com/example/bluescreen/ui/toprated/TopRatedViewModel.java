package com.example.bluescreen.ui.toprated;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TopRatedViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TopRatedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is toprated fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}