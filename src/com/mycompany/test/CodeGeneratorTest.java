package com.mycompany.test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import com.mycompany.generator.AsenAPIDriver;
import com.sun.media.sound.InvalidFormatException;

public class CodeGeneratorTest {
//  @Test
  public static void main(String[] args) {
	AsenAPIDriver a = AsenAPIDriver.newInstance();
	a.init("/Users/loandinh/MuleESBBeginner/muleESBbeginner/");
	try {
//		Map<String, Object> data = new HashMap<String, Object>();
//	    data.put( "name", "Mars" );
//	    data.put( "age", 32 );
//	    data.put( "city", "NY" );
//	    JSONObject json = new JSONObject();
//	    json.putAll( data );
//	    System.out.printf( "JSON: %s", json.toString(2) );
		a.generate();
	} catch (InvalidFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (EncryptedDocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
