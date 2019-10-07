package com.hiver.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 0;

	public boolean retry(ITestResult paramITestResult) {
		if (counter < retryLimit) {
			System.out.println("RETRYING :: ATTEMPT" + counter);
			counter++;
			return true;
		}
		return false;
	}
}
