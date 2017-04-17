package com.artivatic.artivaticsdk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 9/11/16.
 */

public class ProductMetaList {
    @SerializedName("product_meta")
    List<ProductMetaAttributes> userMetaList;

    public List<ProductMetaAttributes> getUserMetaList() {
        return userMetaList;
    }

    public void setUserMetaList(List<ProductMetaAttributes> userMetaList) {
        this.userMetaList = userMetaList;
    }

}
