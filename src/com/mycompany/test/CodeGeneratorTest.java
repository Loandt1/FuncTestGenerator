package com.mycompany.test;

import org.testng.annotations.Test;

import com.mycompany.generator.AsenAPIDriver;

public class CodeGeneratorTest {
  @Test
  public void test() throws Exception {
	AsenAPIDriver a = AsenAPIDriver.newInstance();
	a.init("/Users/loandinh/MuleESBBeginner/muleESBbeginner/");
	a.generate();
  }
}
