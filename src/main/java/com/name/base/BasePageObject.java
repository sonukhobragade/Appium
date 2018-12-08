package com.name.base;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class BasePageObject {

	protected AppiumDriver<AndroidElement> driver;
	protected WebDriverWait wait;

	private static final int DEFAULT_TIMEOUT = 30;
	
	protected BasePageObject() {
		driver = AppiumDriverUtils.getDriver();
		wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
	}

	protected void click() {
		System.out.println("In Click Method");
		driver.findElementByXPath("//android.widget.EditText[@text = 'Mobile Number']").sendKeys("9028420303");;
	}

	protected void click(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).click();
	}

	protected void clickAccessibilityId(String id) {
		driver.findElementByAccessibilityId(id);
	}

	protected void clearText(By locator) {
		driver.findElement(locator).clear();
	}

	protected void closeApp() {
		driver.closeApp();
	}

	protected void enter(By locator, String text) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.findElement(locator).sendKeys(text);
			try {
			driver.hideKeyboard();
			}catch (Exception e) {
		}
	}

	protected void getText(By locator) {
		driver.findElement(locator).getText();
	}

	protected void getElementAttributeValue(By locator, String attribute_name) {
		driver.findElement(locator).getAttribute(attribute_name);
	}

	protected void launchApp() {
		driver.launchApp();
	}

	protected void navigateBack() {
		driver.navigate().back();
	}

	protected void pressDelete() {
		driver.getKeyboard().sendKeys(Keys.DELETE);
	}

	protected void selectByValue(By locator, String text) {
		WebElement element = driver.findElement(locator);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	protected void verifyElementByText(By locator, String expected_text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String actual = driver.findElement(locator).getText();
		assertEquals(actual, expected_text);
	}

	protected void verifyElementAttributeValue(By locator, String attribute_name, String attribute_value) {
		String actual_value = driver.findElement(locator).getAttribute(attribute_name);
		assertEquals(actual_value, attribute_value);
	}

	protected void verifyTextPresentOnPage(String text) {
		boolean text_present = driver.getPageSource().contains(text);
		assertEquals(text_present, true);
	}

	protected void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	protected void waitUntilElementVisible(By locator) {
		ExpectedCondition<WebElement> element_visible = ExpectedConditions.visibilityOfElementLocated(locator);
		wait.until(element_visible);
	}

	protected void waitUntilInvisiblityOfElementWithText(By locator, String text) {
		wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
	}
}