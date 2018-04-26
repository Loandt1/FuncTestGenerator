package com.mycompany.generator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        Map<Integer,JsonObject> inputLst = new HashMap<Integer,JsonObject>();
        Map<Integer,JsonObject> outputLst = new HashMap<Integer,JsonObject>();
        for(Sheet sheet: workbook) {

            if (sheet.getSheetName().matches("Input")){
            	DataFormatter dataFormatter = new DataFormatter();
            	Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.rowIterator();
            	Map<Integer,String> keys = new HashMap<Integer,String>();
            	
                while (rowIterator.hasNext()) {

                    org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                    //first row, find keys
                    if (row != null && row.getRowNum() == 0){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                    	
                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		keys.put(new Integer(cell.getColumnIndex()), cellValue);
                    	}
                    }
                    //next rows, find values
                    if (row != null && row.getRowNum() > 0){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                    	
                    	JsonObject inputObject = new JsonObject();            	
                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		int cellNumber = cell.getColumnIndex();
                    		inputObject.addProperty(keys.get(cellNumber), cellValue);
                    	}
                    	
                    	inputLst.put(new Integer(row.getRowNum()), inputObject);
                    }
                }
            }else if (sheet.getSheetName().matches("Output")){
            	DataFormatter dataFormatter = new DataFormatter();
            	Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.rowIterator();
            	Map<Integer,String> keys = new HashMap<Integer,String>();
                while (rowIterator.hasNext()) {
                    org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                    //keys
                    if (row != null && row.getRowNum() == 0){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		keys.put(new Integer(cell.getColumnIndex()), cellValue);
                    	}
                    }
                    if (row != null && row.getRowNum() > 0){
                    	Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                    	
                    	JsonObject outputObject = new JsonObject();            	
                    	while (cellIterator.hasNext()) {
                    		Cell cell = cellIterator.next();
                    		String cellValue = dataFormatter.formatCellValue(cell);
                    		int cellNumber = cell.getColumnIndex();
                    		outputObject.addProperty(keys.get(cellNumber), cellValue);
                    	}
                    	outputLst.put(new Integer(row.getRowNum()), outputObject);
                    }
                }
            }
        }
        int maxRow = inputLst.size() > outputLst.size() ? inputLst.size() : outputLst.size();
        for (int i = 0 ; i <= maxRow; i++){
        	
        	if (inputLst.containsKey(new Integer(i)) && outputLst.containsKey(new Integer(i))){
        		TestCase t = new TestCase();
        		t.setInput(inputLst.get(new Integer(i)));
        		t.setOutput(outputLst.get(new Integer(i)));
        		results.add(t);
        	}
        }
        
        // Closing the workbook
        workbook.close();
        return results;
    }
}
