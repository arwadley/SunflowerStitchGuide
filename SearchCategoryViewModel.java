package com.arwapp.sittm.ui.SearchCategory;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.arwapp.sittm.Stitch;
import com.arwapp.sittm.StitchRepository;

import java.util.List;

public class SearchCategoryViewModel extends AndroidViewModel {
    private StitchRepository mRepository;
    private LiveData<List<Stitch>> mStitchesByCategory;
    private LiveData<List<String>> mAllCategories;

    public SearchCategoryViewModel(Application application) {
        super(application);
        mRepository = new StitchRepository(application);

    }

    public LiveData<List<Stitch>> getStitchesByCategory(String category) {
        mStitchesByCategory = mRepository.getmStitchesByCategory(category);
        return mStitchesByCategory;
    }

    public LiveData<List<String>> findAllCategories(){
        mAllCategories = mRepository.findAllCategories();
        return mAllCategories;
    }
}