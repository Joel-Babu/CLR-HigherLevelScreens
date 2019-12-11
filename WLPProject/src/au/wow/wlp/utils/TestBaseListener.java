/**
 * This class acts as a Listener. It is invoked before each test method
 * It performs the specified operations on Test start, test success, test failure and test skipped
 */
package au.wow.wlp.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBaseListener extends TestListenerAdapter {

	private Logger log;
	private WebDriver driver;
	private CustomExtentReports extreport;
	
	@Override
	public void onTestStart(ITestResult result) {
		super.onTestStart(result);
		log=LogManager.getLogger(result.getTestClass().getRealClass());
		log.info("====================================");
		log.info("\t\tTestStarted\t\t");
		log.info("====================================");
		log.info("Starting test: " + result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
		String filename = tr.getMethod().getMethodName();
//		System.out.println("Screenshot name: " + filename);
		log.error("Test "+ filename+" failed: " + tr.getThrowable().getLocalizedMessage());
		driver = (WebDriver)tr.getTestContext().getAttribute("WebDriver");
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\test-output\\screenshots\\" + filename +".jpg"));
			Reporter.setEscapeHtml(false);
			Reporter.log("<a href='" + System.getProperty("user.dir") + "\\test-output\\screenshots\\" + filename +".jpg'><font color='red'>Link</font></a>" );
		} catch (IOException e) {
			Assert.fail("Error in capturing the screenshot: " ,e);
		}
		extreport = (CustomExtentReports) tr.getTestContext().getAttribute("Customreports");
		ExtentTest test = extreport.getExtentTest();
//		test.log(LogStatus.FAIL, tr.getThrowable().getLocalizedMessage()+test.addScreenCapture(System.getProperty("user.dir") + "\\test-output\\screenshots\\" + filename +".jpg"));
		test.log(LogStatus.FAIL, tr.getMethod().getMethodName()+ " Failed", tr.getThrowable().getLocalizedMessage() + "Screentshot: " + "<a href='" + System.getProperty("user.dir") + "\\test-output\\screenshots\\" + filename +".jpg'><font color='red'>Link</font></a>");
		/*extreport.getExtentTest().addScreenCapture(System.getProperty("user.dir") + "\\test-output\\screenshots\\" + filename +".jpg");
		extreport.getExtentTest().log(LogStatus.FAIL, tr.getThrowable().getLocalizedMessage());*/
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		
		super.onTestSuccess(tr);
		log.info("====================================");
		log.info("\t\tTestCompleted\t\t");
		log.info("====================================");
		log.info("Test Success: " + tr.getMethod().getMethodName());
		extreport = (CustomExtentReports) tr.getTestContext().getAttribute("Customreports");
		ExtentTest test = extreport.getExtentTest();
		test.log(LogStatus.PASS, tr.getMethod().getMethodName()+ " Passed");
	}
	
	@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSkipped(tr);
		log.info("Test Skipped: " + tr.getMethod().getMethodName());
		extreport = (CustomExtentReports) tr.getTestContext().getAttribute("Customreports");
		ExtentTest test = extreport.getExtentTest();
		test.log(LogStatus.SKIP, tr.getMethod().getMethodName()+ " Skipped", tr.getThrowable().getLocalizedMessage());
	}
	
	
	
	        
}
