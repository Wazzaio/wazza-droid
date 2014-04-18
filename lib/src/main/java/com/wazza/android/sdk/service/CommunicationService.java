package com.wazza.android.sdk.service;

import android.content.Context;

import org.json.*;
import com.loopj.android.http.*;
import com.wazza.android.sdk.domain.Communication;
import com.wazza.android.sdk.domain.Item;
import com.wazza.android.sdk.domain.Purchase;

import java.net.URL;

public class CommunicationService {

    private Context context;
    private PersistentCookieStore cookieStore;
    private PersistenceService persist;

    //server endpoints
    private static final String ENDPOINT_AUTH = "auth";
    private static final String ENDPOINT_ITEM_LIST = "items/";
    private static final String ENDPOINT_ITEM_DETAILED_LIST = "items/details/";
    private static final String ENDPOINT_DETAILS = "item/";
    private static final String ENDPOINT_PURCHASE = "purchase";
    private static final String ENDPOINT_SESSION_UPDATE = "session";


    private String createStringFromJson(JSONArray jsonArray){
        return null;
    }

    private RequestParams constructRequestHeader(){
        RequestParams params = new RequestParams();
        params.put("AppName", Util.appName);
        //todo: add security headers

        return params;
    }

    public void getItem(){

    }

    public void getItems(){

    }

    public void fetchItems(){
          RestClient.get(ENDPOINT_ITEM_DETAILED_LIST + Util.appName, constructRequestHeader(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray results) {
                try {
                    for(int i = 0; i < results.length(); i++){
                        JSONObject object = results.getJSONObject(i);
                        persist.createItemFromJson(object);
                    }
                }
                catch (Exception e){
                    //tba
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode,
                                  org.apache.http.Header[] headers,
                                  java.lang.Throwable e,
                                  org.json.JSONObject errorResponse){
                System.err.println("oops.. something went wrong");
            }
        });
    }

    public void makePurchase(Item item){

        Purchase purchase = new Purchase();
        purchase.setFromItem(item);

        RequestParams params = constructRequestHeader();
        params.put("payload", purchase);

        RestClient.post(ENDPOINT_PURCHASE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                //tba
            }
        });
    }



	public void getSession() throws JSONException {
        RestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray timeline) {
                // Pull out the first event on the public timeline
                /*
                JSONObject firstEvent = timeline.get(0);
                String tweetText = firstEvent.getString("text");

                // Do something with the response
                System.out.println(tweetText);
                */
            }
        });
    }
	
	public void postSession() throws JSONException {
        RestClient.post("session", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray timeline) {
                // Pull out the first event on the public timeline
                /*
                JSONObject firstEvent = timeline.get(0);
                String tweetText = firstEvent.getString("text");

                // Do something with the response
                System.out.println(tweetText);
                */
            }
        });
    }

	public void track(){
		
	}
	
	public void onDestroy(){
		
	}

    public CommunicationService(Context context, PersistenceService persistenceService){
        this.context = context;
        this.persist = persistenceService;
        //init

        cookieStore = new PersistentCookieStore(context);
        RestClient.getClient().setCookieStore(cookieStore);
    }
}
