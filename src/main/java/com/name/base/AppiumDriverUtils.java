package com.name.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumDriverUtils {

	static Ini ini = null;
	static protected AppiumDriver<AndroidElement> driver;
	
	protected static AppiumDriver<AndroidElement> getDriver() {
		return driver;
	}
	
	protected static AppiumDriver<AndroidElement> mobileConfiguration(String device) {

		String projectPath = System.getProperty("user.dir");
		String configFilePath = "/src/main/resources/Properties/";
		String apkFilePath = "/src/main/resources/APK/";
		String configFileName = "config.ini";
		
		try {
			ini = new Ini(new FileReader(projectPath + configFilePath + configFileName));
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Section commonSection = ini.get("common");
		Ini.Section section = ini.get(device);
		
		String url = commonSection.get("appiumURL");
		String platformName = section.get("platformName");
		
		String androidVersion = section.get("version");
		String deviceName = section.get("deviceName");
		String udid = section.get("udid");
		String appPackage = section.get("appPackage");
		String appActivity = section.get("appActivity");
		String apkFileName = commonSection.get("apkFileName");
		String freshInstall = commonSection.get("freshInstall");
		
		File apk = new File(projectPath + apkFilePath + apkFileName);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		capabilities.setCapability(MobileCapabilityType.VERSION, androidVersion);
		capabilities.setCapability(MobileCapabilityType.UDID, udid);
		
		if(freshInstall.equalsIgnoreCase("Yes"))
			capabilities.setCapability(MobileCapabilityType.APP, apk);
		
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		
		try {
			driver = new AndroidDriver<>(new URL(url), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}