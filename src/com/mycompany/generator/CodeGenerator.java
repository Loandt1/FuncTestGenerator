package com.mycompany.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.mycompany.model.MuleFlow;

public class CodeGenerator {
	public static CodeGenerator newInstance(){
		return new CodeGenerator();
	}
	
	protected void genTestForMuleApp(MuleFlow flow, String location) {
		try {
				String fileClassName = StringUtils.capitalize(flow.getName().replaceAll("-", ""));
				String testFileName = new StringBuilder().append(location)
														 .append(fileClassName)
														 .append(".java")
														 .toString();
				BufferedWriter writer = new BufferedWriter(new FileWriter(testFileName));

				//import libs
				writer.write("import org.mule.api.MuleEvent;"
						+ "\nimport org.mule.munit.runner.functional.FunctionalMunitSuite;"
						+ "\nimport org.testng.Assert;"
						+ "\nimport org.testng.annotations.Test;");
				writer.newLine();
				
				writer.newLine();
				
				//main 
				String className = "public class ";
				className += fileClassName;
				className += " extends FunctionalMunitSuite {";
				writer.write(className);
				writer.newLine();
				
				writer.write("\t@Test");
				writer.newLine();
				writer.write("\tpublic void Test() throws Exception {");
				writer.newLine();
				writer.write("\t\tString myStringPayload = \"myPayload\";");
				writer.newLine();
				
				String runFlow = "\t\tMuleEvent resultMuleEvent = runFlow(\"";
				runFlow += flow.getName();
				runFlow += "\",testEvent(myStringPayload) );";
				writer.write(runFlow);
				writer.newLine();
				
				writer.write("\t\tAssert.assertEquals(myStringPayload, resultMuleEvent.getMessage().getPayload() ); ");
				writer.newLine();
				writer.write("\t}");
				writer.newLine();
				writer.write("}");
				
				writer.close();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
