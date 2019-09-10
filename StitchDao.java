package com.arwapp.sittm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StitchDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStitch(Stitch... stitches);

    @Update
    void updateStitches(Stitch... stitches);

    @Delete
    void deleteStitches(Stitch... stitches);

    @Query("SELECT * from stitch_table ORDER BY stitch_name ASC")
    LiveData<List<Stitch>> getAllStitches();

    @Query("SELECT * from stitch_table WHERE stitch_name LIKE :search")
    LiveData<Stitch> findStitchWithName(String search);

    @Query("SELECT * from stitch_table WHERE stitch_category LIKE :search")
    LiveData<List<Stitch>> findStitchByCategory(String search);

    @Query("SELECT stitch_category FROM stitch_table ORDER BY stitch_category ASC")
    LiveData<List<String>> findAllCategories();

}
