package com.artivatic.artivaticsdk.rest.models;

/**
 * Created by root on 14/11/16.
 */

public class UserIds {
    String UserID="";
    String weight="";

    public UserIds(String userID, String weight) {
        UserID = userID;
        this.weight = weight;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
