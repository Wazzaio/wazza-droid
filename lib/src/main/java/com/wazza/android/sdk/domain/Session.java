package com.wazza.android.sdk.domain;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class Session {

    private final Date sessionStart;
    public String appName;
    public String companyName;

    public String serialize() {
        // Serialize this class into a JSON string using GSON
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Session create(String serializedData) {
        // Use GSON to instantiate this class using the JSON representation of the state
        Gson gson = new Gson();
        return gson.fromJson(serializedData, Session.class);
    }

    public Session(String app, String company){
        this.appName = app;
        this.companyName = company;
        this.sessionStart = Calendar.getInstance().getTime();
    }
}
