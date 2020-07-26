package com.qa.webDotCom.webdriverListeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass {
	WebDriver driver;
	EventFiringWebDriver e_driver;
	WebdriverEventCapture capture;
	@BeforeMethod
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", "G:\\naresh practice\\I Hub Selenium Manoj Sir\\AllSeleniumConcepts\\src\\main\\resources\\drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		e_driver=new EventFiringWebDriver(driver);
		capture=new WebdriverEventCapture();
		e_driver.register(capture);
		driver.get("https://www.facebook.com");
	}
	@Test
	public void enterUserName() {
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("kjsdfjsdk");
	}

}
