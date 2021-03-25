package com.qa.shoClues.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.shopClues.Base.TestBase;
import com.qa.shopClues.pages.LoginTestCaseShopClues;

public class LoginTestCase extends TestBase {

	LoginTestCaseShopClues loginPage;

	public LoginTestCase() {
		super(); // need to invoke the property loading of the test base class
	}

	@BeforeMethod
	public void initshopClues() {
		initialization();
		loginPage = new LoginTestCaseShopClues();
	}
	@Test(priority = 1)
	public void logintest() throws IOException {
		loginPage.clickAllow();
		loginPage.signIn();
		loginPage.getExcelData();
	}
	@Test(priority = 2)
	public void otherModule() {
		loginPage.searchproduct();
		loginPage.searchNikeShoes();
		loginPage.searchFasttrackWatch();
		loginPage.searchApplePhone();
		loginPage.searchHeadphones();
	}

	@AfterMethod
	public void tear() {
		driver.quit();
	}
}
