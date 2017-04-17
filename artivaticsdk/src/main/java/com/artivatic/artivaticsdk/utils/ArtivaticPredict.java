package com.artivatic.artivaticsdk.utils;

import android.util.Log;

import com.artivatic.artivaticsdk.rest.RestClient;
import com.artivatic.artivaticsdk.rest.models.CategoryFilters;
import com.artivatic.artivaticsdk.rest.models.FilterData;
import com.artivatic.artivaticsdk.rest.models.Filters;
import com.artivatic.artivaticsdk.rest.models.ProductIds;
import com.artivatic.artivaticsdk.rest.models.Sorts;
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
 * Created by root on 13/11/16.
 */
public class ArtivaticPredict {
    private static ArtivaticPredict ourInstance;
    private FilterData filterData;
    List<UserIds> userIds;
    List<ProductIds> client = new ArrayList<>();
    List<CategoryFilters> Filter = new ArrayList<>();
    List<Sorts> sort = new ArrayList<>();

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    String search = "";
    String userId = "";
    String weight = "0.6";

    public String getSelfWeight() {
        return weight;
    }

    public void setSelfWeight(String weight) {
        this.weight = weight;
    }

    //    Type t = null;
    SessionManager sessionManager;
    private ArtivaticPredictApiCallback apiCallback;

    /**
     * @param userId send UserId for the current user
     * @return ArtivaticPredict instance
     */
    public static ArtivaticPredict build(String userId) {
        if (ourInstance == null) {
            ourInstance = new ArtivaticPredict(userId);
        }
//        userId = userId;

        return ourInstance;
    }

    private ArtivaticPredict(String userId) {
        userIds = new ArrayList<>();
        client = new ArrayList<>();
        Filter = new ArrayList<>();
        sort = new ArrayList<>();
        search = "";
        filterData = new FilterData(userIds, client, Filter, sort,search);
//        this.t = T;
        sessionManager = new SessionManager(ArtivaticSDK.getContext());
//        Log.e("LOGGER","USER ID = "+ourInstance.userId);
        this.userId = userId;
        addUserIdToList(userId, "0.6");
    }

    public void reset() {
        userIds = new ArrayList<>();
        client = new ArrayList<>();
        Filter = new ArrayList<>();
        sort = new ArrayList<>();
        search="";
        filterData = new FilterData(userIds, client, Filter, sort,search);
//        this.t = T;
        sessionManager = new SessionManager(ArtivaticSDK.getContext());
        addUserIdToList(userId, weight);
    }

    /**
     * @param userId userId of a friend
     * @param weight weight of the friends personality on predictions
     */
    public void addUserIdToList(String userId, String weight) {
        Log.e("LOGGER", "USER ID = " + userId);
        userIds.add(new UserIds(userId, weight));
    }

    /**
     * @param userFriends List of userIds of a friends
     */
    public void addUserIdList(ArrayList<String> userFriends) {
        double weight = 0.4 / userFriends.size();
        for (int i = 0; i < userFriends.size(); i++) {
            userIds.add(new UserIds(userFriends.get(i), "" + weight));
        }

    }

    /**
     * @param productIds List of product Ids to run predict on.
     */
    public void addProductIds(List<String> productIds) {
        for (int i = 0; i < productIds.size(); i++) {
            addProductId(productIds.get(i));
        }
    }

    public void addFilter(CategoryFilters categoryFilters) {
        Filter.add(categoryFilters);
    }

    /**
     * @param productId add Product Id to run predict on.
     */
    public void addProductId(String productId) {
        client.add(new ProductIds(productId));
    }

    /**
     * @param attributeName attribute name to be sorted in ascending order
     */
    public void addFilterSortColumnAscending(String attributeName) {
        sort.add(new Sorts(attributeName, "asc"));
    }

    /**
     * @param attributeName attribute name to be sorted in descending order
     */
    public void addFilterSortColumnDescending(String attributeName) {
        sort.add(new Sorts(attributeName, "desc"));
    }

    /**
     * @param categoryName category name to be filtered
     * *//*
    public void addFilterByProductCategory(String categoryName){
        filters.add(new Filters("category","",categoryName));
    }*/

    /**
     * @param categoryName category name to be filtered
     * */
    /*public void addFilterByMultipleProductCategory(ArrayList<String> categoryName){
        String categories = "";
        for(int i=0;i<categoryName.size();i++){
            if(i ==0){
                categories+=categoryName.get(i);
            }else{
                categories+=","+categoryName.get(i);
            }
        }
        filters.add(new Filters("category","",categories));
    }*/


    /**
     * Final predict call to get product list in response
     *
     * @return Product Data
     */
    public <T> String callPredict(final Class<T> clazz, ArtivaticPredictApiCallback apiCallback) {
        filterData = new FilterData(userIds, client, Filter, sort,search);
        this.apiCallback = apiCallback;
        final Gson gson = new Gson();
        Log.e("LOGGER", gson.toJson(filterData));
        final Call<ResponseBody> call = new RestClient().getApiService().callUserSuggestApi(filterData);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("LOGGER", "RESPONSE SUCCESS " + response.code());
                if (response.code() == 200) {
                    try {

                        JsonParser parser = new JsonParser();
                        JSONObject jobj = new JSONObject(response.body().string());
                        Log.e("LOGGER", "BEFORE GSON GETTING TYPE = " + jobj.getString("Response"));
                        JsonArray array = parser.parse(jobj.getString("Response")).getAsJsonArray();

                        List<T> lst = new ArrayList<T>();
                        for (final JsonElement json : array) {
                            T entity = gson.fromJson(json, clazz);
                            lst.add(entity);
                        }
                       /* Type type = new TypeToken<ArtivaticResponse<ArrayList<T>>>(){}.getType();
                        ArtivaticResponse<ArrayList<T>> respondedObject = gson.fromJson(response.body().string(),type);
*/
                        ArtivaticPredict.this.apiCallback.onSuccess(lst, jobj.getString("Response"));


                    } catch (Exception e) {
                        e.printStackTrace();
                        ArtivaticPredict.this.apiCallback.onError();
                    }
                } else {
                    ArtivaticPredict.this.apiCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                ArtivaticPredict.this.apiCallback.onError();
            }
        });
        return gson.toJson(filterData);
    }

}
