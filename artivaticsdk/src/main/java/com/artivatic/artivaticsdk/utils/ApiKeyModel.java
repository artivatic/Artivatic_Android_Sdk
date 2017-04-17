package com.artivatic.artivaticsdk.utils;

import java.util.HashMap;
import java.util.Map;

public class ApiKeyModel {

    private String apikey;
    private Integer expiry;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The apikey
     */
    public String getApikey() {
        return apikey;
    }

    /**
     *
     * @param apikey
     * The apikey
     */
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    /**
     *
     * @return
     * The expiry
     */
    public Integer getExpiry() {
        return expiry;
    }

    /**
     *
     * @param expiry
     * The expiry
     */
    public void setExpiry(Integer expiry) {
        this.expiry = expiry;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
