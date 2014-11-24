package io.wazza.android.sdk.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Session {

    private final Date sessionStart;
    private Date sessionEnd;
    private String userID;
    private Location location;
    private String device;
    private ArrayList<Purchase> purchases = new ArrayList<>();

    public Date getSessionStart() {
        return sessionStart;
    }

    public Date getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
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

    public Session(String userID, Location location, String device) {
        this.userID = userID;
        this.location = location;
        this.sessionStart = Calendar.getInstance().getTime();
        this.device = device;
    }
}
