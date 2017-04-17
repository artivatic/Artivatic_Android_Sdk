package com.artivatic.artivaticsdk.rest.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/1/17.
 */

public class GroupFilterData {
    String groupId = "";
    List<ProductIds> products = new ArrayList<>();
    List<CategoryFilters> filter = new ArrayList<>();
    List<Sorts> sort = new ArrayList<>();
    String search = "";

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<ProductIds> getProducts() {
        return products;
    }

    public void setProducts(List<ProductIds> products) {
        this.products = products;
    }

    public List<CategoryFilters> getFilter() {
        return filter;
    }

    public void setFilter(List<CategoryFilters> filter) {
        this.filter = filter;
    }

    public GroupFilterData(String groupId, List<ProductIds> client, List<CategoryFilters> filter, List<Sorts> sort,String search) {
        this.groupId = groupId;
        this.products = client;
        this.sort = sort;
        this.search=search;
        this.filter = filter;
    }
}
