package com.identifyNewBikes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSignIn {
	
	public static Wait wait=new Wait();
	public static void googleSignIn(WebDriver driver) {
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//*[@id='gb_70']")).click();
	}
	
	public static void fillLoginForm(String email,String pass,WebDriver driver) {
		driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys(email);
		wait.waitImplicit(driver);
		driver.findElement(By.xpath("//*[@id='identifierNext']/div/button/div[2]")).click();
		wait.waitImplicit(driver);
		if(validateEmail(email)) {
			driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys(pass);
			driver.findElement(By.xpath("//*[@id='passwordNext']/div/button/div[2]")).click();
		}
		else
			getErrorMessage(driver);
	}
	
	public static void getErrorMessage(WebDriver driver) {
		System.out.println("PART 1:");
		System.out.println("Google Sign-In Error Message");
		wait.waitImplicit(driver);
		String error_msg=driver.findElement(By.xpath("//*[@id='view_container']/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div")).getText();
		wait.waitImplicit(driver);
		System.out.println(error_msg);
	}
	public static boolean validateEmail(String email) {
		int atTheRatepos=email.indexOf('@');
		int dotpos=email.indexOf('.');
		if(atTheRatepos<1 || dotpos<atTheRatepos+2 || dotpos+2>=email.length()) {
			return false;
		}
		else 
			return true;
	}

}
