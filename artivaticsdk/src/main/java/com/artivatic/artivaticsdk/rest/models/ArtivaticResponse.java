package com.artivatic.artivaticsdk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by root on 15/11/16.
 */

public class ArtivaticResponse<T>  {
    @SerializedName("Status")
    private String status;

    @SerializedName("Message")
    private String request;

    @SerializedName("Response")
    private T response;

    public String getStatus() {
        return status;
    }

    public String getRequest() {
        return request;
    }

    public T getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", request=" + request +
                ", response=" + response +
                '}';
    }
}
