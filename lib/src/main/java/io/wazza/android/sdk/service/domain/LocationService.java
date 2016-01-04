/*
 * wazza-droid
 * https://github.com/Wazzaio/wazza-droid
 * Copyright (C) 2013-2015  Duarte Barbosa, João Vazão Vasques
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.wazza.android.sdk.service.domain;

import android.content.Context;

import io.wazza.android.sdk.service.GPSTracker;

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
