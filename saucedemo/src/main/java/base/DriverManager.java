package base;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.WebEventListener;
import utils.PropertyReader;

public class DriverManager{
	
	    private static Logger log = LogManager.getLogger(DriverManager.class);
	    protected static EventFiringWebDriver eventDriver;
	    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	
		public static WebDriver getDriver(Browser t,Method m) throws Exception {
			 WebDriver driver = initializeDriver(t,m);
			 eventDriver = new EventFiringWebDriver(driver);
			 WebEventListener eventListener = new WebEventListener();
			 eventDriver.register(eventListener);
			 return eventDriver;
		}
		
		public static WebDriver getDriver() {
			log.info("Thread Driver is :"+tdriver);
			return tdriver.get();
		}
		
		
		private static WebDriver initializeDriver(Browser t,Method m) throws Exception
		{
			WebDriver driver = null;
			String runLocal = PropertyReader.readProperty(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties", "runLocal");
			log.info("Fetched Property runLocal : : "+runLocal);
			switch(runLocal) {
			case "Yes" :
					log.info("Runnning session on Local");
					switch(t) {
					case FIREFOX:
						   log.info("Setting firefox properties");
						   WebDriverManager.firefoxdriver().setup();
						   driver = new FirefoxDriver();
						   setPageLoadTimeout(driver, 10);
						   log.info("Firefox Initialized");
						   break;
						
					case CHROME:
						   log.info("Setting chrome properties");
						   WebDriverManager.chromedriver().setup();
						   driver = new ChromeDriver();
						   log.info("Chrome Initialized");
						   break;
					default:
						break;
					}
				break;
				
			case "No":
				   log.info("Runnning session on Remote - Sauce Lab");
				   MutableCapabilities sauceOptions = new MutableCapabilities();
				   sauceOptions.setCapability("name", m.getName());
				   sauceOptions.setCapability("seleniumVersion", "3.10.0");
				   sauceOptions.setCapability("username", "oauth-automationtester05-69368");
				   sauceOptions.setCapability("accessKey", "f2389a1d-2b88-4759-a583-199bc6c7ae04");

				   
				   switch(t) {
					case FIREFOX:
						   DesiredCapabilities capabilities =new DesiredCapabilities();
						   capabilities.setCapability("sauce:options", sauceOptions);
						   capabilities.setCapability("browserVersion", "latest");
						   capabilities.setCapability("platformName", "Windows 10");
						   capabilities.setCapability("browserName", "firefox");
						   
						   log.info("Setting firefox properties");
						   WebDriverManager.firefoxdriver().setup();
						   driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"),capabilities);
						   setPageLoadTimeout(driver, 10);
						   log.info("Firefox Initialized");
						   break;
						   
					case CHROME:
						   DesiredCapabilities capabilities1 =new DesiredCapabilities();
						   capabilities1.setCapability("sauce:options", sauceOptions);
						   capabilities1.setCapability("browserVersion", "latest");
						   capabilities1.setCapability("platformName", "macOS 12");
						   capabilities1.setCapability("browserName", "chrome");
						   
						   log.info("Setting firefox properties");
						   WebDriverManager.firefoxdriver().setup();
						   driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"),capabilities1);
						   setPageLoadTimeout(driver, 10);
						   log.info("Firefox Initialized");
						   break;
				
			     default:
				        break;
				   }
			default:
		        break;
			}
			return driver;
		}
		
		static Timeouts setPageLoadTimeout(WebDriver driver,long loadTimeout) {
			return driver.manage().timeouts().pageLoadTimeout(loadTimeout, TimeUnit.SECONDS);
		}
	
}
