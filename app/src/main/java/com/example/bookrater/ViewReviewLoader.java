package com.example.bookrater;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class ViewReviewLoader extends AsyncTaskLoader<List<Review>> {
    private ReviewDatabase db;
    private Review review;
    private ViewReviewLoaderCallback mCallback;

    public ViewReviewLoader(@NonNull Context context, ViewReviewLoaderCallback  callback) {
        super(context);
        db = ReviewDatabase.getInstance(context);
        mCallback = callback;
    }

    @Override
    public List<Review> loadInBackground() {
        return db.reviewDao().getAllReviewsSync();
    }

    @Override
    public void deliverResult(List<Review> reviews) {
//        if (isReset()) {
//            // An async query came in while the loader is stopped
//            return;
//        }

        if (isStarted()) {
            // If the loader is currently started, we can immediately deliver its results.
            super.deliverResult(reviews);
        }

        if (mCallback != null) {
            mCallback.onLoadFinished(reviews);
        }
    }


}
