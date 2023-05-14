package com.example.bookrater;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class UpdateReviewLoader extends AsyncTaskLoader<Void> {
    private ReviewDatabase db;
    private Review review;


    public UpdateReviewLoader(@NonNull Context context, Review review) {
        super(context);
        db = ReviewDatabase.getInstance(context);
        this.review = review;
    }

    @Nullable
    @Override
    public Void loadInBackground() {
        db.reviewDao().Update(review);
        return null;
    }


}
