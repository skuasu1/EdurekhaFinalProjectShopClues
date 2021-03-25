package com.qa.shopClues.Base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qa.shopClues.utils.ShopcluesUtil;

public class TestBase {

	/*
	 * This method is the super class of all the classes This method used for
	 * 
	 * 1. Loading the property file 2. invoke the chrome browser with some basic
	 * implementations
	 * 
	 */
		// need to have class level variablesfor the property file

		public static Properties prop;// here we need to make prop as static so i can access this variable without any
		public static WebDriver driver;

		// need to have constructor to load the property file since this method need not
		// be called explicitly

		public TestBase() {
			// Need to load using try and catch block to catch for exceptions
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/java/com/qa/shopClues/config/config.properties");
				prop.load(ip);
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("unable to load the property file");
			}
		}

		public void initialization() {
			String localvariable = prop.getProperty("browser");
			if (localvariable.equals("chrome")) {
				// chrome driver.exe is in this particular location
				System.setProperty("webdriver.chrome.driver",
						"D:\\Java Coding\\Eclipse\\Eclipse_workspae\\shopClues\\driver\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(ShopcluesUtil.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(ShopcluesUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}
}
