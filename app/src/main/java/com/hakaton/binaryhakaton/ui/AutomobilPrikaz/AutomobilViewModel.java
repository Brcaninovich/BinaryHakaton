package com.hakaton.binaryhakaton.ui.AutomobilPrikaz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AutomobilViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AutomobilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}