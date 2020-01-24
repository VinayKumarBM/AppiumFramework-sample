package com.appium.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
	private final Logger LOG = LoggerFactory.getLogger(TestListener.class); 

	@Override
	public void onTestStart(ITestResult result) {
		printLogs(result.getName()+" STARTED");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		printLogs(result.getName()+" PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		printLogs(result.getName()+" FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		printLogs(result.getName()+" SKIPPED");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

	
	private void printLogs(String logMessage) {
		LOG.info("=================================================================================================\n");
		LOG.info("\t\t\t\t<<--{ "+logMessage.toUpperCase()+" }-->>\n");
		LOG.info("=================================================================================================\n");
	}
}
