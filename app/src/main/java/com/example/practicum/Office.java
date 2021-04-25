package com.example.practicum;

import java.io.Serializable;
import java.util.ArrayList;

public class Office implements Serializable {
    //CITE: code referenced from: https://demonuts.com/sqlite-multiple-tables/
    private String office_name, office_address, phone_number, type;
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String> languages = new ArrayList<>();
    private int id;

    public String getName() {
        return office_name;
    }
    public void setName(String name) {
        this.office_name = name;
    }
    public String getAddress() {
        return office_address;
    }
    public void setAddress(String address) {
        this.office_address = address;
    }
    public String getPhone() {
        return phone_number;
    }
    public void setPhone(String phone) {
        this.phone_number = phone;
    }
    public String getType() {return type;}
    public void setType(String type) {this.type = type; }
    public ArrayList<String> getTags() {return tags;}
    public void setTags(ArrayList<String> input) {
        for (String word:input) {
            tags.add(tags.size()-1,word);
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
