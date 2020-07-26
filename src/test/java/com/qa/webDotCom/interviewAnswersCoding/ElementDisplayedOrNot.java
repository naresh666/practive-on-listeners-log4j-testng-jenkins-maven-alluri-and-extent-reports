package com.qa.webDotCom.interviewAnswersCoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ElementDisplayedOrNot {

	//diff bw isdisplayed,Isenabled,Isselected
	@Test
	public void ElementDisplayedOrnot() {
	WebDriver driver=new ChromeDriver();
	driver.findElement(By.name("user_name")).isDisplayed();
	}
}
