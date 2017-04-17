package com.artivatic.artivaticsdk.utils;

import java.util.Map;

/**
 * Created by root on 31/10/16.
 */

public interface ArtivaticApiCallback {
    public void onSuccess(Map<String,Object> response);
    public void onError(String error);
}
