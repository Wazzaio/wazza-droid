package io.wazza.android.sdk.domain;

import com.google.gson.Gson;

public class Location {

    private Double latitude;
    private Double longitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String serialize() {
        // Serialize this class into a JSON string using GSON
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Location create(String serializedData) {
        // Use GSON to instantiate this class using the JSON representation of the state
        Gson gson = new Gson();
        return gson.fromJson(serializedData, Location.class);
    }

    public Location(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
