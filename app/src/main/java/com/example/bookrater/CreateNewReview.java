package com.example.bookrater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class CreateNewReview extends AppCompatActivity {
    public String author;
    public String title;
    public String review;
    public float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //instantiate room db
        ReviewDatabase db = Room.databaseBuilder(getApplicationContext(), ReviewDatabase.class, "my-db-name").build();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_new_review);
        TextView authorTextView = findViewById(R.id.authorTextNewReview);
        TextView titleTextView = findViewById(R.id.titleTextNewReview);
        Intent intent = getIntent();
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

    public void createReview(View view){
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextInputEditText textInput = findViewById(R.id.reviewText);
        review = textInput.getText().toString();
        rating = ratingBar.getRating();

        saveData(title, author, rating, review);
    }

    private void saveData(String title, String author, float rating, String review){
        Review reviewObj = new Review();
        reviewObj.setTitle(title);
        reviewObj.setAuthor(author);
        reviewObj.setRating(rating);
        reviewObj.setReview(review);
        InsertReviewLoader loader = new InsertReviewLoader(this, reviewObj);
        loader.forceLoad();
//        db.reviewDao().insert(reviewObj);
    }
}