package com.tests;

import java.util.ArrayList;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.identifyNewBikes.DriverSetup;
import com.identifyNewBikes.ExcelUtils;
import com.identifyNewBikes.GoogleSignIn;
import com.identifyNewBikes.Main;
import com.identifyNewBikes.PopularUsedCars;
import com.identifyNewBikes.UpcomingBikes;
import com.identifyNewBikes.Wait;
import com.identifyNewBikes.WritingExcelFile;
import com.identifyNewBikes.WritingPropertiesFile;

public class TestNG extends Main {
	
	    //Declaration of static variables.
		public static WebDriver driver;
		public static String Url= "https://www.zigwheels.com";
		public static Scanner sc=new Scanner(System.in);
		static Wait wait = new Wait();
		
	    //Method selecting the browser depending on platform requirements.
		@BeforeClass
		public static WebDriver selectingBrowser(){ 
			System.out.println("Browser Choices: \n");
		    System.out.println("1. Google Chrome");
	        System.out.println("2. Firefox");
	        System.out.println("3. OperaDriver");
	        System.out.println("Enter your Browser choice: \r");
	        int choice = sc.nextInt();
			switch(choice){
			case 1: driver = new DriverSetup().getChromeDriver(); break;
			case 2: driver = new DriverSetup().getFirefoxDriver(); break;
			case 3: driver=  new DriverSetup().getOperaDriver(); break;
			default: System.out.println("Try again");
		    }
		return driver;
		}
		
		
		@Test		
		public static void getGoogleSignIn() throws Exception {
			//Google Sign-in
			GoogleSignIn.googleSignIn(driver);
			
			String[] data=ExcelUtils.readExcelData("Sheet1");
			String emailInvalid=data[0];
			String passwordInvalid=data[1];
			
			//Filling the Invalid details		
			GoogleSignIn.fillLoginForm(emailInvalid,passwordInvalid, driver);
			//Navigating to the official site of Zigwheels
			driver.manage().window().maximize();
			driver.get(Url);
			driver.navigate().to(Url);	
		}
		
		@Test(dependsOnMethods = "getGoogleSignIn")
		public static void getPopularUsedCars() throws Exception {
			ArrayList<String> cars = PopularUsedCars.popularUsedCars(driver);
			//Writing them in a file
			WritingPropertiesFile.writingfile(cars, "Used-Cars");
		}
		
		@Test(dependsOnMethods = "getPopularUsedCars")
		public static void getUpcomingBikes() throws Exception {
			ArrayList<String> bikes = UpcomingBikes.findingUpcomingBikes(driver);
			//Writing them
			WritingExcelFile.writeExcel(bikes);
			driver.close();
			driver.quit();
		}
		
		//Closing the Driver
		@AfterClass
		public static void closeBrowser(WebDriver driver){
		driver.close();
		driver.quit();
		}
		
}