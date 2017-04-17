package com.artivatic.artivaticsdk.rest.models;

/**
 * Created by root on 13/11/16.
 */

public class ProductFilterableAttributes {
    String attributeName = "";
    String attributeType = "";
    String category = "";

    public ProductFilterableAttributes(String attributeName, String attributeType,String category) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }
}
