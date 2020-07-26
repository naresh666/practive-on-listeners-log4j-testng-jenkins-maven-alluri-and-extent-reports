package com.qa.webDotCom.extentreports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasePage {

	
	public WebDriver driver;
	@BeforeClass
	public void init() {
		
	}
	@BeforeMethod
	public void loaswebPage() {
		System.setProperty("webdriver.chrome.driver","G:/naresh practice/I Hub Selenium Manoj Sir/AutomationPractice/src/main/resources/LIB/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.onlinesbi.com");
	}
	@Test(priority=2)
	public void testSkipped() {

		System.out.println("Executing skipped test method");
		throw new SkipException("skipped by me skipped method");
	}
	@Test(priority=1)
	public void testPassed() {
	System.out.println("Executing successfully test method");
	Assert.assertTrue(true);
	}
	@Test(priority=3)
	public void testFailed() {

		System.out.println("Executing failed test method");
		Assert.fail();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	/*
	@AfterMethod()
	//ITestResult it discribs result of test case
	public void afterclass1(ITestResult result){
	
	}
	*/
	
	
	
}
