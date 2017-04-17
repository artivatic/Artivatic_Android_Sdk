package com.example.artivatic.artivaticsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.artivatic.artivaticsdk.rest.ArtivaticApi;
import com.artivatic.artivaticsdk.rest.models.CategoryFilters;
import com.artivatic.artivaticsdk.rest.models.GroupData;
import com.artivatic.artivaticsdk.rest.models.GroupDetails;
import com.artivatic.artivaticsdk.rest.models.GroupList;
import com.artivatic.artivaticsdk.utils.ArtivaticApiCallback;
import com.artivatic.artivaticsdk.utils.ArtivaticGroupDetailsCallback;
import com.artivatic.artivaticsdk.utils.ArtivaticGroupListApiCallback;
import com.artivatic.artivaticsdk.utils.ArtivaticGroupsApi;
import com.artivatic.artivaticsdk.utils.ArtivaticPredict;
import com.artivatic.artivaticsdk.utils.ArtivaticPredictApiCallback;
import com.artivatic.artivaticsdk.utils.ArtivaticSDK;
import com.artivatic.artivaticsdk.utils.ArtivaticSuggestionCallback;
import com.artivatic.artivaticsdk.utils.ArtivaticUserSuggestions;
import com.artivatic.artivaticsdk.utils.SuggestionDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.R.id.input;
import static android.R.id.mask;

public class MainActivity extends AppCompatActivity {
    ArtivaticPredict artivaticPredict;
    CategoryFilters categoryFilters;
    ArtivaticApi artivaticApi;
    Suggestion suggestion;

    ArtivaticGroupsApi artivaticGroupsApi;
    ArrayList<String> data=new ArrayList<>();
    private ArrayList<String> client_member_id = new ArrayList<>();
    ArtivaticUserSuggestions artivaticUserSuggestions;

    static ArrayList<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArtivaticSDK artivaticSDK = ArtivaticSDK.init(MainActivity.this);

        try{
            categories = artivaticSDK.getProductCategory();
            Log.e("categoryyy",""+categories);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        artivaticGroupsApi=new ArtivaticGroupsApi("1");
        artivaticApi=new ArtivaticApi();

      //  artivaticPredict = ArtivaticPredict.build("1");

        setContentView(R.layout.activity_main);
/*
Call  the Predict Detail
 */


//        artivaticPredict.reset();
//     categoryFilters=new CategoryFilters("Clothes & Shoes");
//        artivaticPredict.addFilter(categoryFilters);
//
//        artivaticPredict.callPredict(RestaurantList.class, new ArtivaticPredictApiCallback<RestaurantList>() {
//            @Override
//            public void onSuccess(List<RestaurantList> response, String responseString) {
//                Log.e("response_gettttt",""+response.size());
//
//
//            }
//
//            @Override
//            public void onError() {
//                Log.e("LOGGER", "I HAVE FAILURE");
//            }
//        });



        /*
        User Suggestion APi
         */

//        artivaticUserSuggestions=ArtivaticUserSuggestions.build("1");
//        artivaticUserSuggestions.addUserIdToList("1");
//        artivaticUserSuggestions.addUserIdToList("2");
//        artivaticUserSuggestions.addUserIdToList("3");
//        artivaticUserSuggestions.addUserIdToList("4");
//        artivaticUserSuggestions.addUserIdToList("5");
//
//        artivaticUserSuggestions.getUserCompatSuggestion(SuggestionData.class, new ArtivaticSuggestionCallback<SuggestionData>() {
//            @Override
//            public void onSuccess(List<SuggestionData> response, String responseString) {
//                Log.e("response_gettt",""+response.size());
//
//            }
//
//            @Override
//            public void onError() {
//
//
//            }
//        });

        /*
        User to Product
         */
    ;
//        artivaticUserSuggestions.getUserCompatToProductId(SuggestionData.class,"f823", new ArtivaticSuggestionCallback<SuggestionData>() {
//            @Override
//            public void onSuccess(List<SuggestionData> response, String responseString) {
//                Log.e("response_gettt",""+response.size());
//
//            }
//
//            @Override
//            public void onError() {
//
//
//            }
//        });

        /*
Save Group Detail
         */

//        {"client_group_id": "1",
//                "client_member_id": ["500", "144", "291", "385", "50"],
//            "client_user_id": "318",
//                "av_group_id":"","
//                "group_name": "av_rebel-different-v2-0"

//        }
//        client_member_id.add("500");
//        client_member_id.add("144");
//        client_member_id.add("291");
//
//       artivaticGroupsApi.setGroupData(client_member_id,"318","av_rebel-different-v2-0");
//        artivaticGroupsApi.callCreateGroup(new ArtivaticApiCallback() {
//            @Override
//            public void onSuccess(Map<String, Object> response) {
//                Log.e("response_gett","");
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });
        /*
        Call Group List
         */

//        artivaticGroupsApi.callGetGroupList(new ArtivaticGroupListApiCallback() {
//            @Override
//            public void onSuccess(GroupList response) {
//
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });

/*
GroupList Details
 */

//        artivaticGroupsApi.callGetGroupDetailsApi("1", new ArtivaticGroupDetailsCallback() {
//            @Override
//            public void onSuccess(GroupDetails response) {
//
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });


/*
Add Product
  "category":"baby-gear",
    "id":"14",
    "year":"3",
    "price":"1408",
    "year":"3",
    "price":"1408",
    "name":"Cosmos Baby World Tricycle - Red and Yellow",
    "gender":["M","F"]
 */
//
//
     HashMap<String ,Object> addData=new HashMap<>();
        addData.put("category","14");

        data.add("M");

        data.add("F");
        addData.put("gender",data);
        Log.e("mappp",""+addData);
//                 suggestion=new Suggestion();
//
//
        artivaticApi.callAddProductApi("f823",  addData, new ArtivaticApiCallback() {
            @Override
            public void onSuccess(Map<String, Object> response) {

            }

            @Override
            public void onError(String error) {

            }
        });

//

//        artivaticApi.callRegisterUserApi(add, new ArtivaticApiCallback() {
//            @Override
//            public void onSuccess(Map<String, Object> response) {
//
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });
//

//artivaticApi.callInteractPredictionApi("1", "f823", 9,"1_oida", new ArtivaticApiCallback() {
//    @Override
//    public void onSuccess(Map<String, Object> response) {
//
//    }
//
//    @Override
//    public void onError(String error) {
//
//    }
//});



        }

}
