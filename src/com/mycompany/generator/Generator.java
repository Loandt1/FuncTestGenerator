package com.mycompany.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.mycompany.model.FlowInstance;

public class Generator {
	private String sourceCodeProj = "";
	private String destTestProj = "";
	public static Generator newInstance() {
		return new Generator();
	}
	public void init(String filePath, String destination) {
		this.sourceCodeProj = filePath + "\\src\\main\\app";
		this.destTestProj = destination + "\\src\\test\\java";
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
	/**
	 *
	 * LOANDT Apr 17, 2018
	 */
	public void scanCode() {
		System.out.println("SCAN CODE BEGIN");
		List<String> lstFile = this.getListFiles();
		for (String filename : lstFile) {
			String filePath = this.sourceCodeProj + "\\" + filename;
			System.out.println(filePath);
			try {
				this.readFile(filePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void readFile(String filename) throws Exception {
		String contents = FileUtils.readFileToString(new File(filename), "UTF-8");
		int firstIndex = contents.indexOf("<flow");
		int lastIndex = contents.lastIndexOf("</flow>");
		String content = contents.substring(firstIndex, lastIndex+7);
		List<FlowInstance> flows = new ArrayList<FlowInstance>();
		String[] lstFlows = content.split("(?=<)|(?<=>)");
		FlowInstance  itemFlow = null;
		for (String item : lstFlows) {
			if (item.indexOf("<flow") >= 0 ) {
				itemFlow = new FlowInstance ();
				itemFlow.getContent().add(item);
			}else if (item.indexOf("</flow>") >= 0 ) {
				flows.add(itemFlow);
				itemFlow = null;
			}else if (null != itemFlow) {
				itemFlow.getContent().add(item);
			}
		}
		for (FlowInstance item : flows) {
			item.extractContent();
			System.out.println(item.toString());
		}
	}
	/**
	 *
	 * LOANDT Apr 17, 2018
	 */
	public void generateTest() {

	}
}
