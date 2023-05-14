package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ViewReviews extends AppCompatActivity implements ViewReviewLoaderCallback{
    List<Review> reviewsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);
//        TextView reviewList = findViewById(R.id.reviewList);
        ViewReviewLoader loader = new ViewReviewLoader(this, this);
        loader.forceLoad();

//        ReviewDatabase db = ReviewDatabase.getInstance(this);
//        ReviewDao reviewDao = db.reviewDao();
//        reviewsData = reviewDao.getAllReviews();

    }


    @Override
    public void onLoadFinished(List<Review> reviews) {



        RecyclerView reviewsRecyclerView = findViewById(R.id.reviewsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reviewsRecyclerView.setLayoutManager(layoutManager);
        ReviewsAdapter adapter = new ReviewsAdapter(reviews);
        reviewsRecyclerView.setAdapter(adapter);

    }
}