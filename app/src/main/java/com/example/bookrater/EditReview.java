package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class EditReview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String title = null;
        String author = null;
        String review = null;
        float rating = 0;
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

                authorEdit.setText(author);
                titleEdit.setText(title);
                ratingEdit.setRating(rating);
                reviewEdit.setText(review);

            }
        }


    }
}