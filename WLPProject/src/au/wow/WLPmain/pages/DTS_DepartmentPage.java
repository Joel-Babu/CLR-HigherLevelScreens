package au.wow.WLPmain.pages;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.objects.DTS_DepartmentObjects;
import au.wow.WLPmain.objects.DailyPlanningStoreObjects;
import au.wow.WLPmain.objects.ForecastCalendarObject;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.SalesHistoryCalendarObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;

public class DTS_DepartmentPage extends TestBase {

	ExtentReports report;
	ExtentTest extentTest;
	WebDriver driver;
	ExtentReportsScreenshotCode Util;
	CommonFunctions common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
	public Logger log = LogManager.getLogger(WeeklyPlanningStorePage.class);
	SQLWrapper sql = new SQLWrapper(log);
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DTS_DepartmentObjects objDTS_DepartmentObjects;
	static ExtentReportsScreenshotCode ScreenShotUtil;
	static WebDriverWait wait;
	String image;

	public DTS_DepartmentPage() {
		// TODO Auto-generated constructor stub
		super();
	}

	// Select Department
	public void SelectDepartment(DTS_DepartmentObjects objDTS_DepartmentObjects, ExtentTest extentTest,
			String Department) throws Exception {
		try {
			if ((isDisplayed(objDTS_DepartmentObjects.DepartmentHeader))
					&& (getText(objDTS_DepartmentObjects.DepartmentHeader).contains("Department: "))) {
				selectByValue(objDTS_DepartmentObjects.DepartmentDropdown, Department);
				Report_AddStep("testcase", "Department To Display is Selected Successfully from the Dropdown", "", "","Pass");
				htmlToExtent(cName, mName, extentTest, driver1,"" + Department + " is Selected Successfully from department drop down ;;;Pass");
			} else {
				Report_AddStep("testcase", "Failed to Select Department Option from DropDown", "", "", "Fail");
				htmlToExtent(cName, mName, extentTest, driver1,"Failed to select " + Department + " from department drop down ;;;Fail");
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Select History Option from DropDown", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception occured while selecting department ;;;Fail");
		}
	}

	// Get UI Value to Compare with DB Value
	public List<List<Object>> UIResults(DTS_DepartmentObjects objDTS_DepartmentObjects, ExtentTest extentTest,
			String FieldToVerify, String FieldToVerifyYellow, String Table) throws Exception {
		String Day, Values = null;
		double CalcValue;
		int columnsize = 2;
		WebElement Data;
		List<List<Object>> UIValues = new ArrayList<List<Object>>();

		int RowCount = TradingStatementTableCount(Table);
		for (int i = 0; i <= RowCount - 2; i++) {
			List<Object> row = new ArrayList<>(columnsize);
			WebElement Department = prepareWebElementWithDynamicXpathWithInt(objDTS_DepartmentObjects.Days, i);
			String Element = FieldToVerify.replace("dynamic", "" + i);
			if (isElementPresent(Element))
				Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerify, i);
			else
				Data = prepareWebElementWithDynamicXpathWithInt(FieldToVerifyYellow, i);

			Day = getText(Department);
			Values = getText(Data);
			CalcValue = TrimData(Values);
			row.add(Day);
			row.add(CalcValue);
			UIValues.add(row);
		}
		return UIValues;
	}

	// Compare UI and DB values
	public void CompareValues(ExtentTest extentTest, List<List<Object>> DBValues, List<List<Object>> UIValues)
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
				for (int j = 0; j < 2; j++) {
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
						if (Variance <= 1 && Variance >= -1)
							result = true;
						else
							result = false;
					} else {
						DBName = new String(DBValues.get(i).get(j).toString());
						UIName = new String(UIValues.get(i).get(j).toString());
						System.out.println(UIName + " " + DBName);
						if ((DBName.trim()).equalsIgnoreCase(UIName.trim()))
							result = true;
						else {
							System.out.println(UIName + " " + DBName);
							result = false;
							break;

						}
					}
				}
				if (result == true) {
					Report_AddStep("testcase","System Fetch the Values from DB for : " + DBName + " and Displayed Correctly","" + CalcuatedDBValue + "", "" + CalcuatedUIValue + "", "Pass");
					htmlToExtent(cName, mName, extentTest, driver1,"Value displayed Correctly for : " + DBName + "; Actual Value = " + CalcuatedUIValue+ "; Excepted Value = " + CalcuatedDBValue + ";Pass");
				} else {
					Report_AddStep("testcase", "Failed to Display the UI and DB Values Correctly " + DBName+ " and value is " + CalcuatedDBValue + "", "" + CalcuatedUIValue + "", "", "Fail");
					htmlToExtent(cName, mName, extentTest, driver1,"Failed to display data Correctly for : " + DBName + "; Actual Value = " + CalcuatedUIValue+ "; Excepted Value = " + CalcuatedDBValue + ";Fail");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Verify UI and DB Values", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	// Get Total
	public List<List<Object>> GetTotal(ExtentTest extentTest, String RowName, String FieldToVerify) {
		String Dept, Data = null;
		double CalcValue;
		BigDecimal FinalUIValue = null;
		int columnsize = 2;
		List<List<Object>> UIValues = new ArrayList<List<Object>>();
		List<Object> row = new ArrayList<>(columnsize);
		WebElement RwNm = prepareWebElementWithDynamicXpathWithInt(RowName);
		Dept = RwNm.getText();
		WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify);
		Data = getText(Element);
		CalcValue = TrimData(Data);
		FinalUIValue = ConvertBigDecimal(CalcValue);
		row.add(Dept);
		row.add(FinalUIValue);
		UIValues.add(row);
		return UIValues;
	}

	// Get Week to Date Total
	public List<List<Object>> WkToDtTotal(DailyPlanningStoreObjects objDailyPlanningStoreObjects, String WeekToDate) {
		try {
			int i, columnsize = 3;
			WebElement Department = null, WeekToDateElmt;
			String Dept, WkToDate = null;
			double WeekToDateVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);

			// To Get Department and Store Total Column Names
			Department = prepareWebElementWithDynamicXpathWithInt(objDailyPlanningStoreObjects.Total);

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

	// Planned Sales to BDGT Rate Percentage Calculations
	public void ActualHours_Variance(String Input, DTS_DepartmentObjects objDTSDepartmentObjects, ExtentTest extentTest,
			String WeekName, String SMS, String Hours, String Variance, String Table, String PlannedSMS)
			throws Exception {
		int i, RowCount;
		double ActualSMS, ActualHours, PlndSMS, VarianceVal = 0.0, CalculatedValue = 0.0;
		String Value, Value1, Value2, Value3, WeekName1, Result = null;
		try {
			RowCount = TradingStatementTableCount(Table);
			for (i = 0; i <= RowCount - 2; i++) {
				WebElement Week = prepareWebElementWithDynamicXpathWithInt(WeekName, i);
				WebElement xpath1 = prepareWebElementWithDynamicXpathWithInt(SMS, i);
				WebElement xpath2 = prepareWebElementWithDynamicXpathWithInt(Hours, i);
				WebElement xpath3 = prepareWebElementWithDynamicXpathWithInt(PlannedSMS, i);
				WebElement CalculatedXpath = prepareWebElementWithDynamicXpathWithInt(Variance, i);

				WeekName1 = getText(Week);
				Value1 = getText(xpath1);
				Value2 = getText(xpath2);
				Value3 = getText(xpath3);
				Value = getText(CalculatedXpath);
				ActualSMS = TrimData(Value1);
				ActualHours = TrimData(Value2);
				PlndSMS = TrimData(Value3);
				VarianceVal = TrimData(Value);
				System.out.println(VarianceVal + " " + VarianceVal);
				BigDecimal Val = ConvertBigDecimal(VarianceVal);

				if (Input.contains("Variance")) {
					CalculatedValue = ActualHours - ActualSMS;
					BigDecimal Val1 = ConvertBigDecimal(CalculatedValue);
					Result = (Val.subtract(Val1).toString());
					boolean Final1 = Result.equals("-1");
					boolean Final = Result.equals("1");
					if (Val.equals(Val1) || Final == true || Final1 == true) {
						Report_AddStep("testcase", "Calculated Value  is displayed correctly for " + WeekName1 + " as","" + Val + "", "" + Val1 + "", "Pass");
						htmlToExtent(cName, mName, extentTest, driver1, "Value displayed Correctly for : " + WeekName1+ "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Pass");
					} else {
						Report_AddStep("testcase", "Failed to Display Value Correctly for " + WeekName1 + " :","" + Val + "", "" + Val1 + "", "Fail");
						htmlToExtent(cName, mName, extentTest, driver1, "Failed to display data Correctly for : "+ WeekName1 + "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Fail");
					}
				}
				if (Input.contains("OR")) {
					if (ActualSMS == 0)
						CalculatedValue = PlndSMS / ActualHours * 100;
					else
						CalculatedValue = ActualSMS / ActualHours * 100;
					String total2 = Double.toString(CalculatedValue);
					if (total2.contains("NaN") || total2.contains("Infinity"))
						CalculatedValue = 0;
					BigDecimal Val1 = ConvertBigDecimal(CalculatedValue);
					BigDecimal biggy = Val.subtract(Val1);
					double Var = biggy.doubleValue();
					if (Var <= 0.05 && Var >= -0.05) {
						System.out.println(Val + "  " + Val1);
						Report_AddStep("testcase", "Calculated Value  is displayed correctly for " + WeekName1 + " as","" + Val + "", "" + Val1 + "", "Pass");
						htmlToExtent(cName, mName, extentTest, driver1, "Value displayed Correctly for : " + WeekName1+ "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Pass");
					} else {
						System.out.println(Val + "  " + Val1);
						Report_AddStep("testcase", "Failed to Display Value Correctly for " + WeekName1 + " :","" + Val + "", "" + Val1 + "", "Fail");
						htmlToExtent(cName, mName, extentTest, driver1, "Failed to display data Correctly for : "+ WeekName1 + "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Fail");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Fetch Week Total Value", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	// Total
	// Planned Sales to BDGT Rate Percentage Calculations
	public void Actual_Total(String Input, DTS_DepartmentObjects objDTSDepartmentObjects, ExtentTest extentTest,
			String WeekName, String SMS, String Hours, String Variance, String PlannedSMS) throws Exception {
		double ActualSMS, ActualHours, PlanSMS, VarianceVal = 0.0, CalculatedValue = 0.0;
		String Value, Value1, Value2, Value3, WeekName1, Result = null;
		try {
			WebElement Week = prepareWebElementWithDynamicXpathWithInt(WeekName);
			WebElement xpath1 = prepareWebElementWithDynamicXpathWithInt(SMS);
			WebElement xpath2 = prepareWebElementWithDynamicXpathWithInt(Hours);
			WebElement xpath3 = prepareWebElementWithDynamicXpathWithInt(PlannedSMS);
			WebElement CalculatedXpath = prepareWebElementWithDynamicXpathWithInt(Variance);

			WeekName1 = getText(Week);
			Value1 = getText(xpath1);
			Value2 = getText(xpath2);
			Value3 = getText(xpath3);
			Value = getText(CalculatedXpath);
			ActualSMS = TrimData(Value1);
			ActualHours = TrimData(Value2);
			PlanSMS = TrimData(Value3);
			VarianceVal = TrimData(Value);
			System.out.println(VarianceVal + " " + VarianceVal);
			BigDecimal Val = ConvertBigDecimal(VarianceVal);

			if (Input.contains("Variance")) {
				CalculatedValue = ActualHours - ActualSMS;
				BigDecimal Val1 = ConvertBigDecimal(CalculatedValue);
				Result = (Val.subtract(Val1).toString());
				boolean Final1 = Result.equals("-1");
				boolean Final = Result.equals("1");
				if (Val.equals(Val1) || Final == true || Final1 == true) {
					System.out.println(Val + "  " + Val1);
					Report_AddStep("testcase", "Calculated Value  is displayed correctly for " + WeekName1 + " as","" + Val + "", "" + Val1 + "", "Pass");
					htmlToExtent(cName, mName, extentTest, driver1, "Value displayed Correctly for : " + WeekName1+ "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Pass");
				} else {
					System.out.println(Val + "  " + Val1);
					Report_AddStep("testcase", "Failed to Display Value Correctly for " + WeekName1 + " :","" + Val + "", "" + Val1 + "", "Fail");
					htmlToExtent(cName, mName, extentTest, driver1, "Failed to display data Correctly for : "+ WeekName1 + "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Fail");
				}
			}
			if (Input.contains("OR")) {
				if (ActualSMS == 0)
					CalculatedValue = PlanSMS / ActualHours * 100;
				else
					CalculatedValue = ActualSMS / ActualHours * 100;
				String total2 = Double.toString(CalculatedValue);
				if (total2.contains("NaN") || total2.contains("Infinity"))
					CalculatedValue = 0;
				BigDecimal Val1 = ConvertBigDecimal(CalculatedValue);
				BigDecimal biggy = Val.subtract(Val1);
				double Var = biggy.doubleValue();
				if (Var <= 0.05 && Var >= -0.05) {
					System.out.println(Val + "  " + Val1);
					Report_AddStep("testcase", "Calculated Value  is displayed correctly for " + WeekName1 + " as","" + Val + "", "" + Val1 + "", "Pass");
					htmlToExtent(cName, mName, extentTest, driver1, "Value displayed Correctly for : " + WeekName1+ "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Pass");
				} else {
					System.out.println(Val + "  " + Val1);
					Report_AddStep("testcase", "Failed to Display Value Correctly for " + WeekName1 + " :","" + Val + "", "" + Val1 + "", "Fail");
					htmlToExtent(cName, mName, extentTest, driver1, "Failed to display data Correctly for : "+ WeekName1 + "; Actual Value = " + Val + "; Excepted Value = " + Val1 + ";Fail");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
			Report_AddStep("testcase", "Failed to Fetch Week Total Value", "", "", "Fail");
			htmlToExtent(cName, mName, extentTest, driver1, "Exception Occured while comparing data;;;Fail");
		}
	}

	// Get Total
	public List<List<Object>> GetValuefromTextBox(ExtentTest extentTest, String RowName, String FieldToVerify) {
		String Dept, Data = null;
		double CalcValue;
		BigDecimal FinalUIValue = null;
		int columnsize = 2;
		List<List<Object>> UIValues = new ArrayList<List<Object>>();
		List<Object> row = new ArrayList<>(columnsize);
		WebElement RwNm = prepareWebElementWithDynamicXpathWithInt(RowName);
		Dept = RwNm.getText();
		WebElement Element = prepareWebElementWithDynamicXpathWithInt(FieldToVerify);
		Data = getAttribute(Element);
		CalcValue = TrimData(Data);
		FinalUIValue = ConvertBigDecimal(CalcValue);
		row.add(Dept);
		row.add(FinalUIValue);
		UIValues.add(row);
		return UIValues;
	}

	// AllowedWages
	// Get Total
	public int AllowedWagesValidation(ExtentTest extentTest, String BudgetSales, String PlannedSales,
			String ActualSales) {
		String BdgtSls, ActlSls, PlndSls = null;
		double BSls, ASls, PSls, compare = 0.0;
		BigDecimal FinalUIValue = null;
		WebElement Bdgt = prepareWebElementWithDynamicXpathWithInt(BudgetSales);
		BdgtSls = Bdgt.getText();
		BSls = TrimData(BdgtSls);
		WebElement Actl = prepareWebElementWithDynamicXpathWithInt(ActualSales);
		ActlSls = getText(Actl);
		ASls = TrimData(ActlSls);
		WebElement Plnd = prepareWebElementWithDynamicXpathWithInt(PlannedSales);
		PlndSls = getText(Plnd);
		PSls = TrimData(PlndSls);
		if (ASls == 0) {
			if (PSls <= BSls)
				return 1;
			else
				return 2;
		} else {
			if (ASls <= BSls)
				return 1;
			else
				return 2;
		}
	}

	// Get UI Value WeektoDate
	public List<List<Object>> PlnVsActlUIResultsDayWise(String ColumnName, String PlanCompletedStr, String Monday,
			String Tuesday, String Wednesday, String Thursday, String Friday, String Saturday, String Sunday,
			String Table) {
		try {
			int i, columnsize = 3;
			String Element;
			WebElement Department = null, Mon, Tue, Wed, Thu, Fri, Sat, Sun;
			String Dept, Mn, Tu, Wd, Th, Fr, Sa, Su, Mndy = null, Tsdy = null, Wdy = null, Thdy = null, Frdy = null,
					Stdy = null, Sndy = null;
			double DyWiseMon, DyWiseTue, DyWiseWed, DyWiseThu, DyWiseFri, DyWiseSat, DyWiseSun;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row;
			String[] Values1, Values2, Values3, Values4, Values5, Values6, Values7;
			int RowCount = TradingStatementTableCount(Table);
			BigDecimal FinalVal;
			for (i = 0; i <= RowCount-5; i++) {
				row = new ArrayList<>(columnsize);
				Element = PlanCompletedStr.replace("dynamic", "" + i);
				Mon = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				Tue = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				Wed = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
				Thu = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				Fri = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				Sat = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				Sun = prepareWebElementWithDynamicXpathWithInt(Sunday, i);
				Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i);
				Dept = getText(Department);
				Mn = getText(Mon);
				Tu = getText(Tue);
				Wd = getText(Wed);
				Th = getText(Thu);
				Fr = getText(Fri);
				Sa = getText(Sat);
				Su = getText(Sun);
				Values1 = Mn.split("\n");
				Values2 = Tu.split("\n");
				Values3 = Wd.split("\n");
				Values4 = Th.split("\n");
				Values5 = Fr.split("\n");
				Values6 = Sa.split("\n");
				Values7 = Su.split("\n");
				if (ColumnName.equalsIgnoreCase("Wages")) {
					Mndy = Values1[0];
					Tsdy = Values2[0];
					Wdy = Values3[0];
					Thdy = Values4[0];
					Frdy = Values5[0];
					Stdy = Values6[0];
					Sndy = Values7[0];
				}
				if (ColumnName.equalsIgnoreCase("Hours")) {
					Mndy = Values1[1];
					Tsdy = Values2[1];
					Wdy = Values3[1];
					Thdy = Values4[1];
					Frdy = Values5[1];
					Stdy = Values6[1];
					Sndy = Values7[1];
				}
				DyWiseMon = TrimData(Mndy);
				DyWiseTue = TrimData(Tsdy);
				DyWiseWed = TrimData(Wdy);
				DyWiseThu = TrimData(Thdy);
				DyWiseFri = TrimData(Frdy);
				DyWiseSat = TrimData(Stdy);
				DyWiseSun = TrimData(Sndy);

				row.add(Dept);
				row.add(DyWiseMon);
				row.add(DyWiseTue);
				row.add(DyWiseWed);
				row.add(DyWiseThu);
				row.add(DyWiseFri);
				row.add(DyWiseSat);
				row.add(DyWiseSun);

				UIValues.add(row);
				System.out.println(UIValues);
			}
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Fetching Value");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total Actuals
	public List<List<Object>> PlnVsActlUITotal(String ColumnName, String GroupTotal, String WeekToDateTotal) {
		try {
			int columnsize = 3;
			WebElement Department = null, WeekToDateElmt;
			String Dept, WeekToDate, WkToDate = null;
			double WeekToDateVal;
			BigDecimal FinalVal = null;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);
			row = new ArrayList<>(columnsize);
			Department = prepareWebElementWithDynamicXpathWithInt(GroupTotal);
			WeekToDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateTotal);

			Dept = getText(Department);
			WeekToDate = getText(WeekToDateElmt);
			String[] Values = WeekToDate.split("\n");
			if (ColumnName.equalsIgnoreCase("Wages"))
				WkToDate = Values[0];
			if (ColumnName.equalsIgnoreCase("Hours"))
				WkToDate = Values[1];
			WeekToDateVal = TrimData(WkToDate);
			row.add(Dept);
			row.add(WeekToDateVal);
			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Fetching Data");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Value - Day Wise
	public List<List<Object>> PlnVsActlUIResults(String ColumnName, String PlanCompletedStr, String WeekToDateElement,
			String Table) {
		try {
			int i, columnsize = 3;
			String Element;
			WebElement Department = null, WeektoDateElmt;
			String Dept, WkToDate;
			double WkToDateVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row;
			int RowCount = TradingStatementTableCount(Table);
			BigDecimal FinalVal;
			for (i = 0; i <= RowCount-5; i++) {
				row = new ArrayList<>(columnsize);
				Element = PlanCompletedStr.replace("dynamic", "" + i);
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElement, i);
				Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i);
				Dept = getText(Department);
				WkToDate = getText(WeektoDateElmt);
				String[] Values = WkToDate.split("\n");
				if (ColumnName.equalsIgnoreCase("Wages"))
					WkToDate = Values[0];
				if (ColumnName.equalsIgnoreCase("Hours"))
					WkToDate = Values[1];
				WkToDateVal = TrimData(WkToDate);
				FinalVal = ConvertBigDecimal(WkToDateVal);
				row.add(Dept);
				row.add(FinalVal);
				UIValues.add(row);
				System.out.println(UIValues);
			}
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Fetching Value");
			e.printStackTrace();
		}
		return null;
	}

	// Get UI Total Actuals
	public List<List<Object>> PlnVsActlUITotalDayWise(String ColumnName, String StoreTotal, String Monday,
			String Tuesday, String Wednesday, String Thursday, String Friday, String Saturday, String Sunday,
			String Table) {
		try {
			int columnsize = 3, i = 0;
			WebElement Department = null, Mon, Tue, Wed, Thu, Fri, Sat, Sun;
			String Dept, Mn, Tu, Wd, Th, Fr, Sa, Su, Mndy = null, Tsdy = null, Wdy = null, Thdy = null, Frdy = null,Stdy = null, Sndy = null;
			double DyWiseMon, DyWiseTue, DyWiseWed, DyWiseThu, DyWiseFri, DyWiseSat, DyWiseSun;
			BigDecimal FinalVal = null;
			String[] Values1, Values2, Values3, Values4, Values5, Values6, Values7;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			List<Object> row = new ArrayList<>(columnsize);
			row = new ArrayList<>(columnsize);
			Department = prepareWebElementWithDynamicXpathWithInt(StoreTotal);
			Mon = prepareWebElementWithDynamicXpathWithInt(Monday);
			Tue = prepareWebElementWithDynamicXpathWithInt(Tuesday);
			Wed = prepareWebElementWithDynamicXpathWithInt(Wednesday);
			Thu = prepareWebElementWithDynamicXpathWithInt(Thursday);
			Fri = prepareWebElementWithDynamicXpathWithInt(Friday);
			Sat = prepareWebElementWithDynamicXpathWithInt(Saturday);
			Sun = prepareWebElementWithDynamicXpathWithInt(Sunday);
			Dept = getText(Department);
			Mn = getText(Mon);
			Tu = getText(Tue);
			Wd = getText(Wed);
			Th = getText(Thu);
			Fr = getText(Fri);
			Sa = getText(Sat);
			Su = getText(Sun);
			Values1 = Mn.split("\n");
			Values2 = Tu.split("\n");
			Values3 = Wd.split("\n");
			Values4 = Th.split("\n");
			Values5 = Fr.split("\n");
			Values6 = Sa.split("\n");
			Values7 = Su.split("\n");
			if (ColumnName.equalsIgnoreCase("Wages")) {
				Mndy = Values1[0];
				Tsdy = Values2[0];
				Wdy = Values3[0];
				Thdy = Values4[0];
				Frdy = Values5[0];
				Stdy = Values6[0];
				Sndy = Values7[0];
			}
			if (ColumnName.equalsIgnoreCase("Hours")) {
				Mndy = Values1[1];
				Tsdy = Values2[1];
				Wdy = Values3[1];
				Thdy = Values4[1];
				Frdy = Values5[1];
				Stdy = Values6[1];
				Sndy = Values7[1];
			}
			DyWiseMon = TrimData(Mndy);
			DyWiseTue = TrimData(Tsdy);
			DyWiseWed = TrimData(Wdy);
			DyWiseThu = TrimData(Thdy);
			DyWiseFri = TrimData(Frdy);
			DyWiseSat = TrimData(Stdy);
			DyWiseSun = TrimData(Sndy);

			row.add(Dept);
			row.add(DyWiseMon);
			row.add(DyWiseTue);
			row.add(DyWiseWed);
			row.add(DyWiseThu);
			row.add(DyWiseFri);
			row.add(DyWiseSat);
			row.add(DyWiseSun);
			UIValues.add(row);
			System.out.println(UIValues);
			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Fetching Data");
			e.printStackTrace();
		}
		return null;
	}

	// Sales History Calendar
	// Sales History Calendar validation
	public List<List<Object>> SlsHistCalUIResults(String ColumnName, String PlanCompletedStr, String WeekToDateElement,
			String Table) throws Exception {
		try {
			double CalcValue;
			WebElement Department = null, WeektoDateElmt;
			String Dept, WkToDate;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);
			for (int i = 0; i < RowCount; i++) {
				List<Object> row = new ArrayList<>();
				Department = prepareWebElementWithDynamicXpathWithInt(PlanCompletedStr, i);
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElement, i);
				Dept = getText(Department);
				WkToDate = getText(WeektoDateElmt);
				CalcValue = TrimData(WkToDate);
				row.add(Dept);
				row.add(CalcValue);
				UIValues.add(row);
				System.out.println(UIValues);
			}

			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Fetching Data");
			e.printStackTrace();
		}
		return null;
	}

	public List<List<Object>> UIResultsFC(String ColumnName, String WeekToDateElement, String Table) throws Exception {
		try {
			double CalcValue;
			WebElement Department = null, WeektoDateElmt;
			String Dept, WkToDate;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);
			for (int i = 0; i < RowCount; i++) {
				List<Object> row = new ArrayList<>();
				WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElement, i);
				WkToDate = getText(WeektoDateElmt);
				CalcValue = TrimData(WkToDate);
				row.add(CalcValue);
				UIValues.add(row);
				System.out.println(UIValues);
			}

			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Fetching Data");
			e.printStackTrace();
		}
		return null;
	}

	// FC Second TRY
	public List<List<Object>> UIResultsFC2(String ColumnName, String WeekToDateElement, String Table) throws Exception {
		try {
			double CalcValue;
			WebElement WeektoDateElmt;
			String WkToDate;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);
			for (int i = 0; i < RowCount; i++) {
				List<Object> row = new ArrayList<>();
				if (i == 26) {
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithIntTry(WeekToDateElement, i);
				} else {
					WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElement, i);
				}
				WkToDate = getText(WeektoDateElmt);
				CalcValue = TrimData(WkToDate);
				row.add(CalcValue);
				UIValues.add(row);
				System.out.println(UIValues);
			}

			return UIValues;
		} catch (Exception e) {
			System.out.println("Exception occured while Fetching Data");
			e.printStackTrace();
		}
		return null;
	}

	// FC TRY
	public List<List<Object>> UIResultsFC1(String ColumnName, String WeekToDateElement, String Table) throws Exception {
		double CalcValue;
		WebElement WeektoDateElmt, WeektoDateElmttwo;
		String WkToDate = null;
		List<List<Object>> UIValues = new ArrayList<List<Object>>();
		int RowCount = TradingStatementTableCount(Table);
		for (int i = 0; i < RowCount; i++) {
			List<Object> row = new ArrayList<>();
			WeektoDateElmt = prepareWebElementWithDynamicXpathWithInt(WeekToDateElement, i);
			if (isDisplayed(WeektoDateElmt)) {
				WkToDate = getText(WeektoDateElmt);
			} else {
				WeektoDateElmttwo = prepareWebElementWithDynamicXpathWithIntTry(WeekToDateElement, i);
				WkToDate = getText(WeektoDateElmttwo);
			}
			CalcValue = TrimData(WkToDate);
			row.add(CalcValue);
			UIValues.add(row);
			System.out.println(UIValues);
		}

		return UIValues;
	}

	// Week day UI value:
	public List<List<Object>> WeekdayUIValue(SalesHistoryCalendarObjects objSalesHistoryCalendarobjects,
			String Weekdayname, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday,
			String Saturday, String Sunday, String Table, String RowName) {
		try {
			int i = 0, columnsize = 7;
			WebElement TitleElmt, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt, SaturdayElmt,
					SundayElmt;
			String Title, Mon, Tue, Wed, Thu, Fri, Sat, Sun;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);

			for (i = 0; i < RowCount; i++) {
				List<Object> row = new ArrayList<>(columnsize);
				TitleElmt = prepareWebElementWithDynamicXpathWithInt(RowName, i);
				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);

				Title = getText(TitleElmt);
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

				row.add(Title);
				row.add(MonVal);
				row.add(TueVal);
				row.add(WedVal);
				row.add(ThuVal);
				row.add(FriVal);
				row.add(SatVal);
				row.add(SunVal);

				UIValues.add(row);
				System.out.println(UIValues);
			}

			return UIValues;

		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// ForecastCalendar
	public List<List<Object>> WeekdayUIValueFC(ForecastCalendarObject objForecastCalendar, String Weekdayname,
			String Monday, String Tuesday, String Wednesday, String Thursday, String Friday, String Saturday,
			String Sunday, String Table, String RowName) {
		try {
			int i = 0, columnsize = 7;
			WebElement TitleElmt, MondayElmt, TuesdayElmt, WednesdayElmt, ThursdayElmt, FridayElmt, SaturdayElmt,
					SundayElmt;
			String Title, Mon, Tue, Wed, Thu, Fri, Sat, Sun;
			double MonVal, TueVal, WedVal, ThuVal, FriVal, SatVal, SunVal;
			List<List<Object>> UIValues = new ArrayList<List<Object>>();
			int RowCount = TradingStatementTableCount(Table);

			for (i = 0; i < RowCount; i++) {
				List<Object> row = new ArrayList<>(columnsize);
				TitleElmt = prepareWebElementWithDynamicXpathWithInt(RowName, i);
				MondayElmt = prepareWebElementWithDynamicXpathWithInt(Monday, i);
				TuesdayElmt = prepareWebElementWithDynamicXpathWithInt(Tuesday, i);
				WednesdayElmt = prepareWebElementWithDynamicXpathWithInt(Wednesday, i);
				ThursdayElmt = prepareWebElementWithDynamicXpathWithInt(Thursday, i);
				FridayElmt = prepareWebElementWithDynamicXpathWithInt(Friday, i);
				SaturdayElmt = prepareWebElementWithDynamicXpathWithInt(Saturday, i);
				SundayElmt = prepareWebElementWithDynamicXpathWithInt(Sunday, i);

				Title = getText(TitleElmt);
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

				row.add(Title);
				row.add(MonVal);
				row.add(TueVal);
				row.add(WedVal);
				row.add(ThuVal);
				row.add(FriVal);
				row.add(SatVal);
				row.add(SunVal);

				UIValues.add(row);
				System.out.println(UIValues);
			}

			return UIValues;

		} catch (Exception e) {
			System.out.println("Exception occured while Switching the Frame");
			e.printStackTrace();
		}
		return null;
	}

	// Screen Validation
	public void ScreenValidation(WebElement MainHeading, WebElement PrYrBox, WebElement NxtYrBox,
			WebElement TableArHeading) {
		String HeadingValue = getText(MainHeading);
		String TableAreaHeading = getText(TableArHeading);
		String PrYrBoxValue = getValueAttribute(PrYrBox);
		String NxtYrBoxValue = getValueAttribute(NxtYrBox);
		String[] Value1 = HeadingValue.split(" ");
		String[] Value2 = TableAreaHeading.split(" ");
		System.out.println("The Main Heading Year Value is " + Value1[3]);
		System.out.println("The Table Area Heading - Year Value is " + Value2[7]);
		System.out.println("Previous Year Box value is " + PrYrBoxValue);
		System.out.println("Next Year Box value is " + NxtYrBoxValue);
		if (Value1[3].equals(Value2[7])) {
			System.out.println("The Year in Main Heading and the Table Area Heading are matching");
		}
		NxtYrBox.click();
		waitForElement(MainHeading);
		String FutureHeadingValue = getText(MainHeading);
		String FutureTableAreaHeading = getText(TableArHeading);
		String[] Value3 = FutureHeadingValue.split(" ");
		String[] Value4 = FutureTableAreaHeading.split(" ");
		System.out.println("The Main Heading Year Value is " + Value3[3]);
		System.out.println("The Table Area Heading - Year Value is " + Value4[7]);
		if (NxtYrBoxValue.equals(Value3[3]) && NxtYrBoxValue.equals(Value4[7])) {
			System.out.println("The Next Year Box and the New Screen Area are matching");
		}

	}

}
