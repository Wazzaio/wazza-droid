package com.wazza.android.sdk.domain;

import android.location.Location;

import com.google.gson.Gson;
import com.wazza.android.sdk.Wazza;
import com.wazza.android.sdk.service.SecurityService;
import com.wazza.android.sdk.service.Util;

import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Purchase {

    private String id;
    private String userId;
    private String appName;
    private String itemId;
    private double price;
    private String time = new SimpleDateFormat("EEE, dd, MMM yyyy HH:mm:ss ZZZ").format(Calendar.getInstance().getTime());
    private Location location;
    private Device deviceInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Device getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(Device deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    private JSONObject locationToJson(){
        JSONObject locationJson = new JSONObject();
        try {
            //TODO: add relevant fields.
            locationJson.put("latitude", location.getLatitude());
            locationJson.put("longitude", location.getLongitude());
        }
        catch (Exception e){
            //tba
        }
        return locationJson;
    }

    public JSONObject toJson() {
        JSONObject purchase = new JSONObject();

        try {
            purchase.put("id", id);
            purchase.put("userId", userId);
            purchase.put("appName", appName);
            purchase.put("itemId", itemId);
            purchase.put("price", price);
            purchase.put("time", time);
            purchase.put("location", locationToJson());
            purchase.put("device", deviceInfo.deviceToJson());
        }
        catch (Exception e){
            //tba
        }
        return purchase;
    }

    public void setFromItem(Item item) {
        setItemId(item.getId());
        setAppName(Wazza.appName);
        setPrice(item.getCurrency().getValue());
        setUserId(Wazza.username);
        //setDeviceInfo();
        //setLocation();

        setId(SecurityService.hash(appName + itemId + time + deviceInfo.hashCode()).toString());
    }

    public String serialize() {
        // Serialize this class into a JSON string using GSON
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Purchase create(String serializedData) {
        // Use GSON to instantiate this class using the JSON representation of the state
        Gson gson = new Gson();
        return gson.fromJson(serializedData, Purchase.class);
    }
}
