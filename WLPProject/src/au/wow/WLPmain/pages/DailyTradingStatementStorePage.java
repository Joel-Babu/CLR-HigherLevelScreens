package au.wow.WLPmain.pages;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.DailyTradingStatementZoneObjects;
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

public class DailyTradingStatementStorePage extends TestBase {
	CommonFunctions common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
	public Logger log = LogManager.getLogger(DailyTradingStatementStorePage.class);
	SQLWrapper sql = new SQLWrapper(log);
	DailyTradingStatementObjects objDailyTradingStatementObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil;
	static WebDriverWait wait;
	String image;

	public DailyTradingStatementStorePage() {
		super();
	}

	// Iteration Values
	public int CurrentloopValue(int i, String FieldName) {
		if (FieldName.equalsIgnoreCase("Sales")) {
			if (i == 74)
				i = i + 1;
			if (i == 70 || i == 72)
				i = i - 5;
		}
		if (FieldName.equalsIgnoreCase("SMS")) {
			if (i == 64)
				i = i + 4;
			if (i == 75)
				i = i + 1;
		}
		if (FieldName.equalsIgnoreCase("Hours")) {
			if (i == 76)
				i = i + 1;
			if (i == 65)
				i = i + 4;
		}
		if (FieldName.equalsIgnoreCase("Wages")) {
			if (i == 78)
				i = i + 1;
			if (i == 67)
				i = i + 4;
		}
		if (FieldName.equalsIgnoreCase("OR")) {
			if (i == 66)
				i = i + 4;
			if (i == 77)
				i = i + 1;
		}
		if (FieldName.equalsIgnoreCase("WagesPercentage")) {
			if (i == 79)
				i = i + 1;
			if (i == 68)
				i = i + 4;
		}
		if (FieldName.equalsIgnoreCase("Items")) {
			if (i == 69 || i == 71)
				i = i - 5;
			if (i == 80)
				i = i + 1;
		}
		if (FieldName.equalsIgnoreCase("Customers")) {
			if (i == 81)
				i = i + 1;
		}
		return i;
	}

	// Get UI Value Single column
	public List<List<Object>> CurrentWeekToDateData(String Value,
			DailyTradingStatementObjects objDailyTradingStatementObjects, String WeekToDate) {
		try {
			int i, initial = 0, columnsize = 1;
			WebElement Department = null, WeektoDateElmt;
			String Dept, FullWeek = null;
			double FullWeekVal;
			SwitchFrame(objDailyTradingStatementObjects.DataFrame);
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount();

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				initial = 0;
			if (Value.equalsIgnoreCase("SMS"))
				initial = 1;
			if (Value.equalsIgnoreCase("Hours"))
				initial = 2;
			if (Value.equalsIgnoreCase("OR"))
				initial = 3;
			if (Value.equalsIgnoreCase("Wages"))
				initial = 4;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				initial = 5;
			if (Value.equalsIgnoreCase("Items"))
				initial = 6;
			if (Value.equalsIgnoreCase("Customers"))
				initial = 74;

			for (i = initial; i <= RowCount - 8; i = i + 7) {
				List<Object> row = new ArrayList<>(columnsize);
				i = CurrentloopValue(i, Value);
				System.out.println(i);
				if (i > 75 && Value.equalsIgnoreCase("Sales"))
					break;
				if (i > 76 && Value.equalsIgnoreCase("SMS"))
					break;
				if (i > 77 && Value.equalsIgnoreCase("Hours"))
					break;
				if (i > 78 && Value.equalsIgnoreCase("OR"))
					break;
				if (i > 79 && Value.equalsIgnoreCase("Wages"))
					break;
				if (i > 80 && Value.equalsIgnoreCase("WagesPercentage"))
					break;
				if (i > 81 && Value.equalsIgnoreCase("Items"))
					break;
				if (i > 82 && Value.equalsIgnoreCase("Customers"))
					break;

				// To Get Department and Store Total Column Names
				if (Value.equalsIgnoreCase("Sales"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i);
				if (Value.equalsIgnoreCase("SMS"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 1);
				if (Value.equalsIgnoreCase("Customers"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 7);
				if (Value.equalsIgnoreCase("Hours")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 2);
				}
				if (Value.equalsIgnoreCase("OR")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 3);
				}
				if (Value.equalsIgnoreCase("Wages")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 4);
				}
				if (Value.equalsIgnoreCase("WagesPercentage")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 5);
				}
				if (Value.equalsIgnoreCase("Items")) {
					if (i == 64 || i == 66)
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 1);
					else
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 6);
				}

				String Element = WeekToDate.replace("dynamic", "" + i);

				if (isElementPresent(Element))
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
				else if (Value.equalsIgnoreCase("Sales"))
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
							objDailyTradingStatementObjects.WeekToDateActlSalesYellow, i);
				else
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
							objDailyTradingStatementObjects.WeekToDateActlValueYellow, i);

				Dept = getText(Department);
				FullWeek = getText(WeektoDateElmt);

				FullWeekVal = TrimData(FullWeek);

				row.add(Dept);
				row.add(FullWeekVal);

				UIValues.add(row);
			}
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while getting DTS Screen Data");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CurrentWeekToDateTotal(String Value,
			DailyTradingStatementObjects objDailyTradingStatementObjects, String WeekToDate) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, WeektoDateElmt;
			String Dept, FullWeek = null;
			double FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				i = 83;
			if (Value.equalsIgnoreCase("SMS"))
				i = 84;
			if (Value.equalsIgnoreCase("Hours"))
				i = 85;
			if (Value.equalsIgnoreCase("OR"))
				i = 86;
			if (Value.equalsIgnoreCase("Wages"))
				i = 87;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				i = 88;
			if (Value.equalsIgnoreCase("Items"))
				i = 89;
			if (Value.equalsIgnoreCase("Customers"))
				i = 90;

			// To Get Department and Store Total Column Names

			Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal, 83);
			String Element = WeekToDate.replace("dynamic", "" + i);
			if (isElementPresent(Element))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
			else if (Value.equalsIgnoreCase("Sales"))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlSalesYellow, i);
			else
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlValueYellow, i);

			Dept = getText(Department);
			FullWeek = getText(WeektoDateElmt);

			FullWeekVal = TrimData(FullWeek);

			row.add(Dept);
			row.add(FullWeekVal);

			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Compare UI and DB values
	public void DTSCompareValues(ExtentTest extentTest, List<List<Object>> DBValues, List<List<Object>> UIValues)
			throws Exception {
		try {
			int RowCount = DBValues.size();
			double CalcuatedUIValue = 0, CalcuatedDBValue = 0, RoundOffValue = 0, RoundOffValue1 = 0;
			boolean result = false;
			String DBName = null, UIName = null;
			BigDecimal FinalDBValue = null, FinalUIValue = null;
			System.out.println(UIValues);
			System.out.println(DBValues);

			for (int i = 0; i <= RowCount - 1; i++) {
				for (int j = 0; j < 8; j++) {
					if (j != 0) {

						CalcuatedDBValue = new Double(DBValues.get(i).get(j).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);
						FinalDBValue = ConvertBigDecimal(CalcuatedDBValue);
						System.out.println(FinalDBValue);

						CalcuatedUIValue = new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue = ConvertBigDecimal(CalcuatedUIValue);
						System.out.println(FinalUIValue);
						BigDecimal biggy = FinalUIValue.subtract(FinalDBValue);
						int Variance = biggy.intValue();
						if (Variance <= 3 && Variance >= -3) {
							Report_AddStep("testcase","System Fetch the Values from DB for : " + UIName + " and Displayed Correctly","" + CalcuatedDBValue + "", "" + CalcuatedUIValue + "", "Pass");
							htmlToExtent(cName, mName, extentTest, driver1,"Value displayed Correctly for : " + UIName + "; " + CalcuatedUIValue+ "; " + CalcuatedDBValue + ";Pass");
						} else {
							Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly " + UIName + "","" + CalcuatedUIValue + "", "" + CalcuatedDBValue + "", "Fail");
							htmlToExtent(cName, mName, extentTest, driver1,"Failed to display data Correctly for : " + UIName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Fail");
						}
					} else {
						UIName = new String(UIValues.get(i).get(j).toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Verify UI and DB Values", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	// Log OFF
	public void LogOFF(DailyTradingStatementObjects objDailyTradingStatementObjects) throws IOException {
		try {
			if (isDisplayed(objDailyTradingStatementObjects.Logoff)) {
				click(objDailyTradingStatementObjects.Logoff);
				Report_AddStep("testcase", "Logged Out of Application Successfully", "", "", "Pass");
				htmlToExtent(cName, mName, extentTest, driver1, "Logged Out of Application Successfully ;;;Pass");
			} else {
				Report_AddStep("testcase", "Failed to Log OFF Application", "", "", "Fail");
				htmlToExtent(cName, mName, extentTest, driver1, "Failed to Log OFF Application ;;;Fail");
			}
		} catch (Exception e) {
			image = TakeScreenshot(extentTest, "LogOFF");
			Report_AddStep("testcase", "Failed to Log OFF Application", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Failed to Log OFF Application ;;;Fail");
		}
	}

	// Get UI Value to Compare with DB Value
	public List<List<Object>> CurrentDayWiseUIData(String Value, String Column,
			DailyTradingStatementObjects objDailyTradingStatementObjects) {
		try {
			int i, initial = 0, columnsize = 1;
			WebElement Department = null, DayElmt;
			String Dept, Day = null;
			double DayVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount();
			List<Object> row;

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				initial = 0;
			if (Value.equalsIgnoreCase("SMS"))
				initial = 1;
			if (Value.equalsIgnoreCase("Hours"))
				initial = 2;
			if (Value.equalsIgnoreCase("OR"))
				initial = 3;
			if (Value.equalsIgnoreCase("Wages"))
				initial = 4;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				initial = 5;
			if (Value.equalsIgnoreCase("Items"))
				initial = 6;
			if (Value.equalsIgnoreCase("Customers"))
				initial = 74;

			for (i = initial; i <= RowCount - 8; i = i + 7) {
				i = CurrentloopValue(i, Value);
				System.out.println(i);
				row = new ArrayList<>(columnsize);

				// To Break the loop after fetching all Department Values
				if (i > 75 && Value.equalsIgnoreCase("Sales"))
					break;
				if (i > 76 && Value.equalsIgnoreCase("SMS"))
					break;
				if (i > 77 && Value.equalsIgnoreCase("Hours"))
					break;
				if (i > 78 && Value.equalsIgnoreCase("OR"))
					break;
				if (i > 79 && Value.equalsIgnoreCase("Wages"))
					break;
				if (i > 80 && Value.equalsIgnoreCase("WagesPercentage"))
					break;
				if (i > 81 && Value.equalsIgnoreCase("Items"))
					break;
				if (i > 82 && Value.equalsIgnoreCase("Customers"))
					break;

				// To Get Department and Store Total Column Names
				if (Value.equalsIgnoreCase("Sales"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i);
				if (Value.equalsIgnoreCase("SMS"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 1);
				if (Value.equalsIgnoreCase("Customers"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 7);
				if (Value.equalsIgnoreCase("Hours")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 2);
				}
				if (Value.equalsIgnoreCase("OR")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 3);
				}
				if (Value.equalsIgnoreCase("Wages")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 4);
				}
				if (Value.equalsIgnoreCase("WagesPercentage")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 5);
				}
				if (Value.equalsIgnoreCase("Items")) {
					if (i == 64 || i == 66)
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 1);
					else
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 6);
				}

				// Add department into list
				Dept = getText(Department);
				row.add(Dept);

				for (int k = 0; k < 7; k++) {
					String Datej = ("Datek".replace("k", "" + k));
					DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects, Datej, Column,
							i);
					Day = getText(DayElmt);
					DayVal = TrimData(Day);
					row.add(DayVal);
				}
				UIValues.add(row);
			}
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while fetching DTS Data");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CurrentDayWiseUITotal(String Value, String Column,
			DailyTradingStatementObjects objDailyTradingStatementObjects) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, DayElmt;
			String Dept, Day = null;
			double DayVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				i = 83;
			if (Value.equalsIgnoreCase("SMS"))
				i = 84;
			if (Value.equalsIgnoreCase("Hours"))
				i = 85;
			if (Value.equalsIgnoreCase("OR"))
				i = 86;
			if (Value.equalsIgnoreCase("Wages"))
				i = 87;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				i = 88;
			if (Value.equalsIgnoreCase("Items"))
				i = 89;
			if (Value.equalsIgnoreCase("Customers"))
				i = 90;

			// To Get Department and Store Total Column Names
			Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal, 83);
			Dept = getText(Department);
			row.add(Dept);
			for (int k = 0; k < 7; k++) {
				String Datej = ("Datek".replace("k", "" + k));
				DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects, Datej, Column, i);
				Day = getText(DayElmt);
				DayVal = TrimData(Day);
				row.add(DayVal);
			}
			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	/////////////////////////////////////////////// Alternate for Getting UI Data
	/////////////////////////////////////////////// for Week days
	/////////////////////////////////////////////// //////////////////////////////////////////////////

	// Get UI Value to Compare with DB Value
	public List<List<Object>> UIResults(String Value, DailyTradingStatementObjects objDailyTradingStatementObjects,
			String Monday, String Tuesday, String Wednesday, String Thursday, String ThursdayWhite, String Friday,
			String FridayWhite, String Saturday, String SaturdayWhite, String Sunday, String SundayWhite) {
		try {
			int i, initial = 0, columnsize = 3;
			WebElement Department = null, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt,
					SaturdayElmt, SundayElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(objDailyTradingStatementObjects.Table);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				initial = 0;
			if (Value.equalsIgnoreCase("SMS"))
				initial = 1;
			if (Value.equalsIgnoreCase("Hours"))
				initial = 2;
			if (Value.equalsIgnoreCase("OR"))
				initial = 3;
			if (Value.equalsIgnoreCase("Wages"))
				initial = 4;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				initial = 5;
			if (Value.equalsIgnoreCase("Items"))
				initial = 6;
			if (Value.equalsIgnoreCase("Customers"))
				initial = 74;

			for (i = initial; i <= RowCount - 8; i = i + 7) {
				List<Object> row = new ArrayList<>(columnsize);
				i = CurrentloopValue(i, Value);
				System.out.println(i);
				if (i > 75 && Value.equalsIgnoreCase("Sales"))
					break;
				if (i > 76 && Value.equalsIgnoreCase("SMS"))
					break;
				if (i > 77 && Value.equalsIgnoreCase("Hours"))
					break;
				if (i > 78 && Value.equalsIgnoreCase("OR"))
					break;
				if (i > 79 && Value.equalsIgnoreCase("Wages"))
					break;
				if (i > 80 && Value.equalsIgnoreCase("WagesPercentage"))
					break;
				if (i > 81 && Value.equalsIgnoreCase("Items"))
					break;
				if (i > 82 && Value.equalsIgnoreCase("Customers"))
					break;

				// To Get Department and Store Total Column Names
				if (Value.equalsIgnoreCase("Sales"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i);
				if (Value.equalsIgnoreCase("SMS"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 1);
				if (Value.equalsIgnoreCase("Customers"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 7);
				if (Value.equalsIgnoreCase("Hours")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 2);
				}
				if (Value.equalsIgnoreCase("OR")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 3);
				}
				if (Value.equalsIgnoreCase("Wages")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 4);
				}
				if (Value.equalsIgnoreCase("WagesPercentage")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 5);
				}
				if (Value.equalsIgnoreCase("Items")) {
					if (i == 64 || i == 66)
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 1);
					else
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 6);
				}

				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);

				String ThursdayWt = ThursdayWhite.replace("dynamic", "" + i);
				String FridayWt = FridayWhite.replace("dynamic", "" + i);
				String SaturdayWt = SaturdayWhite.replace("dynamic", "" + i);
				String SundayWt = SundayWhite.replace("dynamic", "" + i);

				if (isElementPresent(ThursdayWt))
					ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayWhite, i);
				else
					ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				if (isElementPresent(FridayWt))
					FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayWhite, i);
				else
					FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				if (isElementPresent(SaturdayWt))
					SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayWhite, i);
				else
					SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				if (isElementPresent(SundayWt))
					SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayWhite, i);
				else
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

	// Get UI Total to Compare with DB Value
	public List<List<Object>> UITotal(String Value, DailyTradingStatementObjects objDailyTradingStatementObjects,
			String Monday, String Tuesday, String Wednesday, String Thursday, String ThursdayWhite, String Friday,
			String FridayWhite, String Saturday, String SaturdayWhite, String Sunday, String SundayWhite,
			String Table) {
		try {
			int i = 0, columnsize = 3;
			WebElement Department, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt, SaturdayElmt,
					SundayElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				i = 83;
			if (Value.equalsIgnoreCase("SMS"))
				i = 84;
			if (Value.equalsIgnoreCase("Hours"))
				i = 85;
			if (Value.equalsIgnoreCase("OR"))
				i = 86;
			if (Value.equalsIgnoreCase("Wages"))
				i = 87;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				i = 88;
			if (Value.equalsIgnoreCase("Items"))
				i = 89;
			if (Value.equalsIgnoreCase("Customers"))
				i = 90;

			// To Get Department and Store Total Column Names

			Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal, 83);
			MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
			TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
			WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
			String ThursdayWt = ThursdayWhite.replace("dynamic", "" + i);
			String FridayWt = FridayWhite.replace("dynamic", "" + i);
			String SaturdayWt = SaturdayWhite.replace("dynamic", "" + i);
			String SundayWt = SundayWhite.replace("dynamic", "" + i);

			if (isElementPresent(ThursdayWt))
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayWhite, i);
			else
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
			if (isElementPresent(FridayWt))
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayWhite, i);
			else
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
			if (isElementPresent(SaturdayWt))
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayWhite, i);
			else
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
			if (isElementPresent(SundayWt))
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayWhite, i);
			else
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
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while fetching data");
			e.printStackTrace();
		}
		return null;
	}

	// Compare UI and DB values
	public void DTSCompareValuesTotal(ExtentTest extentTest, List<List<Object>> DBValues, List<List<Object>> UIValues)
			throws Exception {
		try {
			int RowCount = DBValues.size();
			String WeekName = null;
			double CalcuatedUIValue = 0, CalcuatedDBValue = 0, RoundOffValue = 0, RoundOffValue1 = 0;
			BigDecimal FinalDBValue = null, FinalUIValue = null;
			System.out.println(UIValues);
			System.out.println(DBValues);

			for (int i = 0; i <= RowCount - 1; i++) {
				for (int j = 0; j < 8; j++) {
					if (j != 0) {
						CalcuatedDBValue = new Double(DBValues.get(i).get(j).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);
						FinalDBValue = ConvertBigDecimal(CalcuatedDBValue);
						System.out.println("FinalDBValue" + FinalDBValue);

						CalcuatedUIValue = new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue = ConvertBigDecimal(CalcuatedUIValue);
						System.out.println("FinalUIValue" + FinalUIValue);
						BigDecimal biggy = FinalUIValue.subtract(FinalDBValue);
						int Variance = biggy.intValue();
						if (Variance <= 3 && Variance >= -3) {
							Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: " + WeekName + "","" + FinalUIValue + "", "" + FinalDBValue + "", "Pass");
							htmlToExtent(cName, mName, extentTest, driver1,"Value displayed Correctly for : " + WeekName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Pass");
						} else {
							Report_AddStep("testcase", "Calculated DB values are Not Matching for: " + WeekName + "","" + FinalUIValue + "", "" + FinalDBValue + "", "Fail");
							htmlToExtent(cName, mName, extentTest, driver1,"Failed to display data Correctly for : " + WeekName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Fail");
						}
					} else {
						WeekName = new String(UIValues.get(i).get(j).toString());
					}

				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Verify UI and DB Values", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	// Compare UI and DB values
	public void CompareValueDecimalFields(ExtentTest extentTest, List<List<Object>> DBValues,
			List<List<Object>> UIValues) throws Exception {
		try {
			int RowCount = DBValues.size();
			double CalcuatedUIValue = 0, CalcuatedDBValue = 0, RoundOffValue = 0, RoundOffValue1 = 0;
			boolean result = false;
			String DBName = null, UIName = null;
			BigDecimal FinalDBValue = null, FinalUIValue = null;
			System.out.println(UIValues);
			System.out.println(DBValues);

			for (int i = 0; i <= RowCount - 1; i++) {
				for (int j = 0; j < 8; j++) {
					if (j != 0) {

						CalcuatedDBValue = new Double(DBValues.get(i).get(j).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);
						FinalDBValue = ConvertBigDecimal(CalcuatedDBValue);
						System.out.println(FinalDBValue);

						CalcuatedUIValue = new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue = ConvertBigDecimal(CalcuatedUIValue);
						System.out.println(FinalUIValue);
						BigDecimal biggy = FinalUIValue.subtract(FinalDBValue);
						double Variance = biggy.doubleValue();
						if (Variance <= 0.1 && Variance >= -0.1) {
							Report_AddStep("testcase","System Fetch the Values from DB for : " + UIName + " and Displayed Correctly","" + CalcuatedDBValue + "", "" + CalcuatedUIValue + "", "Pass");
							htmlToExtent(cName, mName, extentTest, driver1,"Value displayed Correctly for : " + UIName + "; " + CalcuatedUIValue+ "; " + CalcuatedDBValue + ";Pass");
						} else {
							Report_AddStep("testcase","Failed to Display the UI and DB Values Correctly " + UIName + "","" + CalcuatedUIValue + "", "" + CalcuatedDBValue + "", "Fail");
							htmlToExtent(cName, mName, extentTest, driver1,"Failed to display data Correctly for : " + UIName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Fail");
						}
					} else {
						UIName = new String(UIValues.get(i).get(j).toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Verify UI and DB Values", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	// Compare UI and DB values
	public void CompareValueDecimalTotal(ExtentTest extentTest, List<List<Object>> DBValues,
			List<List<Object>> UIValues) throws Exception {
		try {
			int RowCount = DBValues.size();
			String WeekName = null, DBName = null;
			double CalcuatedUIValue = 0, CalcuatedDBValue = 0, RoundOffValue = 0, RoundOffValue1 = 0;
			BigDecimal FinalDBValue = null, FinalUIValue = null;
			System.out.println(UIValues);
			System.out.println(DBValues);

			for (int i = 0; i <= RowCount - 1; i++) {
				for (int j = 0; j < 8; j++) {
					if (j != 0) {
						CalcuatedDBValue = new Double(DBValues.get(i).get(j).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);
						FinalDBValue = ConvertBigDecimal(CalcuatedDBValue);
						System.out.println("FinalDBValue" + FinalDBValue);

						CalcuatedUIValue = new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue = ConvertBigDecimal(CalcuatedUIValue);
						System.out.println("FinalUIValue" + FinalUIValue);
						BigDecimal biggy = FinalUIValue.subtract(FinalDBValue);
						double Variance = biggy.doubleValue();
						if (Variance <= 0.1 && Variance >= -0.1) {
							Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: " + WeekName + "","" + FinalUIValue + "", "" + FinalDBValue + "", "Pass");
							htmlToExtent(cName, mName, extentTest, driver1,"Value displayed Correctly for : " + WeekName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Pass");
						} else {
							Report_AddStep("testcase", "Calculated DB values are Not Matching for: " + WeekName + "","" + FinalUIValue + "", "" + FinalDBValue + "", "Fail");
							htmlToExtent(cName, mName, extentTest, driver1,"Failed to display data Correctly for : " + WeekName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Fail");

						}
					} else {
						WeekName = new String(UIValues.get(i).get(j).toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Verify UI and DB Values", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	// Compare UI and DB OR and Hours
	public void CompareValueDecimalORTotal(ExtentTest extentTest, List<List<Object>> DBValues,
			List<List<Object>> UIValues) throws Exception {
		try {
			int RowCount = DBValues.size();
			String WeekName = null;
			double CalcuatedUIValue = 0, CalcuatedDBValue = 0, RoundOffValue = 0, RoundOffValue1 = 0;
			BigDecimal FinalDBValue = null, FinalUIValue = null;
			System.out.println(UIValues);
			System.out.println(DBValues);

			for (int i = 0; i <= RowCount - 1; i++) {
				for (int j = 0; j < 8; j++) {
					if (j != 0) {
						CalcuatedDBValue = new Double(DBValues.get(i).get(j).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);
						FinalDBValue = ConvertBigDecimalSingleDigit(CalcuatedDBValue);
						System.out.println("FinalDBValue" + FinalDBValue);

						CalcuatedUIValue = new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue = ConvertBigDecimalSingleDigit(CalcuatedUIValue);
						System.out.println("FinalUIValue" + FinalUIValue);
						BigDecimal biggy = FinalUIValue.subtract(FinalDBValue);
						double Variance = biggy.doubleValue();
						if (Variance <= 0.3 && Variance >= -0.3) {
							Report_AddStep("testcase","Calculated DB values are displayed Correctly in UI for: " + WeekName + "","" + FinalUIValue + "", "" + FinalDBValue + "", "Pass");
							htmlToExtent(cName, mName, extentTest, driver1,"Value displayed Correctly for : " + WeekName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Pass");
						} else {
							Report_AddStep("testcase", "Calculated DB values are Not Matching for: " + WeekName + "","" + FinalUIValue + "", "" + FinalDBValue + "", "Fail");
							htmlToExtent(cName, mName, extentTest, driver1,"Failed to display data Correctly for : " + WeekName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Fail");
						}
					} else {
						WeekName = new String(UIValues.get(i).get(j).toString());
					}

				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Verify UI and DB Values", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	////////////////////////////////////////////////////////////////////////// PAST
	////////////////////////////////////////////////////////////////////////// WEEK
	////////////////////////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////////
	// Get UI Value Single column
	public List<List<Object>> WeekToDateData(String Value,
			DailyTradingStatementObjects objDailyTradingStatementZoneObjects, String WeekToDate) {
		try {
			int i, initial = 8, columnsize = 1;
			WebElement Department = null, WeektoDateElmt;
			String Dept, FullWeek = null;
			double FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount();

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				initial = 8;
			if (Value.equalsIgnoreCase("SMS"))
				initial = 9;
			if (Value.equalsIgnoreCase("Hours"))
				initial = 10;
			if (Value.equalsIgnoreCase("OR"))
				initial = 11;
			if (Value.equalsIgnoreCase("Wages"))
				initial = 12;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				initial = 13;
			if (Value.equalsIgnoreCase("Items"))
				initial = 14;
			if (Value.equalsIgnoreCase("Customers"))
				initial = 106;

			for (i = initial; i <= RowCount - 8; i = i + 7) {
				List<Object> row = new ArrayList<>(columnsize);
				// Sales
				if (i == 78)
					i = i - 5;
				else if (i == 80)
					i = i - 5;
				else if (i == 82)
					i = i ;
				else if (i == 89)
					i = i + 1;
				
			
				// Wage%
				else if (i == 76)
					i = i + 4;
				else if (i == 87)
					i = i ;
				else if (i == 94)
					i = i + 1;
				// sms
				else if (i == 72)
					i = i + 4;
				else if (i == 83)
					i = i ;
				else if (i == 90)
					i = i +1 ;
				// Hours
				else if (i == 73)
					i = i + 4;
				else if (i == 84)
					i = i ;
				else if (i == 91)
					i = i + 1;
				// OR%
				else if (i == 74)
					i = i + 4;
				else if (i == 85)
					i = i ;
				else if (i == 92)
					i = i + 1;
				// Wages
				else if (i == 75)
					i = i + 4;
				else if (i == 86)
					i = i ;
				else if (i == 93)
					i = i + 1;
				// Items
				else if (i == 77)
					i = i - 5;
				else if (i == 79)
					i = i - 5;

				else if (i == 88)
					i = i  ;

				else if (i == 95)
					i = i + 1;
				// customers
				else if (i == 106)
					i = i - 17;
				else if (i == 96)
					i = i + 1;

				System.out.println(i);
				if (i > 90 && Value.equalsIgnoreCase("Sales"))
					break;
				if (i > 91 && Value.equalsIgnoreCase("SMS"))
					break;
				if (i > 92 && Value.equalsIgnoreCase("Hours"))
					break;
				if (i > 93 && Value.equalsIgnoreCase("OR"))
					break;
				if (i > 94 && Value.equalsIgnoreCase("Wages"))
					break;
				if (i > 95 && Value.equalsIgnoreCase("WagesPercentage"))
					break;
				if (i > 96 && Value.equalsIgnoreCase("Items"))
					break;
				if (i > 106 && Value.equalsIgnoreCase("Customers"))
					break;
				// To Get Department and Store Total Column Names
				if (Value.equalsIgnoreCase("Sales"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i);
				if (Value.equalsIgnoreCase("SMS"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 1);
				if (Value.equalsIgnoreCase("Customers"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i-7 );
				if (Value.equalsIgnoreCase("Hours")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 2);
				}
				if (Value.equalsIgnoreCase("OR")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 3);
				}
				if (Value.equalsIgnoreCase("Wages")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 4);
				}
				if (Value.equalsIgnoreCase("WagesPercentage")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 5);
				}
				if (Value.equalsIgnoreCase("Items")) {
					if (i == 72 || i == 74)
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 1);
					else
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 6);
				}

				String Element = WeekToDate.replace("dynamic", "" + i);

				if (isElementPresent(Element))
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
				else if (Value.equalsIgnoreCase("Sales"))
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
							objDailyTradingStatementObjects.WeekToDateActlSalesYellow, i);
				else
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
							objDailyTradingStatementObjects.WeekToDateActlValueYellow, i);

				Dept = getText(Department);
				FullWeek = getText(WeektoDateElmt);

				FullWeekVal = TrimData(FullWeek);

				row.add(Dept);
				row.add(FullWeekVal);

				UIValues.add(row);
			}
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while getting DTS Screen Data");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> WeekToDateTotal(String Value,
			DailyTradingStatementObjects objDailyTradingStatementZoneObjects, String WeekToDate) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, WeektoDateElmt;
			String Dept, FullWeek = null;
			double FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// To Get Department and Store Total Column Names
			if (Value.equalsIgnoreCase("Sales"))
				i = 0;
			if (Value.equalsIgnoreCase("SMS"))
				i = 1;
			if (Value.equalsIgnoreCase("Hours"))
				i = 2;
			if (Value.equalsIgnoreCase("OR"))
				i = 3;
			if (Value.equalsIgnoreCase("Wages"))
				i = 4;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				i = 5;
			if (Value.equalsIgnoreCase("Items"))
				i = 6;
			if (Value.equalsIgnoreCase("Customers"))
				i = 7;
			// To Get Department and Store Total Column Names

			Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal, 0);
			String Element = WeekToDate.replace("dynamic", "" + i);
			if (isElementPresent(Element))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
			else if (Value.equalsIgnoreCase("Sales"))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlSalesYellow, i);
			else
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlValueYellow, i);

			Dept = getText(Department);
			FullWeek = getText(WeektoDateElmt);

			FullWeekVal = TrimData(FullWeek);

			row.add(Dept);
			row.add(FullWeekVal);

			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Value to Compare with DB Value
	public List<List<Object>> DayWiseUIData(String Value, String Column,
			DailyTradingStatementObjects objDailyTradingStatementObjects) {
		try {
			int i, initial = 0, columnsize = 1;
			WebElement Department = null, DayElmt;
			String Dept, Day = null;
			double DayVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount();
			List<Object> row;

			if (Value.equalsIgnoreCase("Sales"))
				initial = 8;
			if (Value.equalsIgnoreCase("SMS"))
				initial = 9;
			if (Value.equalsIgnoreCase("Hours"))
				initial = 10;
			if (Value.equalsIgnoreCase("OR"))
				initial = 11;
			if (Value.equalsIgnoreCase("Wages"))
				initial = 12;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				initial = 13;
			if (Value.equalsIgnoreCase("Items"))
				initial = 14;
			if (Value.equalsIgnoreCase("Customers"))
				initial = 101;

			for (i = initial; i <= RowCount - 8; i = i + 7) {
				List<Object> row1 = new ArrayList<>(columnsize);
				// Sales
				if (i == 78)
					i = i - 5;
				else if (i == 80)
					i = i - 5;
				else if (i == 82)
					i = i ;
				else if (i == 89)
					i = i + 1;
				
			
				// Wage%
				else if (i == 76)
					i = i + 4;
				else if (i == 87)
					i = i ;
				else if (i == 94)
					i = i + 1;
				// sms
				else if (i == 72)
					i = i + 4;
				else if (i == 83)
					i = i ;
				else if (i == 90)
					i = i +1 ;
				// Hours
				else if (i == 73)
					i = i + 4;
				else if (i == 84)
					i = i ;
				else if (i == 91)
					i = i + 1;
				// OR%
				else if (i == 74)
					i = i + 4;
				else if (i == 85)
					i = i ;
				else if (i == 92)
					i = i + 1;
				// Wages
				else if (i == 75)
					i = i + 4;
				else if (i == 86)
					i = i ;
				else if (i == 93)
					i = i + 1;
				// Items
				else if (i == 77)
					i = i - 5;
				else if (i == 79)
					i = i - 5;

				else if (i == 88)
					i = i  ;

				else if (i == 95)
					i = i + 1;
				// customers
				else if (i == 101)
					i = i - 12;
				else if (i == 96)
					i = i + 1;

				System.out.println(i);
				if (i > 90 && Value.equalsIgnoreCase("Sales"))
					break;
				if (i > 91 && Value.equalsIgnoreCase("SMS"))
					break;
				if (i > 92 && Value.equalsIgnoreCase("Hours"))
					break;
				if (i > 93 && Value.equalsIgnoreCase("OR"))
					break;
				if (i > 94 && Value.equalsIgnoreCase("Wages"))
					break;
				if (i > 95 && Value.equalsIgnoreCase("WagesPercentage"))
					break;
				if (i > 96 && Value.equalsIgnoreCase("Items"))
					break;
				if (i > 101 && Value.equalsIgnoreCase("Customers"))
					break;

				// To Get Department and Store Total Column Names
				if (Value.equalsIgnoreCase("Sales"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i);
				if (Value.equalsIgnoreCase("SMS"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 1);
				if (Value.equalsIgnoreCase("Customers"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i -7);
				if (Value.equalsIgnoreCase("Hours")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 2);
				}
				if (Value.equalsIgnoreCase("OR")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 3);
				}
				if (Value.equalsIgnoreCase("Wages")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 4);
				}
				if (Value.equalsIgnoreCase("WagesPercentage")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 5);
				}
				if (Value.equalsIgnoreCase("Items")) {
					if (i == 72 || i == 74)
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 1);
					else
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 6);
				}

				// Add department into list
				Dept = getText(Department);
				row1.add(Dept);

				for (int k = 0; k < 7; k++) {
					String Datej = ("Datek".replace("k", "" + k));
					DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects, Datej, Column,
							i);
					Day = getText(DayElmt);
					DayVal = TrimData(Day);
					row1.add(DayVal);
				}
				UIValues.add(row1);
			}
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while fetching DTS Data");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> DayWiseUITotal(String Value, String Column,
			DailyTradingStatementObjects objDailyTradingStatementObjects) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, DayElmt;
			String Dept, Day = null;
			double DayVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			if (Value.equalsIgnoreCase("Sales"))
				i = 0;
			if (Value.equalsIgnoreCase("SMS"))
				i = 1;
			if (Value.equalsIgnoreCase("Hours"))
				i = 2;
			if (Value.equalsIgnoreCase("OR"))
				i = 3;
			if (Value.equalsIgnoreCase("Wages"))
				i = 4;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				i = 5;
			if (Value.equalsIgnoreCase("Items"))
				i = 6;
			if (Value.equalsIgnoreCase("Customers"))
				i = 7;

			// To Get Department and Store Total Column Names
			Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal, 0);
			Dept = getText(Department);
			row.add(Dept);
			for (int k = 0; k < 7; k++) {
				String Datej = ("Datek".replace("k", "" + k));
				DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects, Datej, Column, i);
				Day = getText(DayElmt);
				DayVal = TrimData(Day);
				row.add(DayVal);
			}
			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	/////////////////////////////////////////////// Alternate for Getting UI Data
	/////////////////////////////////////////////// for Week days
	/////////////////////////////////////////////// //////////////////////////////////////////////////

	// Get UI Value to Compare with DB Value
	public List<List<Object>> PastUIResults(String Value, DailyTradingStatementObjects objDailyTradingStatementObjects,
			String Monday, String Tuesday, String Wednesday, String Thursday, String ThursdayWhite, String Friday,
			String FridayWhite, String Saturday, String SaturdayWhite, String Sunday, String SundayWhite) {
		try {
			int i, initial = 8, columnsize = 3;
			WebElement Department = null, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt,
					SaturdayElmt, SundayElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(objDailyTradingStatementObjects.Table);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				initial = 8;
			if (Value.equalsIgnoreCase("SMS"))
				initial = 9;
			if (Value.equalsIgnoreCase("Hours"))
				initial = 10;
			if (Value.equalsIgnoreCase("OR"))
				initial = 11;
			if (Value.equalsIgnoreCase("Wages"))
				initial = 12;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				initial = 13;
			if (Value.equalsIgnoreCase("Items"))
				initial = 14;
			if (Value.equalsIgnoreCase("Customers"))
				initial = 97;

			for (i = initial; i <= RowCount - 8; i = i + 7) {
				List<Object> row = new ArrayList<>(columnsize);
				if (i == 78)
					i = i - 5;
				else if (i == 80)
					i = i - 5;
				else if (i == 82)
					i = i ;
				else if (i == 89)
					i = i+1 ;

				System.out.println(i);
				if (i > 90 && Value.equalsIgnoreCase("Sales"))
					break;
				if (i > 91 && Value.equalsIgnoreCase("SMS"))
					break;
				if (i > 92 && Value.equalsIgnoreCase("Hours"))
					break;
				if (i > 93 && Value.equalsIgnoreCase("OR"))
					break;
				if (i > 87 && Value.equalsIgnoreCase("Wages"))
					break;
				if (i > 88 && Value.equalsIgnoreCase("WagesPercentage"))
					break;
				if (i > 89 && Value.equalsIgnoreCase("Items"))
					break;
				if (i > 96 && Value.equalsIgnoreCase("Customers"))
					break;

				// To Get Department and Store Total Column Names
				if (Value.equalsIgnoreCase("Sales"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i);
				if (Value.equalsIgnoreCase("SMS"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 1);
				if (Value.equalsIgnoreCase("Customers"))
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 7);
				if (Value.equalsIgnoreCase("Hours")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 2);
				}
				if (Value.equalsIgnoreCase("OR")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 3);
				}
				if (Value.equalsIgnoreCase("Wages")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 4);
				}
				if (Value.equalsIgnoreCase("WagesPercentage")) {
					Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department,
							i - 5);
				}
				if (Value.equalsIgnoreCase("Items")) {
					if (i == 72 || i == 75)
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 1);
					else
						Department = prepareWebElementWithDynamicXpathWithInt(
								objDailyTradingStatementObjects.Department, i - 6);
				}

				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);

				String ThursdayWt = ThursdayWhite.replace("dynamic", "" + i);
				String FridayWt = FridayWhite.replace("dynamic", "" + i);
				String SaturdayWt = SaturdayWhite.replace("dynamic", "" + i);
				String SundayWt = SundayWhite.replace("dynamic", "" + i);

				if (isElementPresent(ThursdayWt))
					ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayWhite, i);
				else
					ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				if (isElementPresent(FridayWt))
					FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayWhite, i);
				else
					FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				if (isElementPresent(SaturdayWt))
					SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayWhite, i);
				else
					SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				if (isElementPresent(SundayWt))
					SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayWhite, i);
				else
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
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	public List<List<Object>> PastUITotal(String Value, DailyTradingStatementObjects objDailyTradingStatementObjects,
			String Monday, String Tuesday, String Wednesday, String Thursday, String ThursdayWhite, String Friday,
			String FridayWhite, String Saturday, String SaturdayWhite, String Sunday, String SundayWhite,
			String Table) {
		try {
			int i = 0, initial = 0, columnsize = 3;
			WebElement Department, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt, SaturdayElmt,
					SundayElmt, WeektoDateElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun, FullWeek = null;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal, FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("Sales"))
				i = 0;
			if (Value.equalsIgnoreCase("SMS"))
				i = 1;
			if (Value.equalsIgnoreCase("Hours"))
				i = 2;
			if (Value.equalsIgnoreCase("OR"))
				i = 3;
			if (Value.equalsIgnoreCase("Wages"))
				i = 4;
			if (Value.equalsIgnoreCase("WagesPercentage"))
				i = 5;
			if (Value.equalsIgnoreCase("Items"))
				i = 6;
			if (Value.equalsIgnoreCase("Customers"))
				i = 7;

			// To Get Department and Store Total Column Names

			Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.StoreTotal, 0);
			MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
			TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
			WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);

			String ThursdayWt = ThursdayWhite.replace("dynamic", "" + i);
			String FridayWt = FridayWhite.replace("dynamic", "" + i);
			String SaturdayWt = SaturdayWhite.replace("dynamic", "" + i);
			String SundayWt = SundayWhite.replace("dynamic", "" + i);

			if (isElementPresent(ThursdayWt))
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayWhite, i);
			else
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
			if (isElementPresent(FridayWt))
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayWhite, i);
			else
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
			if (isElementPresent(SaturdayWt))
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayWhite, i);
			else
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
			if (isElementPresent(SundayWt))
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayWhite, i);
			else
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
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Compare UI and DB values
	public void CompareValueDecimalORFields(ExtentTest extentTest, List<List<Object>> DBValues,
			List<List<Object>> UIValues) throws Exception {
		try {
			int RowCount = DBValues.size();
			double CalcuatedUIValue = 0, CalcuatedDBValue = 0, RoundOffValue = 0, RoundOffValue1 = 0;
			boolean result = false;
			String DBName = null, UIName = null;
			BigDecimal FinalDBValue = null, FinalUIValue = null;
			System.out.println(UIValues);
			System.out.println(DBValues);

			for (int i = 0; i <= RowCount - 1; i++) {
				for (int j = 0; j < 8; j++) {
					if (j != 0) {
						CalcuatedDBValue = new Double(DBValues.get(i).get(j).toString());
						RoundOffValue = Math.round(CalcuatedDBValue);
						FinalDBValue = ConvertBigDecimalSingleDigit(CalcuatedDBValue);
						System.out.println(FinalDBValue);

						CalcuatedUIValue = new Double(UIValues.get(i).get(j).toString());
						RoundOffValue1 = Math.round(CalcuatedUIValue);
						FinalUIValue = ConvertBigDecimalSingleDigit(CalcuatedUIValue);
						System.out.println(FinalUIValue);
						BigDecimal biggy = FinalUIValue.subtract(FinalDBValue);
						double Variance = biggy.doubleValue();
						if (Variance <= 0.2 && Variance >= -0.2) {
							Report_AddStep("testcase","System Fetch the Values from DB for : " + UIName + " and Displayed Correctly","" + CalcuatedUIValue + "", "" + CalcuatedDBValue + "", "Pass");
							htmlToExtent(cName, mName, extentTest, driver1,"Value displayed Correctly for : " + UIName + "; " + CalcuatedUIValue+ "; " + CalcuatedDBValue + ";Pass");
						} else {
							Report_AddStep("testcase", "Failed to Display the UI and DB Values Correctly " + UIName+ " and value is " + CalcuatedUIValue + "","" + CalcuatedDBValue + "", "", "Fail");
							htmlToExtent(cName, mName, extentTest, driver1,"Failed to display data Correctly for : " + DBName + "; "+ CalcuatedUIValue + "; " + CalcuatedDBValue + ";Fail");
						}
					} else {
						UIName = new String(UIValues.get(i).get(j).toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Verify UI and DB Values", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	//////////////////////////////////////////////////////////////////////// LONGLIFE
	//////////////////////////////////////////////////////////////////////// and
	//////////////////////////////////////////////////////////////////////// SEAFOOD
	//////////////////////////////////////////////////////////////////////// DELI
	//////////////////////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////////////

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CombinedPastUITotal(String Value,
			DailyTradingStatementObjects objDailyTradingStatementObjects, String Monday, String Tuesday,
			String Wednesday, String Thursday, String ThursdayWhite, String Friday, String FridayWhite, String Saturday,
			String SaturdayWhite, String Sunday, String SundayWhite, String Table) // ,String WeekToDate
	{
		try {
			int i = 0, initial = 0, columnsize = 3;
			WebElement Department, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt, SaturdayElmt,
					SundayElmt, WeektoDateElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun, FullWeek = null;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal, FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("LLSales"))
				i = 92;
			else if (Value.equalsIgnoreCase("SFSales"))
				i = 99;
			if (Value.equalsIgnoreCase("LLSMS"))
				i = 93;
			else if (Value.equalsIgnoreCase("SFSMS"))
				i = 100;
			if (Value.equalsIgnoreCase("LLHours"))
				i = 94;
			else if (Value.equalsIgnoreCase("SFHours"))
				i = 101;
			if (Value.equalsIgnoreCase("LLOR"))
				i = 95;
			else if (Value.equalsIgnoreCase("SFOR"))
				i = 102;
			if (Value.equalsIgnoreCase("LLWages"))
				i = 96;
			else if (Value.equalsIgnoreCase("SFWages"))
				i = 103;
			if (Value.equalsIgnoreCase("LLWagesPercentage"))
				i = 97;
			else if (Value.equalsIgnoreCase("SFWagesPercentage"))
				i = 104;
			if (Value.equalsIgnoreCase("LLItems"))
				i = 98;
			else if (Value.equalsIgnoreCase("SFItems"))
				i = 105;

			// To Get Department and Store Total Column Names

			if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("LLSMS")
					|| Value.equalsIgnoreCase("LLHours") || Value.equalsIgnoreCase("LLOR")
					|| Value.equalsIgnoreCase("LLWages") || Value.equalsIgnoreCase("LLWagesPercentage")
					|| Value.equalsIgnoreCase("LLItems"))
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 92);
			else
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 99);
			MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
			TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
			WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);

			String ThursdayWt = ThursdayWhite.replace("dynamic", "" + i);
			String FridayWt = FridayWhite.replace("dynamic", "" + i);
			String SaturdayWt = SaturdayWhite.replace("dynamic", "" + i);
			String SundayWt = SundayWhite.replace("dynamic", "" + i);

			if (isElementPresent(ThursdayWt))
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayWhite, i);
			else
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
			if (isElementPresent(FridayWt))
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayWhite, i);
			else
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
			if (isElementPresent(SaturdayWt))
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayWhite, i);
			else
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
			if (isElementPresent(SundayWt))
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayWhite, i);
			else
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
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CombinedUITotal(String Value,
			DailyTradingStatementObjects objDailyTradingStatementObjects, String Monday, String Tuesday,
			String Wednesday, String Thursday, String ThursdayWhite, String Friday, String FridayWhite, String Saturday,
			String SaturdayWhite, String Sunday, String SundayWhite, String Table) {
		try {
			int i = 0, initial = 0, columnsize = 3;
			WebElement Department, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt, SaturdayElmt,
					SundayElmt, WeektoDateElmt;
			String Dept, Mon, Tue, Wed, Thu, Fri, Sat, Sun, FullWeek = null;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal, FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("LLSales"))
				i = 92;
			else if (Value.equalsIgnoreCase("SFSales"))
				i = 99;
			if (Value.equalsIgnoreCase("LLSMS"))
				i = 93;
			else if (Value.equalsIgnoreCase("SFSMS"))
				i = 100;
			if (Value.equalsIgnoreCase("LLHours"))
				i = 94;
			else if (Value.equalsIgnoreCase("SFHours"))
				i = 101;
			if (Value.equalsIgnoreCase("LLOR"))
				i = 95;
			else if (Value.equalsIgnoreCase("SFOR"))
				i = 102;
			if (Value.equalsIgnoreCase("LLWages"))
				i = 96;
			else if (Value.equalsIgnoreCase("SFWages"))
				i = 103;
			if (Value.equalsIgnoreCase("LLWagesPercentage"))
				i = 97;
			else if (Value.equalsIgnoreCase("SFWagesPercentage"))
				i = 104;
			if (Value.equalsIgnoreCase("LLItems"))
				i = 98;
			else if (Value.equalsIgnoreCase("SFItems"))
				i = 105;

			// To Get Department and Store Total Column Names

			if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("LLSMS")
					|| Value.equalsIgnoreCase("LLHours") || Value.equalsIgnoreCase("LLOR")
					|| Value.equalsIgnoreCase("LLWages") || Value.equalsIgnoreCase("LLWagesPercentage")
					|| Value.equalsIgnoreCase("LLItems"))
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 92);
			else
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 99);
			MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
			TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
			WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
			String ThursdayWt = ThursdayWhite.replace("dynamic", "" + i);
			String FridayWt = FridayWhite.replace("dynamic", "" + i);
			String SaturdayWt = SaturdayWhite.replace("dynamic", "" + i);
			String SundayWt = SundayWhite.replace("dynamic", "" + i);

			if (isElementPresent(ThursdayWt))
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(ThursdayWhite, i);
			else
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
			if (isElementPresent(FridayWt))
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(FridayWhite, i);
			else
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
			if (isElementPresent(SaturdayWt))
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(SaturdayWhite, i);
			else
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
			if (isElementPresent(SundayWt))
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(SundayWhite, i);
			else
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
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CombinedDayWiseUITotal(String Value, String Column,
			DailyTradingStatementObjects objDailyTradingStatementObjects) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, DayElmt;
			String Dept, Day = null;
			double DayVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			if (Value.equalsIgnoreCase("LLSales"))
				i = 92;
			else if (Value.equalsIgnoreCase("SFSales"))
				i = 99;
			if (Value.equalsIgnoreCase("LLSMS"))
				i = 93;
			else if (Value.equalsIgnoreCase("SFSMS"))
				i = 100;
			if (Value.equalsIgnoreCase("LLHours"))
				i = 94;
			else if (Value.equalsIgnoreCase("SFHours"))
				i = 101;
			if (Value.equalsIgnoreCase("LLOR"))
				i = 95;
			else if (Value.equalsIgnoreCase("SFOR"))
				i = 102;
			if (Value.equalsIgnoreCase("LLWages"))
				i = 96;
			else if (Value.equalsIgnoreCase("SFWages"))
				i = 103;
			if (Value.equalsIgnoreCase("LLWagesPercentage"))
				i = 97;
			else if (Value.equalsIgnoreCase("SFWagesPercentage"))
				i = 104;
			if (Value.equalsIgnoreCase("LLItems"))
				i = 98;
			else if (Value.equalsIgnoreCase("SFItems"))
				i = 105;

			// To Get Department and Store Total Column Names
			if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("LLSMS")
					|| Value.equalsIgnoreCase("LLHours") || Value.equalsIgnoreCase("LLOR")
					|| Value.equalsIgnoreCase("LLWages") || Value.equalsIgnoreCase("LLWagesPercentage")
					|| Value.equalsIgnoreCase("LLItems"))
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 92);
			else
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 99);
			Dept = getText(Department);
			row.add(Dept);
			for (int k = 0; k < 7; k++) {
				String Datej = ("Datek".replace("k", "" + k));
				DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects, Datej, Column, i);
				Day = getText(DayElmt);
				DayVal = TrimData(Day);
				row.add(DayVal);
			}
			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CombinedCurrentDayWiseUITotal(String Value, String Column,
			DailyTradingStatementObjects objDailyTradingStatementObjects) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, DayElmt;
			String Dept, Day = null;
			double DayVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("LLSales"))
				i = 92;
			else if (Value.equalsIgnoreCase("SFSales"))
				i = 99;
			if (Value.equalsIgnoreCase("LLSMS"))
				i = 93;
			else if (Value.equalsIgnoreCase("SFSMS"))
				i = 100;
			if (Value.equalsIgnoreCase("LLHours"))
				i = 94;
			else if (Value.equalsIgnoreCase("SFHours"))
				i = 101;
			if (Value.equalsIgnoreCase("LLOR"))
				i = 95;
			else if (Value.equalsIgnoreCase("SFOR"))
				i = 102;
			if (Value.equalsIgnoreCase("LLWages"))
				i = 96;
			else if (Value.equalsIgnoreCase("SFWages"))
				i = 103;
			if (Value.equalsIgnoreCase("LLWagesPercentage"))
				i = 97;
			else if (Value.equalsIgnoreCase("SFWagesPercentage"))
				i = 104;
			if (Value.equalsIgnoreCase("LLItems"))
				i = 98;
			else if (Value.equalsIgnoreCase("SFItems"))
				i = 105;

			// To Get Department and Store Total Column Names
			if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("LLSMS")
					|| Value.equalsIgnoreCase("LLHours") || Value.equalsIgnoreCase("LLOR")
					|| Value.equalsIgnoreCase("LLWages") || Value.equalsIgnoreCase("LLWagesPercentage")
					|| Value.equalsIgnoreCase("LLItems"))
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 92);
			else
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 99);
			Dept = getText(Department);
			row.add(Dept);
			for (int k = 0; k < 7; k++) {
				String Datej = ("Datek".replace("k", "" + k));
				DayElmt = prepareWebElementWithDynamicXpathforDays(objDailyTradingStatementObjects, Datej, Column, i);
				Day = getText(DayElmt);
				DayVal = TrimData(Day);
				row.add(DayVal);
			}
			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CombinedWeekToDateTotal(String Value,
			DailyTradingStatementObjects objDailyTradingStatementObjects, String WeekToDate) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, WeektoDateElmt;
			String Dept, FullWeek = null;
			double FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			if (Value.equalsIgnoreCase("LLSales"))
				i = 92;
			else if (Value.equalsIgnoreCase("SFSales"))
				i = 99;
			if (Value.equalsIgnoreCase("LLSMS"))
				i = 93;
			else if (Value.equalsIgnoreCase("SFSMS"))
				i = 100;
			if (Value.equalsIgnoreCase("LLHours"))
				i = 94;
			else if (Value.equalsIgnoreCase("SFHours"))
				i = 101;
			if (Value.equalsIgnoreCase("LLOR"))
				i = 95;
			else if (Value.equalsIgnoreCase("SFOR"))
				i = 102;
			if (Value.equalsIgnoreCase("LLWages"))
				i = 96;
			else if (Value.equalsIgnoreCase("SFWages"))
				i = 103;
			if (Value.equalsIgnoreCase("LLWagesPercentage"))
				i = 97;
			else if (Value.equalsIgnoreCase("SFWagesPercentage"))
				i = 104;
			if (Value.equalsIgnoreCase("LLItems"))
				i = 98;
			else if (Value.equalsIgnoreCase("SFItems"))
				i = 105;

			// To Get Department and Store Total Column Names

			if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("LLSMS")
					|| Value.equalsIgnoreCase("LLHours") || Value.equalsIgnoreCase("LLOR")
					|| Value.equalsIgnoreCase("LLWages") || Value.equalsIgnoreCase("LLWagesPercentage")
					|| Value.equalsIgnoreCase("LLItems"))
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 92);
			else
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 99);
			String Element = WeekToDate.replace("dynamic", "" + i);
			if (isElementPresent(Element))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
			else if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("SFSales"))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlSalesYellow, i);
			else
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlValueYellow, i);

			Dept = getText(Department);
			FullWeek = getText(WeektoDateElmt);

			FullWeekVal = TrimData(FullWeek);

			row.add(Dept);
			row.add(FullWeekVal);

			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total to Compare with DB Value
	public List<List<Object>> CombinedCurrentWeekToDateTotal(String Value,
			DailyTradingStatementObjects objDailyTradingStatementObjects, String WeekToDate) {
		try {
			int i = 0, columnsize = 2;
			WebElement Department, WeektoDateElmt;
			String Dept, FullWeek = null;
			double FullWeekVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// Initialize Value for Loops
			if (Value.equalsIgnoreCase("LLSales"))
				i = 92;
			else if (Value.equalsIgnoreCase("SFSales"))
				i = 99;
			if (Value.equalsIgnoreCase("LLSMS"))
				i = 93;
			else if (Value.equalsIgnoreCase("SFSMS"))
				i = 100;
			if (Value.equalsIgnoreCase("LLHours"))
				i = 94;
			else if (Value.equalsIgnoreCase("SFHours"))
				i = 101;
			if (Value.equalsIgnoreCase("LLOR"))
				i = 95;
			else if (Value.equalsIgnoreCase("SFOR"))
				i = 102;
			if (Value.equalsIgnoreCase("LLWages"))
				i = 96;
			else if (Value.equalsIgnoreCase("SFWages"))
				i = 103;
			if (Value.equalsIgnoreCase("LLWagesPercentage"))
				i = 97;
			else if (Value.equalsIgnoreCase("SFWagesPercentage"))
				i = 104;
			if (Value.equalsIgnoreCase("LLItems"))
				i = 98;
			else if (Value.equalsIgnoreCase("SFItems"))
				i = 105;

			// To Get Department and Store Total Column Names

			if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("LLSMS")
					|| Value.equalsIgnoreCase("LLHours") || Value.equalsIgnoreCase("LLOR")
					|| Value.equalsIgnoreCase("LLWages") || Value.equalsIgnoreCase("LLWagesPercentage")
					|| Value.equalsIgnoreCase("LLItems"))
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 92);
			else
				Department = prepareWebElementWithDynamicXpathWithInt(objDailyTradingStatementObjects.Department, 99);
			String Element = WeekToDate.replace("dynamic", "" + i);
			if (isElementPresent(Element))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDate, i);
			else if (Value.equalsIgnoreCase("LLSales") || Value.equalsIgnoreCase("SFSales"))
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlSalesYellow, i);
			else
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(
						objDailyTradingStatementObjects.WeekToDateActlValueYellow, i);

			Dept = getText(Department);
			FullWeek = getText(WeektoDateElmt);

			FullWeekVal = TrimData(FullWeek);

			row.add(Dept);
			row.add(FullWeekVal);

			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

}