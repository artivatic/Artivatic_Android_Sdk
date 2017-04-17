package com.artivatic.artivaticsdk.rest;

import android.util.Log;

import com.artivatic.artivaticsdk.rest.models.UserMeta;
import com.artivatic.artivaticsdk.utils.ArtivaticApiCallback;
import com.artivatic.artivaticsdk.utils.ErrorStrings;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 28/10/16.
 */
public class ArtivaticApi {
    private static ArtivaticApi ourInstance = new ArtivaticApi();
    private ArtivaticApiCallback apiCallback;
    public static ArtivaticApi getInstance() {
        return ourInstance;
    }

    public ArtivaticApi() {
    }




    public void callInteractApi(String userId,String productId,int interactLevel,final ArtivaticApiCallback apiCallback){
        this.apiCallback = apiCallback;
        RestClient restClient = new RestClient();

        Map<String,Object> body = new HashMap<>();
        body.put("level",interactLevel);

        restClient.getApiService().interaction(userId,productId,body).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if(response.code() == 200){
                    ArtivaticApi.this.apiCallback.onSuccess(response.body());
                }else{
                    ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
            }
        });
    }
    public void callInteractPredictionApi(String userId,String productId,int interactLevel,String predictId,final ArtivaticApiCallback apiCallback){
        this.apiCallback = apiCallback;
        RestClient restClient = new RestClient();

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("level",interactLevel);
        body.put("predictId",predictId);

        restClient.getApiService().interaction(userId,productId,body).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if(response.code() == 200){
                    ArtivaticApi.this.apiCallback.onSuccess(response.body());
                }else{
                    ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
            }
        });
    }



    public void callRegisterUserApi(Map<String,Object> userData, ArtivaticApiCallback apiCallback){
        this.apiCallback = apiCallback;
        RestClient restClient = new RestClient();
        restClient.getApiService().registerUser(userData).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if(response.code() == 200){
                    ArtivaticApi.this.apiCallback.onSuccess(response.body());
                }else{
                    ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
            }
        });
    }

    public void callAddProductApi(String productId, Map<String,Object> productData, ArtivaticApiCallback apiCallback){
        this.apiCallback = apiCallback;
        RestClient restClient = new RestClient();
        restClient.getApiService().addProduct(productId,productData).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if(response.code() == 200){
                    ArtivaticApi.this.apiCallback.onSuccess(response.body());
                }else{
                    ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
            }
        });
    }

    public void callClientMetaApi(ArtivaticApiCallback apiCallback){
        this.apiCallback = apiCallback;
        RestClient restClient = new RestClient();
        restClient.getApiService().getClientMeta().enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

                if(response.code() == 200){
                    ArtivaticApi.this.apiCallback.onSuccess(response.body());
                }else{
                    ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
                }

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                ArtivaticApi.this.apiCallback.onError(ErrorStrings.API_CALL_FAILED);
            }
        });
    }
}
