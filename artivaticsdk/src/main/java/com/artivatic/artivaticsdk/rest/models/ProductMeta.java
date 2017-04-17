package com.artivatic.artivaticsdk.rest.models;

/**
 * Created by root on 9/11/16.
 */

public class ProductMeta {
    String name = "";
    String datatype = "";
    Float weight = 0.0f;
    Boolean required = false;
    String bins = "";
    String min = "";
    String max = "";
    String cType = "";

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getBins() {
        return bins;
    }

    public void setBins(String bins) {
        this.bins = bins;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
