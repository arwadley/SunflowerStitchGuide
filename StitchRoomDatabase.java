package com.arwapp.sittm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Stitch.class}, version = 3, exportSchema = false)
public abstract class StitchRoomDatabase extends RoomDatabase {
    public abstract StitchDao stitchDao();

    private static volatile StitchRoomDatabase INSTANCE;

    static StitchRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (StitchRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StitchRoomDatabase.class, "stitch_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final StitchDao mDao;

        PopulateDbAsync(StitchRoomDatabase db) {
            mDao = db.stitchDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
           
    }
}

