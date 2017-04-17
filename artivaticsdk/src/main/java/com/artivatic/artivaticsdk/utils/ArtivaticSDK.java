package com.artivatic.artivaticsdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.artivatic.artivaticsdk.ArtivaticConstants;
import com.artivatic.artivaticsdk.rest.ArtivaticApi;
import com.artivatic.artivaticsdk.rest.RestClient;
import com.artivatic.artivaticsdk.rest.models.ArtivaticResponse;
import com.artivatic.artivaticsdk.rest.models.ProductFilterableAttributes;
import com.artivatic.artivaticsdk.rest.models.ProductMetaAttributes;
import com.artivatic.artivaticsdk.rest.models.ProductMetaList;
import com.artivatic.artivaticsdk.rest.models.UserMetaList;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 28/10/16.
 */
public class ArtivaticSDK  implements ArtivaticConstants {
    private static ArtivaticSDK ourInstance;
    private static Context appContext = null;

    private static String BASE_URL = "";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static ArtivaticSDK init(Context context) {
        if (ourInstance == null) {
            ourInstance = new ArtivaticSDK(context);
        }
        return ourInstance;
    }

    public static Context getContext() {
        if (appContext == null) {
            Log.e("LOGGER", "Context is null, please initialise again");
        }
        return appContext;
    }

    SessionManager sessionManager;
    ArrayList<String> categories = new ArrayList<>();

    private ArtivaticSDK(Context context) {
        appContext = context;
        ReadManifestMeta readManifestMeta = new ReadManifestMeta(appContext,stageUrlTag);
        if(TextUtils.isEmpty(readManifestMeta.getMeta())){
            BASE_URL = LIVE_BASE_URL;
            Log.i("Artivatic SDK","Using live url");
        }else{
            Log.i("Artivatic SDK","Using staging url");
            BASE_URL = readManifestMeta.getMeta();
        }
        sessionManager = new SessionManager(appContext);
        Log.e("LOGGER","CURRENT API KEY IN SESSION = "+sessionManager.getApiKey());
        if(sessionManager.getApiKey().equals("empty")) {
            Log.e("LOGGER","GEtTING CURRENT API KEY");
            getSHA();
        }
    }

    private void getClientMeta() {
        ArtivaticApi.getInstance().callClientMetaApi(new ArtivaticApiCallback() {
            @Override
            public void onSuccess(Map<String, Object> response) {
//                artivaticOnClick.onInteractSuccess();
                try {
                    Gson gson = new Gson();
                    JSONArray jar = new JSONArray(response.get("user_meta").toString());
                    JSONObject userMetaJson = new JSONObject();
                    userMetaJson.put("user_meta", jar);
                    Log.e("LOGGER", "String generated = " + userMetaJson.toString());
                    sessionManager.putUserMeta(userMetaJson.toString());
                    UserMetaList userMetaList = gson.fromJson(userMetaJson.toString(), UserMetaList.class);
                    Log.e("LOGGER", "USER META LIST SIZE = " + userMetaList.getUserMetaList().size());

                    JSONArray productJar = new JSONArray(response.get("product_meta").toString());
                    JSONObject productMetaJson = new JSONObject();
                    productMetaJson.put("product_meta", productJar);
                    Log.e("LOGGER", "String generated = " + productMetaJson.toString());
                    sessionManager.putProductMeta(productMetaJson.toString());
                    ProductMetaList productMetaList = gson.fromJson(productMetaJson.toString(), ProductMetaList.class);
                    Log.e("LOGGER", "PRODUCT META LIST SIZE = " + productMetaList.getUserMetaList().size());
                    ArrayList<ProductFilterableAttributes> filterableAttributes = getProductFilterableAttributes();
//                    getProductCategory();
                    for (int i = 0; i < filterableAttributes.size(); i++) {
                        Log.e("LOGGER", filterableAttributes.get(i).getAttributeName());
                        Log.e("LOGGER_cattegory", filterableAttributes.get(i).getCategory());
                        Log.e("LOGGER", filterableAttributes.get(i).getAttributeType());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("LOGGER", "I HAVE SUCCESS");
            }

            @Override
            public void onError(String error) {
//                artivaticOnClick.onInteractError();
                Log.e("LOGGER", "I HAVE ERROR");
            }
        });
    }

    public ArrayList<ProductFilterableAttributes> getProductFilterableAttributes() {
        Gson gson = new Gson();
        String productMetaJson = sessionManager.getProductMeta();
//        Log.e("valuee_chkk",""+sessionManager.saveProductMeta());
        ProductMetaList productMetaList = gson.fromJson(productMetaJson, ProductMetaList.class);
        ArrayList<ProductFilterableAttributes> filterableAttributeList = new ArrayList<>();
        for (int i = 0; i < productMetaList.getUserMetaList().size(); i++) {

            ProductMetaAttributes products = productMetaList.getUserMetaList().get(i);
//            sessionManager.saveProductMeta(products);
//            Log.e("product_chkkkk",""+products.getCategory().toString());
            for (int j = 0; j < products.getUserMetaList().size(); j++) {
                if (!products.getUserMetaList().get(j).getcType().equals("na")) {
                    filterableAttributeList.add(new ProductFilterableAttributes(products.getUserMetaList().get(j).getName(), products.getUserMetaList().get(j).getcType(), productMetaList.getUserMetaList().get(i).getCategory()));
                }
            }
        }
        return filterableAttributeList;
    }

    public ArrayList<String> getProductCategory()

    {
        categories=new ArrayList<>();

        Gson gson = new Gson();

        String productMetaJson = sessionManager.getProductMeta();

        ProductMetaList productMetaList = gson.fromJson(productMetaJson, ProductMetaList.class);
        ArrayList<ProductFilterableAttributes> filterableAttributeList = new ArrayList<>();
        if(productMetaList.getUserMetaList()!=null) {
            for (int i = 0; i < productMetaList.getUserMetaList().size(); i++) {

                ProductMetaAttributes products = productMetaList.getUserMetaList().get(i);
//            sessionManager.saveProductMeta(categories);
                categories.add(productMetaList.getUserMetaList().get(i).getCategory());

//

            }
        }


        return categories;
    }

    public void getSHA() {
        PackageInfo info;
        try {
            info = getContext().getPackageManager().getPackageInfo(
                    getContext().getPackageName(), PackageManager.GET_SIGNATURES);
            String something = "";
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                something = new String(Base64.encode(md.digest(), 0));
                Log.e("identifier key in sdk", something);
                Log.e("PACKAGE NAME ", getContext().getPackageName());
                System.out.println("Hash key in sdk" + something);
                Log.e("manifest_name",""+new ReadManifestMeta(appContext,clientTag).getMeta());
            }

//            getApiKey(SHAHasher.SHA1(SHAHasher.getHashSequence(something.trim(),getContext().getPackageName(),"android",new ReadManifestMeta(appContext).getMeta())));
//            Log.e("LOGGER","SHA KEY = "+SHAHasher.SHA1(SHAHasher.getHashSequence(something.trim(),getContext().getPackageName(),"android",new ReadManifestMeta(appContext).getMeta())));

            getApiKey(SHAHasher.SHA1(SHAHasher.getHashSequence(something.trim(),getContext().getPackageName(),"android",new ReadManifestMeta(appContext,clientTag).getMeta())));
            Log.e("LOGGER","SHA KEY = "+SHAHasher.SHA1(SHAHasher.getHashSequence(something.trim(),getContext().getPackageName(),"android",new ReadManifestMeta(appContext,clientTag).getMeta())));


        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getApiKey(String header){
        Log.e("LOGGER","CALLING API "+header);

        Call<ArtivaticResponse<ApiKeyModel>> call = new RestClient().getApiService().getNewApiKey(header);
        call.enqueue(new Callback<ArtivaticResponse<ApiKeyModel>>() {
            @Override
            public void onResponse(Call<ArtivaticResponse<ApiKeyModel>> call, Response<ArtivaticResponse<ApiKeyModel>> response) {
                try{
                    Log.e("LOGGER","RESPONSE FROM API "+response.body().getResponse().getApikey());
                    sessionManager.putApiKey(response.body().getResponse().getApikey());
                    getClientMeta();
                }catch (Exception e){
                    Log.e("LOGGER","THIS FAILED");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ArtivaticResponse<ApiKeyModel>> call, Throwable t) {
                Log.e("LOGGER","API CALL FAILED");
                t.printStackTrace();
            }
        });
    }

    public static void sendInteraction(String userId, String productId, int interactLevel) {
        ArtivaticApi.getInstance().callInteractApi(userId, productId, interactLevel, new ArtivaticApiCallback() {

            @Override
            public void onSuccess(Map<String, Object> response) {

            }

            @Override
            public void onError(String error) {

            }
        });

    }


    public void logOut(){
        sessionManager.clean();
    }

}
