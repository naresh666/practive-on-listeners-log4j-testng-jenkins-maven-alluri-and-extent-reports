package com.qa.webDotCom.extentreports;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class testClass1 {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	public WebDriver driver;
	@BeforeClass
	public void init() {
		htmlReporter=new ExtentHtmlReporter("./reports/extentReports.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("WebDotCom Automation Reports");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.setSystemInfo("Organization", "naresh info tech");
		extent.attachReporter(htmlReporter);
		System.setProperty("webdriver.chrome.driver","G:/naresh practice/I Hub Selenium Manoj Sir/AutomationPractice/src/main/resources/LIB/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	@BeforeMethod
	public void loaswebPage() {
		driver.get("https://www.google.com");
	}
	@Test
	//@Parameters("browser")
	public void testSkipped() {
		extentTest=extent.createTest("testSkipped method");
		//extentTest.log(Status.SKIP, "test method skipped");
		throw new SkipException("skipped by me skipped method");
		
	}
	@Test
	public void testPassed() {
		extentTest=extent.createTest("succesful test");
		//extentTest.log(Status.PASS, "test method succesful");
	}
	@Test
	public void testFailed() {
		extentTest=extent.createTest("failed test");
		//extentTest.log(Status.FAIL, "test method failed");
		Assert.fail();
		
	}
	@AfterMethod()
	//ITestResult it discribs result of test case
	public void afterclass1(ITestResult result){
		String methodName=result.getMethod().getMethodName().toString();
		if(result.getStatus()==ITestResult.FAILURE) {
			String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.fail("<details><summary><b><font color-red>Exception occured , click to see details"+"</font></b></summary>"+exceptionMessage.toString().replaceAll(",", "<br>")+"</details> \n");
			String path=takeScreenShot(result.getMethod().getMethodName());
			try {
				extentTest.fail("<b><font color-red>"+"screenshot of failure "+"</font></b> ",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
				//MediaEntityBuilder.createScreenCaptureFromPath(path, exceptionMessage).build();
			
			} catch (IOException e) {
				extentTest.fail(" test failed , cann't attached screenshot");
			}
			String logTest = "<b>Test Method" + methodName + "Failed</b>";
			Markup m = MarkupHelper.createLabel(logTest, ExtentColor.RED);
			extentTest.log(Status.FAIL, m);
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			String logTest = "<b>Test Method" + methodName + "successful</b>";
			Markup m = MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
			extentTest.log(Status.PASS, m);
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			String logTest = "<b>Test Method" + methodName + "Skipped</b>";
			Markup m = MarkupHelper.createLabel(logTest, ExtentColor.YELLOW);
			extentTest.log(Status.SKIP, m);
		}

	}
	public static String getScreenshotName(String methodName) {
		Date d=new Date();
		String fileName=methodName+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return fileName;
	}
	public String takeScreenShot(String methodname){
		String fileName=getScreenshotName(methodname);
		String directory=System.getProperty("user.dir")+"/screenshots";
		new File(directory).mkdirs();
		String path=directory+fileName;
		try {
			File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(directory));
			System.out.println("************");
			System.out.println("screenshot stored at "+directory);
			System.out.println("****************");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		extent.flush();
	}

}
