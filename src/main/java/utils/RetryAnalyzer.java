package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
int count=0;
int retryCount=1;

/**
 * This method is part of a custom retry mechanism for TestNG tests.
 * It is invoked when a test method fails, and it determines whether the test should be retried based on a specified retry count.
 * If the test fails and the retry count hasn't been exhausted, it will return {@code true} to retry the test.
 * If the retry count has been reached or exceeded, it will return {@code false}, indicating that no further retries should be performed.
 *
 * @param result The ITestResult object containing the details of the test method that failed.
 *               It provides information about the test execution, including the test status and method name.
 *               This parameter is used to determine if the test should be retried based on the failure count and retry limit.
 * 
 * @return {@code true} if the test should be retried; {@code false} if the maximum retry count has been reached
 *         and the test should not be retried.
 */
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		while(count<retryCount) {
			count++;
			return true;
		}
		return false;
	}
	

}
