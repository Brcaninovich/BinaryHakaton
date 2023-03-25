package com.hakaton.binaryhakaton.ui.OdjecaPrikaz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OdjecaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OdjecaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}