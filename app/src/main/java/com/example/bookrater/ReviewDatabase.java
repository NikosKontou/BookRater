package com.example.bookrater;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Review.class}, version = 2)
public abstract class ReviewDatabase  extends RoomDatabase{
    public abstract ReviewDao reviewDao();

}
