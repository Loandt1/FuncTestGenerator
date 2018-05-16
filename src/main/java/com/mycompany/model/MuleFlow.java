package com.mycompany.model;

public class MuleFlow {
	private String name;
	private String docid;
	private MulePayload payload;
	private MuleHttpListener httpListener;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public MulePayload getPayload() {
		return payload;
	}
	public void setPayload(MulePayload payload) {
		this.payload = payload;
	}
	public MuleHttpListener getHttpListener() {
		return httpListener;
	}
	public void setHttpListener(MuleHttpListener httpListener) {
		this.httpListener = httpListener;
	}
}
