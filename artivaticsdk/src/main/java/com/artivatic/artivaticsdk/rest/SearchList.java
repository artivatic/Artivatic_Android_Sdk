package com.artivatic.artivaticsdk.rest;

import java.security.PrivateKey;

/**
 * Created by liveongo on 10/1/17.
 */

public class SearchList {
    private String id;
    private String displayName;
    private String meta;

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

