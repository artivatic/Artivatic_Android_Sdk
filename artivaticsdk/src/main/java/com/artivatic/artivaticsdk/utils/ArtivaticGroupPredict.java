package com.artivatic.artivaticsdk.utils;

import android.text.TextUtils;
import android.util.Log;

import com.artivatic.artivaticsdk.rest.RestClient;
import com.artivatic.artivaticsdk.rest.models.CategoryFilters;
import com.artivatic.artivaticsdk.rest.models.GroupFilterData;
import com.artivatic.artivaticsdk.rest.models.ProductIds;
import com.artivatic.artivaticsdk.rest.models.Sorts;
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
 * Created by root on 2/1/17.
 */

public class ArtivaticGroupPredict {
    private static ArtivaticGroupPredict ourInstance;
    private GroupFilterData filterData;
    String groupId;
    List<ProductIds> client = new ArrayList<>();
    List<CategoryFilters> Filter = new ArrayList<>();
    List<Sorts> sort = new ArrayList<>();
    String offset = "30";
    String start = "0";

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        if(TextUtils.isDigitsOnly(offset))
        this.offset = offset;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        if(TextUtils.isDigitsOnly(start))
        this.start = start;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    String search = "";


    //    Type t = null;
    SessionManager sessionManager;
    private ArtivaticPredictApiCallback apiCallback;

    /**
     * @return ArtivaticPredict instance
     */
    public static ArtivaticGroupPredict build() {
        if (ourInstance == null) {
            ourInstance = new ArtivaticGroupPredict();
        }
//        userId = userId;

        return ourInstance;
    }

    private ArtivaticGroupPredict() {
        groupId = "";
        client = new ArrayList<>();
        Filter = new ArrayList<>();
        sort = new ArrayList<>();
        search = "";
        filterData = new GroupFilterData(groupId, client, Filter, sort,search);
        sessionManager = new SessionManager(ArtivaticSDK.getContext());
        this.groupId = "";
    }

    public void reset() {
        groupId = "";
        client = new ArrayList<>();
        Filter = new ArrayList<>();
        sort = new ArrayList<>();
        search="";
        filterData = new GroupFilterData(groupId, client, Filter, sort,search);
//        this.t = T;
        sessionManager = new SessionManager(ArtivaticSDK.getContext());
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
    public void addFilterSortCollumnAscending(String attributeName) {
        sort.add(new Sorts(attributeName, "asc"));
    }

    /**
     * @param attributeName attribute name to be sorted in descending order
     */
    public void addFilterSortCollumnDescending(String attributeName) {
        sort.add(new Sorts(attributeName, "desc"));
    }

    /**
     * @param categoryName category name to be filtered
     * *//*
    public void addFilterByProductCategory(String categoryName){
        filters.add(new Filters("category","",categoryName));
    }*/





    /**
     * Final predict call to get product list in response
     *
     * @return Product Data
     */
    public <T> String callPredict(final Class<T> clazz, String groupId,ArtivaticPredictApiCallback apiCallback) {
        filterData = new GroupFilterData(groupId, client, Filter, sort,search);
        this.apiCallback = apiCallback;
        final Gson gson = new Gson();
        Log.e("LOGGER", gson.toJson(filterData));
        final Call<ResponseBody> call = new RestClient().getApiService().callGroupPredictApi(offset,start,filterData);
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
                        ArtivaticGroupPredict.this.apiCallback.onSuccess(lst, jobj.getString("Response"));


                    } catch (Exception e) {
                        e.printStackTrace();
                        ArtivaticGroupPredict.this.apiCallback.onError();
                    }
                } else {
                    ArtivaticGroupPredict.this.apiCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                ArtivaticGroupPredict.this.apiCallback.onError();
            }
        });
        return gson.toJson(filterData);
    }

}
