package com.wazza.android.sdk.service;

import android.app.DownloadManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wazza.android.sdk.Wazza;
import com.wazza.android.sdk.domain.Session;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SessionService {

    //server endpoints
    private static final String ENDPOINT_AUTH = "auth";
    private static final String ENDPOINT_SESSION_NEW = "session/new";
    private static final String ENDPOINT_SESSION_END = "session/end";

    private Session currentSession;

    public static long getSessionLenght(Date originalTime){
        return Calendar.getInstance().getTime().getTime() - originalTime.getTime();
    }

    private String constructURL(String URL){
        return URL + "/" + Wazza.companyName + "/" + Wazza.appName;
    }

    public void initSession(){
        currentSession = new Session(Wazza.appName, Wazza.companyName);//wazza.

        RequestParams requestParams = Util.constructRequestHeader();
        requestParams.put("content", currentSession.serialize());

        RestClient.post(constructURL(ENDPOINT_SESSION_NEW), requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray results) {
                //save in persist
                Log.v("WazzaSDK", "Session Opened.");
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

    //TODO:
    public void resumeSession(){
        //currentSession = persist.getCurrentSession();
        //currentSession.date = new Date();
    }

    public void closeSession(){

        if(currentSession == null)
            return; //silent fail

        JsonObject json = new JsonObject();
        json.addProperty("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(Calendar.getInstance().getTime()));
        json.addProperty("hash", currentSession.hashCode());

        RequestParams requestParams = Util.constructRequestHeader();
        requestParams.put("content", json);

        RestClient.post(constructURL(ENDPOINT_SESSION_END), requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray results) {
                currentSession = null;
                Log.v("WazzaSDK", "Session Closed.");
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

    public SessionService(){

    }
}
