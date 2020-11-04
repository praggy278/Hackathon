package com.identifyNewBikes;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PopularUsedCars {
	static Wait wait = new Wait();
	public static ArrayList<String> popularUsedCars(WebDriver driver) throws Exception{
		Actions act = new Actions(driver);
		
		String[] data=ExcelUtils.readExcelData("Sheet1");
		String locationPopularUsedCars=data[2];
		WebElement used_cars=driver.findElement(By.linkText("Used Cars"));
		act.moveToElement(used_cars).perform();
		wait.waitImplicit(driver);
		driver.findElement(By.xpath("//*[@id='headerNewNavWrap']/div[2]/ul/li[5]/ul/li/div[1]/ul/li[1]/a")).click();
		wait.waitImplicit(driver);
		driver.findElement(By.xpath("//*[@id='popularCityList']/li[7]/a")).click();
		wait.waitImplicit(driver);
		String models_list=driver.findElement(By.xpath("//body/div[11]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[2]/div[2]/div[4]")).getText();
		ArrayList<String> models=new ArrayList<String>();
		Collections.addAll(models, models_list.split("\n"));
		System.out.println("PART 2:");
		System.out.println("Popular Models of Used Cars in "+locationPopularUsedCars+" are:-");
		for(int i =0; i< models.size(); i++){
			System.out.println((i+1)+" "+models.get(i));
		}
		return models;
	}

}
