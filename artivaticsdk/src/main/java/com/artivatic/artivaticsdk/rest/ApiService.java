package com.artivatic.artivaticsdk.rest;


import com.artivatic.artivaticsdk.rest.models.ArtivaticResponse;
import com.artivatic.artivaticsdk.rest.models.FilterData;
import com.artivatic.artivaticsdk.rest.models.GroupData;
import com.artivatic.artivaticsdk.rest.models.GroupDetails;
import com.artivatic.artivaticsdk.rest.models.GroupFilterData;
import com.artivatic.artivaticsdk.rest.models.GroupList;
import com.artivatic.artivaticsdk.utils.ApiKeyModel;
import com.artivatic.artivaticsdk.utils.SuggestionDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Soham Dutta on 21-01-2016.
 */
public interface ApiService {


    /*
    * Call Interact API
    * */
    @POST("/interact/{userId}/{productId}")
    Call<Map<String,Object>> interaction(@Path("userId") String userId,@Path("productId") String productId,@Body Map<String, Object> level);


    /*
   * http://192.168.1.4:9000/searchDetail/product/all/rest/0
   *
    */
    @GET("/searchDetail/product/all/{rest}/{offset}")


    //searchDetail/product/all/ban/0
    Call<ArtivaticResponse<ArrayList<SearchList>>>searchList(@Path("rest") String rest, @Path("offset") int offset);

    /*
    * Call user Register API
    * */
    @POST("/user/")
    Call<Map<String,Object>> registerUser(@Body Map<String, Object> userData);

    /*
    * Call user Register API
    * */
    @POST("/product/{productId}")
    Call<Map<String,Object>> addProduct(@Path("productId") String productId, @Body Map<String, Object> productData);

    /*
   * Call user Register API
   * */
    @GET("/getclient/metadata")
    Call<Map<String,Object>> getClientMeta();

    /*
    * Call predict with filters
    * */
    @POST("/predictFilter/details")
    Call<ResponseBody> callUserSuggestApi(@Body FilterData userData);

    /*
    * Call group predict with filters
    * */
    @POST("/groupPredictFilter/details")
    Call<ResponseBody> callGroupPredictApi(@Query("offset") String offset, @Query("start") String start, @Body GroupFilterData userData);

    /*
    * Call create group api
    * */
    @POST("/saveGroupDetails")
    Call<Map<String,Object>> callGroupCreateApi(@Body GroupData groupData);

    /*
    * Call group list api
    * */
    @POST("/getUserGroups")
    Call<ArtivaticResponse<GroupList>> callGetGroupList(@Body Map<String,String> userIdMap);

    /*
    * Call group details api
    * */
    @POST("/getGroupMembers")
    Call<ArtivaticResponse<GroupDetails>> callGetGroupDetails(@Body Map<String,String> groupDetailsMap);


    /*
    * Call user suggestion api
    * */
    @POST("/suggestUsersToUser/{returnType}/{uid}")
    Call<ResponseBody> callUserSuggestApi(@Path("returnType") String returnType, @Path("uid") String uid, @Body SuggestionDataModel userData);

    /*
    * Call user to product suggestion api
    * */
    @POST("/suggestUsersToProduct/{returnType}/{pid}")
    Call<ResponseBody> callUsertoProductSuggestApi(@Path("returnType") String returnType, @Path("pid") String pid, @Body SuggestionDataModel userData);


    /*
    * Call product to user suggestion api
    * */
    @GET("/retrieveNewKey")
    Call<ArtivaticResponse<ApiKeyModel>> getNewApiKey(@Header("hash") String returnType);



}
