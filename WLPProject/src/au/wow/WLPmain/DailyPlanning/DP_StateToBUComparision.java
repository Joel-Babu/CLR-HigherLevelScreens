package au.wow.WLPmain.DailyPlanning;

import java.awt.AWTException;

import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.objects.DailyPlanningStoreObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DBComparision;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
import au.wow.WLPmain.pages.DailyPlanningPage;
import au.wow.WLPmain.pages.DailyTradingStatementGroupPage;
import au.wow.WLPmain.pages.MonthlyPlanningGroupPage;
import au.wow.WLPmain.pages.MonthlyPlanningStorePage;
import au.wow.WLPmain.pages.WeeklyPlanningStorePage;
import au.wow.WLPmain.pages.WoWLoginPage;
import au.wow.WLPmain.tests.StoreProperties;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.CustomExtentReports;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;
import au.wow.wlp.utils.TestStatus;
import au.wow.wlp.utils.XMLDataReader;

public class DP_StateToBUComparision extends TestBase {

	protected Logger log = LogManager.getLogger(DP_StateToBUComparision.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	SQLWrapper sql1 = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	DailyPlanningPage pageDailyPlanningPO;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
	DailyPlanningStoreObjects objDailyPlanningStoreObjects;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	DBComparision pageDBComparisionPO;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> DBValues, DBWkToDate, PayPlanDBValues, PayPlanDBWkToDate, PayHoursDBValues, PayHoursDBWkToDate;
	List<List<Object>> DBTotal, DBWkToDateTotal, PayPlanDBTotal, PayPlanDBWkToDateTotal, PayHoursDBTotal,
			PayHoursDBWkToDateTotal;
	List<List<Object>> UIValues, UIWkToDate, PayHoursUIValues, PayHoursUIWkToDate, PayPlanUIValues, PayPlanUIWkToDate;
	List<List<Object>> UITotal, UIWkToDateTotal, PayHoursUITotal, PayHoursUIWkToDateTotal, PayPlanUITotal,
			PayPlanUIWkToDateTotal;
	String OptionToView, Name;

	public DP_StateToBUComparision() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToStatePage(getContext().getStringProperty("store"));
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - Sales View", priority = 1)
	public void SalesView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales View");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.planningScreens, "Planning Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.dailyPlanning, "Daily Planning/Actual");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.DPscreenToView, "Store");
		pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("DailyPlanningStore"));

		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Sales");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned Sales View";
				Report_AddStep("testcase", " PLANNED SALES VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned Sales View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actuals Sales View";
				Report_AddStep("testcase", " ACTUAL SALES VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals Sales View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance Sales View";
				Report_AddStep("testcase", " VARIANCE SALES VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance Sales View");

			}

			String SalesColumn = data1.getText("" + OptionToView + "SalesColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					SalesColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String SalesTotal = data.getText("" + OptionToView + "SalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					SalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			String SalesWkToDt = data1.getText("" + OptionToView + "SalesWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					SalesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWkToDate);

			String SalesWkToDtTotal = data.getText("" + OptionToView + "SalesWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					SalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "Sales - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "Sales - Week To Date");
		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - Hours View", priority = 2)
	public void ScheduledHoursView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Hours View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Hours");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU  -Planned Hours View";
				Report_AddStep("testcase", " PLANNED HOURS VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned Hours View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU  - Actuals Hours View";
				Report_AddStep("testcase", " ACTUAL HOURS VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals Hours View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU  - Variance Hours View";
				Report_AddStep("testcase", " VARIANCE HOURS VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance Hours View");

			}

			String HoursColumn = data1.getText("" + OptionToView + "HoursColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					HoursColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String HoursTotal = data.getText("" + OptionToView + "HoursTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					HoursTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			String HoursWkToDt = data1.getText("" + OptionToView + "HoursWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					HoursWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWkToDate);

			String HoursWkToDtTotal = data.getText("" + OptionToView + "HoursWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					HoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "Hours - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "Hours - Week To Date");

			// Pay Hours
			String PayHoursColumn = data1.getText("" + OptionToView + "HoursColumn");
			PayHoursDBValues = sql.CLRexecuteQuery(getContext(),
					PayHoursColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + PayHoursDBValues);

			String PayHoursTotal = data.getText("" + OptionToView + "HoursTotal");
			PayHoursDBTotal = sql.CLRexecuteQuery(getContext(),
					PayHoursTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + PayHoursDBTotal);

			String PayHoursWkToDt = data1.getText("" + OptionToView + "PayHoursWkToDt");
			PayHoursDBWkToDate = sql.CLRexecuteQuery(getContext(),
					PayHoursWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + PayHoursDBWkToDate);

			String PayHoursWkToDtTotal = data.getText("" + OptionToView + "HoursWkToDtTotal");
			PayHoursDBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					PayHoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + PayHoursDBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, PayHoursDBTotal, PayHoursDBValues, "Hours - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, PayHoursDBWkToDateTotal, PayHoursDBWkToDate,
					"Hours - Week To Date");
		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - Wages View", priority = 3)
	public void WagesView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Wages View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Wages");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned Wages View";
				Report_AddStep("testcase", " PLANNED WAGES VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned Wages View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actuals Wages View";
				Report_AddStep("testcase", " ACTUAL WAGES VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals Wages View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance Wages View";
				Report_AddStep("testcase", " VARIANCE WAGES VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance Wages View");

			}

			String WagesColumn = data1.getText("" + OptionToView + "WagesColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					WagesColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String WagesTotal = data.getText("" + OptionToView + "WagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					WagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBTotal);

			String WagesWkToDt = data1.getText("" + OptionToView + "WagesWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					WagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDate);

			String WagesWkToDtTotal = data.getText("" + OptionToView + "WagesWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					WagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "Wages - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "Wages - Week To Date");
		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - Wages Percentage View", priority = 4)
	public void WagesPercentageView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Wages % View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Wage%");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned Wages Percentage View";
				Report_AddStep("testcase", " PLANNED WAGEPERCENTAGE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned Wages Percentage View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actuals Wages Percentage View";
				Report_AddStep("testcase", " ACTUAL WAGEPERCENTAGE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals Wages Percentage View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance Wages Percentage View";
				Report_AddStep("testcase", " VARIANCE WAGEPERCENTAGE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance Wages Percentage View");

			}

			String WagesPrcntColumn = data1.getText("" + OptionToView + "WagesPrcntColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					WagesPrcntColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String WagesPrcntTotal = data.getText("" + OptionToView + "WagesPrcntTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					WagesPrcntTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBTotal);

			String WagesPrcntWkToDt = data1.getText("" + OptionToView + "WagesPrcntWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					WagesPrcntWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDate);

			String WagesPrcntWkToDtTotal = data.getText("" + OptionToView + "WagesPrcntWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					WagesPrcntWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "Wages Percentage - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate,
					"Wages Percentage - Week To Date");
		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - OR Percentage View", priority = 5)
	public void ORPercentageView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: OR View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Operating Ratio");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned OR Percentage View";
				Report_AddStep("testcase", " PLANNED ORPERCENTAGE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned OR Percentage View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actuals OR Percentage View";
				Report_AddStep("testcase", " ACTUAL ORPERCENTAGE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals OR Percentage View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance OR Percentage View";
				Report_AddStep("testcase", " VARIANCE ORPERCENTAGE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance OR Percentage View");

			}

			String ORPrcntColumn = data1.getText("" + OptionToView + "ORPrcntColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ORPrcntColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ORPrcntTotal = data.getText("" + OptionToView + "ORPrcntTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ORPrcntTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			String ORPrcntWkToDt = data1.getText("" + OptionToView + "ORPrcntWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					ORPrcntWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWkToDate);

			String ORPrcntWkToDtTotal = data.getText("" + OptionToView + "ORPrcntWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					ORPrcntWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "OR Percentage - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "OR Percentage - Week To Date");

			// Pay Plan
			String PayPlanORPrcntColumn = data1.getText("" + OptionToView + "ORPrcntColumnPayPlan");
			PayPlanDBValues = sql.CLRexecuteQuery(getContext(),
					PayPlanORPrcntColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + PayPlanDBValues);

			String PayPlanORPrcntTotal = data.getText("" + OptionToView + "ORPrcntTotalPayPlan");
			PayPlanDBTotal = sql.CLRexecuteQuery(getContext(),
					PayPlanORPrcntTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + PayPlanDBTotal);

			String PayPlanORPrcntWkToDt = data1.getText("" + OptionToView + "ORPrcntWkToDtPayPlan");
			PayPlanDBWkToDate = sql.CLRexecuteQuery(getContext(),
					PayPlanORPrcntWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + PayPlanDBWkToDate);

			String PayPlanORPrcntWkToDtTotal = data.getText("" + OptionToView + "ORPrcntWkToDtTotalPayPlan");
			PayPlanDBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					PayPlanORPrcntWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + PayPlanDBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, PayPlanDBTotal, PayPlanDBValues, "OR Percentage - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, PayPlanDBWkToDateTotal, PayPlanDBWkToDate,
					"OR Percentage - Week To Date");

		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - Items View", priority = 6)
	public void ItemsView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Items View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Items");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned Items View";
				Report_AddStep("testcase", " PLANNED ITEMS VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned Items View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actuals Items View";
				Report_AddStep("testcase", " ACTUAL ITEMS VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals Items View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance Items View";
				Report_AddStep("testcase", " VARIANCE ITEMS VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance Items View");

			}

			String ItemsColumn = data1.getText("" + OptionToView + "ItemsColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ItemsColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ItemsTotal = data.getText("" + OptionToView + "ItemsTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ItemsTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			String ItemsWkToDt = data1.getText("" + OptionToView + "ItemsWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					ItemsWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWkToDate);

			String ItemsWkToDtTotal = data.getText("" + OptionToView + "ItemsWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					ItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "Items - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "Items - Week To Date");
		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - Item Price View", priority = 7)
	public void ItemsPriceView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: ItemsPrice View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Item Price");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned Item Price View";
				Report_AddStep("testcase", " PLANNED ITEM PRICE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned Item Price View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actual Item Price View";
				Report_AddStep("testcase", " ACTUAL ITEM PRICE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actual Item Price View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance Item Price View";
				Report_AddStep("testcase", " VARIANCE ITEM PRICE VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance Item Price View");

			}

			String ItemsPriceColumn = data1.getText("" + OptionToView + "ItemsPriceColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ItemsPriceColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ItemsPriceTotal = data.getText("" + OptionToView + "ItemsPriceTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ItemsPriceTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWkToDate);

			String ItemsPriceWkToDt = data1.getText("" + OptionToView + "ItemsPriceWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					ItemsPriceWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			String ItemsPriceWkToDtTotal = data.getText("" + OptionToView + "ItemsPriceWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					ItemsPriceWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "Item Price - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "Item Price - Week To Date");
		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - Customer View", priority = 8)
	public void CustomersView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Customer View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Customers");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned Customer View";
				Report_AddStep("testcase", " PLANNED CUSTOMER VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned Customer View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actuals Customer View";
				Report_AddStep("testcase", " ACTUAL CUSTOMER VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals Customer View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance Customer View";
				Report_AddStep("testcase", " VARIANCE CUSTOMER VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance Customer View");

			}

			String CustomersColumn = data1.getText("" + OptionToView + "CustomersColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					CustomersColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String CustomersTotal = data.getText("" + OptionToView + "CustomersTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					CustomersTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			String CustomersWkToDt = data1.getText("" + OptionToView + "CustomersWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					CustomersWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWkToDate);

			String CustomersWkToDtTotal = data.getText("" + OptionToView + "CustomersWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					CustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "Customer - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "Customer - Week To Date");
		}
	}

	@Test(description = "Verify State Total Value and Respective State Value in BU Screen - CPH View", priority = 9)
	public void CPHView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: CPH View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "CPH");

		for (int i = 0; i <= 1; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				String DailyPlanningStore = "STATE to BU - Planned CPH View";
				Report_AddStep("testcase", " PLANNED CPH VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Planned CPH View");

			}
			if (i == 1) {
				OptionToView = "Actual";
				String DailyPlanningStore = "STATE to BU - Actuals CPH View";
				Report_AddStep("testcase", " ACTUAL CPH VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Actuals CPH View");

			}
			if (i == 2) {
				OptionToView = "Variance";
				String DailyPlanningStore = "STATE to BU - Variance CPH View";
				Report_AddStep("testcase", " VARIANCE CPH VIEW ", "", "", "");
				extentTest = report.startTest("STATE to BU - Variance CPH View");

			}

			String CPHColumn = data1.getText("" + OptionToView + "CPHColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					CPHColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String CPHTotal = data.getText("" + OptionToView + "CPHTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					CPHTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			String CPHWkToDt = data1.getText("" + OptionToView + "CPHWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					CPHWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBWkToDate);

			String CPHWkToDtTotal = data.getText("" + OptionToView + "CPHWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					CPHWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			pageDBComparisionPO.CompareValues(extentTest, DBTotal, DBValues, "CPH - Day Wise");
			pageDBComparisionPO.CompareValues(extentTest, DBWkToDateTotal, DBWkToDate, "CPH - Week To Date");
		}
	}

	@Test(priority = 10)
	public void LogoutCLRApplication() throws Exception {
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		pageDailyPlanningPO.LogOFF(objDailyPlanningStoreObjects);
	}

	@BeforeMethod
	public void setUpItemPrice(Method m, ITestContext testcontext) throws IOException {
		System.out.println("test1 - beforemethod");
		mName = m.getName().toString().trim();
		System.out.println(m.getName().toString().trim());
		if (!methodList.contains(m.getName().toString().trim())) {
			methodList.add(m.getName().toString().trim());
			extentTest = report.startTest(m.getName().toString().trim());
		}
		System.out.println(methodList.size());
		// initializeTestcontext();

		System.out.println("test1 - beforemethod");
		log.info("Loading Testdata for the test case: " + m.getName().toString());
		data.getTCNameFromChildren(m.getName().toString().trim());
		data1.getTCNameFromChildren(m.getName().toString().trim());
		testcontext.setAttribute("WebDriver", getDriver());
		testcontext.setAttribute("Customreports", this.customreport);
		customreport.startTest(m.getName().toString());
		initializeTestStatus();
		reportPath = new File(getContext().getStringProperty("reportPath"));
		moduledesc = m.getName().toString().trim();
		dtmoduledesc = m.getAnnotation(Test.class).description();
		testcasesttime = new Date();
		pageWeeklyPlanningPO = PageFactory.initElements(this.getDriver(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objMonthlyPlanningGroupObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupObjects.class);
		objWeeklyTradingStatementStoreObjects = PageFactory.initElements(this.getDriver(),
				WeeklyTradingStatementStoreObjects.class);
		pageDailyTradingStatementGroupPO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementGroupPage.class);
		pageDBComparisionPO = PageFactory.initElements(this.getDriver(), DBComparision.class);
		objMonthlyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningStoreObjects.class);
		pageDailyPlanningPO = PageFactory.initElements(this.getDriver(), DailyPlanningPage.class);
		objDailyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), DailyPlanningStoreObjects.class);
		ScreenShotUtil = PageFactory.initElements(this.getDriver(), ExtentReportsScreenshotCode.class);
		System.out.println(System.getProperty("user.dir"));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy_hmmss");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate); // 12/01/2011 4:48:16 PM
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String formattedDate1 = dateFormat.format(date);
		System.out.println(formattedDate1); // 12/01/2011 4:48:16 PM
		String dat = formattedDate1;
		Report_Header("testcase", reportPath, moduledesc, dat);
	}

	@BeforeClass
	public void loadXMLFile() {
		try {
			cName = this.getClass().getSimpleName();
			reportPath = new File(getContext().getStringProperty("reportPath"));
			report = new ExtentReports(
					getContext().getStringProperty("reportPath") + "\\" + this.getClass().getSimpleName() + ".html");
			classList.add(this.getClass().getSimpleName());
			initializeTestcontext();
			sql.CLRConnectDB(context);
			System.out.println("test1 - Beforeclass");
			data = new XMLDataReader(log);
			customreport = new CustomExtentReports();
			createDriver(getContext().getStringProperty("WLPUrl"), getContext().getStringProperty("browser"), cName,
					mName, extentTest, driver1);
			log.info("Loading data for the class: ");
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\DailyPlanningState.xml");
			data1.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\DailyPlanningBU.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}