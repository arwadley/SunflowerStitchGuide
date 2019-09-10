package com.arwapp.sittm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StitchRepository {
    private StitchDao mStitchDao;
    private LiveData<List<Stitch>> mAllStitchesAlphabetical;
    private LiveData<List<Stitch>> mStitchesByCategory;
    private LiveData<List<String>> mAllCategrories;
    private LiveData<Stitch>mStitchByName;

    public StitchRepository(Application application){
        StitchRoomDatabase database = StitchRoomDatabase.getDatabase(application);
        mStitchDao = database.stitchDao();
        mAllStitchesAlphabetical = mStitchDao.getAllStitches();
    }

    public LiveData<List<Stitch>> getAllStitchesAlphabetical(){
        return mAllStitchesAlphabetical;
    }

    public LiveData<List<Stitch>> getmStitchesByCategory(String category){
        mStitchesByCategory = mStitchDao.findStitchByCategory(category);
        return mStitchesByCategory;
    }

    public LiveData<List<String>> findAllCategories(){
        mAllCategrories = mStitchDao.findAllCategories();
        return mAllCategrories;
    }

    public LiveData<Stitch> findStitchWithName(String search){
        mStitchByName = mStitchDao.findStitchWithName(search);
        return mStitchByName;
    }

    public void insert (Stitch stitch) {
        new insertAsyncTask(mStitchDao).execute(stitch);
    }

    private static class insertAsyncTask extends AsyncTask<Stitch, Void, Void> {

    private StitchDao mAsyncTaskDao;

    insertAsyncTask(StitchDao dao) {
        mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Stitch... params) {
        mAsyncTaskDao.insertStitch(params[0]);
        return null;
    }
    }
}

