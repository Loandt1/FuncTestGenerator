package com.mycompany.model;

import com.google.gson.JsonObject;

public class TestCase {
	private JsonObject input = new JsonObject();
	private JsonObject output = new JsonObject();
	public JsonObject getInput() {
		return input;
	}
	public void setInput(JsonObject input) {
		this.input = input;
	}
	public JsonObject getOutput() {
		return output;
	}
	public void setOutput(JsonObject output) {
		this.output = output;
	}
	public boolean isNotNull(){
		if (this.input == null && this.output ==null) return false;
		if (this.input != null){
			if (this.input.toString().length() <= 2) return false;
		}
		if (this.output != null){
			if (this.output.toString().length() <= 2) return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "TestCase [input=" + input + ", output=" + output + "]";
	}

	
}
