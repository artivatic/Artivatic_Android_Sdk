package com.artivatic.artivaticsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Soham on 3/5/16.
 */
public class SessionManager {

    //Shared Preferences
    SharedPreferences pref;

    //Context
    Context _context;

    //Shared Pref Mode
    int PRIVATE_MODE = 0;

    public static final String TAG = "SESSION MANAGER";

    private static final String apiKey="API_KEY";
    private static final String userMeta="user_meta";
    private static final String productMeta="product_meta";

    //SharedPref File Name
    private static final String PREF_NAME = "ArtivaticPreferences";



    //Constructor for Session Manager
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
       }

    public static String getKey(String initialKey) {
        return "com.artivatic.config." + initialKey;
    }



    public void putApiKey(String apiKey){
        this.pref.edit().putString(this.apiKey, apiKey).apply();


    }


    public  String getApiKey() {
        return this.pref.getString(this.apiKey,"empty");
    }

    public String getUserMeta() {

        return this.pref.getString(this.userMeta,"");
    }

    public String getProductMeta()
    {
//        Log.e("value_gettt",""+this.pref.getString(this.userMeta,""));
        return this.pref.getString(this.productMeta,"");
    }

    public void putUserMeta(String userMeta){
        this.pref.edit().putString(this.userMeta, userMeta).apply();
    }
    public void putProductMeta(String productMeta){
        this.pref.edit().putString(this.productMeta, productMeta).apply();
    }


    public ArrayList<String> saveProductMeta(ArrayList<String> categories) {
        return  categories;

    }

    public void clean(){
        this.pref.edit()
                .clear().commit();
    }

}
