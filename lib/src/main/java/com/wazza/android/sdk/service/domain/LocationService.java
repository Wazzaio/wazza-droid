package com.wazza.android.sdk.service.domain;

import android.content.Context;

import com.wazza.android.sdk.service.GPSTracker;

public class LocationService {

    private GPSTracker gps;
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return String.valueOf(getLatitude()) + ";" + String.valueOf(getLongitude());
    }

    public void setLocation() {
        if (gps.canGetLocation()) {
            setLatitude(gps.getLatitude());
            setLongitude(gps.getLongitude());
        } else {
            // can't get location because GPS or Network are not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    public LocationService(Context context) {
        gps = new GPSTracker(context);
        setLocation();
    }


}
