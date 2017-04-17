package com.artivatic.artivaticsdk.rest.models;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by root on 30/12/16.
 */

public class GroupList {
    ArrayList<Group> groups = new ArrayList<>();


    public ArrayList<Group> getGroups() {
        return groups;
    }



    public class Group {
        String client_group_id = "";
        String group_name = "";
        private ArrayList<String> id=new ArrayList<>();

        public String getClientGroupId() {
            return client_group_id;
        }

        public String getGroupName() {
            return group_name;
        }
        public ArrayList<String> getId() {
            return id;
        }

        public void setId(ArrayList<String> id) {
            this.id = id;
        }
    }
}
