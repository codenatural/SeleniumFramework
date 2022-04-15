package listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import reporting.ExtentManager;
import reporting.ExtentReporter;

public class TestNGListener implements ITestListener{

	public void onStart(ITestContext context) {	
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentReporter.endTest();
		ExtentManager.getInstance().flush();
	}
	
		public void onTestStart(ITestResult result) {
			System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
			ExtentReporter.startTest(result.getMethod().getMethodName());
		}
		
		public void onTestSuccess(ITestResult result) {
			System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
			ExtentReporter.getTest().log(Status.PASS, "Test passed");
		}
		

		public void onTestFailure(ITestResult result) {
			ITestContext context = result.getTestContext();
			WebDriver driver = (WebDriver) context.getAttribute("driver");

			String targetLocation = null;

			String testClassName = result.getInstanceName().trim();
			String timeStamp = String.valueOf(System.currentTimeMillis());
			String testMethodName = result.getName().toString().trim();
			String screenShotName = testMethodName + timeStamp + ".png";
			String fileSeperator = System.getProperty("file.separator");
			String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
					+ "screenshots";
			System.out.println("Screen shots reports path - " + reportsPath);
			
			try {
				File file = new File(reportsPath + fileSeperator + testClassName); // Set
																					// screenshots
																					// folder
				if (!file.exists()) {
					if (file.mkdirs()) {
						System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
					} else {
						System.out.println("Failed to create directory: " + file.getAbsolutePath());
					}

				}

				File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
																												// location
				File targetFile = new File(targetLocation);
				System.out.println("Screen shot file location - " + screenshotFile.getAbsolutePath());
				System.out.println("Target File location - " + targetFile.getAbsolutePath());
				FileHandler.copy(screenshotFile, targetFile);

			} catch (FileNotFoundException e) {
				System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
			} catch (Exception e) {
				System.out.println("An exception occurred while taking screenshot " + e.getCause());
			}

			// attach screenshots to report
			try {
				ExtentReporter.getTest().fail("Screenshot",
						MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
			} catch (IOException e) {
				System.out.println("An exception occured while taking screenshot " + e.getCause());
			}
			
			
			System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
			ExtentReporter.getTest().log(Status.FAIL, "Test Failed");
		}

		public void onTestSkipped(ITestResult result) {
			System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
			ExtentReporter.getTest().log(Status.SKIP, "Test Skipped");
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
		}	
	
}
