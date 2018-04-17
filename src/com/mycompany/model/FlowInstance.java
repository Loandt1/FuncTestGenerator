/**
 * LOANDT Apr 17, 2018
 */
package com.mycompany.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author toann
 *
 */
public class FlowInstance {
	private String listener = "";
	private String flowName  ="";
	private List<String> content = new ArrayList<String>();

	/**
	 * @return the listener
	 */
	public String getListener() {
		return listener;
	}
	/**
	 * @param listener the listener to set
	 */
	public void setListener(String listener) {
		this.listener = listener;
	}
	/**
	 * @return the flowName
	 */
	public String getFlowName() {
		return flowName;
	}
	/**
	 * @param flowName the flowName to set
	 */
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	/**
	 * @return the content
	 */
	public List<String> getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(List<String> content) {
		this.content = content;
	}
	/**
	 * LOANDT Apr 17, 2018
	 */
	public void extractContent() {
		for (String item : this.getContent()) {
			if (item.indexOf("<flow") >= 0 ) {
				this.extractFlowName(item);
			}
			if (item.indexOf("<http:listener") >= 0 ) {
				this.extractFlowListener(item);
			}
		}
	}
	private void extractFlowName(String flowtag) {
		String[] lst = StringUtils.split(flowtag," ");
		for (String s : lst) {
			if(s.indexOf("name=\"") >=0 ){
				String name = StringUtils.substringBetween(s, "\"");
				this.flowName = name;
			}
		}
	}
	private void extractFlowListener(String flowtag) {
		String[] lst = StringUtils.split(flowtag," ");
		for (String s : lst) {
			if(s.indexOf("path=\"") >=0 ){
				String name = StringUtils.substringBetween(s, "\"");
				this.listener = name;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlowInstance [listener=" + listener + ", flowName=" + flowName + ", content=" + content + "]";
	}

}
