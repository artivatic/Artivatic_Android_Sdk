package com.artivatic.artivaticsdk.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 28/10/16.
 */

public class ReadManifestMeta {
    Context context;
    private String manifestTag = "";
    public ReadManifestMeta(Context context,String tag){
        this.context = context;
        manifestTag = tag;
    }
    public String getMeta(){
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String myApiKey = bundle.getString(manifestTag);
            return myApiKey;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
        }
        return "";
    }
}

