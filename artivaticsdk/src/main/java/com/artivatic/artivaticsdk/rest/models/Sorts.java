package com.artivatic.artivaticsdk.rest.models;

/**
 * Created by root on 15/11/16.
 */

public class Sorts {
    String collumn_name = "";
    String type = "";

    public String getCollumn_name() {
        return collumn_name;
    }

    public void setCollumn_name(String collumn_name) {
        this.collumn_name = collumn_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sorts(String collumn_name, String type) {
        this.collumn_name = collumn_name;
        this.type = type;
    }
}
