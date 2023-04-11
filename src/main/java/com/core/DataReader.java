package com.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader 
{
	//Return property value from config.properties file
	public static Properties getConfigValue() 
	{
		Properties propertyValue = null;
		
		try 
		{
			propertyValue = new Properties();
			FileInputStream inputFile = new FileInputStream("./src/test/resources/config.properties");
			propertyValue.load(inputFile);
			
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return propertyValue;
	}
	
	//Read data from external spread sheet
	public static String [][] getSpreadSheetData(String workbookName)
	{
		String [][] dataSet = null;
		
		try 
		{
			String dataSheetFilePath = "./datasheets/"+workbookName+".xlsx";
			File dataSheet = new File(dataSheetFilePath);
			FileInputStream fileInput = new FileInputStream(dataSheet);

			XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(1).getLastCellNum();
			
			dataSet = new String [totalRows][totalColumns];
			
			for(int row=1; row<=totalRows; row++) 
			{
				XSSFRow rowData = sheet.getRow(row);
				
				for(int column=0; column<totalColumns; column++) 
				{
					XSSFCell columnData = rowData.getCell(column);
					String cellVal = columnData.getStringCellValue();
					dataSet[row-1][column] = cellVal;
				}
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}catch (EncryptedDocumentException e) {
			e.printStackTrace();
		}
		
		return dataSet;
	}
}
