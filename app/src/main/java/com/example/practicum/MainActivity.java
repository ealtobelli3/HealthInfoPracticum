package com.example.practicum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    //Define UI elements
    private EditText searchBar;
    private Button addOffice;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = (EditText) findViewById(R.id.Search);
        addOffice = (Button) findViewById(R.id.addOffice);

        databaseHelper = new DatabaseHelper(this);

        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    searchBar.setHint("Search for a physician");
                else {
                    searchBar.setHint("");
                }
            }
        });
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchScreenIntent = new Intent (MainActivity.this, SearchScreen.class);
                startActivity(searchScreenIntent);

            }
        });
        addOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addOfficeIntent = new Intent (MainActivity.this, AddOfficeScreen.class);
                startActivity(addOfficeIntent);
            }
        });

    }

}