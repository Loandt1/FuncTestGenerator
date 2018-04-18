package com.mycompany.model;

public class MuleHttpListener {
	private String name;
	private String path;
	private String allowedMethod;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAllowedMethod() {
		return allowedMethod;
	}
	public void setAllowedMethod(String allowedMethod) {
		this.allowedMethod = allowedMethod;
	}
}
