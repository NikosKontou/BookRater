package com.example.bookrater;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Review.class}, version = 2)
public abstract class ReviewDatabase  extends RoomDatabase{

    private static volatile ReviewDatabase instance;
    public abstract ReviewDao reviewDao();

    public static synchronized ReviewDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ReviewDatabase.class, "review_database").build();
        }
        return instance;
    }


}
