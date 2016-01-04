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
import android.os.Build;
import android.telephony.TelephonyManager;

import io.wazza.android.sdk.domain.Device;
import io.wazza.android.sdk.service.Util;

import java.util.UUID;

public class DeviceService {

    private Device device;
    private Context context;
    private TelephonyManager tm;


    public Device getDevice() {
        return device;
    }

    public void setDeviceModel() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        device.setModel(Util.capitalize(model));
        device.setManufacturer(Util.capitalize(manufacturer));
    }

    public String getDeviceModel() {
        if (device.getModel().startsWith(device.getManufacturer()))
            return device.getModel();
        else
            return device.getManufacturer() + " " + device.getModel();
    }

    public void setAndroidVersion() {
        String codename = android.os.Build.VERSION.CODENAME;
        String build = android.os.Build.VERSION.INCREMENTAL;
        String release = android.os.Build.VERSION.RELEASE;

        device.setBuildCodename(codename);
        device.setBuildIncremental(build);
        device.setBuildRelease(release);
    }

    public String getAndroidVersion() {
        return "\ncode name: " + device.getBuildCodename() +
                "\nbuild: " + device.getBuildIncremental() +
                "\nrelease: " + device.getBuildRelease();
    }

    public void setDeviceID() {
        device.setTmDevice(tm.getDeviceId());
        device.setSimSerial(tm.getSimSerialNumber());
        //Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
        device.setAndroidID(android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID));
        device.setDeviceUUID(new UUID(device.getAndroidID().hashCode(), ((long) device.getTmDevice().hashCode() << 32) | device.getSimSerial().hashCode()));
    }

    public DeviceService(Context context) {
        this.context = context;
        device = new Device();
        tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        setAndroidVersion();
        setDeviceID();
        setDeviceModel();

    }

}
