package com.mycompany.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;

import com.mycompany.model.MuleApp;
import com.mycompany.model.MuleFlow;
import com.sun.media.sound.InvalidFormatException;

public class Asen {

	private String sourceCodeProj = "";
	private String destTestProj = "";
	private String testCaseProj = "";
	public static Asen newInstance() {
		return new Asen();
	}
	
	public void init(String filePath) {
		this.sourceCodeProj = filePath + "/src/main/app";
		this.destTestProj = filePath + "/src/test/java/";
		this.testCaseProj = filePath + "src/test/resources/";
	}
	/**
	 *
	 * LOANDT Apr 17, 2018
	 * @return
	 */
	private List<String> getListFiles(){
		List<String> arr = new ArrayList<String>();
		File folder = new File(this.sourceCodeProj);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String filename = listOfFiles[i].getName();
				int index = filename.lastIndexOf(".");
				if ("xml".equals(filename.substring(index+1))) {
					arr.add(filename);
				}
			}
		}
		return arr;
	}
	private List<String> getTestCases(String fileSource) throws InvalidFormatException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException{
		ExcelReader reader = ExcelReader.newInstanc();
		return reader.read(fileSource);
	}
	
	public void generate() throws InvalidFormatException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException{
		List<String> lstFile = this.getListFiles();
		for (String f : lstFile){
			DomParser parser = new DomParser(this.sourceCodeProj+"/" + f);
			MuleApp app = parser.parseFromXml();

			System.out.println("List flow size " + app.getListFlows().size());
			for (int i=0; i<app.getListFlows().size(); i++) {
				MuleFlow flow = app.getListFlows().get(i);
				String fileTest = this.testCaseProj + flow.getName()+".xlsx";
				
				this.getTestCases(fileTest);
				CodeGenerator generator = CodeGenerator.newInstance();
				generator.genTestForMuleApp(flow,this.destTestProj);
			}
			
		}
	}
	 

}
