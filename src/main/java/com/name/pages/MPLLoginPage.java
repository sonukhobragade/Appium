package com.name.pages;

import org.openqa.selenium.WebElement;

import com.name.base.BasePageObject;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class MPLLoginPage extends BasePageObject {

	@AndroidFindBy()
	public WebElement abc;
	
	public MPLLoginPage xyz() {
		click();
		System.out.println("Second Page xyz");
		return this;
	}
	
	public SecondPage abcd() {
		System.out.println("Second Page abcd");
		return new SecondPage();
	}
}