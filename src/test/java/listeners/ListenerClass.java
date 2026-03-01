package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.SauceDemoBaseClass;
import utility.ExtentManager;

public class ListenerClass implements ITestListener {
	ExtentReports report = ExtentManager.getReport();
    ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("[ ***** TEST CASE NAME " + result.getName() + " STARTED ***** ]");
		test = report.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("[ ***** TEST CASE NAME " + result.getName() + " SUCCESS ***** ]");
		test.pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("[ ***** TEST CASE NAME " + result.getName() + " FAILED ***** ]");
		String imageName = result.getName();
		
		System.out.println("SCREENSHOT CAPTURING . . .");
		Object testClass = result.getInstance();
	    SauceDemoBaseClass baseObj = (SauceDemoBaseClass) testClass;
	    WebDriver driver = baseObj.driver;
	    
	    try {
	    	utility.UtilityClass.takeScreenShot(driver, imageName);
			System.out.println("screenshot taken and path is:-> SauceDemo\\screenshots" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.out.println("SCREENSHOT CAPTURED . . .");
	    
		test.fail("Test failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("[ ***** TEST CASE NAME " + result.getName() + " SKIPPED ***** ]");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("WELCOME TO SAUCEDEMO TESTING");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("END OF SAUCEDEMO TESTING");
		report.flush();
	}
}
