/**
 * LOANDT Apr 17, 2018
 */
package com.mycompany.generator;

import org.junit.jupiter.api.Test;

/**
 * @author toann
 *
 */
class TestMain {

	@Test
	void test() {
		//		fail("Not yet implemented");
		Generator t = Generator.newInstance();
		t.init("D:\\LOANDT\\MuleESBBeginner\\muleESBbeginner", "D:\\LOANDT\\MuleESBBeginner\\muleESBbeginner");
		t.scanCode();
		t.generateTest();
	}

}
