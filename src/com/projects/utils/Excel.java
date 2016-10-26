package com.projects.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;

public class Excel {
		
		public static String getCellValue(String path2excel, String SheetName, int rowNum, int cellNum)
		{
			//To get the Value present in a cell of the Excel sheet
			String cellValue = "";
			
			try
			{
				FileInputStream fis = new FileInputStream(path2excel);
				Workbook wb = WorkbookFactory.create(fis);
				
				cellValue = wb.getSheet(SheetName).getRow(rowNum).getCell(cellNum).toString();
			}
			catch(Exception e)
			{
				Reporter.log(TimeStamp.getSimpleTimeStampt()+" Exception found while trying to get Cell Content from the Excel.\n"+e.getMessage(), true);
			}
			
			return cellValue;
		}
		
		public static int getRowCount(String path2excel, String SheetName)
		{
			//To find the number of rows present in the excel's particular sheet
			int rowCount = 0;
			
			try
			{
				FileInputStream fis = new FileInputStream(path2excel);
				Workbook wb = WorkbookFactory.create(fis);
				
				rowCount = wb.getSheet(SheetName).getLastRowNum();		
			}
			catch (Exception e)
			{
				Reporter.log(TimeStamp.getSimpleTimeStampt()+" Exception found while trying to find the number of rows present in the excel sheet."+e.getMessage(), true);
			}
			
			return rowCount;
			
		}
		

}
