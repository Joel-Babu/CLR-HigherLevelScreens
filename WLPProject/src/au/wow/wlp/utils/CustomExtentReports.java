/**
 * This class creates the extent reports in test-output\extentreports folder,
 * a html report used for logging validations.
 * 
 */
package au.wow.wlp.utils;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class CustomExtentReports{
	
	public ExtentReports extreport;
	private ExtentTest exttest;
	private String reportfilepath=System.getProperty("user.dir") + "\\test-output\\extentreports\\ExtentReport.html";
	
	private void createReportFile(){
		File file = new File(reportfilepath);
		if(file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				Assert.fail("Error in creating the extent report html file", e.fillInStackTrace());
			}
			}
	}
	
	/**
	 * Creates the extent report 
	 */
	public void createExtentReport(){
		createReportFile();
		extreport = new ExtentReports(reportfilepath);
	}
	
	/**
	 * Attaching each testmethod to the reports
	 * @param testmethod
	 */
	public void startTest(String testmethod){
		try{
		exttest=extreport.startTest(testmethod);
		}catch(Exception e){
			Assert.fail("Error in creating test for extent reports", e.fillInStackTrace());
		}
		
	}
	
	public ExtentTest getExtentTest(){
		return exttest;
	}
	
	public ExtentReports getExtentReport(){
		return extreport;
	}


	
	
	
	
	
	
	
	
	
	
	
	
/*	String actual,expected;
	private Map<AssertionError, IAssert> m_errors = Maps.newLinkedHashMap();

	@Override
	public void executeAssert(IAssert a) {
		try {
			actual=(String) a.getActual();
			expected=(String)a.getExpected();
			a.doAssert();
		} catch(AssertionError ex) {
			m_errors.put(ex, a);
		}
	}

	public String getActual(){
		return actual;
	}

	public String getExpected(){
		return expected;
	}
	*/
	

	
	
}
