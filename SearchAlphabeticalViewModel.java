package com.arwapp.sittm.ui.SearchAlphabetical;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.arwapp.sittm.Stitch;
import com.arwapp.sittm.StitchRepository;

import java.util.List;

public class SearchAlphabeticalViewModel extends AndroidViewModel{

    private StitchRepository mRepository;
    private LiveData<List<Stitch>> mAllStitches;

    public SearchAlphabeticalViewModel(Application application) {
        super(application);
        mRepository = new StitchRepository(application);
        mAllStitches = mRepository.getAllStitchesAlphabetical();

    }

    public LiveData<List<Stitch>> getStitches() {
        return mAllStitches;
    }

}