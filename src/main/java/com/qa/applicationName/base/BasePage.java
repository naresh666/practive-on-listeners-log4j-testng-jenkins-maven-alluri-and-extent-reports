package com.qa.applicationName.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;



public class BasePage {
	public WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	public Properties prop;

	/*
	 * mvn clean test -DtestSuite123456="testng.xml" -Denv="qa" here i given
	 * testSuite123456 in POM.xml and my testNG xml file name is testng.xml i set
	 * system property in POM.xml nam is env i catch this env name in BasePage based
	 * on passed p parameters that environment will executes
	 */
	
	/*
	public BasePage() {
		prop = new Properties();
		String env = System.getProperty("env");
		String path = null;
		try {
			if (env.equalsIgnoreCase("qa")) {
				path = ".//src/main/resources/properties/config.qa.properties";
				System.out.println("from qa");

			}
			if (env.equalsIgnoreCase("stg")) {
				path = ".//src/main/resources/properties/config.stg.properties";
				System.out.println("from stg");
			}
		} catch (Exception e) {
			path = ".//src/main/resources/properties/config.properties";
			System.out.println("normal prop");
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("some issue with config properties....Please correct your config...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	public WebDriver init_driver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					projectPath + "//src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "//src/main/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("internetExplorer")) {
			System.setProperty("", "");
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			System.setProperty("", "");
			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("", "");
			driver = new EdgeDriver();
		} else {
			System.out.println("browser Name " + browserName + " is not found, please pass the correct browser");
		}
		driver.manage().deleteAllCookies();
		// driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		return driver;
	}

	public void enterURL() {
		driver.get(prop.getProperty("url"));
	}
	public void launchChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", projectPath + "//src/main/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}
	public void enterURL1() {
		driver.get("https://www.google.com");
	}
	public static void main(String[] args) {
		new BasePage().launchChromeBrowser();
		new BasePage().enterURL1();
		
	}

}
