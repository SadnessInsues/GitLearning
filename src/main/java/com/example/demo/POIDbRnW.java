package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.*;
public class POIDbRnW  {

	    private static String[] columns = {"Engine", "Speed", "Year", "Color"};
	    private static List<Car> cars =  new ArrayList<>();

		// Initializing Car data to insert into the excel file
	    static {
	    	System.out.println("Reading entries...");
	        cars.add(new Car("Diesel",190,1999,"Red" ));

	        cars.add(new Car("Fuel",140,1961,"Black"));

	        cars.add(new Car("Electric",250,2019,"White")); 
	     if (cars!=null) {
	    	 System.out.println("Read successful!");;
	     }
	    }
	    
	    public static void main(String[] args) throws IOException, InvalidFormatException {
	        // Create a Workbook
	        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

	        /* CreationHelper helps us create instances of various things like DataFormat, 
	           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	        CreationHelper createHelper = workbook.getCreationHelper();

	        // Create a Sheet
	        Sheet sheet = workbook.createSheet("Car");

	        // Create a Font for styling header cells
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.BLACK.getIndex());

	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);

	        // Create a Row
	        Row headerRow = sheet.createRow(0);

	        // Create cells
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        // Create Other rows and cells with Cars data
	        int rowNum = 1;
	        for(Car car: cars) {
	            Row row = sheet.createRow(rowNum++);

	            row.createCell(0)
	                    .setCellValue(car.getEngine());

	            row.createCell(1)
	                    .setCellValue(car.getSpeed());

	            row.createCell(2)
	            		.setCellValue(car.getYear());
	            

	            row.createCell(3)
	                    .setCellValue(car.getColor());
	        }

			// Resize all columns to fit the content size
	        for(int i = 0; i < columns.length; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write the output to a file
	        System.out.println("Writing to file!");
	        FileOutputStream fileOut = new FileOutputStream("/home/levsgordejevs/Desktop/poi-generated-file.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();

	        // Closing the workbook
	        workbook.close();
	    }
	   public void gitTest() {
		   /* This here is a test of version control */
	   }

	}


