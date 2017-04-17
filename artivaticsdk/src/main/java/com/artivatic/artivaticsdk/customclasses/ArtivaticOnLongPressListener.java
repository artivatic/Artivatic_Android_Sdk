package com.artivatic.artivaticsdk.customclasses;

import android.util.Log;
import android.view.View;

import com.artivatic.artivaticsdk.rest.ArtivaticApi;
import com.artivatic.artivaticsdk.utils.ArtivaticApiCallback;

import java.util.Map;

/**
 * Created by root on 4/11/16.
 */

public class ArtivaticOnLongPressListener implements View.OnLongClickListener {
    private String userId = "";
    private String productId = "";
    private int interactLevel = 0;

    ArtivaticOnClickListener.ArtivaticOnClick artivaticOnClick;

    public ArtivaticOnLongPressListener(String userId,String productId, int interactLevel,ArtivaticOnClickListener.ArtivaticOnClick artivaticOnClick) {
        this.interactLevel = interactLevel;
        this.productId = productId;
        this.userId = userId;
        this.artivaticOnClick = artivaticOnClick;
    }


    @Override
    public boolean onLongClick(View v) {
        ArtivaticApi.getInstance().callInteractApi(this.userId, this.productId,this.interactLevel, new ArtivaticApiCallback() {
            @Override
            public void onSuccess(Map<String, Object> response) {
                artivaticOnClick.onInteractSuccess();
                Log.e("LOGGER","I HAVE SUCCESS");
            }

            @Override
            public void onError(String error) {
                artivaticOnClick.onInteractError();
                Log.e("LOGGER","I HAVE ERROR");
            }
        });
        artivaticOnClick.onClick(v);
        return false;
    }
}
