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
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import au.wow.WLPmain.objects.DailyPlanningMainObjects;
import au.wow.WLPmain.objects.DailyPlanningStoreObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
import au.wow.WLPmain.pages.DailyPlanningPage;
import au.wow.WLPmain.pages.DailyTradingStatementStorePage;
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

public class DailyPlanningState extends TestBase {

	protected Logger log = LogManager.getLogger(DailyPlanningState.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	ExtentTest extLogger;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	DailyPlanningStoreObjects objDailyPlanningStoreObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	DailyPlanningPage pageDailyPlanningPO;
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
	String OptionToView, Month;

	public DailyPlanningState() {
		super.log = log;
	}

	/**
	 * AL_1 Verifying Department Hierarchy and Paginations
	 * 
	 * @throws Exception
	 */

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToStatePage(getContext().getStringProperty("store"));
	}

	@Test(description = "Daily Planning - State, Sales View", priority = 1)
	public void SalesView() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Sales View");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,objWeeklyPlanningStoreObjects.planningScreens, "Planning Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar, objWeeklyPlanningStoreObjects.dailyPlanning, "Daily Planning/Actual");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar, objWeeklyPlanningStoreObjects.DPscreenToView,"State");
		//pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("DailyPlanningState"));

		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Sales");
		pageDailyPlanningPO.SelectDepartment(objDailyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("DepartmentToView"));
	
		for (int i = 0; i <= 2; i++) {
			if (i == 0) {
				OptionToView = "Planned";
				Report_AddStep("testcase", " PLANNED SALES VIEW ", "", "", "");
				extentTest = report.startTest("Planned Sales Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.Monday, objDailyPlanningStoreObjects.Tuesday,
						objDailyPlanningStoreObjects.Wednesday, objDailyPlanningStoreObjects.Thursday,
						objDailyPlanningStoreObjects.Friday, objDailyPlanningStoreObjects.Saturday,
						objDailyPlanningStoreObjects.Sunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects, objDailyPlanningStoreObjects.Total,
						objDailyPlanningStoreObjects.MondayTtl, objDailyPlanningStoreObjects.TuesdayTtl,
						objDailyPlanningStoreObjects.WednesdayTtl, objDailyPlanningStoreObjects.ThursdayTtl,
						objDailyPlanningStoreObjects.FridayTtl, objDailyPlanningStoreObjects.SaturdayTtl,
						objDailyPlanningStoreObjects.SundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.WeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.WeekToDateTtl);
			}
			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL SALES VIEW ", "", "", "");
				extentTest = report.startTest("Actual Sales Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTMonday, objDailyPlanningStoreObjects.ACTTuesday,
						objDailyPlanningStoreObjects.ACTWednesday, objDailyPlanningStoreObjects.ACTThursday,
						objDailyPlanningStoreObjects.ACTFriday, objDailyPlanningStoreObjects.ACTSaturday,
						objDailyPlanningStoreObjects.ACTSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ACTTotal, objDailyPlanningStoreObjects.ACTMondayTtl,
						objDailyPlanningStoreObjects.ACTTuesdayTtl, objDailyPlanningStoreObjects.ACTWednesdayTtl,
						objDailyPlanningStoreObjects.ACTThursdayTtl, objDailyPlanningStoreObjects.ACTFridayTtl,
						objDailyPlanningStoreObjects.ACTSaturdayTtl, objDailyPlanningStoreObjects.ACTSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTWeekToDateTtl);

			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIENCE SALES VIEW ", "", "", "");
				extentTest = report.startTest("Variance Sales Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, DailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARMonday, objDailyPlanningStoreObjects.VARTuesday,
						objDailyPlanningStoreObjects.VARWednesday, objDailyPlanningStoreObjects.VARThursday,
						objDailyPlanningStoreObjects.VARFriday, objDailyPlanningStoreObjects.VARSaturday,
						objDailyPlanningStoreObjects.VARSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VARTotal, objDailyPlanningStoreObjects.VARMondayTtl,
						objDailyPlanningStoreObjects.VARTuesdayTtl, objDailyPlanningStoreObjects.VARWednesdayTtl,
						objDailyPlanningStoreObjects.VARThursdayTtl, objDailyPlanningStoreObjects.VARFridayTtl,
						objDailyPlanningStoreObjects.VARSaturdayTtl, objDailyPlanningStoreObjects.VARSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARWeekToDateTtl);

			}

			String SalesColumn = data.getText("" + OptionToView + "SalesColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					SalesColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBValues);

			String SalesTotal = data.getText("" + OptionToView + "SalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					SalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBTotal);

			String SalesWkToDt = data.getText("" + OptionToView + "SalesWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					SalesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBWkToDate);

			String SalesWkToDtTotal = data.getText("" + OptionToView + "SalesWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					SalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			Report_AddStep("testcase", " SALES VIEW - DAY WISE DATA", "", "", "");
			extentTest = report.startTest("Day Wise Data Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBValues, UIValues);

			Report_AddStep("testcase", " SALES VIEW - DAY WISE TOTAL", "", "", "");
			extentTest = report.startTest("Day Wise Total Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBTotal, UITotal);

			Report_AddStep("testcase", " SALES VIEW - WEEK TO DATE", "", "", "");
			extentTest = report.startTest("Week to Date data Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDate, UIWkToDate);

			Report_AddStep("testcase", " SALES VIEW - WEEK TO DATE TOTAL", "", "", "");
			extentTest = report.startTest("Week to Date Total Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDateTotal, UIWkToDateTotal);

		}
	}

	@Test(description = "Daily Planning - State, Hours View", priority = 2)
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
				Report_AddStep("testcase", " PLANNED HOURS VIEW", "", "", "");
				extentTest = report.startTest("Planned Sales Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedHRTable, objDailyPlanningStoreObjects.HrDepartments,
						objDailyPlanningStoreObjects.HrMonday, objDailyPlanningStoreObjects.HrTuesday,
						objDailyPlanningStoreObjects.HrWednesday, objDailyPlanningStoreObjects.HrThursday,
						objDailyPlanningStoreObjects.HrFriday, objDailyPlanningStoreObjects.HrSaturday,
						objDailyPlanningStoreObjects.HrSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.HrTotal, objDailyPlanningStoreObjects.HrMondayTtl,
						objDailyPlanningStoreObjects.HrTuesdayTtl, objDailyPlanningStoreObjects.HrWednesdayTtl,
						objDailyPlanningStoreObjects.HrThursdayTtl, objDailyPlanningStoreObjects.HrFridayTtl,
						objDailyPlanningStoreObjects.HrSaturdayTtl, objDailyPlanningStoreObjects.HrSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedHRTable, objDailyPlanningStoreObjects.HrDepartments,
						objDailyPlanningStoreObjects.HrWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedHRTable, objDailyPlanningStoreObjects.HrWeekToDateTtl);

				String HoursColumn = data.getText("" + OptionToView + "HoursColumn");
				DBValues = sql.CLRexecuteQuery(getContext(),
						HoursColumn.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBValues);

				String HoursTotal = data.getText("" + OptionToView + "HoursTotal");
				DBTotal = sql.CLRexecuteQuery(getContext(),
						HoursTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBTotal);

				String HoursWkToDt = data.getText("" + OptionToView + "HoursWkToDt");
				DBWkToDate = sql.CLRexecuteQuery(getContext(),
						HoursWkToDt.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBWkToDate);

				String HoursWkToDtTotal = data.getText("" + OptionToView + "HoursWkToDtTotal");
				DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						HoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBWkToDateTotal);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE DATA", "", "", "");
				extentTest = report.startTest("Day Wise Data Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBValues, UIValues);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBTotal, UITotal);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDate, UIWkToDate);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDateTotal, UIWkToDateTotal);
			}

			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL HOURS VIEW", "", "", "");
				extentTest = report.startTest("Actual Hours Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHRTable, objDailyPlanningStoreObjects.ActualHrDepartments,
						objDailyPlanningStoreObjects.ActualHrMonday, objDailyPlanningStoreObjects.ActualHrTuesday,
						objDailyPlanningStoreObjects.ActualHrWednesday, objDailyPlanningStoreObjects.ActualHrThursday,
						objDailyPlanningStoreObjects.ActualHrFriday, objDailyPlanningStoreObjects.ActualHrSaturday,
						objDailyPlanningStoreObjects.ActualHrSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHrTotal, objDailyPlanningStoreObjects.ActualHrMondayTtl,
						objDailyPlanningStoreObjects.ActualHrTuesdayTtl,
						objDailyPlanningStoreObjects.ActualHrWednesdayTtl,
						objDailyPlanningStoreObjects.ActualHrThursdayTtl,
						objDailyPlanningStoreObjects.ActualHrFridayTtl,
						objDailyPlanningStoreObjects.ActualHrSaturdayTtl,
						objDailyPlanningStoreObjects.ActualHrSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHRTable, objDailyPlanningStoreObjects.ActualHrDepartments,
						objDailyPlanningStoreObjects.ActualHrWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHrTotal, objDailyPlanningStoreObjects.ActualHrWeekToDateTtl);

				PayHoursUIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHRTable, objDailyPlanningStoreObjects.ActualHrDepartments,
						objDailyPlanningStoreObjects.ActualPayHrMonday, objDailyPlanningStoreObjects.ActualPayHrTuesday,
						objDailyPlanningStoreObjects.ActualPayHrWednesday,
						objDailyPlanningStoreObjects.ActualPayHrThursday,
						objDailyPlanningStoreObjects.ActualPayHrFriday,
						objDailyPlanningStoreObjects.ActualPayHrSaturday,
						objDailyPlanningStoreObjects.ActualPayHrSunday);
				PayHoursUITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHrTotal, objDailyPlanningStoreObjects.ActualPayHrMondayTtl,
						objDailyPlanningStoreObjects.ActualPayHrTuesdayTtl,
						objDailyPlanningStoreObjects.ActualPayHrWednesdayTtl,
						objDailyPlanningStoreObjects.ActualPayHrThursdayTtl,
						objDailyPlanningStoreObjects.ActualPayHrFridayTtl,
						objDailyPlanningStoreObjects.ActualPayHrSaturdayTtl,
						objDailyPlanningStoreObjects.ActualPayHrSundayTtl);

				PayHoursUIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHRTable, objDailyPlanningStoreObjects.ActualHrDepartments,
						objDailyPlanningStoreObjects.ActualPayHrWeekToDate);
				PayHoursUIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualHrTotal,
						objDailyPlanningStoreObjects.ActualPayHrWeekToDateTtl);

				String HoursColumn = data.getText("" + OptionToView + "HoursColumn");
				DBValues = sql.CLRexecuteQuery(getContext(),
						HoursColumn.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBValues);

				String HoursTotal = data.getText("" + OptionToView + "HoursTotal");
				DBTotal = sql.CLRexecuteQuery(getContext(),
						HoursTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBTotal);

				String HoursWkToDt = data.getText("" + OptionToView + "HoursWkToDt");
				DBWkToDate = sql.CLRexecuteQuery(getContext(),
						HoursWkToDt.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBWkToDate);

				String HoursWkToDtTotal = data.getText("" + OptionToView + "HoursWkToDtTotal");
				DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						HoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBWkToDateTotal);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE DATA", "", "", "");
				extentTest = report.startTest("Day Wise Data Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBValues, UIValues);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBTotal, UITotal);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDate, UIWkToDate);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDateTotal, UIWkToDateTotal);

				// Pay Hours
				String PayHoursColumn = data.getText("" + OptionToView + "PayHoursColumn");
				PayHoursDBValues = sql.CLRexecuteQuery(getContext(),
						PayHoursColumn.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBValues);

				String PayHoursTotal = data.getText("" + OptionToView + "PayHoursTotal");
				PayHoursDBTotal = sql.CLRexecuteQuery(getContext(),
						PayHoursTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBTotal);

				String PayHoursWkToDt = data.getText("" + OptionToView + "PayHoursWkToDt");
				PayHoursDBWkToDate = sql.CLRexecuteQuery(getContext(),
						PayHoursWkToDt.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBWkToDate);

				String PayHoursWkToDtTotal = data.getText("" + OptionToView + "PayHoursWkToDtTotal");
				PayHoursDBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						PayHoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBWkToDateTotal);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE ", "", "", "");
				extentTest = report.startTest("PayPlan - Day Wise Data Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, PayHoursDBValues,
						PayHoursUIValues);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("PayPlan - Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, PayHoursDBTotal,
						PayHoursUITotal);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("PayPlan - Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, PayHoursDBWkToDate, PayHoursUIWkToDate);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("PayPlan - Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, PayHoursDBWkToDateTotal, PayHoursUIWkToDateTotal);

			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE HOURS VIEW", "", "", "");
				extentTest = report.startTest("Variance Hours Validation");	

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceHRTable, objDailyPlanningStoreObjects.VarHrDepartments,
						objDailyPlanningStoreObjects.VarHrMonday, objDailyPlanningStoreObjects.VarHrTuesday,
						objDailyPlanningStoreObjects.VarHrWednesday, objDailyPlanningStoreObjects.VarHrThursday,
						objDailyPlanningStoreObjects.VarHrFriday, objDailyPlanningStoreObjects.VarHrSaturday,
						objDailyPlanningStoreObjects.VarHrSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarHrTotal, objDailyPlanningStoreObjects.VarHrMondayTtl,
						objDailyPlanningStoreObjects.VarHrTuesdayTtl, objDailyPlanningStoreObjects.VarHrWednesdayTtl,
						objDailyPlanningStoreObjects.VarHrThursdayTtl, objDailyPlanningStoreObjects.VarHrFridayTtl,
						objDailyPlanningStoreObjects.VarHrSaturdayTtl, objDailyPlanningStoreObjects.VarHrSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceHRTable, objDailyPlanningStoreObjects.VarHrDepartments,
						objDailyPlanningStoreObjects.VarHrWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarHrTotal, objDailyPlanningStoreObjects.VarHrWeekToDateTtl);

				String HoursColumn = data.getText("" + OptionToView + "HoursColumn");
				DBValues = sql.CLRexecuteQuery(getContext(),
						HoursColumn.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBValues);

				String HoursTotal = data.getText("" + OptionToView + "HoursTotal");
				DBTotal = sql.CLRexecuteQuery(getContext(),
						HoursTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBTotal);

				String HoursWkToDt = data.getText("" + OptionToView + "HoursWkToDt");
				DBWkToDate = sql.CLRexecuteQuery(getContext(),
						HoursWkToDt.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBWkToDate);

				String HoursWkToDtTotal = data.getText("" + OptionToView + "HoursWkToDtTotal");
				DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						HoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBWkToDateTotal);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE DATA", "", "", "");
				extentTest = report.startTest("Day Wise Data Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBValues, UIValues);

				Report_AddStep("testcase", " HOURS VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBTotal, UITotal);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDate, UIWkToDate);

				Report_AddStep("testcase", " HOURS VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDateTotal, UIWkToDateTotal);

			}
		}
	}

	@Test(description = "Daily Planning - State, Wages View", priority = 3)
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
				Report_AddStep("testcase", " PLANNED WAGES VIEW", "", "", "");
				extentTest = report.startTest("Planned Wages Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.Monday, objDailyPlanningStoreObjects.Tuesday,
						objDailyPlanningStoreObjects.Wednesday, objDailyPlanningStoreObjects.Thursday,
						objDailyPlanningStoreObjects.Friday, objDailyPlanningStoreObjects.Saturday,
						objDailyPlanningStoreObjects.Sunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects, objDailyPlanningStoreObjects.Total,
						objDailyPlanningStoreObjects.MondayTtl, objDailyPlanningStoreObjects.TuesdayTtl,
						objDailyPlanningStoreObjects.WednesdayTtl, objDailyPlanningStoreObjects.ThursdayTtl,
						objDailyPlanningStoreObjects.FridayTtl, objDailyPlanningStoreObjects.SaturdayTtl,
						objDailyPlanningStoreObjects.SundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.WeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.WeekToDateTtl);

			}
			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL WAGES VIEW", "", "", "");
				extentTest = report.startTest("Actual Wages Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTMonday, objDailyPlanningStoreObjects.ACTTuesday,
						objDailyPlanningStoreObjects.ACTWednesday, objDailyPlanningStoreObjects.ACTThursday,
						objDailyPlanningStoreObjects.ACTFriday, objDailyPlanningStoreObjects.ACTSaturday,
						objDailyPlanningStoreObjects.ACTSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ACTTotal, objDailyPlanningStoreObjects.ACTMondayTtl,
						objDailyPlanningStoreObjects.ACTTuesdayTtl, objDailyPlanningStoreObjects.ACTWednesdayTtl,
						objDailyPlanningStoreObjects.ACTThursdayTtl, objDailyPlanningStoreObjects.ACTFridayTtl,
						objDailyPlanningStoreObjects.ACTSaturdayTtl, objDailyPlanningStoreObjects.ACTSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTWeekToDateTtl);

			}

			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE WAGES VIEW", "", "", "");
				extentTest = report.startTest("Variance Wages Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARMonday, objDailyPlanningStoreObjects.VARTuesday,
						objDailyPlanningStoreObjects.VARWednesday, objDailyPlanningStoreObjects.VARThursday,
						objDailyPlanningStoreObjects.VARFriday, objDailyPlanningStoreObjects.VARSaturday,
						objDailyPlanningStoreObjects.VARSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VARTotal, objDailyPlanningStoreObjects.VARMondayTtl,
						objDailyPlanningStoreObjects.VARTuesdayTtl, objDailyPlanningStoreObjects.VARWednesdayTtl,
						objDailyPlanningStoreObjects.VARThursdayTtl, objDailyPlanningStoreObjects.VARFridayTtl,
						objDailyPlanningStoreObjects.VARSaturdayTtl, objDailyPlanningStoreObjects.VARSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARWeekToDateTtl);

			}

			String WagesColumn = data.getText("" + OptionToView + "WagesColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					WagesColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBValues);

			String WagesTotal = data.getText("" + OptionToView + "WagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					WagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBWkToDate);

			String WagesWkToDt = data.getText("" + OptionToView + "WagesWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					WagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBTotal);

			String WagesWkToDtTotal = data.getText("" + OptionToView + "WagesWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					WagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			Report_AddStep("testcase", " WAGES VIEW - DAY WISE ", "", "", "");
			extentTest = report.startTest("Day Wise Data Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBValues, UIValues);

			Report_AddStep("testcase", " WAGES VIEW - DAY WISE TOTAL", "", "", "");
			extentTest = report.startTest("Day Wise Total Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBTotal, UITotal);

			Report_AddStep("testcase", " WAGES VIEW - WEEK TO DATE ", "", "", "");
			extentTest = report.startTest("Week to Date data Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDate, UIWkToDate);

			Report_AddStep("testcase", " WAGES VIEW - WEEK TO DATE TOTAL", "", "", "");
			extentTest = report.startTest("Week to Date Total Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDateTotal, UIWkToDateTotal);
		}
	}

	@Test(description = "Daily Planning - State, WagesPercentage View", priority = 4)
	public void WagesPercentageView() throws Exception {

		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: WagesPercentageView View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Wage%");
		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				Report_AddStep("testcase", " PLANNED WAGE PERCENTAGE VIEW", "", "", "");
				extentTest = report.startTest("Planned Wages Percentage Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.Monday, objDailyPlanningStoreObjects.Tuesday,
						objDailyPlanningStoreObjects.Wednesday, objDailyPlanningStoreObjects.Thursday,
						objDailyPlanningStoreObjects.Friday, objDailyPlanningStoreObjects.Saturday,
						objDailyPlanningStoreObjects.Sunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects, objDailyPlanningStoreObjects.Total,
						objDailyPlanningStoreObjects.MondayTtl, objDailyPlanningStoreObjects.TuesdayTtl,
						objDailyPlanningStoreObjects.WednesdayTtl, objDailyPlanningStoreObjects.ThursdayTtl,
						objDailyPlanningStoreObjects.FridayTtl, objDailyPlanningStoreObjects.SaturdayTtl,
						objDailyPlanningStoreObjects.SundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.WeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.WeekToDateTtl);

			}
			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL WAGE PERCENTAGE VIEW", "", "", "");
				extentTest = report.startTest("Actual Wages Percentage Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTMonday, objDailyPlanningStoreObjects.ACTTuesday,
						objDailyPlanningStoreObjects.ACTWednesday, objDailyPlanningStoreObjects.ACTThursday,
						objDailyPlanningStoreObjects.ACTFriday, objDailyPlanningStoreObjects.ACTSaturday,
						objDailyPlanningStoreObjects.ACTSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ACTTotal, objDailyPlanningStoreObjects.ACTMondayTtl,
						objDailyPlanningStoreObjects.ACTTuesdayTtl, objDailyPlanningStoreObjects.ACTWednesdayTtl,
						objDailyPlanningStoreObjects.ACTThursdayTtl, objDailyPlanningStoreObjects.ACTFridayTtl,
						objDailyPlanningStoreObjects.ACTSaturdayTtl, objDailyPlanningStoreObjects.ACTSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTWeekToDateTtl);

			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE WAGE PERCENTAGE VIEW", "", "", "");
				extentTest = report.startTest("Variance Wages Percentage Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARMonday, objDailyPlanningStoreObjects.VARTuesday,
						objDailyPlanningStoreObjects.VARWednesday, objDailyPlanningStoreObjects.VARThursday,
						objDailyPlanningStoreObjects.VARFriday, objDailyPlanningStoreObjects.VARSaturday,
						objDailyPlanningStoreObjects.VARSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VARTotal, objDailyPlanningStoreObjects.VARMondayTtl,
						objDailyPlanningStoreObjects.VARTuesdayTtl, objDailyPlanningStoreObjects.VARWednesdayTtl,
						objDailyPlanningStoreObjects.VARThursdayTtl, objDailyPlanningStoreObjects.VARFridayTtl,
						objDailyPlanningStoreObjects.VARSaturdayTtl, objDailyPlanningStoreObjects.VARSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARWeekToDateTtl);

			}

			String WagesPrcntColumn = data.getText("" + OptionToView + "WagesPrcntColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					WagesPrcntColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBValues);

			String WagesPrcntTotal = data.getText("" + OptionToView + "WagesPrcntTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					WagesPrcntTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBWkToDate);

			String WagesPrcntWkToDt = data.getText("" + OptionToView + "WagesPrcntWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					WagesPrcntWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBTotal);

			String WagesPrcntWkToDtTotal = data.getText("" + OptionToView + "WagesPrcntWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					WagesPrcntWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			Report_AddStep("testcase", " WAGE PERCENTAGE VIEW - DAY WISE ", "", "", "");
			extentTest = report.startTest("Day Wise Data Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBValues, UIValues);

			Report_AddStep("testcase", " WAGE PERCENTAGE VIEW - DAY WISE TOTAL", "", "", "");
			extentTest = report.startTest("Day Wise Total Validation");

			pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBTotal, UITotal);

			Report_AddStep("testcase", " WAGE PERCENTAGE VIEW - WEEK TO DATE ", "", "", "");
			extentTest = report.startTest("Week to Date data Validation");

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWkToDate, UIWkToDate);

			Report_AddStep("testcase", " WAGE PERCENTAGE VIEW - WEEK TO DATE TOTAL", "", "", "");
			extentTest = report.startTest("Week to Date Total Validation");

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWkToDateTotal, UIWkToDateTotal);
		}
	}

	@Test(description = "Daily Planning - State, Operatinoal Ratio View", priority = 5)
	public void ORPercentageView() throws Exception {

		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: ORPercentageView View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Operating Ratio");

		// OR% Work Plan
		for (int i = 0; i <= 2; i++) {
			if (i == 0) {
				OptionToView = "Planned";
				Report_AddStep("testcase", " PLANNED OPERATIONAL RATIO VIEW", "", "", "");
				extentTest = report.startTest("Planned OR Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedORTable, objDailyPlanningStoreObjects.ORDepartments,
						objDailyPlanningStoreObjects.ORMonday, objDailyPlanningStoreObjects.ORTuesday,
						objDailyPlanningStoreObjects.ORWednesday, objDailyPlanningStoreObjects.ORThursday,
						objDailyPlanningStoreObjects.ORFriday, objDailyPlanningStoreObjects.ORSaturday,
						objDailyPlanningStoreObjects.ORSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotal, objDailyPlanningStoreObjects.ORMondayTtl,
						objDailyPlanningStoreObjects.ORTuesdayTtl, objDailyPlanningStoreObjects.ORWednesdayTtl,
						objDailyPlanningStoreObjects.ORThursdayTtl, objDailyPlanningStoreObjects.ORFridayTtl,
						objDailyPlanningStoreObjects.ORSaturdayTtl, objDailyPlanningStoreObjects.ORSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedORTable, objDailyPlanningStoreObjects.ORDepartments,
						objDailyPlanningStoreObjects.ORWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotal, objDailyPlanningStoreObjects.ORWeekToDateTtl);

				String ORPrcntColumn = data.getText("" + OptionToView + "ORPrcntColumn");
				DBValues = sql.CLRexecuteQuery(getContext(),
						ORPrcntColumn.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBValues);

				String ORPrcntTotal = data.getText("" + OptionToView + "ORPrcntTotal");
				DBTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBTotal);

				String ORPrcntWkToDt = data.getText("" + OptionToView + "ORPrcntWkToDt");
				DBWkToDate = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDt.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBWkToDate);

				String ORPrcntWkToDtTotal = data.getText("" + OptionToView + "ORPrcntWkToDtTotal");
				DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDtTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBWkToDateTotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE ", "", "", "");
				extentTest = report.startTest("Day Wise Data Validation");
				// DTSCompareValuesTotal
				pageDailyTradingStatementStorePO.CompareValueDecimalORFields(extentTest, DBValues, UIValues);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBTotal, UITotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDate, UIWkToDate);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDateTotal, UIWkToDateTotal);

			}
		/*	if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL OPERATIONAL RATIO VIEW", "", "", "");
				extentTest = report.startTest("Actual OR Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualORTable, objDailyPlanningStoreObjects.ORDepartmentsActl,
						objDailyPlanningStoreObjects.ORMondayActl, objDailyPlanningStoreObjects.ORTuesdayActl,
						objDailyPlanningStoreObjects.ORWednesdayActl, objDailyPlanningStoreObjects.ORThursdayActl,
						objDailyPlanningStoreObjects.ORFridayActl, objDailyPlanningStoreObjects.ORSaturdayActl,
						objDailyPlanningStoreObjects.ORSundayActl);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotalActl, objDailyPlanningStoreObjects.ORMondayTtlActl,
						objDailyPlanningStoreObjects.ORTuesdayTtlActl, objDailyPlanningStoreObjects.ORWednesdayTtlActl,
						objDailyPlanningStoreObjects.ORThursdayTtlActl, objDailyPlanningStoreObjects.ORFridayTtlActl,
						objDailyPlanningStoreObjects.ORSaturdayTtlActl, objDailyPlanningStoreObjects.ORSundayTtlActl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualORTable, objDailyPlanningStoreObjects.ORDepartmentsActl,
						objDailyPlanningStoreObjects.ORWeekToDateActl);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotalActl, objDailyPlanningStoreObjects.ORWeekToDateTtlActl);

				PayPlanUIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualORTable, objDailyPlanningStoreObjects.ORDepartmentsActl,
						objDailyPlanningStoreObjects.PayORMondayActl, objDailyPlanningStoreObjects.PayORTuesdayActl,
						objDailyPlanningStoreObjects.PayORWednesdayActl, objDailyPlanningStoreObjects.PayORThursdayActl,
						objDailyPlanningStoreObjects.PayORFridayActl, objDailyPlanningStoreObjects.PayORSaturdayActl,
						objDailyPlanningStoreObjects.PayORSundayActl);
				PayPlanUITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotalActl, objDailyPlanningStoreObjects.PayORMondayTtlActl,
						objDailyPlanningStoreObjects.PayORTuesdayTtlActl,
						objDailyPlanningStoreObjects.PayORWednesdayTtlActl,
						objDailyPlanningStoreObjects.PayORThursdayTtlActl,
						objDailyPlanningStoreObjects.PayORFridayTtlActl,
						objDailyPlanningStoreObjects.PayORSaturdayTtlActl,
						objDailyPlanningStoreObjects.PayORSundayTtlActl);

				PayPlanUIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualORTable, objDailyPlanningStoreObjects.ORDepartmentsActl,
						objDailyPlanningStoreObjects.PayORWeekToDateActl);
				PayPlanUIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotalActl, objDailyPlanningStoreObjects.PayORWeekToDateTtlActl);

				String ORPrcntColumn = data.getText("" + OptionToView + "ORPrcntColumn");
				DBValues = sql.CLRexecuteQuery(getContext(),
						ORPrcntColumn.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBValues);

				String ORPrcntTotal = data.getText("" + OptionToView + "ORPrcntTotal");
				DBTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBTotal);

				String ORPrcntWkToDt = data.getText("" + OptionToView + "ORPrcntWkToDt");
				DBWkToDate = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDt.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBWkToDate);

				String ORPrcntWkToDtTotal = data.getText("" + OptionToView + "ORPrcntWkToDtTotal");
				DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDtTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBWkToDateTotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE ", "", "", "");
				extentTest = report.startTest("Day Wise Data Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBValues, UIValues);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBTotal, UITotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDate, UIWkToDate);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDateTotal, UIWkToDateTotal);

				// Pay Plan
				Report_AddStep("testcase", "OR PERCENTAGE VIEW - PAY PLAN VALUE VALIDATION", "", "", "");

				String ORPrcntColumnPayPlan = data.getText("" + OptionToView + "ORPrcntColumnPayPlan");
				PayPlanDBValues = sql.CLRexecuteQuery(getContext(),
						ORPrcntColumnPayPlan.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("PayPlanDB Value: " + PayPlanDBValues);

				String ORPrcntTotalPayPlan = data.getText("" + OptionToView + "ORPrcntTotalPayPlan");
				PayPlanDBTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntTotalPayPlan.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("PayPlanDB Total: " + PayPlanDBTotal);

				String ORPrcntWkToDtPayPlan = data.getText("" + OptionToView + "ORPrcntWkToDtPayPlan");
				PayPlanDBWkToDate = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDtPayPlan.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("PayPlanDB Value: " + PayPlanDBWkToDate);

				String ORPrcntWkToDtTotalPayPlan = data.getText("" + OptionToView + "ORPrcntWkToDtTotalPayPlan");
				PayPlanDBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDtTotalPayPlan.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("PayPlanDB Total: " + PayPlanDBWkToDateTotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE ", "", "", "");
				extentTest = report.startTest("PayPlan - Day Wise Data Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, PayPlanDBValues,PayPlanUIValues);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("PayPlan - Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, PayPlanDBTotal, PayPlanUITotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("PayPlan - Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, PayPlanDBWkToDate, PayPlanUIWkToDate);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("PayPlan - Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, PayPlanDBWkToDateTotal, PayPlanUIWkToDateTotal);
			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE OPERATIONAL RATIO VIEW", "", "", "");
				extentTest = report.startTest("Variance OR Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceORTable, objDailyPlanningStoreObjects.VARORDepartments,
						objDailyPlanningStoreObjects.VARORMonday, objDailyPlanningStoreObjects.VARORTuesday,
						objDailyPlanningStoreObjects.VARORWednesday, objDailyPlanningStoreObjects.VARORThursday,
						objDailyPlanningStoreObjects.VARORFriday, objDailyPlanningStoreObjects.VARORSaturday,
						objDailyPlanningStoreObjects.VARORSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotal, objDailyPlanningStoreObjects.VARORMondayTtl,
						objDailyPlanningStoreObjects.VARORTuesdayTtl, objDailyPlanningStoreObjects.VARORWednesdayTtl,
						objDailyPlanningStoreObjects.VARORThursdayTtl, objDailyPlanningStoreObjects.VARORFridayTtl,
						objDailyPlanningStoreObjects.VARORSaturdayTtl, objDailyPlanningStoreObjects.VARORSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceORTable, objDailyPlanningStoreObjects.VARORDepartments,
						objDailyPlanningStoreObjects.VARORWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ORTotal, objDailyPlanningStoreObjects.VARORWeekToDateTtl);

				String ORPrcntColumn = data.getText("" + OptionToView + "ORPrcntColumn");
				DBValues = sql.CLRexecuteQuery(getContext(),
						ORPrcntColumn.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBValues);

				String ORPrcntTotal = data.getText("" + OptionToView + "ORPrcntTotal");
				DBTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBTotal);

				String ORPrcntWkToDt = data.getText("" + OptionToView + "ORPrcntWkToDt");
				DBWkToDate = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDt.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Value: " + DBWkToDate);

				String ORPrcntWkToDtTotal = data.getText("" + OptionToView + "ORPrcntWkToDtTotal");
				DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
						ORPrcntWkToDtTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("FinWk", getContext().getStringProperty("FinWk"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("Dept", getContext().getStringProperty("DepartmentToView"))
								.replace("Year", getContext().getStringProperty("Year"))
								.replace("FinWk", getContext().getStringProperty("FinWk")));
				System.out.println("DB Total: " + DBWkToDateTotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE ", "", "", "");
				extentTest = report.startTest("Day Wise Data Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBValues, UIValues);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - DAY WISE TOTAL", "", "", "");
				extentTest = report.startTest("Day Wise Total Validation");

				pageDailyTradingStatementStorePO.CompareValueDecimalORTotal(extentTest, DBTotal, UITotal);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE ", "", "", "");
				extentTest = report.startTest("Week to Date data Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDate, UIWkToDate);

				Report_AddStep("testcase", " OPERATIONAL RATIO VIEW - WEEK TO DATE TOTAL", "", "", "");
				extentTest = report.startTest("Week to Date Total Validation");

				pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBWkToDateTotal, UIWkToDateTotal);

			}*/
		}
	}

	@Test(description = "Daily Planning - State, Items View", priority = 6)
	public void ItemsView() throws Exception {

		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Items View ");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Items");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				Report_AddStep("testcase", " PLANNED ITEMS VIEW", "", "", "");
				extentTest = report.startTest("Planned Items Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.Monday, objDailyPlanningStoreObjects.Tuesday,
						objDailyPlanningStoreObjects.Wednesday, objDailyPlanningStoreObjects.Thursday,
						objDailyPlanningStoreObjects.Friday, objDailyPlanningStoreObjects.Saturday,
						objDailyPlanningStoreObjects.Sunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects, objDailyPlanningStoreObjects.Total,
						objDailyPlanningStoreObjects.MondayTtl, objDailyPlanningStoreObjects.TuesdayTtl,
						objDailyPlanningStoreObjects.WednesdayTtl, objDailyPlanningStoreObjects.ThursdayTtl,
						objDailyPlanningStoreObjects.FridayTtl, objDailyPlanningStoreObjects.SaturdayTtl,
						objDailyPlanningStoreObjects.SundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.WeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.WeekToDateTtl);

			}
			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL ITEMS VIEW", "", "", "");
				extentTest = report.startTest("Actual Items Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTMonday, objDailyPlanningStoreObjects.ACTTuesday,
						objDailyPlanningStoreObjects.ACTWednesday, objDailyPlanningStoreObjects.ACTThursday,
						objDailyPlanningStoreObjects.ACTFriday, objDailyPlanningStoreObjects.ACTSaturday,
						objDailyPlanningStoreObjects.ACTSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ACTTotal, objDailyPlanningStoreObjects.ACTMondayTtl,
						objDailyPlanningStoreObjects.ACTTuesdayTtl, objDailyPlanningStoreObjects.ACTWednesdayTtl,
						objDailyPlanningStoreObjects.ACTThursdayTtl, objDailyPlanningStoreObjects.ACTFridayTtl,
						objDailyPlanningStoreObjects.ACTSaturdayTtl, objDailyPlanningStoreObjects.ACTSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTWeekToDateTtl);

			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE ITEMS VIEW", "", "", "");
				extentTest = report.startTest("Variance Items Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARMonday, objDailyPlanningStoreObjects.VARTuesday,
						objDailyPlanningStoreObjects.VARWednesday, objDailyPlanningStoreObjects.VARThursday,
						objDailyPlanningStoreObjects.VARFriday, objDailyPlanningStoreObjects.VARSaturday,
						objDailyPlanningStoreObjects.VARSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VARTotal, objDailyPlanningStoreObjects.VARMondayTtl,
						objDailyPlanningStoreObjects.VARTuesdayTtl, objDailyPlanningStoreObjects.VARWednesdayTtl,
						objDailyPlanningStoreObjects.VARThursdayTtl, objDailyPlanningStoreObjects.VARFridayTtl,
						objDailyPlanningStoreObjects.VARSaturdayTtl, objDailyPlanningStoreObjects.VARSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARWeekToDateTtl);

			}

			String ItemsColumn = data.getText("" + OptionToView + "ItemsColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ItemsColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBValues);

			String ItemsTotal = data.getText("" + OptionToView + "ItemsTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ItemsTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBTotal);

			String ItemsWkToDt = data.getText("" + OptionToView + "ItemsWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					ItemsWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBWkToDate);

			String ItemsWkToDtTotal = data.getText("" + OptionToView + "ItemsWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					ItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			Report_AddStep("testcase", " ITEMS VIEW - DAY WISE ", "", "", "");
			extentTest = report.startTest("Day Wise Data Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBValues, UIValues);

			Report_AddStep("testcase", " ITEMS VIEW - DAY WISE TOTAL", "", "", "");
			extentTest = report.startTest("Day Wise Total Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBTotal, UITotal);

			Report_AddStep("testcase", " ITEMS VIEW - WEEK TO DATE ", "", "", "");
			extentTest = report.startTest("Week to Date data Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDate, UIWkToDate);

			Report_AddStep("testcase", " ITEMS VIEW - WEEK TO DATE TOTAL", "", "", "");
			extentTest = report.startTest("Week to Date Total Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDateTotal, UIWkToDateTotal);
		}
	}

	@Test(description = "Daily Planning - State, Items Price View", priority = 7)
	public void ItemsPriceView() throws Exception {

		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Items Price View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Item Price");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				Report_AddStep("testcase", " PLANNED ITEM PRICE VIEW", "", "", "");
				extentTest = report.startTest("Planned Item Price Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.Monday, objDailyPlanningStoreObjects.Tuesday,
						objDailyPlanningStoreObjects.Wednesday, objDailyPlanningStoreObjects.Thursday,
						objDailyPlanningStoreObjects.Friday, objDailyPlanningStoreObjects.Saturday,
						objDailyPlanningStoreObjects.Sunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects, objDailyPlanningStoreObjects.Total,
						objDailyPlanningStoreObjects.MondayTtl, objDailyPlanningStoreObjects.TuesdayTtl,
						objDailyPlanningStoreObjects.WednesdayTtl, objDailyPlanningStoreObjects.ThursdayTtl,
						objDailyPlanningStoreObjects.FridayTtl, objDailyPlanningStoreObjects.SaturdayTtl,
						objDailyPlanningStoreObjects.SundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.WeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.WeekToDateTtl);

			}
			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL ITEM PRICE VIEW", "", "", "");
				extentTest = report.startTest("Actual Item Price Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTMonday, objDailyPlanningStoreObjects.ACTTuesday,
						objDailyPlanningStoreObjects.ACTWednesday, objDailyPlanningStoreObjects.ACTThursday,
						objDailyPlanningStoreObjects.ACTFriday, objDailyPlanningStoreObjects.ACTSaturday,
						objDailyPlanningStoreObjects.ACTSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ACTTotal, objDailyPlanningStoreObjects.ACTMondayTtl,
						objDailyPlanningStoreObjects.ACTTuesdayTtl, objDailyPlanningStoreObjects.ACTWednesdayTtl,
						objDailyPlanningStoreObjects.ACTThursdayTtl, objDailyPlanningStoreObjects.ACTFridayTtl,
						objDailyPlanningStoreObjects.ACTSaturdayTtl, objDailyPlanningStoreObjects.ACTSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTWeekToDateTtl);

			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE ITEM PRICE VIEW", "", "", "");
				extentTest = report.startTest("Variance Item Price Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARMonday, objDailyPlanningStoreObjects.VARTuesday,
						objDailyPlanningStoreObjects.VARWednesday, objDailyPlanningStoreObjects.VARThursday,
						objDailyPlanningStoreObjects.VARFriday, objDailyPlanningStoreObjects.VARSaturday,
						objDailyPlanningStoreObjects.VARSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VARTotal, objDailyPlanningStoreObjects.VARMondayTtl,
						objDailyPlanningStoreObjects.VARTuesdayTtl, objDailyPlanningStoreObjects.VARWednesdayTtl,
						objDailyPlanningStoreObjects.VARThursdayTtl, objDailyPlanningStoreObjects.VARFridayTtl,
						objDailyPlanningStoreObjects.VARSaturdayTtl, objDailyPlanningStoreObjects.VARSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARWeekToDateTtl);

			}

			String ItemsPriceColumn = data.getText("" + OptionToView + "ItemsPriceColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ItemsPriceColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBValues);

			String ItemsPriceTotal = data.getText("" + OptionToView + "ItemsPriceTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ItemsPriceTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBWkToDate);

			String ItemsPriceWkToDt = data.getText("" + OptionToView + "ItemsPriceWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					ItemsPriceWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBTotal);

			String ItemsPriceWkToDtTotal = data.getText("" + OptionToView + "ItemsPriceWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					ItemsPriceWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			Report_AddStep("testcase", " ITEM PRICE VIEW - DAY WISE ", "", "", "");
			extentTest = report.startTest("Day Wise Data Validation");

			pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBValues, UIValues);

			Report_AddStep("testcase", " ITEM PRICE VIEW - DAY WISE TOTAL", "", "", "");
			extentTest = report.startTest("Day Wise Total Validation");

			pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBTotal, UITotal);

			Report_AddStep("testcase", " ITEM PRICE VIEW - WEEK TO DATE ", "", "", "");
			extentTest = report.startTest("Week to Date data Validation");

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWkToDate, UIWkToDate);

			Report_AddStep("testcase", " ITEM PRICE VIEW - WEEK TO DATE TOTAL", "", "", "");
			extentTest = report.startTest("Week to Date Total Validation");

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWkToDateTotal, UIWkToDateTotal);
		}
	}

	@Test(description = "Daily Planning - State, Customers View", priority = 8)
	public void CustomersView() throws Exception {

		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: Customers View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "Customers");

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				Report_AddStep("testcase", " PLANNED CUSTOMERS VIEW", "", "", "");
				extentTest = report.startTest("Planned Customers Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.Monday, objDailyPlanningStoreObjects.Tuesday,
						objDailyPlanningStoreObjects.Wednesday, objDailyPlanningStoreObjects.Thursday,
						objDailyPlanningStoreObjects.Friday, objDailyPlanningStoreObjects.Saturday,
						objDailyPlanningStoreObjects.Sunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects, objDailyPlanningStoreObjects.Total,
						objDailyPlanningStoreObjects.MondayTtl, objDailyPlanningStoreObjects.TuesdayTtl,
						objDailyPlanningStoreObjects.WednesdayTtl, objDailyPlanningStoreObjects.ThursdayTtl,
						objDailyPlanningStoreObjects.FridayTtl, objDailyPlanningStoreObjects.SaturdayTtl,
						objDailyPlanningStoreObjects.SundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.WeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.WeekToDateTtl);

			}
			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL CUSTOMERS VIEW", "", "", "");
				extentTest = report.startTest("Actual Customers Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTMonday, objDailyPlanningStoreObjects.ACTTuesday,
						objDailyPlanningStoreObjects.ACTWednesday, objDailyPlanningStoreObjects.ACTThursday,
						objDailyPlanningStoreObjects.ACTFriday, objDailyPlanningStoreObjects.ACTSaturday,
						objDailyPlanningStoreObjects.ACTSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ACTTotal, objDailyPlanningStoreObjects.ACTMondayTtl,
						objDailyPlanningStoreObjects.ACTTuesdayTtl, objDailyPlanningStoreObjects.ACTWednesdayTtl,
						objDailyPlanningStoreObjects.ACTThursdayTtl, objDailyPlanningStoreObjects.ACTFridayTtl,
						objDailyPlanningStoreObjects.ACTSaturdayTtl, objDailyPlanningStoreObjects.ACTSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTWeekToDateTtl);

			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE CUSTOMERS VIEW", "", "", "");
				extentTest = report.startTest("Variance Customers Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARMonday, objDailyPlanningStoreObjects.VARTuesday,
						objDailyPlanningStoreObjects.VARWednesday, objDailyPlanningStoreObjects.VARThursday,
						objDailyPlanningStoreObjects.VARFriday, objDailyPlanningStoreObjects.VARSaturday,
						objDailyPlanningStoreObjects.VARSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VARTotal, objDailyPlanningStoreObjects.VARMondayTtl,
						objDailyPlanningStoreObjects.VARTuesdayTtl, objDailyPlanningStoreObjects.VARWednesdayTtl,
						objDailyPlanningStoreObjects.VARThursdayTtl, objDailyPlanningStoreObjects.VARFridayTtl,
						objDailyPlanningStoreObjects.VARSaturdayTtl, objDailyPlanningStoreObjects.VARSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARWeekToDateTtl);

			}

			String CustomersColumn = data.getText("" + OptionToView + "CustomersColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					CustomersColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBValues);

			String CustomersTotal = data.getText("" + OptionToView + "CustomersTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					CustomersTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBTotal);

			String CustomersWkToDt = data.getText("" + OptionToView + "CustomersWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					CustomersWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBWkToDate);

			String CustomersWkToDtTotal = data.getText("" + OptionToView + "CustomersWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					CustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			Report_AddStep("testcase", " CUSTOMERS VIEW - DAY WISE ", "", "", "");
			extentTest = report.startTest("Day Wise Data Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBValues, UIValues);

			Report_AddStep("testcase", " CUSTOMERS VIEW - DAY WISE TOTAL", "", "", "");
			extentTest = report.startTest("Day Wise Total Validation");

			pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBTotal, UITotal);

			Report_AddStep("testcase", " CUSTOMERS VIEW - WEEK TO DATE ", "", "", "");
			extentTest = report.startTest("Week to Date data Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDate, UIWkToDate);

			Report_AddStep("testcase", " CUSTOMERS VIEW - WEEK TO DATE TOTAL", "", "", "");
			extentTest = report.startTest("Week to Date Total Validation");

			pageMonthlyPlanningPO.CompareValues(extentTest, DBWkToDateTotal, UIWkToDateTotal);
		}
	}

	@Test(description = "Daily Planning - State, CPH View", priority = 9)
	public void CPHView() throws Exception {

		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyPlanningPO = new DailyPlanningPage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), DailyPlanningPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase: CPH View");
		pageDailyPlanningPO.SelectView(objDailyPlanningStoreObjects, extentTest, "CPH");
		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				OptionToView = "Planned";
				Report_AddStep("testcase", " PLANNED CPH VIEW", "", "", "");
				extentTest = report.startTest("Planned CPH Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.Monday, objDailyPlanningStoreObjects.Tuesday,
						objDailyPlanningStoreObjects.Wednesday, objDailyPlanningStoreObjects.Thursday,
						objDailyPlanningStoreObjects.Friday, objDailyPlanningStoreObjects.Saturday,
						objDailyPlanningStoreObjects.Sunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects, objDailyPlanningStoreObjects.Total,
						objDailyPlanningStoreObjects.MondayTtl, objDailyPlanningStoreObjects.TuesdayTtl,
						objDailyPlanningStoreObjects.WednesdayTtl, objDailyPlanningStoreObjects.ThursdayTtl,
						objDailyPlanningStoreObjects.FridayTtl, objDailyPlanningStoreObjects.SaturdayTtl,
						objDailyPlanningStoreObjects.SundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.Departments,
						objDailyPlanningStoreObjects.WeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.PlannedTable, objDailyPlanningStoreObjects.WeekToDateTtl);

			}
			if (i == 1) {
				OptionToView = "Actual";
				Report_AddStep("testcase", " ACTUAL CPH VIEW", "", "", "");
				extentTest = report.startTest("Actual CPH Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTMonday, objDailyPlanningStoreObjects.ACTTuesday,
						objDailyPlanningStoreObjects.ACTWednesday, objDailyPlanningStoreObjects.ACTThursday,
						objDailyPlanningStoreObjects.ACTFriday, objDailyPlanningStoreObjects.ACTSaturday,
						objDailyPlanningStoreObjects.ACTSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ACTTotal, objDailyPlanningStoreObjects.ACTMondayTtl,
						objDailyPlanningStoreObjects.ACTTuesdayTtl, objDailyPlanningStoreObjects.ACTWednesdayTtl,
						objDailyPlanningStoreObjects.ACTThursdayTtl, objDailyPlanningStoreObjects.ACTFridayTtl,
						objDailyPlanningStoreObjects.ACTSaturdayTtl, objDailyPlanningStoreObjects.ACTSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTDepartments,
						objDailyPlanningStoreObjects.ACTWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.ActualTable, objDailyPlanningStoreObjects.ACTWeekToDateTtl);

			}
			if (i == 2) {
				OptionToView = "Variance";
				Report_AddStep("testcase", " VARIANCE CPH VIEW", "", "", "");
				extentTest = report.startTest("Variance CPH Validation");

				UIValues = pageDailyPlanningPO.UIResults(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARMonday, objDailyPlanningStoreObjects.VARTuesday,
						objDailyPlanningStoreObjects.VARWednesday, objDailyPlanningStoreObjects.VARThursday,
						objDailyPlanningStoreObjects.VARFriday, objDailyPlanningStoreObjects.VARSaturday,
						objDailyPlanningStoreObjects.VARSunday);
				UITotal = pageDailyPlanningPO.UITotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VARTotal, objDailyPlanningStoreObjects.VARMondayTtl,
						objDailyPlanningStoreObjects.VARTuesdayTtl, objDailyPlanningStoreObjects.VARWednesdayTtl,
						objDailyPlanningStoreObjects.VARThursdayTtl, objDailyPlanningStoreObjects.VARFridayTtl,
						objDailyPlanningStoreObjects.VARSaturdayTtl, objDailyPlanningStoreObjects.VARSundayTtl);

				UIWkToDate = pageDailyPlanningPO.WkToDtValue(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARDepartments,
						objDailyPlanningStoreObjects.VARWeekToDate);
				UIWkToDateTotal = pageDailyPlanningPO.WkToDtTotal(objDailyPlanningStoreObjects,
						objDailyPlanningStoreObjects.VarianceTable, objDailyPlanningStoreObjects.VARWeekToDateTtl);
			}

			String CPHColumn = data.getText("" + OptionToView + "CPHColumn");
			DBValues = sql.CLRexecuteQuery(getContext(),
					CPHColumn.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBValues);

			String CPHTotal = data.getText("" + OptionToView + "CPHTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					CPHTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBTotal);

			String CPHWkToDt = data.getText("" + OptionToView + "CPHWkToDt");
			DBWkToDate = sql.CLRexecuteQuery(getContext(),
					CPHWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Value: " + DBWkToDate);

			String CPHWkToDtTotal = data.getText("" + OptionToView + "CPHWkToDtTotal");
			DBWkToDateTotal = sql.CLRexecuteQuery(getContext(),
					CPHWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Dept", getContext().getStringProperty("DepartmentToView"))
							.replace("Year", getContext().getStringProperty("Year"))
							.replace("FinWk", getContext().getStringProperty("FinWk")));
			System.out.println("DB Total: " + DBWkToDateTotal);

			Report_AddStep("testcase", " CPH VIEW - DAY WISE ", "", "", "");
			extentTest = report.startTest("Day Wise Data Validation");

			pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBValues, UIValues);

			Report_AddStep("testcase", " CPH VIEW - DAY WISE TOTAL", "", "", "");
			extentTest = report.startTest("Day Wise Total Validation");

			pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBTotal, UITotal);

			Report_AddStep("testcase", " CPH VIEW - WEEK TO DATE TOTAL", "", "", "");
			extentTest = report.startTest("Week to Date data Validation");

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWkToDate, UIWkToDate);

			Report_AddStep("testcase", " CPH VIEW - WEEK TO DATE ", "", "", "");
			extentTest = report.startTest("Week to Date Total Validation");

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBWkToDateTotal, UIWkToDateTotal);
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
		pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStorePage.class);
		pageDailyPlanningPO = PageFactory.initElements(this.getDriver(), DailyPlanningPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objMonthlyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningStoreObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\DailyPlanningState_ALL.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}