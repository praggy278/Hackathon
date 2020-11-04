package com.identifyNewBikes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup {
	//Declaring the static variables
	private static WebDriver driverChrome;
	private static WebDriver driverFirefox;
	private static WebDriver driverOpera;
	
	//Method for creating and returning the Chrome Driver
	public WebDriver getChromeDriver() {
		String chromeDriverPath = "\\Drivers\\chromedriver.exe";
		String path=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path+chromeDriverPath);
		
		driverChrome= new ChromeDriver();
		return driverChrome;
	}
	
	//Method for creating and returning the Firefox Driver
	public WebDriver getFirefoxDriver() {
		String firefoxDriverPath="\\Drivers\\geckodriver.exe";
		String path= System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", path+firefoxDriverPath);
		
		driverFirefox = new FirefoxDriver();
		return driverFirefox;
	}
	//Method for creating and returning the Opera Driver
	public WebDriver getOperaDriver() {
		String operaDriverPath="\\Drivers\\operadriver.exe";
		String path= System.getProperty("user.dir");
		System.setProperty("webdriver.opera.driver", path+operaDriverPath);
		
		driverOpera = new OperaDriver();
		return driverOpera;
	}

}