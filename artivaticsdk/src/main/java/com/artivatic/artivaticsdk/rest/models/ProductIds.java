package com.artivatic.artivaticsdk.rest.models;

/**
 * Created by root on 14/11/16.
 */

public class ProductIds {
    String client_product_id = "";

    public ProductIds(String client_product_id) {
        this.client_product_id = client_product_id;
    }

    public String getClient_product_id() {
        return client_product_id;
    }

    public void setClient_product_id(String client_product_id) {
        this.client_product_id = client_product_id;
    }
}
