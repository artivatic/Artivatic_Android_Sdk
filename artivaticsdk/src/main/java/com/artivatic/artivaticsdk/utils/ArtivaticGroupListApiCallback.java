package com.artivatic.artivaticsdk.utils;

import com.artivatic.artivaticsdk.rest.models.GroupList;

/**
 * Created by root on 30/12/16.
 */

public interface ArtivaticGroupListApiCallback {
    public void onSuccess(GroupList response);
    public void onError(String error);
}
