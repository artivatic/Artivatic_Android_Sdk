package com.artivatic.artivaticsdk.utils;

import com.artivatic.artivaticsdk.rest.models.ArtivaticResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 14/11/16.
 */

public interface ArtivaticPredictApiCallback<T> {
    public void onSuccess(List<T> response,String responseString);
    public void onError();
}