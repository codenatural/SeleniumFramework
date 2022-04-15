package pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleHomePage {

	
	private static Logger log = LogManager.getLogger(GoogleHomePage.class);
	WebDriver driver;
	public GoogleHomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS,css =".gLFyf.gsfi")
	WebElement searchBar;
	
	@FindBy(how = How.CSS,css = ".FPdoLc.lJ9FBc .gNO89b")
	WebElement searchButton;
	
	
	public void openGoogleHomePage() {
		log.info("Opening Google Home Page");
		driver.get("https://www.google.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void searchGoogle(String searchTerm) throws InterruptedException{
		log.info("Search Bar : "+searchBar);
		WebDriverWait wait  = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(searchBar));
		searchBar.sendKeys(searchTerm);
		Thread.sleep(4000);
		log.info("Searching google for the term : "+searchTerm);
//		searchButton.click();
//		log.info("Google search button clicked");
	}
	
	
}
