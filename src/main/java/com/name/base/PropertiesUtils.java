package com.name.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	Properties prop;

	// COnstructor to load properties file
	public PropertiesUtils() {

		prop = new Properties();
		String propertiesFilePath = "src/main/resources/PropertiesFile/PropertiesFile/DataFile.properties";

		try {
			FileInputStream fis = new FileInputStream(propertiesFilePath);
			prop.load(fis);
			fis.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// Method to get locator type
	public String getValue(String Value) {

		// Retrieve value from properties file using key
		String locatorTypeAndValue = prop.getProperty(Value);
		return locatorTypeAndValue;
	}
}
