package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateNewReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_new_review);
        TextView authorTextView = findViewById(R.id.authorTextNewReview);
        TextView titleTextView = findViewById(R.id.titleTextNewReview);
        Intent intent = getIntent();
        String title ="";
        String author ="";
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                title = bundle.getString("title");
                author = bundle.getString("author");
            }
        }
        authorTextView.setText(author);
        titleTextView.setText(title);
    }
}