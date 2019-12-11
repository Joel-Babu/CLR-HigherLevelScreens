package au.wow.WLPmain.pages;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.datetime.Format;
import org.apache.tools.ant.filters.TokenFilter.Trim;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.openqa.selenium.remote.server.handler.interactions.SendKeyToActiveElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import au.wow.WLPmain.objects.DTS_DepartmentObjects;
import au.wow.WLPmain.objects.DailyPlanningStoreObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.Click;
import com.thoughtworks.selenium.webdriven.commands.GetAttribute;
import com.thoughtworks.selenium.webdriven.commands.GetText;

public class DailyPlanningPage extends TestBase {
	CommonFunctions common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
	public Logger log = LogManager.getLogger(DailyPlanningPage.class);
	SQLWrapper sql = new SQLWrapper(log);
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil;
	static WebDriverWait wait;
	String image;

	public DailyPlanningPage() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Select Page Name
	public void VerifyPageName(DailyPlanningStoreObjects objDailyPlanningStoreObjects, ExtentTest extentTest,
			String Page) throws Exception {
		try {
			String[] Name = getInnerText(objDailyPlanningStoreObjects.ScreenName).split(":");
			System.out.println(Name[0]);
			if (Name[0].equalsIgnoreCase("" + Page + "")) {
				Report_AddStep("testcase", "Page Name is displayed Correctly as " + Page + "", "", "", "Pass");
				htmlToExtent(cName, mName, extentTest, driver1,"Page Name is displayed Correctly as " + Page + ";;;Pass");
			} else {
				Report_AddStep("testcase", "Failed to Display Page Name Correctly", "", "", "Fail");
				htmlToExtent(cName, mName, extentTest, driver1, "Failed to Display Page Name Correctly ; ; ;Fail");
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Display Page Name Correctly", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while Verifying Page Name ; ; ;Fail");
		}
	}

	// Select View
	public void SelectView(DailyPlanningStoreObjects objDailyPlanningStoreObjects, ExtentTest extentTest,
			String OptionView) throws Exception {
		try {
			// if(getText(objDailyPlanningStoreObjects.View).equalsIgnoreCase("Select
			// View"))
			{
				selectByVisibleText(objDailyPlanningStoreObjects.PageDropdown, OptionView);
				Report_AddStep("testcase", "" + OptionView + " is Selected Successfully from the Dropdown", "", "","Pass");
				htmlToExtent(cName, mName, extentTest, driver1,"" + OptionView + " is Selected Successfully from the Dropdown ;;;Pass");
			}
			/*
			 * else { image = TakeScreenshot(extentTest, "HistoryDropDown");
			 * extentTest.log(LogStatus.FAIL,
			 * "Failed to Select View Option from DropDown",image);
			 * Report_AddStep("testcase","Failed to Select View Option from DropDown"
			 * ,"","", "Fail"); htmlToExtent(cName,mName,extentTest,driver1,
			 * "Page Name is displayed Correctly as "+Page+";;;Fail"); }
			 */
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Select View Option from DropDown", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while Selecting View ;;;Fail");
		}
	}

	// Select Department
	public void SelectDepartment(DailyPlanningStoreObjects objDailyPlanningStoreObjects, ExtentTest extentTest,
			String Department) throws Exception {
		try {
			if ((getText(objDailyPlanningStoreObjects.DepartmentHeader).contains("Departments"))) {
				selectByValue(objDailyPlanningStoreObjects.DepartmentDropdown, Department);
				Report_AddStep("testcase", "Department To Display is Selected Successfully from the Dropdown", "", "","Pass");
				htmlToExtent(cName, mName, extentTest, driver1,"" + Department + " is Selected Successfully from the Department Dropdown ;;;Pass");
			} else {
				Report_AddStep("testcase", "Failed to Select Department Option from DropDown", "", "", "Fail");
				htmlToExtent(cName, mName, extentTest, driver1,"Failed to select " + Department + " from the Department Dropdown ;;;Fail");
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Select History Option from DropDown", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while selecting Drop down;;;Fail");
		}
	}

	// Select View
	public void RadioButton(ExtentTest extentTest, String ButtonToSelect, WebElement Option) throws Exception {
		try {
			if (isElementPresent(ButtonToSelect)) {
				click(Option);
				Report_AddStep("testcase", "Radio Button is selected Successfully", "", "", "Pass");
				htmlToExtent(cName, mName, extentTest, driver1,"" + Option + ": Radio Button is selected Successfully ;;;Pass");
			} else {
				Report_AddStep("testcase", "Failed to Select Radio Button", "", "", "Fail");
				htmlToExtent(cName, mName, extentTest, driver1,"Failed to select " + Option + ": Radio Button ;;;Fail");
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Switch Frame for Selecting Radio Button", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while selecting Radio Button ;;;Fail");
		}
	}

	// Get UI Value to Compare with DB Value
	public List<List<Object>> UIResults(DailyPlanningStoreObjects objDailyPlanningStoreObjects, String Table,
			String Departments, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday,
			String Saturday, String Sunday) {
		try {
			int i, columnsize = 3;
			WebElement Department = null, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt,
					SaturdayElmt, SundayElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun = null;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);

			for (i = 0; i <= RowCount-2 ; i = i + 1) {
				List<Object> row = new ArrayList<>(columnsize);
				System.out.println(i);

				// To Get Department and Store Total Column Names
				
				
					
				
				
				Department = prepareWebElementWithDynamicXpathWithInt(Departments, i);

				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);

				Dept = getText(Department);
				Mon = getText(MondayElmt);
				Tue = getText(TuesdayElmt);
				Wed = getText(WednesdayElmt);
				Thu = getText(ThursdayElmt);
				Fri = getText(FridayElmt);
				Sat = getText(SaturdayElmt);
				Sun = getText(SundayElmt);

				MonVal = TrimData(Mon);
				TueVal = TrimData(Tue);
				WedVal = TrimData(Wed);
				ThuVal = TrimData(Thu);
				FriVal = TrimData(Fri);
				SatVal = TrimData(Sat);
				SunVal = TrimData(Sun);

				row.add(Dept);
				row.add(MonVal);
				row.add(TueVal);
				row.add(WedVal);
				row.add(ThuVal);
				row.add(FriVal);
				row.add(SatVal);
				row.add(SunVal);

				UIValues.add(row);
			}
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while fetching data");
			e.printStackTrace();
		}
		return null;
	}

	// Get Week to Date
	public List<List<Object>> WkToDtValue(DailyPlanningStoreObjects objDailyPlanningStoreObjects, String Table,
			String Departments, String WeekToDate) {
		try {
			int i, columnsize = 3;
			WebElement Department = null, WeekToDateElmt;
			String Dept, WkToDate = null;
			double WeekToDateVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);

			for (i = 0; i <= RowCount - 2; i = i + 1) {
				List<Object> row = new ArrayList<>(columnsize);
				System.out.println(i);

				// To Get Department and Store Total Column Names
				Department = prepareWebElementWithDynamicXpathWithInt(Departments, i);
				WeekToDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);

				Dept = getText(Department);
				WkToDate = getText(WeekToDateElmt);
				WeekToDateVal = TrimData(WkToDate);
				row.add(Dept);
				row.add(WeekToDateVal);
				UIValues.add(row);
			}
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total
	public List<List<Object>> UITotal(DailyPlanningStoreObjects objDailyPlanningStoreObjects, String Total,
			String MondayTtl, String TuesdayTtl, String WednesdayTtl, String ThursdayTtl, String FridayTtl,
			String SaturdayTtl, String SundayTtl) {
		try {
			int columnsize = 3;
			WebElement Department, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt, SaturdayElmt,
					SundayElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun = null;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// To Get Department and Store Total Column Names
			Department = prepareWebElementWithDynamicXpathWithInt(Total);
			MondayElmt = prepareWebElementWithDynamicXpathWithInt(MondayTtl);
			TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(TuesdayTtl);
			WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(WednesdayTtl);
			ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayTtl);
			FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayTtl);
			SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayTtl);
			SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayTtl);

			Dept = getText(Department);
			Mon = getText(MondayElmt);
			Tue = getText(TuesdayElmt);
			Wed = getText(WednesdayElmt);
			Thu = getText(ThursdayElmt);
			Fri = getText(FridayElmt);
			Sat = getText(SaturdayElmt);
			Sun = getText(SundayElmt);

			MonVal = TrimData(Mon);
			TueVal = TrimData(Tue);
			WedVal = TrimData(Wed);
			ThuVal = TrimData(Thu);
			FriVal = TrimData(Fri);
			SatVal = TrimData(Sat);
			SunVal = TrimData(Sun);

			row.add(Dept);
			row.add(MonVal);
			row.add(TueVal);
			row.add(WedVal);
			row.add(ThuVal);
			row.add(FriVal);
			row.add(SatVal);
			row.add(SunVal);

			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception While Getting Week To Date Value");
			e.printStackTrace();
		}
		return null;
	}

	// Get Week to Date Total
	public List<List<Object>> WkToDtTotal(DailyPlanningStoreObjects objDailyPlanningStoreObjects, String Total,
			String WeekToDate) {
		try {
			int columnsize = 3;
			WebElement Department = null, WeekToDateElmt;
			String Dept, WkToDate = null;
			double WeekToDateVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// To Get Department and Store Total Column Names
			Department = prepareWebElementWithDynamicXpathWithInt(Total);
			WeekToDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate);

			Dept = getText(Department);
			WkToDate = getText(WeekToDateElmt);
			WeekToDateVal = TrimData(WkToDate);
			row.add(Dept);
			row.add(WeekToDateVal);
			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception While Getting Week To Date Total Value");
			e.printStackTrace();
		}
		return null;
	}

	// Log OFF
	public void LogOFF(DailyPlanningStoreObjects objDailyPlanningStoreObjects) throws IOException {
		try {
			if (isDisplayed(objDailyPlanningStoreObjects.Logoff)) {
				click(objDailyPlanningStoreObjects.Logoff);
				Report_AddStep("testcase", "Logged Out of Application Successfully", "", "", "Pass");
				htmlToExtent(cName, mName, extentTest, driver1, "Logged Out of Application Successfully ;;;Pass");
			} else {
				Report_AddStep("testcase", "Failed to Log OFF Application", "", "", "Fail");
				htmlToExtent(cName, mName, extentTest, driver1, "Failed to Log OFF Application ;;;Fail");
			}
		} catch (Exception e) {
			Report_AddStep("testcase", "Failed to Log OFF Application", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Failed to Log OFF Application ;;;Fail");
		}
	}
}