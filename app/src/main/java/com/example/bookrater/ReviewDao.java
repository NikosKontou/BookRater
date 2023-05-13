package com.example.bookrater;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReviewDao {
    @Insert
    void insert(Review review);

    @Update
    void Update(Review review);

    @Delete
    void Delete(Review review);

    @Query("SELECT * FROM review")
    List<Review> getAllReviews();
}
