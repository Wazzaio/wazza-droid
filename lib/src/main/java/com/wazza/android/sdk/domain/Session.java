package com.wazza.android.sdk.domain;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;

public class Session {

    private final Date sessionStart;
    private String userID;
    private String location;
    private String device;
    private String appName;
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getSessionStart() {
        return sessionStart;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

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

    public Session(String userID, String location, String device, String app, String company) {
        this.userID = userID;
        this.location = location;
        this.device = device;
        this.appName = app;
        this.companyName = company;
        this.sessionStart = Calendar.getInstance().getTime();
    }
}
