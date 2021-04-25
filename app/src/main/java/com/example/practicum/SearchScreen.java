package com.example.practicum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchScreen extends AppCompatActivity {
    //Define UI elements
    private AutoCompleteTextView searchBar;
    private ImageButton searchButton;
    private ListView listView;
    private ArrayList<Office> offices;
    private CustomAdapter customAdapter;
    private DatabaseHelper db;

    private static final String[] suggestions = new String[] {
            "Dentist", "Dentist near me", "Dentist for kids",
            "Doctor", "Doctor near me", "Doctor for kids",
            "Medicare", "Medicaid", "Medicare doctors", "Medicaid doctors",
            "Chiropractors", "Chiropractors near me",
            "Eye doctor near me"};

    void checkForEmpty() {
//        if(searchBar.getText().toString().equals("")) {
//            searchButton.setEnabled(false);
//        } else
//            searchBar.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        searchBar = (AutoCompleteTextView) findViewById(R.id.Search1);
        searchButton = (ImageButton) findViewById(R.id.searchButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestions);
        searchBar.setAdapter(adapter);


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkForEmpty(); }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                checkForEmpty(); }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView = (ListView) findViewById(R.id.lv1);
                db = new DatabaseHelper(SearchScreen.this);
                String[] input = searchBar.getText().toString().split("\\s");
                offices = db.getSpecificOffices(input);
//                offices = db.getOffices();
                customAdapter = new CustomAdapter(SearchScreen.this,offices);
                listView.setAdapter(customAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //CITE: code referenced from https://demonuts.com/sqlite-multiple-tables/
                        Intent intent = new Intent(SearchScreen.this, OfficeDetail.class);
                        intent.putExtra("office_name", offices.get(position).getName());
                        intent.putExtra("office_address", offices.get(position).getAddress());
                        intent.putExtra("office_phone", offices.get(position).getPhone());
                        intent.putExtra("office_type", offices.get(position).getType());
                        startActivity(intent);
                    }
                });
            }
        });
    }
}