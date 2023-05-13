package com.example.bookrater;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "review")
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;
    private String review;
    private float rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
