package com.artivatic.artivaticsdk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 9/11/16.
 */

public class ProductMetaAttributes {
    String category = "";
    @SerializedName("attribute")
    List<ProductMeta> userMetaList;

    public List<ProductMeta> getUserMetaList() {
        return userMetaList;
    }

    public void setUserMetaList(List<ProductMeta> userMetaList) {
        this.userMetaList = userMetaList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
