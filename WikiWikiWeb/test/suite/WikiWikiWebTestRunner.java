/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suite;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;

/**
 *
 * Execute test suite
 */
public class WikiWikiWebTestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(WikiWikiWebTestSuite.class);
		for (Failure failure : result.getFailures()){
			System.out.println(failure.toString());
		}
		int percentPassed = (int)((double)result.getFailureCount()* 100.0 / result.getRunCount());
		System.out.println("suite.WikiWikiWebTestRunner.main()\nTests run: "
				+result.getRunCount()+"("+percentPassed+" % passed)\n"+"Success: "+result.wasSuccessful());
	}
}
