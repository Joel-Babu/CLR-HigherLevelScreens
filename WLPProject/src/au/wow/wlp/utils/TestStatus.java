/**
 * This class updates the assertions/validations to extent report
 * 
 * For eg:
 * To compare two strings, use the below code in the pages
 * getStatus().assertEqualsIgnorecase(string1, string2, Message);
 * 
 */
package au.wow.wlp.utils;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
public class TestStatus extends SoftAssert{
	
	String message;
	private CustomExtentReports extreport;
	String expected,actual;
	
	public TestStatus(CustomExtentReports extreport){
		this.extreport=extreport;
	}
	
	/*private ExtentTest getExtentTest(){
		return extreport.getExtentTest();
	}*/


	@Override
	public void fail() {
		super.fail();
		extreport.getExtentTest().log(LogStatus.FAIL,"");
		
	}
	
	@Override
	public void fail(String message) {
		super.fail(message);
		
		extreport.getExtentTest().log(LogStatus.FAIL,message);
	}
	
	
	@Override
	public void fail(String message, Throwable realCause) {
		super.fail(message, realCause);
		extreport.getExtentTest().log(LogStatus.FAIL,realCause);
	}
	
	public void assertCondTrue(boolean cond, String msg){
		message=msg;
		assertTrue(cond, "");
	}
	
	
	public void assertContains(String str1, String str2, String msg) {
		message=msg;
		assertTrue(str1.contains(str2), "Verify if \"" + str1+"\"" + " contains \""+ str2+"\"");		
	}
	

	public void assertEqualsIgnorecase(String actual, String expected, String msg){
		message=msg;
		assertEquals(actual, expected, "Actual: \"" + actual+"\"" + "\tExpected: \""+ expected+"\"");

	}
	
	public void assertEqual(String actual, String expected, String msg){
		message=msg;
		assertEquals(actual, expected, "Actual: \""+actual+"\"" + "\tExpected: \""+expected+"\"");

	}
	
	public void assertEqual(int actual, int expected, String msg){
		message=msg;
		assertEquals(actual, expected, "Actual: \""+actual+"\"" + "\tExpected: \""+expected+"\"");

	}
	
	public void assertEqual(Double actual, Double expected, String msg){
		message=msg;
		assertEquals(actual, expected, "Actual: \""+actual+"\"" + "\tExpected: \""+expected+"\"");

	}
	
	
	@Override
	public void onAssertSuccess(IAssert assertCommand) {
		super.onAssertSuccess(assertCommand);
		extreport.getExtentTest().log(LogStatus.PASS, message, assertCommand.getMessage());
	}
	
	@Override
	public void onAssertFailure(IAssert assertCommand, AssertionError ex) {
		super.onAssertFailure(assertCommand, ex);
		extreport.getExtentTest().log(LogStatus.FAIL, message, assertCommand.getMessage());
	
	}


}
