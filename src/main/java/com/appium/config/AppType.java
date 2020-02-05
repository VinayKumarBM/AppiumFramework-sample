package com.appium.config;

public enum AppType {
	ANDROID_APP(".android.app."),
	ANDROID_INSTALLED_APP(".android.installed.app."),
	ANDROID_WEB(".android.web."),
	IOS_APP(".ios.app."),	
	IOS_WEB(".ios.web.");

	private String value;

	private AppType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
