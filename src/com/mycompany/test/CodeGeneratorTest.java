package com.mycompany.test;

import com.mycompany.generator.AsenAPIDriver;

public class CodeGeneratorTest {
//  @Test
  public static void main(String[] args) {
	AsenAPIDriver a = AsenAPIDriver.newInstance();
	a.init("/Users/loandinh/AnypointStudio/workspace/testmule");
	try {
		a.generate();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}
