package com.artivatic.artivaticsdk.utils;

import com.artivatic.artivaticsdk.rest.models.GroupDetails;

/**
 * Created by root on 30/12/16.
 */

public interface ArtivaticGroupDetailsCallback {
    public void onSuccess(GroupDetails response);
    public void onError(String error);
}
