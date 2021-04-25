package com.example.practicum;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OfficeDetail extends AppCompatActivity {

    private TextView officeName, officeAddress, officePhone, officeType;
    private Spinner rating;
    private Button postReviewButton;
    private EditText review;
    private DatabaseHelper db;
    private ListView reviewsList;
    private ArrayList<Review> reviews;
    private ReviewAdapter reviewAdapter;


    void checkForEmpty() {
        if(review.getText().toString().equals("")) {
            postReviewButton.setEnabled(false);
        } else
            postReviewButton.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_detail);

        officeName = (TextView) findViewById(R.id.name_detail);
        officeAddress = (TextView) findViewById(R.id.address_detail);
        officePhone = (TextView) findViewById(R.id.phone_detail);
        officeType = (TextView) findViewById(R.id.type_detail);
        rating = (Spinner) findViewById(R.id.rating);
        postReviewButton = (Button) findViewById(R.id.post_button);
        review = (EditText) findViewById(R.id.review_input);
        reviewsList = (ListView) findViewById(R.id.reviews_lv);
        db = new DatabaseHelper(OfficeDetail.this);
        reviews = db.getReviews(getIntent().getStringExtra("office_name"));


        reviewAdapter = new ReviewAdapter(OfficeDetail.this,reviews);
        reviewsList.setAdapter(reviewAdapter);

        officeName.setText(getIntent().getStringExtra("office_name"));
        officeAddress.setText(getIntent().getStringExtra("office_address"));
        officePhone.setText(getIntent().getStringExtra("office_phone"));
        officeType.setText(getIntent().getStringExtra("office_type"));

        checkForEmpty();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ratings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(adapter);

        review.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                checkForEmpty();
            }
        });

        postReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String review_text = review.getText().toString();
                String[] rating_Text = rating.getSelectedItem().toString().split("\\s");
                int rating_num = Integer.parseInt(rating_Text[0]);

                db.insertReviewEntry(getIntent().getStringExtra("office_name"),review_text, rating_num);

                //prevent multiple reviews
                rating.setEnabled(false);
                review.setEnabled(false);
                review.setText("");
                postReviewButton.setEnabled(false);
                Toast.makeText(OfficeDetail.this, "Thanks for posting a review!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
