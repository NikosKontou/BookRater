package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ViewReviews extends AppCompatActivity {
    List<Review> reviewsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);
        TextView reviewList = findViewById(R.id.reviewList);
        ReviewDatabase db = ReviewDatabase.getInstance(this);
        ReviewDao reviewDao = db.reviewDao();
        reviewsData = reviewDao.getAllReviews();
        reviewList.setText(reviewsData.toString());

    }


}