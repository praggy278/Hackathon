package com.identifyNewBikes;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelUtils {        

	public static String[] excelInputData=new String[6];
	private static XSSFWorkbook workbook;
	
	public static String[] readExcelData(String sheetName) throws Exception {
		
		String workingDir=System.getProperty("user.dir");
		String excelFilePath=workingDir+File.separator+"InputDetails.xlsx";
		FileInputStream excelFile=new FileInputStream(excelFilePath);
		workbook = new XSSFWorkbook(excelFile);
		XSSFSheet sheet=workbook.getSheet(sheetName);
		XSSFRow row;
		XSSFCell cell;
		row=sheet.getRow(0);
		for(int i=0;i<6;i++)
		{
			cell=row.getCell(i);
			excelInputData[i]=String.valueOf(cell);
		}
		return excelInputData;
		
	}

}

