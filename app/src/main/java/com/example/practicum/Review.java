package com.example.practicum;

import java.io.Serializable;
import java.util.ArrayList;

public class Review implements Serializable {
    //CITE: code referenced from: https://demonuts.com/sqlite-multiple-tables/
    private String review_text;
    private int rating;
    private int id;

    public String getReview_text() {
        return review_text;
    }
    public void setReview_text(String text) { this.review_text =text; }
    public int getRating() {
        return rating;
    }
    public void setRating(int num) {
        this.rating = num;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
