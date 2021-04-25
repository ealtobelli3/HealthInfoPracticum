package com.example.practicum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddOfficeScreen extends AppCompatActivity {
    private EditText officeName;
    private EditText phoneNumber;
    private EditText address;
    private EditText type;
    private Button submitButton;
    private Button getOfficesButton;
    private DatabaseHelper db;

    void checkForEmpty() {
        if(officeName.getText().toString().equals("") || address.getText().toString().equals("") || phoneNumber.getText().toString().equals("") || type.getText().toString().equals("")) {
                    submitButton.setEnabled(false);
        } else
            submitButton.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_office_screen);

        officeName = (EditText) findViewById(R.id.officeName);
        phoneNumber = (EditText) findViewById(R.id.officePhoneNumber);
        address = (EditText) findViewById(R.id.officeAddress);
        type = (EditText) findViewById(R.id.officeType);
        submitButton = (Button) findViewById(R.id.submitOffice);
        getOfficesButton = (Button)findViewById(R.id.getAllOffices);
        db = new DatabaseHelper(AddOfficeScreen.this);

        checkForEmpty();

        officeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                checkForEmpty(); }
        });

        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                checkForEmpty(); }
        });

        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                checkForEmpty(); }
        });

        type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                checkForEmpty(); }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertOfficeEntry(officeName.getText().toString(), phoneNumber.getText().toString(), address.getText().toString(), type.getText().toString());
                Toast.makeText(AddOfficeScreen.this, "Office added successfully", Toast.LENGTH_SHORT).show();

            }
        });

        getOfficesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddOfficeScreen.this, GetAllOffices.class);
                startActivity(intent);
            }
        });
    }

}