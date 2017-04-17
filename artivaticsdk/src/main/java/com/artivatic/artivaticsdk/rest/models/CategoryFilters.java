package com.artivatic.artivaticsdk.rest.models;

import java.util.ArrayList;

/**
 * Created by root on 5/12/16.
 */

public class CategoryFilters {
    String category = "";
    ArrayList<Filters> Filter = new ArrayList<>();

    public CategoryFilters(String category) {
        this.category = category;
    }


    /**
     * @param attributeName name of product attribute for filtering
     * @param value value field should match
     * */
    public void addFilterColumnEquals(String attributeName, String value){
        Filter.add(new Filters("EQUALS",attributeName,value));
    }
    /**
     * @param attributeName name of product attribute for filtering
     * @param latitude value field should match
     * @param longitude value field should match
     * @param radius value field should match
     * */
    public void addGeoFilterColumnRange(String attributeName, String latitude,String longitude, String radius){
        Filter.add(new Filters("DIST",attributeName,latitude+","+longitude+","+radius));
    }

    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be less than
     * */
    public void addFilterColumnLessThan(String attributeName,String value){
        Filter.add(new Filters("LESS_THAN",attributeName,value));
    }
    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be greater than
     * */
    public void addFilterColumnGreaterThan(String attributeName,String value){
        Filter.add(new Filters("GREATER_THAN",attributeName,value));
    }
    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be less than
     * */
    public void addFilterColumnLessThanEquals(String attributeName,String value){
        Filter.add(new Filters("LESS_THAN_EQUALS",attributeName,value));
    }
    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be greater than
     * */
    public void addFilterColumnGreaterThanEquals(String attributeName,String value){
        Filter.add(new Filters("GREATER_THAN_EQUALS",attributeName,value));
    }

    /**
     * @param attributeName name of product attribute for filtering
     * @param minValue value attribute should be greater than
     * @param maxValue value attribute should be less than
     **/
    public void addFilterColumnInRange(String attributeName,String minValue,String maxValue){
        addFilterColumnLessThan(attributeName,maxValue);
        addFilterColumnGreaterThan(attributeName,minValue);
    }

}
