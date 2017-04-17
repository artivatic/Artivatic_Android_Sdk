package com.artivatic.artivaticsdk.rest.models;

/**
 * Created by root on 14/11/16.
 */

public class Filters {
    String type_value = "";
    String collumn_name = "";
    String value = "";


    public String getType_value() {
        return type_value;
    }

    public void setType_value(String type_value) {
        this.type_value = type_value;
    }

    public String getCollumn_name() {
        return collumn_name;
    }

    public void setCollumn_name(String collumn_name) {
        this.collumn_name = collumn_name;
    }

    public String getMin() {
        return value;
    }

    public void setMin(String min) {
        this.value = min;
    }

    public Filters(String type_value, String collumn_name, String value) {
        this.type_value = type_value;
        this.collumn_name = collumn_name;
        this.value = value;

    }
}
