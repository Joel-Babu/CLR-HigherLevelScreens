package au.wow.WLPmain.DailyTradingStatement;

import java.awt.AWTException;

import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
import au.wow.WLPmain.objects.DailyTradingStatementGroupObjects;
import au.wow.WLPmain.objects.DailyTradingStatementObjects;
import au.wow.WLPmain.objects.DailyTradingStatementStateObjects;
import au.wow.WLPmain.objects.DailyTradingStatementZoneObjects;
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
import au.wow.WLPmain.pages.DailyTradingStatementGroupPage;
import au.wow.WLPmain.pages.DailyTradingStatementStorePage;
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

public class DTS_State extends TestBase {
	protected Logger log = LogManager.getLogger(DTS_State.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	DailyTradingStatementObjects objDailyTradingStatementObjects;
	DailyTradingStatementStateObjects objDailyTradingStatementStateObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	DailyTradingStatementStorePage pageDailyTradingStatementStorePO;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;

	List<List<Object>> UIValues, UITotal;
	List<List<Object>> PlndVal, ActlVal, LYVal, PlndValTtl, ActlValTtl, LYValTtl, BdgtSls, BdgtSlsTtl, EstFcst,
			EstFcstTtl, AllowedWgs, AllowedWgsTtl, Var, VarTtl, BdgtWg, BdgtWgTtl;
	List<List<Object>> PlndValWkToDt, ActlValWkToDt, LYValWkToDt, PlndValTtlWkToDt, ActlValTtlWkToDt,
			DBActlValTtlWkToDt, LYValTtlWkToDt, VarWkToDt, ORWkToDt, VarTtlWkToDt, ORTtlWkToDt, DBActlValTtl;
	List<List<Object>> DBPlndVal, DBActlVal, DBLYVal, DBPlndValTtl, DBActlTtl, DBLYValTtl, DBBdgtSls, DBBdgtSlsTtl,
			DBEstFcst, DBEstFcstTtl, DBAllowedWgs, DBAllowedWgsTtl, DBVar, DBVarTtl, DBBdgtWg, DBBdgtWgTtl, DBAlwdWgs,
			DBAlwdWgsTtl, AlwdWgs, AlwdWgsTtl, DBEstFcstSls, DBEstFcstSlsTtl, EstFcstSls, EstFcstSlsTtl, DBEstFcstWgs,
			DBEstFcstWgsTtl, EstFcstWgs, EstFcstWgsTtl, DBEstFcstWgsPrcnt, DBEstFcstWgsPrcntTtl, EstFcstWgsPrcnt,
			EstFcstWgsPrcntTtl, DBVarSales, DBVarSalesTtl, VarSls, VarSlsTtl, DBVarWages, DBVarWagesTtl, VarWages,
			VarWagesTtl, DBVarWagesPrcnt, DBVarWagesPrcntTtl, VarWagesPrcnt, VarWagesPrcntTtl, DBPlndORVal,
			DBPlndORValTtl, PlndValOR, PlndValORTtl, DBPlndValWkToDtOR, DBPlndValTtlWkToDtOR, PlndValWkToDtOR,
			PlndValTtlWkToDtOR;
	List<List<Object>> DBPlndValWkToDt, DBActlValWkToDt, DBLYValWkToDt, DBPlndValTtlWkToDt, DBActlTtlWkToDt,
			DBLYValTtlWkToDt, DBVarWkToDt, DBORWkToDt, DBVarWkToDtTtl, DBORWkToDtTtl, DBActlValOR, ActlValOR,
			DBActlValORTtl, ActlValORTtl, DBActlORVal, DBActlORValTtl, DBActlValWkToDtOR, DBActlValTtlWkToDtOR,
			ActlValWkToDtOR, ActlValTtlWkToDtOR,Allowedwages,AllowedwagesTtl;
	String WeekToTest, OptionToView, WeekValue = null;

	public DTS_State() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToStatePage(getContext().getStringProperty("store"));
	}

	//////////// Sales ///////////////
	@Test(description = "Daily Trading Statement - State, Sales Values", priority = 1)
	public void SalesColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS State - Sales View");
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.tradingScreens, "Trading Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.dailyTrading, "Daily Trading");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.screenToViewDailyTrading, "State");
		//pageWeeklyPlanningPO.clickButton(objDailyTradingStatementObjects.ShowButton, extentTest, "State");
		/*pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("DTSState"));*/

		WeekValue = getContext().getStringProperty("WeekToTest");
		OptionToView = getContext().getStringProperty("OptionToView");

		// Planned Sales
		Report_AddStep("testcase", " PLANNED SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales - Day Wise");
		
		String PlannedSales = data.getText("PlannedSales");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("Sales", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Actual Sales
		Report_AddStep("testcase", " ACTUAL SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales - Day Wise");
		if (WeekValue.contains("Past") ) {
		String ActualSales = data.getText("PastWeekActualSales");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualSalesTotal = data.getText("PastWeekActualSalesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		}
		if (WeekValue.contains("Current") ) {
			String ActualSales = data.getText("CurrentWeekActualSales");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					ActualSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String ActualSalesTotal = data.getText("CurrentWeekActualSalesTotal");
			DBActlTtl = sql.CLRexecuteQuery(getContext(),
					ActualSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtl);
			}
		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("Sales", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlVal, ActlVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlTtl, ActlValTtl);

		// LY Sales
		Report_AddStep("testcase", " LY SALES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Sales - Day Wise");
		if (WeekValue.contains("Past") ) {
		String LYsales = data.getText("PastWeekLYsales");
		DBLYVal = sql.CLRexecuteQuery(getContext(),
				LYsales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYVal);

		String LYSalesTotal = data.getText("PastWeekLYSalesTotal");
		DBLYValTtl = sql.CLRexecuteQuery(getContext(),
				LYSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValTtl);
		}
		if (WeekValue.contains("Current") ) {
			String LYsales = data.getText("CurrentWeekLYsales");
			DBLYVal = sql.CLRexecuteQuery(getContext(),
					LYsales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYVal);

			String LYSalesTotal = data.getText("CurrentWeekLYSalesTotal");
			DBLYValTtl = sql.CLRexecuteQuery(getContext(),
					LYSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtl);
			}
		LYVal = pageDailyTradingStatementGroupPO.DayWiseValue("Sales", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayLYSales, objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		LYValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayLYSales,
				objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYVal, LYVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYValTtl, LYValTtl);

		// Week to Date - Planned Sales
		Report_AddStep("testcase", " PLANNED SALES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Sales - Week Wise");
		
		String PlannedSalesWkToDt = data.getText("PlannedSalesWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedSalesWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedSalesWkToDtTotal = data.getText("PlannedSalesWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedSalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - Actual Sales
		Report_AddStep("testcase", " ACTUAL SALES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Sales - Week Wise");
		if (WeekValue.contains("Past") ) {
		String ActualSalesWkToDt = data.getText("PastWeekActualSalesWkToDt");
		DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
				ActualSalesWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlValWkToDt);

		String ActualSalesWkToDtTotal = data.getText("PastWeekActualSalesWkToDtTotal");
		DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				ActualSalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String ActualSalesWkToDt = data.getText("CurrentWeekActualSalesWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualSalesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualSalesWkToDtTotal = data.getText("CurrentWeekActualSalesWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualSalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
			}
		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtlWkToDt, ActlValTtlWkToDt);

		// Week to Date - LY Sales
		Report_AddStep("testcase", " LY SALES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("LY Sales - Week Wise");
		if (WeekValue.contains("Past") ) {
		String LYSalesWkToDt = data.getText("PastWeekLYSalesWkToDt");
		DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
				LYSalesWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValWkToDt);

		String LYSalesWkToDtTotal = data.getText("PastWeekLYSalesWkToDtTotal");
		DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				LYSalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String LYSalesWkToDt = data.getText("CurrentWeekLYSalesWkToDt");
			DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
					LYSalesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValWkToDt);

			String LYSalesWkToDtTotal = data.getText("CurrentWeekLYSalesWkToDtTotal");
			DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					LYSalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtlWkToDt);
			}
		LYValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		LYValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValWkToDt, LYValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValTtlWkToDt, LYValTtlWkToDt);

		// Week to Date - Variance Sales
		Report_AddStep("testcase", " VARIANCE SALES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Sales - Week Wise");
		if (WeekValue.contains("Past") ) {
		String VarSalesWkToDt = data.getText("PastWeekVarSalesWkToDt");
		DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
				VarSalesWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarWkToDt);

		String VarSalesWkToDtTotal = data.getText("PastWeekVarSalesWkToDtTotal");
		DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
				VarSalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarWkToDtTtl);
		}
		if (WeekValue.contains("Current") ) {
			String VarSalesWkToDt = data.getText("CurrentWeekVarSalesWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					VarSalesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String VarSalesWkToDtTotal = data.getText("CurrentWeekVarSalesWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					VarSalesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
			}
		
		VarWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		VarTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDt, VarWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDtTtl, VarTtlWkToDt);

		// Budget Sales
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET SALES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Budget Sales");
			
			String BudgetSales = data.getText("BudgetSales");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBBdgtSlsTtl = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSlsTtl);

			BdgtSls = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			BdgtSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, BdgtSls);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSlsTtl, BdgtSlsTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST SALES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Forecast Sales");
			
			String ForecastSales = data.getText("ForecastSales");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBBdgtSlsTtl = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSlsTtl);

			BdgtSls = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			BdgtSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, BdgtSls);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSlsTtl, BdgtSlsTtl);
		}

		// Estimated Forecast Sales
		Report_AddStep("testcase", " ESTIMATED FORECAST SALES - BUDGET COLUMN", "", "", "");
		extentTest = report.startTest("Estimated Forecast Sales");
		if (WeekValue.contains("Past") ) {
		String EstimatedFcstSales = data.getText("PastWeekEstimatedFcstSales");
		DBEstFcstSls = sql.CLRexecuteQuery(getContext(),
				EstimatedFcstSales.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBEstFcstSls);

		String EstimatedFcstSalesTotal = data.getText("PastWeekEstimatedFcstSalesTotal");
		DBEstFcstSlsTtl = sql.CLRexecuteQuery(getContext(),
				EstimatedFcstSalesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBEstFcstSlsTtl);
		}
		if (WeekValue.contains("Current") ) {
			String EstimatedFcstSales = data.getText("CurrentWeekEstimatedFcstSales");
			DBEstFcstSls = sql.CLRexecuteQuery(getContext(),
					EstimatedFcstSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstSls);

			String EstimatedFcstSalesTotal = data.getText("CurrentWeekEstimatedFcstSalesTotal");
			DBEstFcstSlsTtl = sql.CLRexecuteQuery(getContext(),
					EstimatedFcstSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstSlsTtl);
			}
		EstFcstSls = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.EstForecastSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		EstFcstSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.EstForecastSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBEstFcstSls, EstFcstSls);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBEstFcstSlsTtl, EstFcstSlsTtl);

		// Variance Sales
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET VARIANCE SALES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Variance Budget Sales");
			if (WeekValue.contains("Past") ) {
			String VarSales = data.getText("PastWeekVarSales");
			DBVarSales = sql.CLRexecuteQuery(getContext(),
					VarSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarSales);

			String VarSalesTotal = data.getText("PastWeekVarSalesTotal");
			DBVarSalesTtl = sql.CLRexecuteQuery(getContext(),
					VarSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarSalesTtl);
			}
			if (WeekValue.contains("Current") ) {
				String VarSales = data.getText("CurrentWeekVarSales");
				DBVarSales = sql.CLRexecuteQuery(getContext(),
						VarSales.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarSales);

				String VarSalesTotal = data.getText("CurrentWeekVarSalesTotal");
				DBVarSalesTtl = sql.CLRexecuteQuery(getContext(),
						VarSalesTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarSalesTtl);
				}
			VarSls = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			VarSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarSales, VarSls);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarSalesTtl, VarSlsTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST VARIANCE SALES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Variace Forecast Sales");
			if (WeekValue.contains("Past") ) {
			String ForecastVarSales = data.getText("PastWeekForecastVarSales");
			DBVarSales = sql.CLRexecuteQuery(getContext(),
					ForecastVarSales.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarSales);

			String ForecastVarSalesTotal = data.getText("PastWeekForecastVarSalesTotal");
			DBVarSalesTtl = sql.CLRexecuteQuery(getContext(),
					ForecastVarSalesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarSalesTtl);
			}
			
			if (WeekValue.contains("Current") ) {
				String ForecastVarSales = data.getText("CurrentWeekForecastVarSales");
				DBVarSales = sql.CLRexecuteQuery(getContext(),
						ForecastVarSales.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarSales);

				String ForecastVarSalesTotal = data.getText("CurrentWeekForecastVarSalesTotal");
				DBVarSalesTtl = sql.CLRexecuteQuery(getContext(),
						ForecastVarSalesTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarSalesTtl);
				}
			VarSls = pageDailyTradingStatementGroupPO.WeekToDateValue("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			VarSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Sales",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarSales, VarSls);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarSalesTtl, VarSlsTtl);
		}
		//Allowed Wages 
		if (OptionToView.contains("Budget"))
		{
		Report_AddStep("testcase", " Allowed Wages - BUDGET COLUMN", "", "", "");
		extentTest = report.startTest("Allowed Wages");
		if (WeekValue.contains("Past") ) {
		String AllowedWgs = data.getText("PastWeekAllowedWages");
		DBAllowedWgs = sql.CLRexecuteQuery(getContext(),
				AllowedWgs.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAllowedWgs);

		String AllowedWgsTtl = data.getText("PastWeekAllowedWagesTotal");
		DBAllowedWgsTtl = sql.CLRexecuteQuery(getContext(),
				AllowedWgsTtl.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBAllowedWgsTtl);
		}
		if (WeekValue.contains("Current") ) {
			String AllowedWgs = data.getText("CurrentWeekAllowedWages");
			DBAllowedWgs = sql.CLRexecuteQuery(getContext(),
					AllowedWgs.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBAllowedWgs);

			String AllowedWgsTtl = data.getText("CurrentWeekAllowedWagesTotal");
			DBAllowedWgsTtl = sql.CLRexecuteQuery(getContext(),
					AllowedWgsTtl.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBAllowedWgsTtl);
			}
		Allowedwages = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
				objDailyTradingStatementStateObjects.DataFrame,objDailyTradingStatementStateObjects.AllowedWages,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		AllowedwagesTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame,objDailyTradingStatementStateObjects.AllowedWages,
				objDailyTradingStatementStateObjects.Table,objDailyTradingStatementStateObjects.StateTotal);

		pageWeeklyPlanningPO.CompareValues(extentTest, DBAllowedWgs, Allowedwages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBAllowedWgsTtl, AllowedwagesTtl );
	}
	}

	//////////// SMS ///////////////
	@Test(description = "Daily Trading Statement - State, SMS Values", priority = 2)
	public void SMSColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - SMS View");

		WeekValue = getContext().getStringProperty("WeekToTest");

		// Planned SMS
		Report_AddStep("testcase", " PLANNED SMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned SMS - Day wise");
		
		String PlannedSMS = data.getText("PlannedSMS");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedSMS.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedSMSTotal = data.getText("PlannedSMSTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedSMSTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("SMS", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("SMS",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Actual SMS
	/*	Report_AddStep("testcase", " ACTUAL SMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual SMS - Day wise");
		
		if (WeekValue.contains("Past")) {
			String ActualSMS = data.getText("PastWeekActualSMS");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					ActualSMS.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String ActualSMSTotal = data.getText("PastWeekActualSMSTotal");
			DBActlValTtl = sql.CLRexecuteQuery(getContext(),
					ActualSMSTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtl);
		}
		if (WeekValue.contains("Current") ) {
			String CurrentActualSMS = data.getText("CurrentWeekActualSMS");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					CurrentActualSMS.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String CurrentActualSMSTotal = data.getText("CurrentWeekActualSMSTotal");
			DBActlValTtl = sql.CLRexecuteQuery(getContext(),
					CurrentActualSMSTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtl);
		}

		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("SMS", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("SMS",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlVal, ActlVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlValTtl, ActlValTtl);
*/
		// Week to Date - Planned SMS
		Report_AddStep("testcase", " PLANNED SMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned SMS - Week wise");
		
		String PlannedSMSWkToDt = data.getText("PlannedSMSWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedSMSWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedSMSWkToDtTotal = data.getText("PlannedSMSWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedSMSWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("SMS",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("SMS",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - ACTUAL SMS
	/*	Report_AddStep("testcase", " ACTUAL SMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual SMS - Week wise");
		
		if (WeekValue.contains("Past")) {
			String ActualSMSWkToDt = data.getText("PastWeekActualSMSWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualSMSWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualSMSWkToDtTotal = data.getText("PastWeekActualSMSWkToDtTotal");
			DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualSMSWkToDtTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String CurrentActualSMSWkToDt = data.getText("CurrentWeekActualSMSWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualSMSWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String CurrentActualSMSWkToDtTotal = data.getText("CurrentWeekActualSMSWkToDtTotal");
			DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualSMSWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDt);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("SMS",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("SMS",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValTtlWkToDt, ActlValTtlWkToDt);
		//Variance SMS
		Report_AddStep("testcase", " VARIANCE SMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance SMS - Week wise");
		
		if (WeekValue.contains("Past")) {
			String ActualSMSWkToDt = data.getText("PastWeekVarSMSWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualSMSWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualSMSWkToDtTotal = data.getText("PastWeekVarSMSWkToDtTotal");
			DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualSMSWkToDtTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String CurrentActualSMSWkToDt = data.getText("CurrentWeekVarSMSWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualSMSWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String CurrentActualSMSWkToDtTotal = data.getText("CurrentWeekVarSMSWkToDtTotal");
			DBActlValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualSMSWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDt);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("SMS",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("SMS",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateVarSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValTtlWkToDt, ActlValTtlWkToDt);*/
	}

	//////////// OR ///////////////
	@Test(description = "Daily Trading Statement - State, OR Values", priority = 3)
	public void ORColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - OR View");

		WeekValue = getContext().getStringProperty("WeekToTest");

		// Planned OR
		Report_AddStep("testcase", " PLANNED OR - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Day wise");
		
		String PlannedOR = data.getText("PlannedOR");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedOR.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedORTotal = data.getText("PlannedORTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedORTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("OR", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("OR", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Actual OR
	/*	Report_AddStep("testcase", " ACTUAL OR - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual OR - Day wise");
		
		if (WeekValue.contains("Past")) {
			String ActualOR = data.getText("PastWeekActualOR");
			DBActlORVal = sql.CLRexecuteQuery(getContext(),
					ActualOR.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlORVal);

			String ActualORTotal = data.getText("PastWeekActualORTotal");
			DBActlORValTtl = sql.CLRexecuteQuery(getContext(),
					ActualORTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlORValTtl);
		}
		if (WeekValue.contains("Current") ) {
			String CurrentActualOR = data.getText("CurrentWeekActualOR");
			DBActlORVal = sql.CLRexecuteQuery(getContext(),
					CurrentActualOR.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlORVal);

			String CurrentActualORTotal = data.getText("CurrentWeekActualORTotal");
			DBActlORValTtl = sql.CLRexecuteQuery(getContext(),
					CurrentActualORTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlORValTtl);
		}

		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("OR", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("OR", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlORVal, ActlVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlORValTtl, ActlValTtl); */

		// Week to Date - Planned OR
		Report_AddStep("testcase", " PLANNED OR - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Week wise");
		
		String PlannedORWkToDt = data.getText("PlannedORWkToDt");
		DBPlndValWkToDtOR = sql.CLRexecuteQuery(getContext(),
				PlannedORWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedORWkToDtTotal = data.getText("PlannedORWkToDtTotal");
		DBPlndValTtlWkToDtOR = sql.CLRexecuteQuery(getContext(),
				PlannedORWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("OR",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("OR",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValWkToDtOR, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValTtlWkToDtOR, PlndValTtlWkToDt);

		// Week to Date - ACTUAL OR
	/*	Report_AddStep("testcase", " ACTUAL OR - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned OR - Week wise");
		
		if (WeekValue.contains("Past")) {
			String ActualORWkToDt = data.getText("PastWeekActualORWkToDt");
			DBActlValWkToDtOR = sql.CLRexecuteQuery(getContext(),
					ActualORWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDtOR);

			String ActualORWkToDtTotal = data.getText("PastWeekActualORWkToDtTotal");
			DBActlValTtlWkToDtOR = sql.CLRexecuteQuery(getContext(),
					ActualORWkToDtTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDtOR);
		}
		if (WeekValue.contains("Current") ) {
			String CurrentActualORWkToDt = data.getText("CurrentWeekActualORWkToDt");
			DBActlValWkToDtOR = sql.CLRexecuteQuery(getContext(),
					CurrentActualORWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDtOR);

			String CurrentActualORWkToDtTotal = data.getText("CurrentWeekActualORWkToDtTotal");
			DBActlValTtlWkToDtOR = sql.CLRexecuteQuery(getContext(),
					CurrentActualORWkToDtTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDtOR);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("OR",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("OR",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValWkToDtOR, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValTtlWkToDtOR, ActlValTtlWkToDt);
		//Variance OR
		Report_AddStep("testcase", " VARIANCE OR - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance OR - Week wise");
		
		if (WeekValue.contains("Past")) {
			String ActualORWkToDt = data.getText("PastWeekVarORWkToDt");
			DBActlValWkToDtOR = sql.CLRexecuteQuery(getContext(),
					ActualORWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDtOR);

			String ActualORWkToDtTotal = data.getText("PastWeekVarORWkToDtTotal");
			DBActlValTtlWkToDtOR = sql.CLRexecuteQuery(getContext(),
					ActualORWkToDtTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDtOR);
		}
		if (WeekValue.contains("Current") ) {
			String CurrentActualORWkToDt = data.getText("CurrentWeekVarORWkToDt");
			DBActlValWkToDtOR = sql.CLRexecuteQuery(getContext(),
					CurrentActualORWkToDt.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDtOR);

			String CurrentActualORWkToDtTotal = data.getText("CurrentWeekVarORWkToDtTotal");
			DBActlValTtlWkToDtOR = sql.CLRexecuteQuery(getContext(),
					CurrentActualORWkToDtTotal.replace("FinWk", getContext().getStringProperty("FinWk"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Year", getContext().getStringProperty("FinYr"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValTtlWkToDtOR);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("OR",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("OR",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateVarSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValWkToDtOR, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValTtlWkToDtOR, ActlValTtlWkToDt);*/
	}

	//////////// Hours ///////////////
	@Test(description = "Daily Trading Statement - State, Hours Values", priority = 4)
	public void HoursColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - SMS View");
		WeekValue = getContext().getStringProperty("WeekToTest");

		// Planned Hours
		Report_AddStep("testcase", " PLANNED HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours - Day wise");
		
		String PlannedHours = data.getText("PlannedHours");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("Hours", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Hours",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Actual Hours
		Report_AddStep("testcase", " ACTUAL HOURS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Hours - Day wise");
		if (WeekValue.contains("Past") ) {
		String ActualHours = data.getText("PastWeekActualHours");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualHours.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualHoursTotal = data.getText("PastWeekActualHoursTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualHoursTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		}
		if (WeekValue.contains("Current") ) {
			String ActualHours = data.getText("CurrentWeekActualHours");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					ActualHours.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String ActualHoursTotal = data.getText("CurrentWeekActualHoursTotal");
			DBActlTtl = sql.CLRexecuteQuery(getContext(),
					ActualHoursTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtl);
			}
		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("Hours", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Hours",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlVal, ActlVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlTtl, ActlValTtl);

		// Week to Date - Planned Hours
		Report_AddStep("testcase", " PLANNED HOURS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Hours - Week wise");
		
		String PlannedHoursWkToDt = data.getText("PlannedHoursWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedHoursWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedHoursWkToDtTotal = data.getText("PlannedHoursWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedHoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Hours",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Hours",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - Actual Hours
		Report_AddStep("testcase", " ACTUAL HOURS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Hours - Week wise");
		
		if (WeekValue.contains("Past") ) {
			String ActualHoursWkToDt = data.getText("PastWeekActualHoursWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualHoursWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualHoursWkToDtTotal = data.getText("PastWeekActualHoursWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualHoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}
		if (WeekValue.contains("Current")) {
			String CurrentActualHoursWkToDt = data.getText("CurrentWeekActualHoursWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualHoursWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String CurrentActualHoursWkToDtTotal = data.getText("CurrentWeekActualHoursWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualHoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Hours",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Hours",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlTtlWkToDt, ActlValTtlWkToDt);

		// Week to Date - Variance Hours
		Report_AddStep("testcase", " VARIANCE HOURS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Hours - Week wise");
		
		if (WeekValue.contains("Past") ) {
			String VarHoursWkToDt = data.getText("PastWeekVarHoursWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					VarHoursWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String VarHoursWkToDtTotal = data.getText("PastWeekVarHoursWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					VarHoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}
		if (WeekValue.contains("Current")) {
			String CurrentVarHoursWkToDt = data.getText("CurrentWeekVarHoursWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentVarHoursWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String CurrentVarHoursWkToDtTotal = data.getText("CurrentWeekVarHoursWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					CurrentVarHoursWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}

		VarWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Hours",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		VarTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Hours",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWkToDt, VarWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWkToDtTtl, VarTtlWkToDt);
	}

	@Test(description = "Daily Trading Statement - State, Items Values", priority = 5)
	public void ItemsColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Items View");

		WeekValue = getContext().getStringProperty("WeekToTest");

		// Planned Items
		Report_AddStep("testcase", " PLANNED ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Items - Day wise");
		
		String PlannedItems = data.getText("PlannedItems");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedItemsTotal = data.getText("PlannedItemsTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("Items", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Items",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Actual Items
		Report_AddStep("testcase", " ACTUAL ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Items - Day wise");
		if (WeekValue.contains("Past") ) {
		String ActualItems = data.getText("PastWeekActualItems");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualItemsTotal = data.getText("PastWeekActualItemsTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		}
		if (WeekValue.contains("Current") ) {
			String ActualItems = data.getText("CurrentWeekActualItems");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					ActualItems.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String ActualItemsTotal = data.getText("CurrentWeekActualItemsTotal");
			DBActlTtl = sql.CLRexecuteQuery(getContext(),
					ActualItemsTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtl);
			}
		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("Items", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Items",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlVal, ActlVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlTtl, ActlValTtl);

		// LY Items
		Report_AddStep("testcase", " LY ITEMS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Items - Day wise");
		if (WeekValue.contains("Past") ) {
		String LYItems = data.getText("PastWeekLYItems");
		DBLYVal = sql.CLRexecuteQuery(getContext(),
				LYItems.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYVal);

		String LYItemsTotal = data.getText("PastWeekLYItemsTotal");
		DBLYValTtl = sql.CLRexecuteQuery(getContext(),
				LYItemsTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValTtl);
		}
		if (WeekValue.contains("Current") ) {
			String LYItems = data.getText("CurrentWeekLYItems");
			DBLYVal = sql.CLRexecuteQuery(getContext(),
					LYItems.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYVal);

			String LYItemsTotal = data.getText("CurrentWeekLYItemsTotal");
			DBLYValTtl = sql.CLRexecuteQuery(getContext(),
					LYItemsTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtl);
			}
		LYVal = pageDailyTradingStatementGroupPO.DayWiseValue("Items", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayLYSales, objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		LYValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Items",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayLYSales,
				objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYVal, LYVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYValTtl, LYValTtl);

		// Week to Date - Planned Items
		Report_AddStep("testcase", " PLANNED ITEMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Items - Week wise");
		
		String PlannedItemsWkToDt = data.getText("PlannedItemsWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedItemsWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedItemsWkToDtTotal = data.getText("PlannedItemsWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Items",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Items",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - Actual Items
		Report_AddStep("testcase", " ACTUAL ITEMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Items - Week wise");
		if (WeekValue.contains("Past") ) {
		String ActualItemsWkToDt = data.getText("PastWeekActualItemsWkToDt");
		DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
				ActualItemsWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlValWkToDt);

		String ActualItemsWkToDtTotal = data.getText("PastWeekActualItemsWkToDtTotal");
		DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				ActualItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String ActualItemsWkToDt = data.getText("CurrentWeekActualItemsWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualItemsWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualItemsWkToDtTotal = data.getText("CurrentWeekActualItemsWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
			}
		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Items",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Items",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtlWkToDt, ActlValTtlWkToDt);

		// Week to Date - LY Items
		Report_AddStep("testcase", " LY ITEMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("LY Items - Week wise");
		if (WeekValue.contains("Past") ) {
		String LYItemsWkToDt = data.getText("PastWeekLYItemsWkToDt");
		DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
				LYItemsWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValWkToDt);

		String LYItemsWkToDtTotal = data.getText("PastWeekLYItemsWkToDtTotal");
		DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				LYItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String LYItemsWkToDt = data.getText("CurrentWeekLYItemsWkToDt");
			DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
					LYItemsWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValWkToDt);

			String LYItemsWkToDtTotal = data.getText("CurrentWeekLYItemsWkToDtTotal");
			DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					LYItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtlWkToDt);
			}
		LYValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Items",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		LYValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Items",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValWkToDt, LYValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValTtlWkToDt, LYValTtlWkToDt);

		// Week to Date - Variance Items
		Report_AddStep("testcase", " VARIANCE ITEMS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Items - Week wise");
		if (WeekValue.contains("Past") ) {
		String VarItemsWkToDt = data.getText("PastWeekVarItemsWkToDt");
		DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
				VarItemsWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarWkToDt);

		String VarItemsWkToDtTotal = data.getText("PastWeekVarItemsWkToDtTotal");
		DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
				VarItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarWkToDtTtl);
		}
		if (WeekValue.contains("Current") ) {
			String VarItemsWkToDt = data.getText("CurrentWeekVarItemsWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					VarItemsWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String VarItemsWkToDtTotal = data.getText("CurrentWeekVarItemsWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					VarItemsWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
			}
		VarWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Items",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		VarTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Items",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDt, VarWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDtTtl, VarTtlWkToDt);
	}

	//////////// CUSTOMERS ///////////////
	@Test(description = "Daily Trading Statement - State, Customers Values", priority = 6)
	public void CustomersColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Items View");
		WeekValue = getContext().getStringProperty("WeekToTest");

		// Planned Customers
		Report_AddStep("testcase", " PLANNED CUSTOMERS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Customers - Day wise");
		
		String PlannedCustomers = data.getText("PlannedCustomers");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedCustomers.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedCustomersTotal = data.getText("PlannedCustomersTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedCustomersTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Actual Customers
		Report_AddStep("testcase", " ACTUAL CUSTOMERS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Customers - Day wise");
		if (WeekValue.contains("Past") ) {
		String ActualCustomers = data.getText("PastWeekActualCustomers");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualCustomers.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualCustomersTotal = data.getText("PastWeekActualCustomersTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualCustomersTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		}
		if (WeekValue.contains("Current") ) {
			String ActualCustomers = data.getText("CurrentWeekActualCustomers");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					ActualCustomers.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String ActualCustomersTotal = data.getText("CurrentWeekActualCustomersTotal");
			DBActlTtl = sql.CLRexecuteQuery(getContext(),
					ActualCustomersTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtl);
			}
		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlVal, ActlVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlTtl, ActlValTtl);

		// LY Customers
		Report_AddStep("testcase", " LY CUSTOMERS - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Customers - Day wise");
		if (WeekValue.contains("Past") ) {
		String LYCustomers = data.getText("PastWeekLYCustomers");
		DBLYVal = sql.CLRexecuteQuery(getContext(),
				LYCustomers.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYVal);

		String LYCustomersTotal = data.getText("PastWeekLYCustomersTotal");
		DBLYValTtl = sql.CLRexecuteQuery(getContext(),
				LYCustomersTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValTtl);
		}
		if (WeekValue.contains("Current") ) {
			String LYCustomers = data.getText("CurrentWeekLYCustomers");
			DBLYVal = sql.CLRexecuteQuery(getContext(),
					LYCustomers.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYVal);

			String LYCustomersTotal = data.getText("CurrentWeekLYCustomersTotal");
			DBLYValTtl = sql.CLRexecuteQuery(getContext(),
					LYCustomersTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtl);
			}
		LYVal = pageDailyTradingStatementGroupPO.DayWiseValue("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayLYSales,
				objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		LYValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayLYSales,
				objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYVal, LYVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYValTtl, LYValTtl);

		// Week to Date - Planned Customers
		Report_AddStep("testcase", " PLANNED CUSTOMERS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Customers - Week wise");
		
		String PlannedCustomersWkToDt = data.getText("PlannedCustomersWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedCustomersWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedCustomersWkToDtTotal = data.getText("PlannedCustomersWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedCustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Customers",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Customers",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - Actual Customers
		Report_AddStep("testcase", " ACTUAL CUSTOMERS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Customers - Week wise");
		if (WeekValue.contains("Past") ) {
		String ActualCustomersWkToDt = data.getText("PastWeekActualCustomersWkToDt");
		DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
				ActualCustomersWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlValWkToDt);

		String ActualCustomersWkToDtTotal = data.getText("PastWeekActualCustomersWkToDtTotal");
		DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				ActualCustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String ActualCustomersWkToDt = data.getText("CurrentWeekActualCustomersWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualCustomersWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualCustomersWkToDtTotal = data.getText("CurrentWeekActualCustomersWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualCustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
			}
		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Customers",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Customers",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtlWkToDt, ActlValTtlWkToDt);

		// Week to Date - LY Customers
		Report_AddStep("testcase", " LY CUSTOMERS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("LY Customers - Week wise");
		if (WeekValue.contains("Past") ) {
		String LYCustomersWkToDt = data.getText("PastWeekLYCustomersWkToDt");
		DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
				LYCustomersWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValWkToDt);

		String LYCustomersWkToDtTotal = data.getText("PastWeekLYCustomersWkToDtTotal");
		DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				LYCustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValTtlWkToDt);
		}
		if (WeekValue.contains("Current") ) {
			String LYCustomersWkToDt = data.getText("CurrentWeekLYCustomersWkToDt");
			DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
					LYCustomersWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValWkToDt);

			String LYCustomersWkToDtTotal = data.getText("CurrentWeekLYCustomersWkToDtTotal");
			DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					LYCustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtlWkToDt);
			}
		
		
		LYValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		LYValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValWkToDt, LYValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValTtlWkToDt, LYValTtlWkToDt);

		// Week to Date - Variance Customers
		Report_AddStep("testcase", " VARIANCE CUSTOMERS - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Customers - Week wise");
		
		if (WeekValue.contains("Past") ) {
		String VarCustomersWkToDt = data.getText("PastWeekVarCustomersWkToDt");
		DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
				VarCustomersWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarWkToDt);

		String VarCustomersWkToDtTotal = data.getText("PastWeekVarCustomersWkToDtTotal");
		DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
				VarCustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBVarWkToDtTtl);
		}
		if (WeekValue.contains("Current") ) {
			String VarCustomersWkToDt = data.getText("CurrentWeekVarCustomersWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					VarCustomersWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String VarCustomersWkToDtTotal = data.getText("CurrentWeekVarCustomersWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					VarCustomersWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
			}
		VarWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		VarTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Customers",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDt, VarWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDtTtl, VarTtlWkToDt);
	}

	//////////// WAGES PERCENTAGE ///////////////
	@Test(description = "Daily Trading Statement - State, Wages Values", priority = 7)
	public void WagesPercentageColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Items View");
		WeekValue = getContext().getStringProperty("WeekToTest");

		// Day wise - Planned WagesPercentage
		Report_AddStep("testcase", " PLANNED WAGES PERCENTAGE - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages Prcnt - Day wise");
		
		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Day wise - Actual WagesPercentage
		Report_AddStep("testcase", " ACTUAL WAGES PERCENTAGE - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages Prcnt - Day wise");
		if (WeekValue.contains("Past") ) {
		String ActualWagesPercentage = data.getText("PastWeekActualWagesPercentage");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWagesPercentage.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagesPercentageTotal = data.getText("PastWeekActualWagesPercentageTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesPercentageTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		}
		if (WeekValue.contains("Current") ) {
			String ActualWagesPercentage = data.getText("CurrentWeekActualWagesPercentage");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					ActualWagesPercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String ActualWagesPercentageTotal = data.getText("CurrentWeekActualWagesPercentageTotal");
			DBActlTtl = sql.CLRexecuteQuery(getContext(),
					ActualWagesPercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtl);
			}
		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlVal, ActlVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBActlTtl, ActlValTtl);

		// Week to Date - Planned WagesPercentage
		Report_AddStep("testcase", " PLANNED WAGES PERCENTAGE - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages Prcnt - Week wise");
		
		String PlannedWagesPercentageWkToDt = data.getText("PlannedWagesPercentageWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedWagesPercentageWkToDtTotal = data.getText("PlannedWagesPercentageWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - Actual WagesPercentage
		Report_AddStep("testcase", " ACTUAL WAGES PERCENTAGE - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages Prcnt - Week wise");
		
		if (WeekValue.contains("Past") ) {
			String ActualWagesPercentageWkToDt = data.getText("PastWeekActualWagesPercentageWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualWagesPercentageWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualWagesPercentageWkToDtTotal = data.getText("PastWeekActualWagesPercentageWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualWagesPercentageWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}
		if (WeekValue.contains("Current")) {
			String CurrentActualWagesPercentageWkToDt = data.getText("CurrentWeekActualWagesPercentageWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualWagesPercentageWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String CurrentActualWagesPercentageWkToDtTotal = data.getText("CurrentWeekActualWagesPercentageWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualWagesPercentageWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlTtlWkToDt, ActlValTtlWkToDt);

		// Week to Date - Variance WagesPercentage
		Report_AddStep("testcase", " VARIANCE WAGES PERCENTAGE - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Wages Prcnt - Week wise");
		
		if (WeekValue.contains("Past") ) {
			String VarWagesPercentageWkToDt = data.getText("PastWeekVarWagesPercentageWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					VarWagesPercentageWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String VarWagesPercentageWkToDtTotal = data.getText("PastWeekVarWagesPercentageWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					VarWagesPercentageWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}
		if (WeekValue.contains("Current")) {
			String CurrentVarWagesPercentageWkToDt = data.getText("CurrentWeekVarWagesPercentageWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentVarWagesPercentageWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String CurrentVarWagesPercentageWkToDtTotal = data.getText("CurrentWeekVarWagesPercentageWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					CurrentVarWagesPercentageWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}

		VarWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		VarTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWkToDt, VarWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWkToDtTtl, VarTtlWkToDt);

		// Budget Wage %
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET WAGES % - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Budget Wages Prcnt");
			
			String BudgetWagePercentage = data.getText("BudgetWagePercentage");
			DBBdgtWg = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWg);

			String BudgetWagePercentageTotal = data.getText("BudgetWagePercentageTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWg = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWg, BdgtWg);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST WAGES % - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Forecast Wages Prcnt");
			
			String ForecastWagePercentage = data.getText("ForecastWagePercentage");
			DBBdgtWg = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWg);

			String ForecastWagePercentageTotal = data.getText("ForecastWagePercentageTotal");
			DBBdgtWgTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagePercentageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtWgTtl);

			BdgtWg = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			BdgtWgTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWg, BdgtWg);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBBdgtWgTtl, BdgtWgTtl);
		}

		// Estimated Forecast Wages %
		Report_AddStep("testcase", " ESTIMATED FORECAST WAGES PERCENTAGE - BUDGET COLUMN", "", "", "");
		extentTest = report.startTest("Estimated Forecast Wages Prcnt");
		
		if (WeekValue.contains("Past") ) {
			String EstimatedFcstWagePrcnt = data.getText("PastWeekEstimatedFcstWagePrcnt");
			DBEstFcstWgsPrcnt = sql.CLRexecuteQuery(getContext(),
					EstimatedFcstWagePrcnt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgsPrcnt);

			String EstimatedFcstWagePrcntTotal = data.getText("PastWeekEstimatedFcstWagePrcntTotal");
			DBEstFcstWgsPrcntTtl = sql.CLRexecuteQuery(getContext(),
					EstimatedFcstWagePrcntTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgsPrcntTtl);
		}
		if (WeekValue.contains("Current")) {
			String CurrentEstimatedFcstWagePrcnt = data.getText("CurrentWeekEstimatedFcstWagePrcnt");
			DBEstFcstWgsPrcnt = sql.CLRexecuteQuery(getContext(),
					CurrentEstimatedFcstWagePrcnt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgsPrcnt);

			String CurrentEstimatedFcstWagePrcntTotal = data.getText("CurrentWeekEstimatedFcstWagePrcntTotal");
			DBEstFcstWgsPrcntTtl = sql.CLRexecuteQuery(getContext(),
					CurrentEstimatedFcstWagePrcntTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgsPrcntTtl);
		}

		EstFcstWgsPrcnt = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.EstForecastSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		EstFcstWgsPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.EstForecastSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBEstFcstWgsPrcnt, EstFcstWgsPrcnt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBEstFcstWgsPrcntTtl, EstFcstWgsPrcntTtl);

		// Variance Wages %
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET VARIANCE WAGES PERCENTAGE - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Variance Budget Wages Prcnt");

			if (WeekValue.contains("Past") ) {
				String VarWagesPrecentage = data.getText("PastWeekVarWagesPrecentage");
				DBVarWagesPrcnt = sql.CLRexecuteQuery(getContext(),
						VarWagesPrecentage.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcnt);

				String VarWagesPrecentageTotal = data.getText("PastWeekVarWagesPrecentageTotal");
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(),
						VarWagesPrecentageTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcntTtl);
			}
			if (WeekValue.contains("Current")) {
				String CurrentVarWagesPrecentage = data.getText("CurrentWeekVarWagesPrecentage");
				DBVarWagesPrcnt = sql.CLRexecuteQuery(getContext(),
						CurrentVarWagesPrecentage.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcnt);

				String CurrentVarWagesPrecentageTotal = data.getText("CurrentWeekVarWagesPrecentageTotal");
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(),
						CurrentVarWagesPrecentageTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcntTtl);
			}

			VarWagesPrcnt = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			VarWagesPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWagesPrcnt, VarWagesPrcnt);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWagesPrcntTtl, VarWagesPrcntTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", "FORECAST VARIANCE WAGES PERCENTAGE - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Variance Forecast Wages Prcnt");
			
			if (WeekValue.contains("Past") ) {
				String ForecastVarWagesPrecentage = data.getText("PastWeekForecastVarWagesPrecentage");
				DBVarWagesPrcnt = sql.CLRexecuteQuery(getContext(),
						ForecastVarWagesPrecentage.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcnt);

				String ForecastVarWagesPrecentageTotal = data.getText("PastWeekForecastVarWagesPrecentageTotal");
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(),
						ForecastVarWagesPrecentageTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcntTtl);
			}
			if (WeekValue.contains("Current")) {
				String CurrentForecastVarWagesPrecentage = data.getText("CurrentWeekForecastVarWagesPrecentage");
				DBVarWagesPrcnt = sql.CLRexecuteQuery(getContext(),
						CurrentForecastVarWagesPrecentage.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcnt);

				String CurrentForecastVarWagesPrecentageTotal = data.getText("CurrentWeekForecastVarWagesPrecentageTotal");
				DBVarWagesPrcntTtl = sql.CLRexecuteQuery(getContext(),
						CurrentForecastVarWagesPrecentageTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesPrcntTtl);
			}

			VarWagesPrcnt = pageDailyTradingStatementGroupPO.WeekToDateValue("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			VarWagesPrcntTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("WagesPercentage",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWagesPrcnt, VarWagesPrcnt);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWagesPrcntTtl, VarWagesPrcntTtl);
		}
	}

	//////////// WAGES ///////////////
	@Test(description = "Daily Trading Statement - State, Wages Values", priority = 8)
	public void WagesColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log,
				status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Items View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		OptionToView = getContext().getStringProperty("OptionToView");

		// Day Wise - Planned Wages
		Report_AddStep("testcase", " PLANNED WAGES  - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages - Day Wise");
		
		String PlannedWages = data.getText("PlannedWages");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("Wages", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Day Wise - Actual Wages
		Report_AddStep("testcase", " ACTUAL WAGES - DAY WISE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages - Day Wise");
		if (WeekValue.contains("Past") ) {
		String ActualWages = data.getText("PastWeekActualWages");
		DBActlVal = sql.CLRexecuteQuery(getContext(),
				ActualWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlVal);

		String ActualWagesTotal = data.getText("PastWeekActualWagesTotal");
		DBActlTtl = sql.CLRexecuteQuery(getContext(),
				ActualWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBActlTtl);
		}
		if (WeekValue.contains("Current") ) {
			String ActualWages = data.getText("CurrentWeekActualWages");
			DBActlVal = sql.CLRexecuteQuery(getContext(),
					ActualWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlVal);

			String ActualWagesTotal = data.getText("CurrentWeekActualWagesTotal");
			DBActlTtl = sql.CLRexecuteQuery(getContext(),
					ActualWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtl);
			}
		ActlVal = pageDailyTradingStatementGroupPO.DayWiseValue("Wages", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		ActlValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayActlSales,
				objDailyTradingStatementStateObjects.TuesdayActlSales,
				objDailyTradingStatementStateObjects.WednesdayActlSales,
				objDailyTradingStatementStateObjects.ThursdayActlSales,
				objDailyTradingStatementStateObjects.FridayActlSales,
				objDailyTradingStatementStateObjects.SaturdayActlSales,
				objDailyTradingStatementStateObjects.SundayActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlVal, ActlVal);
		pageDailyTradingStatementStorePO.DTSCompareValuesTotal(extentTest, DBActlTtl, ActlValTtl);

		// LY Wages
		Report_AddStep("testcase", " LY WAGES - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("LY Wages - Day Wise");
		if (WeekValue.contains("Past") ) {
		String LYWages = data.getText("PastWeekLYWages");
		DBLYVal = sql.CLRexecuteQuery(getContext(),
				LYWages.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYVal);

		String LYWagesTotal = data.getText("PastWeekLYWagesTotal");
		DBLYValTtl = sql.CLRexecuteQuery(getContext(),
				LYWagesTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBLYValTtl);
		}
		if (WeekValue.contains("Current") ) {
			String LYWages = data.getText("CurrentWeekLYWages");
			DBLYVal = sql.CLRexecuteQuery(getContext(),
					LYWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYVal);

			String LYWagesTotal = data.getText("CurrentWeekLYWagesTotal");
			DBLYValTtl = sql.CLRexecuteQuery(getContext(),
					LYWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtl);
			}
		LYVal = pageDailyTradingStatementGroupPO.DayWiseValue("Wages", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayLYSales, objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		LYValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayLYSales,
				objDailyTradingStatementStateObjects.TuesdayLYSales,
				objDailyTradingStatementStateObjects.WednesdayLYSales,
				objDailyTradingStatementStateObjects.ThursdayLYSales,
				objDailyTradingStatementStateObjects.FridayLYSales,
				objDailyTradingStatementStateObjects.SaturdayLYSales,
				objDailyTradingStatementStateObjects.SundayLYSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYVal, LYVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBLYValTtl, LYValTtl);

		// Week to Date - Planned Wages
		Report_AddStep("testcase", " PLANNED WAGES  - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned Wages - Week Wise");
		
		String PlannedWagesWkToDt = data.getText("PlannedWagesWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedWagesWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedWagesWkToDtTotal = data.getText("PlannedWagesWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedWagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("LYWk", getContext().getStringProperty("LYWk"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - Actual Wages
		Report_AddStep("testcase", " ACTUAL WAGES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual Wages - Week Wise");
		
		if (WeekValue.contains("Past") ) {
			String ActualWagesWkToDt = data.getText("PastWeekActualWagesWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualWagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualWagesWkToDtTotal = data.getText("PastWeekActualWagesWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualWagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}
		if (WeekValue.contains("Current")) {
			String CurrentActualWagesWkToDt = data.getText("CurrentWeekActualWagesWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualWagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String CurrentActualWagesWkToDtTotal = data.getText("CurrentWeekActualWagesWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualWagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBActlTtlWkToDt, ActlValTtlWkToDt);

		// Week to Date - LY Wages
		Report_AddStep("testcase", " LY WAGES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("LY Wages - Week Wise");
		
		if (WeekValue.contains("Past") ) {
			String LYWagesWkToDt = data.getText("PastWeekLYWagesWkToDt");
			DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
					LYWagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValWkToDt);

			String LYWagesWkToDtTotal = data.getText("PastWeekLYWagesWkToDtTotal");
			DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					LYWagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtlWkToDt);
		}
		if (WeekValue.contains("Current")) {
			String CurrentLYWagesWkToDt = data.getText("CurrentWeekLYWagesWkToDt");
			DBLYValWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentLYWagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValWkToDt);

			String CurrentLYWagesWkToDtTotal = data.getText("CurrentWeekLYWagesWkToDtTotal");
			DBLYValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentLYWagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBLYValTtlWkToDt);
		}

		LYValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		LYValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateLYSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValWkToDt, LYValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBLYValTtlWkToDt, LYValTtlWkToDt);

		// Week to Date - Variance Wages
		Report_AddStep("testcase", " VARIANCE WAGES - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance Wages - Week Wise");
		
		if (WeekValue.contains("Past") ) {
			String VarWagesWkToDt = data.getText("PastWeekVarWagesWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					VarWagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String VarWagesWkToDtTotal = data.getText("PastWeekVarWagesWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					VarWagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}
		if (WeekValue.contains("Current")) {
			String CurrentVarWagesWkToDt = data.getText("CurrentWeekVarWagesWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentVarWagesWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String CurrentVarWagesWkToDtTotal = data.getText("CurrentWeekVarWagesWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					CurrentVarWagesWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}

		VarWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		VarTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDt, VarWkToDt);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWkToDtTtl, VarTtlWkToDt);

		// Budget Wages
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET WAGES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Budget Wages");

			String BudgetWages = data.getText("BudgetWages");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBBdgtSlsTtl = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSlsTtl);

			BdgtSls = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			BdgtSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, BdgtSls);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSlsTtl, BdgtSlsTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST WAGES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Forecast Wages");
			
			String ForecastWages = data.getText("ForecastWages");
			DBBdgtSls = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSls);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBBdgtSlsTtl = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBBdgtSlsTtl);

			BdgtSls = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			BdgtSlsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.BudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSls, BdgtSls);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBBdgtSlsTtl, BdgtSlsTtl);
		}

		// Estimated Forecast Wages
		Report_AddStep("testcase", " ESTIMATED FORECAST WAGES- BUDGET COLUMN", "", "", "");
		extentTest = report.startTest("Estimated Forecast Wages");
		
		if (WeekValue.contains("Past") ) {
			String EstimatedFcstWage = data.getText("PastWeekEstimatedFcstWage");
			DBEstFcstWgs = sql.CLRexecuteQuery(getContext(),
					EstimatedFcstWage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgs);

			String EstimatedFcstWageTotal = data.getText("PastWeekEstimatedFcstWageTotal");
			DBEstFcstWgsTtl = sql.CLRexecuteQuery(getContext(),
					EstimatedFcstWageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgsTtl);
		}
		if (WeekValue.contains("Current")) {
			String CurrentEstimatedFcstWage = data.getText("CurrentWeekEstimatedFcstWage");
			DBEstFcstWgs = sql.CLRexecuteQuery(getContext(),
					CurrentEstimatedFcstWage.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgs);

			String CurrentEstimatedFcstWageTotal = data.getText("CurrentWeekEstimatedFcstWageTotal");
			DBEstFcstWgsTtl = sql.CLRexecuteQuery(getContext(),
					CurrentEstimatedFcstWageTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBEstFcstWgsTtl);
		}

		EstFcstWgs = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.EstForecastSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		EstFcstWgsTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.EstForecastSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValues(extentTest, DBEstFcstWgs, EstFcstWgs);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBEstFcstWgsTtl, EstFcstWgsTtl);

		// Variance Wages
		if (OptionToView.contains("Budget")) {
			Report_AddStep("testcase", " BUDGET VARIANCE WAGES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Variance Budget Wages");

			if (WeekValue.contains("Past") ) {
				String VarWages = data.getText("PastWeekVarWages");
				DBVarWages = sql.CLRexecuteQuery(getContext(),
						VarWages.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWages);

				String VarWagesTotal = data.getText("PastWeekVarWagesTotal");
				DBVarWagesTtl = sql.CLRexecuteQuery(getContext(),
						VarWagesTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesTtl);
			}
			if (WeekValue.contains("Current")) {
				String CurrentVarWages = data.getText("CurrentWeekVarWages");
				DBVarWages = sql.CLRexecuteQuery(getContext(),
						CurrentVarWages.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWages);

				String CurrentVarWagesTotal = data.getText("CurrentWeekVarWagesTotal");
				DBVarWagesTtl = sql.CLRexecuteQuery(getContext(),
						CurrentVarWagesTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesTtl);
			}

			VarWages = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			VarWagesTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWages, VarWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWagesTtl, VarWagesTtl);
		}
		if (OptionToView.contains("Forecast")) {
			Report_AddStep("testcase", " FORECAST VARIANCE WAGES - BUDGET COLUMN", "", "", "");
			extentTest = report.startTest("Variance Forecast Wages");
			
			if (WeekValue.contains("Past") ) {
				String ForecastVarWages = data.getText("PastWeekForecastVarWages");
				DBVarWages = sql.CLRexecuteQuery(getContext(),
						ForecastVarWages.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWages);

				String ForecastVarWagesTotal = data.getText("PastWeekForecastVarWagesTotal");
				DBVarWagesTtl = sql.CLRexecuteQuery(getContext(),
						ForecastVarWagesTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesTtl);
			}
			if (WeekValue.contains("Current")) {
				String CurrentForecastVarWages = data.getText("CurrentWeekForecastVarWages");
				DBVarWages = sql.CLRexecuteQuery(getContext(),
						CurrentForecastVarWages.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWages);

				String CurrentForecastVarWagesTotal = data.getText("CurrentWeekForecastVarWagesTotal");
				DBVarWagesTtl = sql.CLRexecuteQuery(getContext(),
						CurrentForecastVarWagesTotal.replace("store", getContext().getStringProperty("store"))
								.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
								.replace("Region", getContext().getStringProperty("Region"))
								.replace("Zone", getContext().getStringProperty("Zone"))
								.replace("Area", getContext().getStringProperty("Area"))
								.replace("LYWk", getContext().getStringProperty("LYWk"))
								.replace("Division", getContext().getStringProperty("Division"))
								.replace("Division", getContext().getStringProperty("Division")));
				System.out.println("DB Value: " + DBVarWagesTtl);
			}

			VarWages = pageDailyTradingStatementGroupPO.WeekToDateValue("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
			VarWagesTtl = pageDailyTradingStatementGroupPO.WeekToDateTotal("Wages",
					objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.VarBudgetSales,
					objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWages, VarWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBVarWagesTtl, VarWagesTtl);
		}
	}

	//////////// CPH ///////////////
	@Test(description = "Daily Trading Statement - State, CPH Values", priority = 9)
	public void CPHColumnValidation() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementStorePO = new DailyTradingStatementStorePage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		TestStatus status = getTestStatus();
		System.out.println("Entering into TestCase : DTS Store - Sales View");
		WeekValue = getContext().getStringProperty("WeekToTest");
		OptionToView = getContext().getStringProperty("OptionToView");

		// Planned CPH
		Report_AddStep("testcase", " PLANNED CPH - WEEK WISE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH - Day Wise");
		
		String PlannedCPH = data.getText("PlannedCPH");
		DBPlndVal = sql.CLRexecuteQuery(getContext(),
				PlannedCPH.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndVal);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBPlndValTtl = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtl);

		PlndVal = pageDailyTradingStatementGroupPO.DayWiseValue("CPH", objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);
		PlndValTtl = pageDailyTradingStatementGroupPO.DayWiseTotal("CPH",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.MondayPlndSales,
				objDailyTradingStatementStateObjects.TuesdayPlndSales,
				objDailyTradingStatementStateObjects.WednesdayPlndSales,
				objDailyTradingStatementStateObjects.ThursdayPlndSales,
				objDailyTradingStatementStateObjects.FridayPlndSales,
				objDailyTradingStatementStateObjects.SaturdayPlndSales,
				objDailyTradingStatementStateObjects.SundayPlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.Department);

		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndVal, PlndVal);
		pageDailyTradingStatementStorePO.CompareValueDecimalTotal(extentTest, DBPlndValTtl, PlndValTtl);

		// Week to Date - Planned CPH
		Report_AddStep("testcase", " PLANNED CPH - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Planned CPH - Week Wise");
		
		String PlannedCPHWkToDt = data.getText("PlannedCPHWkToDt");
		DBPlndValWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedCPHWkToDt.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValWkToDt);

		String PlannedCPHWkToDtTotal = data.getText("PlannedCPHWkToDtTotal");
		DBPlndValTtlWkToDt = sql.CLRexecuteQuery(getContext(),
				PlannedCPHWkToDtTotal.replace("store", getContext().getStringProperty("store"))
						.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
						.replace("Region", getContext().getStringProperty("Region"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBPlndValTtlWkToDt);

		PlndValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("CPH",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		PlndValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("CPH",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDatePlndSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValWkToDt, PlndValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBPlndValTtlWkToDt, PlndValTtlWkToDt);

		// Week to Date - Actual CPH
		Report_AddStep("testcase", " ACTUAL CPH - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Actual CPH - Week Wise");
		
		if (WeekValue.contains("Past") ) {
			String ActualCPHWkToDt = data.getText("PastWeekActualCPHWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualCPHWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String ActualCPHWkToDtTotal = data.getText("PastWeekActualCPHWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					ActualCPHWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}
		if (WeekValue.contains("Current")) {
			String CurrentActualCPHWkToDt = data.getText("CurrentWeekActualCPHWkToDt");
			DBActlValWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualCPHWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlValWkToDt);

			String CurrentActualCPHWkToDtTotal = data.getText("CurrentWeekActualCPHWkToDtTotal");
			DBActlTtlWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentActualCPHWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBActlTtlWkToDt);
		}

		ActlValWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("CPH",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		ActlValTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("CPH",
				objDailyTradingStatementStateObjects.DataFrame,
				objDailyTradingStatementStateObjects.WeekToDateActlSales, objDailyTradingStatementStateObjects.Table,
				objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlValWkToDt, ActlValWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBActlTtlWkToDt, ActlValTtlWkToDt);

		// Week to Date - Variance CPH
		Report_AddStep("testcase", " VARIANCE CPH - WEEK TO DATE DATA", "", "", "");
		extentTest = report.startTest("Variance CPH - Week Wise");
		
		if (WeekValue.contains("Past") ) {
			String VarCPHWkToDt = data.getText("PastWeekVarCPHWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					VarCPHWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String VarCPHWkToDtTotal = data.getText("PastWeekVarCPHWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					VarCPHWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}
		if (WeekValue.contains("Current")) {
			String CurrentVarCPHWkToDt = data.getText("CurrentWeekVarCPHWkToDt");
			DBVarWkToDt = sql.CLRexecuteQuery(getContext(),
					CurrentVarCPHWkToDt.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDt);

			String CurrentVarCPHWkToDtTotal = data.getText("CurrentWeekVarCPHWkToDtTotal");
			DBVarWkToDtTtl = sql.CLRexecuteQuery(getContext(),
					CurrentVarCPHWkToDtTotal.replace("store", getContext().getStringProperty("store"))
							.replace("Week", getContext().getStringProperty("SelectFiscalWeek"))
							.replace("Region", getContext().getStringProperty("Region"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("LYWk", getContext().getStringProperty("LYWk"))
							.replace("Division", getContext().getStringProperty("Division"))
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBVarWkToDtTtl);
		}

		VarWkToDt = pageDailyTradingStatementGroupPO.WeekToDateValue("CPH",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Department, objDailyTradingStatementStateObjects.Table);
		VarTtlWkToDt = pageDailyTradingStatementGroupPO.WeekToDateTotal("CPH",
				objDailyTradingStatementStateObjects.DataFrame, objDailyTradingStatementStateObjects.WeekToDateVarSales,
				objDailyTradingStatementStateObjects.Table, objDailyTradingStatementStateObjects.StateTotal);

		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWkToDt, VarWkToDt);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBVarWkToDtTtl, VarTtlWkToDt);
	}

	@Test(priority = 10)
	public void LogoutCLRApplication() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects);
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
		pageDailyTradingStatementStorePO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStorePage.class);
		pageDailyTradingStatementGroupPO = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementGroupPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objDailyTradingStatementObjects = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementObjects.class);
		objDailyTradingStatementStateObjects = PageFactory.initElements(this.getDriver(),
				DailyTradingStatementStateObjects.class);
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\DailyTradingStatementState.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}