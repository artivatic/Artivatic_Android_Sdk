package com.artivatic.artivaticsdk.rest;


import android.text.TextUtils;
import android.util.Log;

import com.artivatic.artivaticsdk.ArtivaticConstants;
import com.artivatic.artivaticsdk.utils.ArtivaticSDK;
import com.artivatic.artivaticsdk.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Soham Dutta on 21-01-2016.
 */
public class RestClient {
//    public static final String
//            BASE_URL = "http://ec2-54-201-186-4.us-west-2.compute.amazonaws.com:8082/api/";



//    192.168.1.190

    public static final String version = "v1";
    private ApiService apiService;

    public RestClient() {



        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory()) // This is the important line ;)
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new SessionRequestInterceptor()).build();
        if(!TextUtils.isEmpty(new SessionManager(ArtivaticSDK.getContext()).getApiKey())){
            Log.e("LOGGER","SESSION ACCESS TOKEN = "+new SessionManager(ArtivaticSDK.getContext()).getApiKey());
            Interceptor sessionInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .header("apikey", new SessionManager(ArtivaticSDK.getContext()).getApiKey())
//                            .addHeader("App-Key","+meGCLKq3FsiF+8un3oo+w==")
                            .build();
                    Response response = chain.proceed(request);
                    return response;
                }
            };
            client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new SessionRequestInterceptor()).addInterceptor(sessionInterceptor).build();
//            client.interceptors().add(sessionInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ArtivaticSDK.getBaseUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService =
                retrofit.create(ApiService.class);

    }

    public ApiService getApiService() {
        return apiService;
    }

}