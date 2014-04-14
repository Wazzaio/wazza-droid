package com.wazza.android.sdk.domain;

import java.util.UUID;

public class Device {

	private String manufacturer;
	private String model;
	
	private String buildCodename;
	private String buildIncremental;
	private String buildRelease;
		
	private String tmDevice;
	private String simSerial;
	
	private String androidID;
	private UUID deviceUUID;
	
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBuildCodename() {
		return buildCodename;
	}

	public void setBuildCodename(String buildCodename) {
		this.buildCodename = buildCodename;
	}

	public String getBuildIncremental() {
		return buildIncremental;
	}

	public void setBuildIncremental(String buildIncremental) {
		this.buildIncremental = buildIncremental;
	}

	public String getBuildRelease() {
		return buildRelease;
	}

	public void setBuildRelease(String buildRelease) {
		this.buildRelease = buildRelease;
	}

	public String getTmDevice() {
		return tmDevice;
	}

	public void setTmDevice(String tmDevice) {
		this.tmDevice = tmDevice;
	}

	public String getSimSerial() {
		return simSerial;
	}

	public void setSimSerial(String tmSerial) {
		this.simSerial = tmSerial;
	}

	public String getAndroidID() {
		return androidID;
	}

	public void setAndroidID(String androidID) {
		this.androidID = androidID;
	}

	public UUID getDeviceUUID() {
		return deviceUUID;
	}

	public void setDeviceUUID(UUID deviceUUID) {
		this.deviceUUID = deviceUUID;
	}
	
}
