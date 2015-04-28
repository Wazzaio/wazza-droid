package io.wazza.android.sdk.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import io.wazza.android.sdk.Wazza;
import io.wazza.android.sdk.domain.Location;
import io.wazza.android.sdk.domain.Session;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SessionService {

    //server endpoints
    private static final String ENDPOINT_AUTH = "auth";
    private static final String ENDPOINT_SESSION_NEW = "session/new";

    private static Session currentSession;

    public static long getSessionLength(Date originalTime) {
        return Calendar.getInstance().getTime().getTime() - originalTime.getTime();
    }

    private static String constructURL(String URL) {
        return URL + "/";
    }

    public static void initSession(Context context, PersistenceService persist) {//add location and device
        GPSTracker gps = new GPSTracker(context);
        Location location = new Location(gps.getLatitude(), gps.getLongitude());
        currentSession = new Session(persist.getUser(), location, persist.getDevice().deviceToJson().toString()); //wazza

        RequestParams requestParams = RestClient.constructRequestHeader();

        RestClient.post(constructURL(ENDPOINT_AUTH), requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.v("WazzaSDK", "Authentication successful.");
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

    public static void resumeSession(Context context, PersistenceService persist) {
        // new session
        if(currentSession == null)
            initSession(context, persist);
        // 10 seconds already gone. close previous session and start a new one.
        else if(currentSession.getSessionEnd() != null && getSessionLength(currentSession.getSessionEnd()) > 10){
            closeSession();
            initSession(context, persist);
        }
        // else: do nothin'.
    }

    public static void closeSession() {

        if (currentSession == null)
            return; //silent fail

        /*
        JsonObject json = new JsonObject();
        json.addProperty("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(Calendar.getInstance().getTime()));
        json.addProperty("hash", currentSession.hashCode());
        */

        currentSession.setSessionEnd(Calendar.getInstance().getTime());

        RequestParams requestParams = RestClient.constructRequestHeader();
        requestParams.put("content", currentSession.serialize());

        Log.v("WazzaSDK", currentSession.serialize());

        RestClient.post(constructURL(ENDPOINT_SESSION_NEW), requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //save in persist
                Log.v("WazzaSDK", "New Session recorded.");
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

}
