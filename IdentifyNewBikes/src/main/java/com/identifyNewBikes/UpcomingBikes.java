package com.identifyNewBikes;

import java.util.*;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UpcomingBikes {
	
	public static Wait wait=new Wait();
	public static ArrayList<String> findingUpcomingBikes(WebDriver driver) throws Exception{
		
		//Navigating to the New Bikes Section.
		Actions act = new Actions(driver);
		
		String[] data=ExcelUtils.readExcelData("Sheet1");
		String upcomingBikesManufacturer=data[3];
		WebElement new_bikes=driver.findElement(By.linkText("New Bikes"));
		act.moveToElement(new_bikes).perform();
		//Clicking the Upcoming bikes Section
		wait.waitImplicit(driver);
		driver.findElement(By.linkText("Upcoming Bikes")).click();
		wait.waitImplicit(driver);
		//Navigating to the Upcoming Honda Bikes page
		driver.navigate().to("https://www.zigwheels.com/upcoming-Honda-bikes");
		wait.waitImplicit(driver);
		//Clicking View More bikes
		driver.findElement(By.xpath("//*[@id='carModels']/ul/li[16]/span")).click();
		
		//Storing the information of all the Upcoming Honda Bikes
		wait.waitImplicit(driver);
		String bikeModels =driver.findElement(By.xpath("//*[@id='carModels']/ul")).getText();
		
		//Storing the info in an ArrayList
		ArrayList<String> bikeModelsElements = new ArrayList<String>();
		Collections.addAll(bikeModelsElements,bikeModels.split("\n"));
		
		//Sorting the information according to names,dates and prices
		ArrayList<String> NameList = new ArrayList<String>();
		ArrayList<String> DateList = new ArrayList<String>();
		ArrayList<String> PriceList =new ArrayList<String>();
		String[] arr = null;
		for(int i = 0 ; i < bikeModelsElements.size(); i++){
			String s = bikeModelsElements.get(i);
			if(s.contains("Honda")){
				NameList.add(s);
			}
			if(s.contains("Rs. ")){
				arr = s.split(" ");
				PriceList.add(arr[1]);
			}
			if(s.contains("Exp. Launch")){
				DateList.add(s);
			}
		}
		//Creating an arraylist which will add only the upcoming bikes under 4 Lakhs
		wait.waitImplicit(driver);
		ArrayList<String> upcomingBikes = new ArrayList<String>();
		for(int i=0; i<NameList.size();i++){
			String temp = NameList.get(i);
			double price = Double.parseDouble(PriceList.get(i));
			String info=temp+"  "+PriceList.get(i)+" Lakh  "+DateList.get(i);
			if(info.contains(temp)){
				if(Double.compare(price, 4d)<0){
					upcomingBikes.add(info);
				}
			}
		}
		//Printing them
		System.out.println("PART 3:");
		System.out.println("Upcoming "+upcomingBikesManufacturer+" Bikes Below 4 Lakhs are as follows:");
		for(int i = 0 ; i < upcomingBikes.size(); i++){
			System.out.println(upcomingBikes.get(i));
		}
		return upcomingBikes;
		
	}

}
