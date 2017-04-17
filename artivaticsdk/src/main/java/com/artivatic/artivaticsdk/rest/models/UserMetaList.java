package com.artivatic.artivaticsdk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 9/11/16.
 */

public class UserMetaList {
    @SerializedName("user_meta")
    List<UserMeta> userMetaList;

    public List<UserMeta> getUserMetaList() {
        return userMetaList;
    }

    public void setUserMetaList(List<UserMeta> userMetaList) {
        this.userMetaList = userMetaList;
    }
}
