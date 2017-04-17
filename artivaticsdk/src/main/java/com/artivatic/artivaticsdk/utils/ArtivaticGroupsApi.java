package com.artivatic.artivaticsdk.utils;

import android.text.TextUtils;
import android.util.Log;

import com.artivatic.artivaticsdk.rest.RestClient;
import com.artivatic.artivaticsdk.rest.models.ArtivaticResponse;
import com.artivatic.artivaticsdk.rest.models.GroupData;
import com.artivatic.artivaticsdk.rest.models.GroupDetails;
import com.artivatic.artivaticsdk.rest.models.GroupList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 29/12/16.
 */

public class ArtivaticGroupsApi {
    private static ArtivaticGroupsApi ourInstance;
    GroupData groupData = new GroupData();
    private ArtivaticApiCallback apiCallback;
    private ArtivaticGroupListApiCallback groupListApiCallback;
    private ArtivaticGroupDetailsCallback groupDetailsApiCallback;
    private String userId="";
    /**
     * @param userId send UserId for the current user
     * @return ArtivaticGroupsApi instance
     */
    public static ArtivaticGroupsApi build(String userId) {
        if (ourInstance == null) {
            ourInstance = new ArtivaticGroupsApi(userId);
        }
//        userId = userId;

        return ourInstance;
    }

    public ArtivaticGroupsApi(String userId) {
        Log.e("getuserid",""+userId);
        this.userId = userId;
        groupData = new GroupData();
    }

    public void setGroupData(ArrayList<String> client_member_id,
                             String client_group_id,
                             String group_name) {
        this.groupData = new GroupData(userId,client_member_id,client_group_id,group_name,"");
    }

    /**
     * Final predict call to get product list in response
     * @param apiCallback instance of ArtivaticApiCallback
     * @return void
     */
    public void callCreateGroup(ArtivaticApiCallback apiCallback) {
        this.apiCallback = apiCallback;
        if(!groupData.checkIfDataIsSet()){
            final Call<Map<String,Object>> call = new RestClient().getApiService().callGroupCreateApi(groupData);
            call.enqueue(new Callback<Map<String,Object>>() {
                @Override
                public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {
                    Log.e("LOGGER", "RESPONSE SUCCESS " + response.code());
                    if (response.code() == 200) {
                        try {


                            ArtivaticGroupsApi.this.apiCallback.onSuccess(response.body());


                        } catch (Exception e) {
                            e.printStackTrace();
                            ArtivaticGroupsApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                        }
                    } else {
                        ArtivaticGroupsApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                    }
                }

                @Override
                public void onFailure(Call<Map<String,Object>> call, Throwable t) {
                    t.printStackTrace();
                    ArtivaticGroupsApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }
            });
        }else{
            ArtivaticGroupsApi.this.apiCallback.onError(ErrorStrings.GROUP_DATA_NOT_SET);
        }



    }

    /**
     * Final predict call to get product list in response
     * @param apiCallback instance of ArtivaticGroupListApiCallback
     * @return void
     */
    public void callGetGroupList(ArtivaticGroupListApiCallback apiCallback) {
        this.groupListApiCallback = apiCallback;
        if(!TextUtils.isEmpty(userId)){
            Map<String,String> userIdList = new HashMap<>();
            userIdList.put("client_user_id",userId);
            final Call<ArtivaticResponse<GroupList>> call = new RestClient().getApiService().callGetGroupList(userIdList);
            call.enqueue(new Callback<ArtivaticResponse<GroupList>>() {
                @Override
                public void onResponse(Call<ArtivaticResponse<GroupList>> call, Response<ArtivaticResponse<GroupList>> response) {
                    Log.e("LOGGER", "RESPONSE SUCCESS " + response.code());
                    if (response.code() == 200) {
                        try {


                            ArtivaticGroupsApi.this.groupListApiCallback.onSuccess(response.body().getResponse());


                        } catch (Exception e) {
                            e.printStackTrace();
                            ArtivaticGroupsApi.this.groupListApiCallback.onError(ErrorStrings.API_CALL_FAILED);
                        }
                    } else {
                        ArtivaticGroupsApi.this.groupListApiCallback.onError(ErrorStrings.API_CALL_FAILED);
                    }
                }

                @Override
                public void onFailure(Call<ArtivaticResponse<GroupList>> call, Throwable t) {
                    t.printStackTrace();
                    ArtivaticGroupsApi.this.groupListApiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }
            });
        }else{
            ArtivaticGroupsApi.this.groupListApiCallback.onError(ErrorStrings.USER_ID_NOT_SET);
        }



    }

    /**
     * Final predict call to get product list in response
     * @param apiCallback instance of ArtivaticGroupDetailsCallback
     * @return void
     */
    public void callGetGroupDetailsApi(String groupId,ArtivaticGroupDetailsCallback apiCallback) {
        this.groupDetailsApiCallback = apiCallback;
        if(!TextUtils.isEmpty(groupId)){
            Map<String,String> userIdList = new HashMap<>();
            userIdList.put("client_group_id",groupId);
            final Call<ArtivaticResponse<GroupDetails>> call = new RestClient().getApiService().callGetGroupDetails(userIdList);
            call.enqueue(new Callback<ArtivaticResponse<GroupDetails>>() {
                @Override
                public void onResponse(Call<ArtivaticResponse<GroupDetails>> call, Response<ArtivaticResponse<GroupDetails>> response) {
                    Log.e("LOGGER", "RESPONSE SUCCESS " + response.code());
                    if (response.code() == 200) {
                        try {


                            ArtivaticGroupsApi.this.groupDetailsApiCallback.onSuccess(response.body().getResponse());


                        } catch (Exception e) {
                            e.printStackTrace();
                            ArtivaticGroupsApi.this.groupDetailsApiCallback.onError(ErrorStrings.API_CALL_FAILED);
                        }
                    } else {
                        ArtivaticGroupsApi.this.groupDetailsApiCallback.onError(ErrorStrings.API_CALL_FAILED);
                    }
                }

                @Override
                public void onFailure(Call<ArtivaticResponse<GroupDetails>> call, Throwable t) {
                    t.printStackTrace();
                    ArtivaticGroupsApi.this.groupDetailsApiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }
            });
        }else{
            ArtivaticGroupsApi.this.groupDetailsApiCallback.onError(ErrorStrings.USER_ID_NOT_SET);
        }



    }
}
