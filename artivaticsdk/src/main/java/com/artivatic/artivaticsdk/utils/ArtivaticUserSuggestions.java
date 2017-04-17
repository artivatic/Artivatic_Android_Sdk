package com.artivatic.artivaticsdk.utils;

import android.util.Log;

import com.artivatic.artivaticsdk.rest.RestClient;
import com.artivatic.artivaticsdk.rest.models.Filters;
import com.artivatic.artivaticsdk.rest.models.UserIds;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 1/12/16.
 */

public class ArtivaticUserSuggestions {

    private static ArtivaticUserSuggestions ourInstance;
    String userId = "";
    SessionManager sessionManager;
    SuggestionDataModel data;
    ArrayList<String> userIDs = new ArrayList<>();
    ArrayList<Filters> filters = new ArrayList<>();
    private ArtivaticSuggestionCallback apiCallback;


    private ArtivaticUserSuggestions(String userId){
        this.userId = userId;
        sessionManager = new SessionManager(ArtivaticSDK.getContext());
        data = new SuggestionDataModel();
        filters = new ArrayList<>();
        userIDs = new ArrayList<>();
    }

    public static ArtivaticUserSuggestions build(String userId) {
        if(ourInstance==null){
            ourInstance = new ArtivaticUserSuggestions(userId);
        }
        return ourInstance;
    }


    /**
     * @param userId userId of a friend
     * */
    public void addUserIdToList(String userId){
        userIDs.add(userId);
    }

    /**
     * @param userList List of userIds of a friends
     * */
    public void addUserIdList(ArrayList<String> userList){
        for(int i = 0 ; i < userList.size() ; i++){
            addUserIdToList(userList.get(i));
        }

    }

    /**
     * @param attributeName name of product attribute for filtering
     * @param value value field should match
     * */
    public void addFilterColumnEquals(String attributeName, String value){
        filters.add(new Filters("EQUALS",attributeName,value));
    }

    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be less than
     * */
    public void addFilterCollumnLessThan(String attributeName,String value){
        filters.add(new Filters("LESS_THAN",attributeName,value));
    }
    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be greater than
     * */
    public void addFilterCollumnGreaterThan(String attributeName,String value){
        filters.add(new Filters("GREATER_THAN",attributeName,value));
    }
    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be less than
     * */
    public void addFilterCollumnLessThanEquals(String attributeName,String value){
        filters.add(new Filters("LESS_THAN_EQUALS",attributeName,value));
    }
    /**
     * @param attributeName name of product attribute for filtering
     * @param value value attribute should be greater than
     * */
    public void addFilterCollumnGreaterThanEquals(String attributeName,String value){
        filters.add(new Filters("GREATER_THAN_EQUALS",attributeName,value));
    }

    /**
     * @param attributeName name of product attribute for filtering
     * @param minValue value attribute should be greater than
     * @param maxValue value attribute should be less than
     **/
    public void addFilterCollumnInRange(String attributeName,String minValue,String maxValue){
        addFilterCollumnLessThan(attributeName,maxValue);
        addFilterCollumnGreaterThan(attributeName,minValue);
    }
    /**
     * Final predict call to get product list in response
     * @param clazz Return Model Class
     * @param apiCallback instance of ArtivaticSuggestionCallback
     * @return null
     * */
    public <T> String  getUserCompatSuggestion(final Class<T> clazz, ArtivaticSuggestionCallback apiCallback){
        this.apiCallback = apiCallback;
        this.data.filter = filters;
        this.data.userIds = userIDs;
        final Gson gson = new Gson();
        final Call<ResponseBody> call = new RestClient().getApiService().callUserSuggestApi("details",userId,data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("LOGGER","RESPONSE SUCCESS "+response.code());
                if(response.code() == 200){
                    try {

                        JsonParser parser = new JsonParser();
                        JSONObject jobj = new JSONObject(response.body().string());
                        Log.e("LOGGER","BEFORE GSON GETTING TYPE = "+jobj.getString("Response"));
                        JsonArray array = parser.parse(jobj.getString("Response")).getAsJsonArray();

                        List<T> lst =  new ArrayList<T>();
                        for(final JsonElement json: array){
                            T entity = gson.fromJson(json, clazz);
                            lst.add(entity);
                        }
                       /* Type type = new TypeToken<ArtivaticResponse<ArrayList<T>>>(){}.getType();
                        ArtivaticResponse<ArrayList<T>> respondedObject = gson.fromJson(response.body().string(),type);
*/
                        ArtivaticUserSuggestions.this.apiCallback.onSuccess(lst,jobj.getString("Response"));


                    }catch (Exception e){
                        e.printStackTrace();
                        ArtivaticUserSuggestions.this.apiCallback.onError();
                    }
                }else{
                    ArtivaticUserSuggestions.this.apiCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ArtivaticUserSuggestions.this.apiCallback.onError();
            }
        });


        return null;

    }



    /**
     * Final predict call to get product list in response
     * @param clazz Return Model Class
     * @param apiCallback instance of ArtivaticSuggestionCallback
     * @return null
     * */
    public <T> String  getUserCompatToProductId(final Class<T> clazz, String productId,ArtivaticSuggestionCallback apiCallback){
        this.apiCallback = apiCallback;
        this.data.filter = filters;
        this.data.userIds = userIDs;
        final Gson gson = new Gson();
        final Call<ResponseBody> call = new RestClient().getApiService().callUsertoProductSuggestApi("details",productId,data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("LOGGER","RESPONSE SUCCESS "+response.code());
                if(response.code() == 200){
                    try {

                        JsonParser parser = new JsonParser();
                        JSONObject jobj = new JSONObject(response.body().string());
                        Log.e("LOGGER","BEFORE GSON GETTING TYPE = "+jobj.getString("Response"));
                        JsonArray array = parser.parse(jobj.getString("Response")).getAsJsonArray();

                        List<T> lst =  new ArrayList<T>();
                        for(final JsonElement json: array){
                            T entity = gson.fromJson(json, clazz);
                            lst.add(entity);
                        }
                       /* Type type = new TypeToken<ArtivaticResponse<ArrayList<T>>>(){}.getType();
                        ArtivaticResponse<ArrayList<T>> respondedObject = gson.fromJson(response.body().string(),type);
*/
                        ArtivaticUserSuggestions.this.apiCallback.onSuccess(lst,jobj.getString("Response"));


                    }catch (Exception e){
                        e.printStackTrace();
                        ArtivaticUserSuggestions.this.apiCallback.onError();
                    }
                }else{
                    ArtivaticUserSuggestions.this.apiCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ArtivaticUserSuggestions.this.apiCallback.onError();
            }
        });


        return null;


    }
}
