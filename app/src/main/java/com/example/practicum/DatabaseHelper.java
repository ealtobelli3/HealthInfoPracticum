package com.example.practicum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database fields

    //Table 1 for office data
    public static final String ID1 = "id1";
    public static final String OFFICE_NAME = "name";
    public static final String PHONE_NUMBER ="number";
    public static final String ADDRESS = "address";
    public static final String TYPE = "type";
    private static final String OFFICE_DATA = "office_data";

    //Table 2 for review data
    public static final String ID2 = "id2";
    public static final String REF_NAME = "ref_name";
    public static final String RATING = "rating";
    public static final String REVIEW = "review";
    public static final String REVIEW_DATA = "review_data";


    SQLiteDatabase database;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "offices.db", null, 2);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //initiate database
        db.execSQL("CREATE TABLE " +OFFICE_DATA+" ( "+ID1+" INTEGER PRIMARY KEY, "+OFFICE_NAME+" TEXT, "+PHONE_NUMBER+" TEXT, "+ADDRESS+" TEXT, " +TYPE+" TEXT)");
        db.execSQL("CREATE TABLE " +REVIEW_DATA+" ( "+ID2+" INTEGER PRIMARY KEY, "+REF_NAME+" TEXT, "+RATING+" TEXT, "+REVIEW+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+OFFICE_DATA);
        db.execSQL("DROP TABLE IF EXISTS "+REVIEW_DATA);
        onCreate(db);
    }

    public void insertOfficeEntry (String office, String phoneNumber, String address, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(OFFICE_NAME, office);
        values.put(PHONE_NUMBER, phoneNumber);
        values.put(ADDRESS, address);
        values.put(TYPE, type);

        db.insert(OFFICE_DATA,null, values);
    }

    public void insertReviewEntry (String office_name, String review, int rating) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();

        values1.put(REF_NAME, office_name);
        values1.put(REVIEW, review);
        values1.put(RATING, rating);

        db1.insert(REVIEW_DATA,null,values1);

    }

    public ArrayList<Office> getOffices() {
        //CITE: some code referenced from https://demonuts.com/sqlite-multiple-tables/
        ArrayList<Office> offices = new ArrayList<Office>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + OFFICE_DATA;

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                Office office = new Office();
                office.setId(cursor.getInt(cursor.getColumnIndex(ID1)));
                office.setName(cursor.getString(cursor.getColumnIndex(OFFICE_NAME)));
                office.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                office.setPhone(cursor.getString(cursor.getColumnIndex(PHONE_NUMBER)));
                office.setType(cursor.getString(cursor.getColumnIndex(TYPE)));

                offices.add(office);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return offices;
    }

    public ArrayList<Office> getSpecificOffices(String[] input) {
        ArrayList<Office> offices = new ArrayList<>();
        String[] newInput = new String[input.length];
        int i = 0;
        for (String word:input) {

            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + OFFICE_DATA + " WHERE " + OFFICE_NAME + " LIKE '"+word+ "%' OR " + TYPE + " LIKE '"+ word +"%' ";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Office office = new Office();
                    office.setId(cursor.getInt(cursor.getColumnIndex(ID1)));
                    office.setName(cursor.getString(cursor.getColumnIndex(OFFICE_NAME)));
                    office.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                    office.setPhone(cursor.getString(cursor.getColumnIndex(PHONE_NUMBER)));
                    office.setType(cursor.getString(cursor.getColumnIndex(TYPE)));

                    offices.add(office);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return offices;
    }

    public ArrayList<Review> getReviews (String office_name) {
        ArrayList<Review> reviews = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+REVIEW_DATA+ " WHERE "+REF_NAME+ " = '"+office_name+"'";
        Cursor cursor =db.rawQuery(query, null);

        if(cursor.moveToNext()) {
            do {
                Review review = new Review();
                review.setId(cursor.getInt(cursor.getColumnIndex(ID2)));
                review.setRating(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RATING))));
                review.setReview_text(cursor.getString(cursor.getColumnIndex(REVIEW)));

                reviews.add(review);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reviews;
    }
}
