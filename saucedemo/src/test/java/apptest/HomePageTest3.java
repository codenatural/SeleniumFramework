package apptest;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Browser;
import base.DriverManager;
import pages.GoogleHomePage;
import pages.SauceLabHomePage;

public class HomePageTest3 extends DriverManager{
	private static Logger log = LogManager.getLogger(HomePageTest3.class);
	private SauceLabHomePage sauceLabHomePage = null;
	private GoogleHomePage googleHomePage = null;
	
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
	
	@Test(description = "Open Sauce Lab Home Page")
	void openSauceLabHomePageTest() {
		log.info("Test Thread ID : "+Thread.currentThread().getId());
		WebDriver driver = getDriver();
		sauceLabHomePage = new SauceLabHomePage(driver);
		sauceLabHomePage.openSauceDemoHomePage();
		Assert.assertEquals(driver.getTitle(),"Swag Labs");
	}
	
	@Test(description = "Open Google Home Page")
	void openGoogleHomePageTest() throws InterruptedException {
		log.info("Test Thread ID : "+Thread.currentThread().getId());
		WebDriver driver = getDriver();
		googleHomePage = new GoogleHomePage(driver);
		googleHomePage.openGoogleHomePage();
		Thread.sleep(4000);
		Assert.assertEquals(driver.getTitle(),"Google");
	}
	
	@Test(description = "Search a string in google search")
	void googleSearchTest() throws InterruptedException {
		log.info("Test Thread ID : "+Thread.currentThread().getId());
		WebDriver driver = getDriver();
		log.info("WEBDRIVER is : "+driver);
		googleHomePage = new GoogleHomePage(driver);
		googleHomePage.openGoogleHomePage();
		googleHomePage.searchGoogle("Halogen");
	}
	
}
