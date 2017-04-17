package com.artivatic.artivaticsdk.rest.models;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by root on 29/12/16.
 */

public class GroupData {
    private String client_user_id="";
    private ArrayList<String> client_member_id = new ArrayList<>();
    private String client_group_id = "";
    private String group_name = "";
    private String av_group_id = "";

    public GroupData(String client_user_id, ArrayList<String> client_member_id, String client_group_id, String group_name, String av_group_id) {
        this.client_user_id = client_user_id;
        this.client_member_id = client_member_id;
        this.client_group_id = client_group_id;
        this.group_name = group_name;
        this.av_group_id = av_group_id;
    }

    public GroupData() {
    }

    public boolean checkIfDataIsSet(){
        Log.e("LOGGER","1"+client_group_id);
        Log.e("LOGGER","2"+group_name);
        Log.e("LOGGER","3"+client_user_id);
        Log.e("LOGGER","4"+client_member_id.size());
        return TextUtils.isEmpty(client_group_id)&&TextUtils.isEmpty(group_name)&&TextUtils.isEmpty(client_user_id)&&client_member_id.size()==0;
    }

}
