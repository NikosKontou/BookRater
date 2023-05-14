package com.example.bookrater;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class DeleteReviewLoader extends AsyncTaskLoader<Void> {
    private ReviewDatabase db;
    private int reviewId;


    public DeleteReviewLoader(@NonNull Context context, int id) {
        super(context);
        db = ReviewDatabase.getInstance(context);
        this.reviewId = id;
    }

    @Nullable
    @Override
    public Void loadInBackground() {
        db.reviewDao().deleteReviewById(reviewId);
        return null;
    }


}
