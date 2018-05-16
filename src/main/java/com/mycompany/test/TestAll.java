package com.mycompany.test;

import com.mycompany.generator.AsenAPIDriver;

public class TestAll {
	// "/Users/loandinh/AnypointStudio/workspace/muleesbbegin"
		public static void main(String[] args) {
			String path = "/Users/loandinh/Desktop/luanvan/Generator/MuleESBBeginner/TestMuleESB";
			AsenAPIDriver a = AsenAPIDriver.newInstance();
			a.init(path);
			try {
				a.generate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
