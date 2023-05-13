package com.example.bookrater;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class InsertReviewLoader extends AsyncTaskLoader<Void> {
    private ReviewDatabase db;
    private Review review;


    public InsertReviewLoader(@NonNull Context context, Review review) {
        super(context);
        db = ReviewDatabase.getInstance(context);
        this.review = review;
    }

    @Nullable
    @Override
    public Void loadInBackground() {
        db.reviewDao().insert(review);
        return null;
    }


}
