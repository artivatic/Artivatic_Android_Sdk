package com.artivatic.artivaticsdk.rest.models;

import java.util.ArrayList;

/**
 * Created by root on 30/12/16.
 */

public class GroupDetails {
    String client_user_id="",av_group_id="",client_group_id="",group_name="";
    Long created_on;
    ArrayList<String> client_member_id = new ArrayList<>();

    public String getClientUserId() {
        return client_user_id;
    }

    public String getAvGroupId() {
        return av_group_id;
    }

    public String getClientGroupId() {
        return client_group_id;
    }

    public String getGroupName() {
        return group_name;
    }

    public Long getCreatedOn() {
        return created_on;
    }

    public ArrayList<String> getClientMemberId() {
        return client_member_id;
    }
}
