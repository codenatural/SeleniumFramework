package pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SauceLabHomePage {
	private static Logger log = LogManager.getLogger(SauceLabHomePage.class);
	WebDriver driver;
	public SauceLabHomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void openSauceDemoHomePage() {
		log.info("Opening Sauce Demo Home Page");
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
}
