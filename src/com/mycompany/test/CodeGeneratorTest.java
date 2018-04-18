package com.mycompany.test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.mycompany.generator.Asen;
import com.sun.media.sound.InvalidFormatException;

public class CodeGeneratorTest {
  @Test
  public void test() throws InvalidFormatException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
	Asen a = Asen.newInstance();
	a.init("/Users/loandinh/MuleESBBeginner/muleESBbeginner/");
	a.generate();
  }
}
