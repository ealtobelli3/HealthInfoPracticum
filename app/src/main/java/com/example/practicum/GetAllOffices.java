package com.example.practicum;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class GetAllOffices extends AppCompatActivity {
    //CITE: code referenced from https://demonuts.com/sqlite-multiple-tables/
    private ListView listView;
    private ArrayList<Office> offices;
    private CustomAdapter customAdapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_offices);

        listView = (ListView) findViewById(R.id.lv);
//        db = new DatabaseHelper(this);
        db = new DatabaseHelper(GetAllOffices.this);
        offices = db.getOffices();
        customAdapter = new CustomAdapter(this,offices);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GetAllOffices.this, UpdateDeleteActivity.class);
                intent.putExtra("user", offices.get(position));
                startActivity(intent);
            }
        });

    }
}
