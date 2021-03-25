package com.qa.shopClues.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.qa.shopClues.Base.TestBase;

public class LoginTestCaseShopClues extends TestBase {

	private XSSFCell cell;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;

	@FindBy(xpath = "//a[normalize-space()='Sign In']")
	WebElement signinEle;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[3]/div[1]/button[2]")
	WebElement allowEle;
	@FindBy(id = "main_user_login")
	WebElement userNameEle;
	@FindBy(xpath = "//form[@name='loginform']//input[@name='password']")
	WebElement passwordEle;
	@FindBy(id = "login_button")
	WebElement loginEle;
	@FindBy(xpath = "//span[normalize-space()='Please enter valid email id or mobile number.']")
	WebElement erorMessageEle;
	@FindBy(xpath = "//a[normalize-space()='Hi karthiks']")
	WebElement nameEle;
	@FindBy(id = "autocomplete")
	WebElement searchFieldEle;
	@FindBy(xpath = "//a[normalize-space()='Search']")
	WebElement searchBtn;
	@FindBy(xpath = "//img[@id='det_img_150323589']")
	WebElement nikeshoesEle;
	@FindBy(xpath = "//*[@id=\"product_list\"]/div[3]/div[1]/a/h2")
	WebElement shoeLabel;
	@FindBy(xpath = "//span[@class='f_price']")
	WebElement priceLabel;
	@FindBy(id = "itemsleft")
	WebElement itemsLeftEle;
	@FindBy(xpath = "//h1[normalize-space()='Fastrack Quartz White Round Men Watch 3124SM01']")
	WebElement watchLabel;
	@FindBy(xpath = "//span[@class='f_price']")
	WebElement watchPriceEle, applePriceEle;
	@FindBy(id = "itemsleft")
	WebElement watchCountEle;
	@FindBy(id = "//div[3]//div[1]//a[1]//h2[1]")
	WebElement appleLabel;

	@FindBy(id = "user_view")
	WebElement phoneItemleftEle;
	@FindBy(xpath = "//*[contains(@id, 'det_img_139845400')]")
	WebElement watchlabelEle;

	@FindBy(xpath = "//*[contains(@id, 'det_img_147954765')]")
	WebElement appleListlabel;
	@FindBy(id="instdcnt")
	WebElement countLabel;

	// need to invoke the page factory
	public LoginTestCaseShopClues() {
		PageFactory.initElements(driver, this);
	}

	public void clickAllow() {
		allowEle.click();
	}

	public void signIn() {
		signinEle.click();
	}

	public void getExcelData() throws IOException {

		File file = new File("./src/test/java/com/qa/shopClues/testdata/login.xlsx");
		FileInputStream fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		// Creating a Sheet object using the sheet Name
		sheet = wb.getSheet("login");
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			cell = sheet.getRow(i).getCell(0);
			DataFormatter formatter = new DataFormatter();
			String username = formatter.formatCellValue(cell);
			System.out.println("Username is :" + username);
			cell = sheet.getRow(i).getCell(1);
			String password = formatter.formatCellValue(cell);
			System.out.println("Password is : " + password);
			userNameEle.sendKeys(username);
			passwordEle.clear();
			passwordEle.sendKeys(password);
			loginEle.click();
			userNameEle.clear();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
	}

	public void searchproduct() {
		allowEle.click();
		searchFieldEle.sendKeys(prop.getProperty("nikeshoes"));
		searchBtn.click();
	}

	public void searchNikeShoes() {
		System.out.println("Will click the nikeshoe that opens diff page");
		shoeLabel.click();
		System.out.println("Nike shoe clicked");
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindow = driver.getWindowHandles();
		Iterator<String> it = childWindow.iterator();
		while (it.hasNext()) {
			String childWindowValue = it.next();
			if (!parentWindow.equalsIgnoreCase(childWindowValue)) {
				driver.switchTo().window(childWindowValue);
				System.out.println("Control has came to child window");
				String shoeTitle = driver
						.findElement(By.xpath("//h1[normalize-space()=\"Fausto Men's Grey Sports Walking Shoes\"]"))
						.getText();
				String shoePrice = priceLabel.getText();
				String count = itemsLeftEle.getText();
				System.out.println(shoeTitle);
				System.out.println(shoePrice);
				System.out.println(count);
				Reporter.log("Title of the Product Nike Shoe is : " + shoeTitle);
				Reporter.log("Price of the Product Nike Shoe is : " + shoePrice);
				Reporter.log("Count of result : " + count);
				driver.close();
			}
			driver.switchTo().window(parentWindow);
		}
	}

	public void searchFasttrackWatch() {
		searchFieldEle.clear();
		searchFieldEle.sendKeys(prop.getProperty("fasttrackwath"));
		searchBtn.click();
		watchlabelEle.click();
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindow = driver.getWindowHandles();
		Iterator<String> it = childWindow.iterator();
		while (it.hasNext()) {
			String childWindowValue = it.next();
			if (!parentWindow.equalsIgnoreCase(childWindowValue)) {
				driver.switchTo().window(childWindowValue);
				System.out.println("Going to find the apple lable to click");
				String watchTitle = watchLabel.getText();
				System.out.println("Clicked");
				String watchPrice = watchPriceEle.getText();
				String watchCount = watchCountEle.getText();
				System.out.println(watchTitle);
				System.out.println(watchPrice);
				System.out.println(watchCount);
				Reporter.log("Title of the Product Fast Track title is : " + watchTitle);
				Reporter.log("Price of the Product Fast Track price is : " + watchPrice);
				Reporter.log("Count of result : " + watchCount);
				driver.close();
			}
			driver.switchTo().window(parentWindow);
		}

	}

	public void searchApplePhone() {
		searchFieldEle.clear();
		searchFieldEle.sendKeys(prop.getProperty("applephones"));
		searchBtn.click();
		appleListlabel.click();
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindow = driver.getWindowHandles();
		Iterator<String> it = childWindow.iterator();
		while (it.hasNext()) {
			String childWindowValue = it.next();
			if (!parentWindow.equalsIgnoreCase(childWindowValue)) {
				driver.switchTo().window(childWindowValue);
				String applePhoneLabel = driver
						.findElement(By.xpath("//h1[contains(text(),'Apple iPhone 11 Pro Max')]")).getText();
				String applePhonePricelabel = applePriceEle.getText();
				String appleCount = phoneItemleftEle.getText();
				System.out.println(applePhoneLabel);
				System.out.println(applePhonePricelabel);
				System.out.println(appleCount);
				Reporter.log("Title of the Apple Phone is : " + applePhoneLabel);
				Reporter.log("Price of the Product Apple Phone price is : " + applePhonePricelabel);
				Reporter.log("Count of result : " + appleCount);
				driver.close();
			}
			driver.switchTo().window(parentWindow);
		}
	}

	public void searchHeadphones() {
		searchFieldEle.clear();
		searchFieldEle.sendKeys(prop.getProperty("headphones"));
		searchBtn.click();
		String number = countLabel.getText();
		System.out.println(number);
		Reporter.log("Count of Headphones searched in Shop Clue are : - Showing " + number + " Results");
	}
}
