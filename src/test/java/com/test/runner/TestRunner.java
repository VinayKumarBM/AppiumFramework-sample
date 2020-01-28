package com.test.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import com.appium.utils.ExcelReportGenerator;

public class TestRunner {

	public static void main(String[] args) throws Exception {
		TestNG runner=new TestNG();
		List<String> suitefiles=new ArrayList<String>();
		suitefiles.add(new File("testng.xml").getAbsolutePath());
		runner.setTestSuites(suitefiles);
		runner.run();
		
		ExcelReportGenerator.generateReport(new File("reports").getAbsolutePath(), "Appium_Report.xlsx");
	}

}
