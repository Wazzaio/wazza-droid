package com.wazza.android.sdk.service;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wazza.android.sdk.Wazza;
import com.wazza.android.sdk.domain.Item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private PersistenceService persist;

    //server endpoints
    private static final String ENDPOINT_ITEM_LIST = "items/";
    private static final String ENDPOINT_ITEM_DETAILED_LIST = "items/details/";
    private static final String ENDPOINT_DETAILS = "item/";


    private List<Item> itemList = new ArrayList<Item>();

    public void addItem(JSONObject item){
        itemList.add(Item.create(item.toString()));
    }

    public void fetchItems(){
        RestClient.get(ENDPOINT_ITEM_DETAILED_LIST + Wazza.appName, Util.constructRequestHeader(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray results) {
                try {
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject object = results.getJSONObject(i);
                        addItem(object);
                    }
                    persist.storeItem(itemList);
                } catch (Exception e) {
                    //tba
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode,
                                  org.apache.http.Header[] headers,
                                  java.lang.Throwable e,
                                  org.json.JSONObject errorResponse) {
                System.err.println("oops.. something went wrong");
            }
        });
    }

    public ItemService(PersistenceService persist){
        this.persist = persist;
    }
}
