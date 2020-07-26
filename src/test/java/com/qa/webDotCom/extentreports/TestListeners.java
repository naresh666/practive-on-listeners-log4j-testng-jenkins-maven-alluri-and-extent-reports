package com.qa.webDotCom.extentreports;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestListeners implements ITestListener {
	//Webdriver driver=(BasePage)result.
	
	private static ExtentReports extent=ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	//if multiple test cases running parallel at this time it will handle multiple threads
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test=extent.createTest(result.getTestClass().getName()+" :: "+result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String logTest = "<b>Test Method" + result.getMethod().getMethodName() + "successful</b>";
		Markup m = MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color-red>Exception occured , click to see details"+"</font></b></summary>"+exceptionMessage.toString().replaceAll(",", "<br>")+"</details> \n");
		WebDriver driver=((BasePage)result.getInstance()).driver;
		String path=takeScreenShot(driver,result.getMethod().getMethodName());
		try {
			extentTest.get().fail("<b><font color-red>"+"screenshot of failure "+"</font></b> ",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			//MediaEntityBuilder.createScreenCaptureFromPath(path, exceptionMessage).build();
		
		} catch (IOException e) {
			extentTest.get().fail(" test failed , cann't attached screenshot");
		}
		String logTest = "<b>Test Method" + methodName + "Failed</b>";
		Markup m = MarkupHelper.createLabel(logTest, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logTest = "<b>Test Method" + result.getMethod().getMethodName() + "Skipped</b>";
		Markup m = MarkupHelper.createLabel(logTest, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		if(extent!=null) {
		extent.flush();	
		}
		
	}

	public static String getScreenshotName(String methodName) {
		Date d=new Date();
		String fileName=methodName+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return fileName;
	}
	public String takeScreenShot(WebDriver driver,String methodname){
		String fileName=getScreenshotName(methodname);
//		String directory=System.getProperty("user.dir")+"/screenshots";
//		new File(directory).mkdirs();
		String path=System.getProperty("user.dir")+"//screenshots/"+fileName;
		try {
			File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(".//screenshots/"+fileName));
			System.out.println("************");
			System.out.println("screenshot stored at "+path);
			System.out.println("****************");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	

}
