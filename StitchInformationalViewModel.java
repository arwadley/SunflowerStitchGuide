package com.arwapp.sittm.ui.StitchInformationalView;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arwapp.sittm.Stitch;
import com.arwapp.sittm.StitchRepository;

import java.util.List;

public class StitchInformationalViewModel extends AndroidViewModel {

    private StitchRepository mRepository;
    private LiveData<List<Stitch>> mAllStitches;
    private LiveData<Stitch> mStitch;

    public StitchInformationalViewModel(Application application) {
        super(application);
        mRepository = new StitchRepository(application);
        mAllStitches = mRepository.getAllStitchesAlphabetical();
    }

    public LiveData<List<Stitch>> getStitches() {
        return mAllStitches;
    }

    public LiveData<Stitch> findStitchWithName(String search){
        mStitch = mRepository.findStitchWithName(search);
        return mStitch;
    }

}