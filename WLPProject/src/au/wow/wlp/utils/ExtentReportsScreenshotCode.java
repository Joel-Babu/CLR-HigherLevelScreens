package au.wow.wlp.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportsScreenshotCode extends BasePage
{
  public static String CaptureScreenshot(WebDriver driver,String ScreenShotName) 
  {
	  try
	  {
		 TakesScreenshot ts= (TakesScreenshot)driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 String loc = "./Reports/Screenshots/"+ScreenShotName+".jpeg";
		 File Destination = new File(loc);
		 FileUtils.copyFile(source,Destination);
		 System.out.println("Screenshot Captured Successfully");
		 return loc;
	  }
	  catch(Exception e)
	  {
		 System.out.println("Exception Occured while taking Screenshot" +e.getMessage()); 
		 return e.getMessage();
	  }
  }
  
  /*public static void TakeScreenshot(WebDriver driver,ExtentTest extentTest,String ScreenShotName) 
  {
	  try
	  {
		  String Screenshot_Path = CaptureScreenshot(driver,ScreenShotName); 
		  String image = extentTest.addScreenCapture(Screenshot_Path);
		  extentTest.log(LogStatus.FAIL, "Failed to Launch Application",image);
	  }
	  catch(Exception e)
	  {
		 System.out.println("Exception Occured while taking Screenshot" +e.getMessage()); 
	  }
  }*/
}
