package com.example.artivatic.artivaticsdk;

import java.util.ArrayList;

/**
 * Created by artivatic on 14/4/17.
 */

class Suggestion {
    private String category="baby-gear";
    private String id;
    private String year;
    private String price;
    private String name;
    private ArrayList<String> gender=new ArrayList<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


//
//"category":"baby-gear",
//        "id":"14",
//        "year":"3",
//        "price":"1408",
//        "name":"Cosmos Baby World Tricycle - Red and Yellow",
//        "gender":["M","F"]
//        }