package com.name.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTestObject {

	static protected AppiumDriver<AndroidElement> driver;
	private static AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
	/*static Logger log;
	
	@BeforeSuite
	protected void setLogger() {
		String projectPath = System.getProperty("user.dir");
		String log4jConfPath = projectPath + "/src/main/resources/Logger/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}*/

	@BeforeTest(alwaysRun = true)
	@Parameters("device")
	protected void methodSetUp(String device) {
		startAppiumServer();
		driver = AppiumDriverUtils.mobileConfiguration(device);
	}

	@AfterTest(alwaysRun = true)
	protected void methodTearDown() {
		driver.quit();
		stopAppiumServer();
	}

	private void startAppiumServer() {
		service.start();
	}
	
	private void stopAppiumServer() {
		service.stop();
	}	
}