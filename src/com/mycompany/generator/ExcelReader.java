package com.mycompany.generator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.gson.JsonObject;
import com.mycompany.model.TestCase;
import com.sun.media.sound.InvalidFormatException;

public class ExcelReader {
	public static ExcelReader newInstanc(){
		return new ExcelReader();
	}

    public static List<TestCase> read(String fileName) throws IOException, InvalidFormatException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
    	List<TestCase> results = new ArrayList<TestCase>();
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(fileName));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());            
        }

        // 2. Or you can use a for-each loop
        System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
            int numberRows = sheet.getLastRowNum();
            for (int i=0; i<numberRows; i++){
            	TestCase t = new TestCase();
            	results.add(t);
            }
            if (sheet.getSheetName().matches("Input")){
            	DataFormatter dataFormatter = new DataFormatter();
            	Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.rowIterator();
            	
                while (rowIterator.hasNext()) {
                    org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                    ArrayList<String> listProperties = new ArrayList<String>();
                    if (row.getRowNum() == 1){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		listProperties.add(cellValue);
                    		System.out.print(cellValue + "\t");
                    	}
                    }
                    if (row.getRowNum() > 1){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                    	
                    	JsonObject inputObject = new JsonObject();            	
                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		int cellNumber = cell.getColumnIndex();
                    		inputObject.addProperty(listProperties.get(cellNumber - 1), cellValue);
                    		System.out.print(cellValue + "\t");                    		
                    	}
                    	results.get(row.getRowNum() - 1).setInput(inputObject);
                    }
                }
            }else if (sheet.getSheetName().matches("Output")){
            	DataFormatter dataFormatter = new DataFormatter();
            	Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.rowIterator();
            	
                while (rowIterator.hasNext()) {
                    org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                    ArrayList<String> listProperties = new ArrayList<String>();
                    if (row.getRowNum() == 1){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		listProperties.add(cellValue);
                    		System.out.print(cellValue + "\t");
                    	}
                    }
                    if (row.getRowNum() > 1){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                    	
                    	JsonObject outputObject = new JsonObject();            	
                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		int cellNumber = cell.getColumnIndex();
                    		outputObject.addProperty(listProperties.get(cellNumber - 1), cellValue);
                    		System.out.print(cellValue + "\t");                    		
                    	}
                    	results.get(row.getRowNum() - 1).setOutput(outputObject);
                    }
                }
            }
        }

//        // 3. Or you can use a Java 8 forEach with lambda
//        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
//        workbook.forEach(sheet -> {
//            System.out.println("=> " + sheet.getSheetName());
//        });
//
//        /*
//           ==================================================================
//           Iterating over all the rows and columns in a Sheet (Multiple ways)
//           ==================================================================
//        */
//
//        // Getting the Sheet at index zero
//        Sheet sheet = workbook.getSheetAt(0);
//
//        // Create a DataFormatter to format and get each cell's value as String
//        DataFormatter dataFormatter = new DataFormatter();
//
//        // 1. You can obtain a rowIterator and columnIterator and iterate over them
//        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
//        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.rowIterator();
//        while (rowIterator.hasNext()) {
//            org.apache.poi.ss.usermodel.Row row = rowIterator.next();
//
//            // Now let's iterate over the columns of the current row
//            Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
//
//        // 2. Or you can use a for-each loop to iterate over the rows and columns
//        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
//        for (Row row: sheet) {
//            for(Cell cell: row) {
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
//
//        // 3. Or you can use Java 8 forEach loop with lambda
//        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
//        sheet.forEach(row -> {
//            row.forEach(cell -> {
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            });
//            System.out.println();
//        });

        // Closing the workbook
        workbook.close();
        return results;
    }
}
