package com.wazza.android.sdk.service;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wazza.android.sdk.Wazza;

import org.json.JSONArray;

public class ItemService {

    private PersistenceService persist;

    //server endpoints
    private static final String ENDPOINT_ITEM_LIST = "items/";
    private static final String ENDPOINT_ITEM_DETAILED_LIST = "items/details/";
    private static final String ENDPOINT_DETAILS = "item/";
    private static final String ENDPOINT_RECOMMENDATION = "rec/user/items/";


    private String constructURL(String URL) {
        return URL + Wazza.companyName + "/" + Wazza.appName;
    }

    public void getRecommendedItems(int limit) {
        String url = constructURL(ENDPOINT_RECOMMENDATION) + "/" + Wazza.username + "/" + limit;
        RestClient.get(url, RestClient.constructRequestHeader(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray results) {
                persist.storeRecommendedItems(results);
            }

            @Override
            public void onFailure(int statusCode,
                                  org.apache.http.Header[] headers,
                                  java.lang.Throwable e,
                                  org.json.JSONObject errorResponse) {
                Log.e("WazzaSDK", "oops.. something went wrong");
            }
        });

    }

    public void fetchItems() {
        RestClient.get(constructURL(ENDPOINT_ITEM_DETAILED_LIST), RestClient.constructRequestHeader(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray results) {
                persist.addItem(results);
                persist.commitItems();
            }

            @Override
            public void onFailure(int statusCode,
                                  org.apache.http.Header[] headers,
                                  java.lang.Throwable e,
                                  org.json.JSONObject errorResponse) {
                Log.e("WazzaSDK", "oops.. something went wrong");
            }
        });
    }

    public ItemService(PersistenceService persist) {
        this.persist = persist;
    }
}
