package com.identifyNewBikes;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingExcelFile {
	
	public static XSSFWorkbook workbook;
	public static void writeExcel(ArrayList<String> arr) {
		workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Upcoming Honda Bikes");
		
		Row row1 = sheet.createRow(0);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue("Upcoming Honda Bikes");
		
		int count = 0;
		for(int i = 1;i<=arr.size();i++) {
			
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(arr.get(count++));
		}
		
		try {
			
			FileOutputStream file = new FileOutputStream("Bikes.xlsx");
			workbook.write(file);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
