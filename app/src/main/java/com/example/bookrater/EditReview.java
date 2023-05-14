package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class EditReview extends AppCompatActivity {
    String title = null;

    int reviewId = 0;
    String author = null;
    String review = null;
    float rating = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);
        TextView authorEdit = findViewById(R.id.authorEdit);
        TextView titleEdit = findViewById(R.id.titleEdit);
        RatingBar ratingEdit = findViewById(R.id.editRating);
        TextInputEditText reviewEdit = findViewById(R.id.editReview);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                title = bundle.getString("title");
                author = bundle.getString("author");
                review = bundle.getString("review");
                rating = bundle.getFloat("rating");
                reviewId = bundle.getInt("id");

                authorEdit.setText(author);
                titleEdit.setText(title);
                ratingEdit.setRating(rating);
                reviewEdit.setText(review);

            }
        }


    }

    public void saveChanges(View view) {
    }

    public void deleteReview(View view) {
        DeleteReviewLoader loader = new DeleteReviewLoader(this, reviewId);
        loader.forceLoad();
        Intent intent = new Intent(EditReview.this, ViewReviews.class);
        startActivity(intent);
    }

    public void qrCodeShare(View view) {
    }

    public void smsShare(View view) {
    }

    private String createReviewText(){
        return "Hi! i share with you my review for the book "+title+" of the author "+author+" \n"+review+"\nMy rating is "+String.valueOf(rating)+"/5";
    }
}