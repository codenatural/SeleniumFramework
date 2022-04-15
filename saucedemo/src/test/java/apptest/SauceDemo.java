package apptest;


import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Browser;
import base.DriverManager;
import reporting.ExtentManager;
import reporting.ExtentReporter;

public class SauceDemo extends DriverManager{

	private static Logger log = LogManager.getLogger(SauceDemo.class);
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser"})
	void setUp(String browser,Method m) throws Exception {
		log.info("B4 Method Current Thread ID : "+Thread.currentThread().getId());
		log.info("Running on Browser : "+Browser.valueOf(browser));
		tdriver.set(getDriver(Browser.valueOf(browser),m));
		log.info("Opened Driver Instance : "+getDriver());
	
	}
	
	@AfterMethod (alwaysRun = true)
	void tearDown() {
		WebDriver driver = getDriver();
		log.info("After Method Current Thread ID : "+Thread.currentThread().getId());
		if(driver!=null) {
			driver.quit();
		}else {
			log.info("Driver is null !!");
		}
	}
	
	@Test
	void sauceLoginTest001() throws InterruptedException {
		ExtentReports report = ExtentManager.getInstance();
		ExtentTest test = report.createTest("Validate Google Home Page");
		
		WebDriver driver = getDriver();
		
		test.log(Status.PASS, "Open SauceLabDemo");
		driver.get("https://www.saucedemo.com/");
		test.log(Status.PASS, "Enter Username");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		test.log(Status.PASS, "Enter Password");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(4000);
		Assert.assertEquals(driver.getTitle(), "Swg Labs");
	}
	
	@Test
	void sauceCartTest002() throws InterruptedException {
		WebDriver driver = getDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']/span")).click();
		String price = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
		Thread.sleep(4000);
		Assert.assertEquals(price, "$29.99");
	}
}
