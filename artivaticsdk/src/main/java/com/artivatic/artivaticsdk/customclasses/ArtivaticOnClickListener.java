package com.artivatic.artivaticsdk.customclasses;

import android.util.Log;
import android.view.View;

import com.artivatic.artivaticsdk.rest.ArtivaticApi;
import com.artivatic.artivaticsdk.utils.ArtivaticApiCallback;

import java.util.Map;

/**
 * Created by root on 2/11/16.
 */

public class ArtivaticOnClickListener implements View.OnClickListener {
    private String userId = "";
    private String productId = "";
    private int interactLevel = 0;
    ArtivaticOnClick artivaticOnClick;

    public ArtivaticOnClickListener(String userId,String productId, int interactLevel,ArtivaticOnClick artivaticOnClick) {
        this.interactLevel = interactLevel;
        this.productId = productId;
        this.userId = userId;
        this.artivaticOnClick = artivaticOnClick;
    }

    @Override
    public void onClick(View v) {
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
    }

    public interface ArtivaticOnClick {
        void onClick(View v);
        void onInteractSuccess();
        void onInteractError();
    }
}
