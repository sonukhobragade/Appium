package com.name.test;

import org.testng.annotations.Test;

import com.name.base.BaseTestObject;
import com.name.pages.MPLLoginPage;
import com.name.pages.SecondPage;

public class Test1 extends BaseTestObject {
	
	@Test
	public void demoTest() {
		MPLLoginPage xxx = new MPLLoginPage();
		xxx.xyz();
		SecondPage yyy = xxx.abcd();
		yyy.secondMethod();
		System.out.println("In Test");
	}
}