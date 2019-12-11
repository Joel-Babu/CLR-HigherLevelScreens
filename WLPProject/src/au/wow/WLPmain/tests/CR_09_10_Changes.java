package au.wow.WLPmain.tests;

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
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyTradingStatementStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
import au.wow.WLPmain.pages.DailyTradingStatementGroupPage;
import au.wow.WLPmain.pages.MonthlyPlanningGroupPage;
import au.wow.WLPmain.pages.MonthlyPlanningStorePage;
import au.wow.WLPmain.pages.WeeklyPlanningStorePage;
import au.wow.WLPmain.pages.WoWLoginPage;
import au.wow.wlp.utils.BasePage;
import au.wow.wlp.utils.CommonRepository;
import au.wow.wlp.utils.CustomExtentReports;
import au.wow.wlp.utils.ExtentReportsScreenshotCode;
import au.wow.wlp.utils.SQLWrapper;
import au.wow.wlp.utils.TestBase;
import au.wow.wlp.utils.TestStatus;
import au.wow.wlp.utils.XMLDataReader;

public class CR_09_10_Changes extends TestBase {

	protected Logger log = LogManager.getLogger(CR_09_10_Changes.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	WeeklyTradingStatementStoreObjects objWeeklyTradingStatementStoreObjects;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> DBValues, DBTotal, UIValues, UITotal;
	String OptionToView;

	public CR_09_10_Changes() {
		super.log = log;
	}

	/*
	 * @Test(description =
	 * "Verify Zone Option in Page to View DropDown for Store Manager") public void
	 * VerifyPageOptionForStoreManager() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek")); List<String>
	 * Pages =
	 * pageMonthlyPlanningPO.VerifyDropdownList(objMonthlyPlanningStoreObjects,
	 * extentTest); pageMonthlyPlanningPO.ComparePages(extentTest,
	 * Pages,"StoreManager");
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for Group Manager") public void
	 * VerifyPageOptionForGroupManager() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek")); List<String>
	 * Pages =
	 * pageMonthlyPlanningPO.VerifyDropdownList(objMonthlyPlanningStoreObjects,
	 * extentTest); pageMonthlyPlanningPO.ComparePages(extentTest,
	 * Pages,"GroupManager");
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for Zone Manager") public void
	 * VerifyPageOptionForZoneManager() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek")); List<String>
	 * Pages =
	 * pageMonthlyPlanningPO.VerifyDropdownList(objMonthlyPlanningStoreObjects,
	 * extentTest); pageMonthlyPlanningPO.ComparePages(extentTest,
	 * Pages,"ZoneManager");
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for State Manager") public void
	 * VerifyPageOptionForStateManager() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek")); List<String>
	 * Pages =
	 * pageMonthlyPlanningPO.VerifyDropdownList(objMonthlyPlanningStoreObjects,
	 * extentTest); pageMonthlyPlanningPO.ComparePages(extentTest,
	 * Pages,"StateManager");
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description = "Verify Options in Page to View DropDown for BU Manager")
	 * public void VerifyPageOptionForBUManager() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek")); List<String>
	 * Pages =
	 * pageMonthlyPlanningPO.VerifyDropdownList(objMonthlyPlanningStoreObjects,
	 * extentTest); pageMonthlyPlanningPO.ComparePages(extentTest,
	 * Pages,"BUManager");
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for WOW Manager") public void
	 * VerifyPageOptionForWOWManager() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek")); List<String>
	 * Pages =
	 * pageMonthlyPlanningPO.VerifyDropdownList(objMonthlyPlanningStoreObjects,
	 * extentTest); pageMonthlyPlanningPO.ComparePages(extentTest,
	 * Pages,"WOWManager");
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for Store Manager - Negative Scenario"
	 * ) public void VerifyPageOptionForStrMngrManagerNegativeScenario() throws
	 * Exception { loginPage = new WoWLoginPage().createPage(getDriver(),
	 * getContext(), log, status, data,report,extentTest, WoWLoginPage.class);
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek")); List<String>
	 * Pages =
	 * pageMonthlyPlanningPO.VerifyDropdownList(objMonthlyPlanningStoreObjects,
	 * extentTest); pageMonthlyPlanningPO.ComparePages(extentTest,
	 * Pages,"NegativeScenario");
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description = "Verify Zone Hyper link in WTS Screen") public void
	 * VerifyZoneHyperLinkForStoreManagerinWTS() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageDailyTradingStatementGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WTSStore"),extentTest);
	 * pageDailyTradingStatementGroupPO.VerifyHyperLink(
	 * objWeeklyTradingStatementStoreObjects, extentTest); }
	 * 
	 * @Test(description = "Verify Zone Hyper link in MTS Screen") public void
	 * VerifyZoneHyperLinkForStoreManagerinMTS() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageDailyTradingStatementGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MTSStore"),extentTest);
	 * pageDailyTradingStatementGroupPO.VerifyHyperLink(
	 * objWeeklyTradingStatementStoreObjects, extentTest); }
	 * 
	 */
	// PLANNED SPEND TO BDGT/FCST RATE
	@Test(description = "Monthly Planning - Group, Planned BDGT/FCST Rate - MP Store")
	public void PlannedSpendToBDGTandFSCTRate_MPStore() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		pageMonthlyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects, extentTest);
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("MonthlyPlanningStore"), extentTest);
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget")) {
			pageMonthlyPlanningPO.PlannedSalestoBDGTRate("RowWiseData", objMonthlyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.Week, objMonthlyPlanningStoreObjects.BDGTRate,
					objMonthlyPlanningStoreObjects.BudgetWagesPercentage, objMonthlyPlanningStoreObjects.PlannedSales,
					objMonthlyPlanningStoreObjects.BudgetSales, objMonthlyPlanningStoreObjects.PlannedWages);
			pageMonthlyPlanningPO.PlannedSalestoBDGTRate("FinalTotal", objMonthlyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.TotalRowName, objMonthlyPlanningStoreObjects.BDGTRateTtl,
					objMonthlyPlanningStoreObjects.BudgetWagesPercentageTtl,
					objMonthlyPlanningStoreObjects.PlannedSalesTtl, objMonthlyPlanningStoreObjects.BudgetSalesTtl,
					objMonthlyPlanningStoreObjects.PlannedWagesTtl);
		}
		if (OptionToView.contains("Forecast")) {
			pageMonthlyPlanningPO.PlannedSalestoBDGTRate("RowWiseData", objMonthlyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.Week, objMonthlyPlanningStoreObjects.FCSTRate,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentage, objMonthlyPlanningStoreObjects.PlannedSales,
					objMonthlyPlanningStoreObjects.ForecastSales, objMonthlyPlanningStoreObjects.PlannedWages);
			pageMonthlyPlanningPO.PlannedSalestoBDGTRate("FinalTotal", objMonthlyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.TotalRowName, objMonthlyPlanningStoreObjects.FCSTRateTtl,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentageTtl,
					objMonthlyPlanningStoreObjects.PlannedSalesTtl, objMonthlyPlanningStoreObjects.ForecastSalesTtl,
					objMonthlyPlanningStoreObjects.PlannedWagesTtl);
		}
		pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects);
	}

	/*
	 * @Test(description =
	 * "Monthly Planning - Group, Planned BDGT/FCST Rate - MP Group") public void
	 * PlannedSpendToBDGTandFSCTRate_MPGroup() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MonthlyPlanningGroup"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales");
	 * pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(
	 * objMonthlyPlanningGroupObjects,extentTest,getContext().getStringProperty(
	 * "GroupToView")); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningGroupObjects.BDGTRate,
	 * objMonthlyPlanningGroupObjects.BudgetWagesPercentage,
	 * objMonthlyPlanningGroupObjects.PlannedSales,objMonthlyPlanningGroupObjects.
	 * BudgetSales,objMonthlyPlanningGroupObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.FCSTRate,
	 * objMonthlyPlanningStoreObjects.ForecastWagesPercentage,
	 * objMonthlyPlanningStoreObjects.FCSTPlannedSales,
	 * objMonthlyPlanningStoreObjects.ForecastSales,objMonthlyPlanningGroupObjects.
	 * PlannedWages); pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Monthly Planning - Group, Planned BDGT/FCST Rate - MP Zone") public void
	 * PlannedSpendToBDGTandFSCTRate_MPZone() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MonthlyPlanningZone"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales");
	 * pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(
	 * objMonthlyPlanningGroupObjects,extentTest,getContext().getStringProperty(
	 * "ZoneToView")); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningGroupObjects.BDGTRate,
	 * objMonthlyPlanningGroupObjects.BudgetWagesPercentage,
	 * objMonthlyPlanningGroupObjects.PlannedSales,objMonthlyPlanningGroupObjects.
	 * BudgetSales,objMonthlyPlanningGroupObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.FCSTRate,
	 * objMonthlyPlanningStoreObjects.ForecastWagesPercentage,
	 * objMonthlyPlanningStoreObjects.FCSTPlannedSales,
	 * objMonthlyPlanningStoreObjects.ForecastSales,objMonthlyPlanningGroupObjects.
	 * PlannedWages); pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Monthly Planning - Group, Planned BDGT/FCST Rate - MP State") public void
	 * PlannedSpendToBDGTandFSCTRate_MPState() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MonthlyPlanningState"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales");
	 * pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(
	 * objMonthlyPlanningGroupObjects,extentTest,getContext().getStringProperty(
	 * "RegionToView")); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningGroupObjects.BDGTRate,
	 * objMonthlyPlanningGroupObjects.BudgetWagesPercentage,
	 * objMonthlyPlanningGroupObjects.PlannedSales,objMonthlyPlanningGroupObjects.
	 * BudgetSales,objMonthlyPlanningGroupObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.FCSTRate,
	 * objMonthlyPlanningStoreObjects.ForecastWagesPercentage,
	 * objMonthlyPlanningStoreObjects.FCSTPlannedSales,
	 * objMonthlyPlanningStoreObjects.ForecastSales,objMonthlyPlanningGroupObjects.
	 * PlannedWages); pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Monthly Planning - Group, Planned BDGT/FCST Rate - MP Business Unit") public
	 * void PlannedSpendToBDGTandFSCTRate_MPBusinessUnit() throws Exception {
	 * loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log,
	 * status, data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO =
	 * new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MonthlyPlanningBU"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningGroupObjects.BDGTRate,
	 * objMonthlyPlanningGroupObjects.BudgetWagesPercentage,
	 * objMonthlyPlanningGroupObjects.PlannedSales,objMonthlyPlanningGroupObjects.
	 * BudgetSales,objMonthlyPlanningGroupObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.FCSTRate,
	 * objMonthlyPlanningStoreObjects.ForecastWagesPercentage,
	 * objMonthlyPlanningStoreObjects.FCSTPlannedSales,
	 * objMonthlyPlanningStoreObjects.ForecastSales,objMonthlyPlanningGroupObjects.
	 * PlannedWages); pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Monthly Planning - Group, Planned BDGT/FCST Rate - MP WOW Group") public
	 * void PlannedSpendToBDGTandFSCTRate_MPWOWGroup() throws Exception { loginPage
	 * = new WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MonthlyPlanningWOWGroup"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningGroupObjects.BDGTRate,
	 * objMonthlyPlanningGroupObjects.BudgetWagesPercentage,
	 * objMonthlyPlanningGroupObjects.PlannedSales,objMonthlyPlanningGroupObjects.
	 * BudgetSales,objMonthlyPlanningGroupObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.FCSTRate,
	 * objMonthlyPlanningStoreObjects.ForecastWagesPercentage,
	 * objMonthlyPlanningStoreObjects.FCSTPlannedSales,
	 * objMonthlyPlanningStoreObjects.ForecastSales,objMonthlyPlanningGroupObjects.
	 * PlannedWages); pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * //Weekly Planning
	 * 
	 * @Test(description = "Weekly Planning - Store, Planned BDGT Rate - MP Store")
	 * public void AAPlannedSpendToBDGTandFSCTRate_WPStore() throws Exception {
	 * loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log,
	 * status, data,report,extentTest, WoWLoginPage.class); pageWeeklyPlanningPO =
	 * new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WeeklyPlanningStore"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Item Price"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.BDGTRate,
	 * objWeeklyPlanningStoreObjects.BudgetWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * BudgetSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FCSTRate,
	 * objWeeklyPlanningStoreObjects.ForecastWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * ForecastSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description = "Weekly Planning - Store, Planned BDGT Rate - WP Group")
	 * public void PlannedSpendToBDGTandFSCTRate_WPGroup() throws Exception {
	 * loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log,
	 * status, data,report,extentTest, WoWLoginPage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WeeklyPlanningGroup"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales");
	 * pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(
	 * objMonthlyPlanningGroupObjects,extentTest,getContext().getStringProperty(
	 * "GroupToView")); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.BDGTRate,
	 * objWeeklyPlanningStoreObjects.BudgetWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * BudgetSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FCSTRate,
	 * objWeeklyPlanningStoreObjects.ForecastWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * ForecastSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description = "Weekly Planning - Store, Planned BDGT Rate - WP Zone")
	 * public void PlannedSpendToBDGTandFSCTRate_WPZone() throws Exception {
	 * loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log,
	 * status, data,report,extentTest, WoWLoginPage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WeeklyPlanningZone"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales");
	 * pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(
	 * objMonthlyPlanningGroupObjects,extentTest,getContext().getStringProperty(
	 * "ZoneToView")); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.BDGTRate,
	 * objWeeklyPlanningStoreObjects.BudgetWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * BudgetSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FCSTRate,
	 * objWeeklyPlanningStoreObjects.ForecastWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * ForecastSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description = "Weekly Planning - Store, Planned BDGT Rate - WP State")
	 * public void PlannedSpendToBDGTandFSCTRate_WPState() throws Exception {
	 * loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log,
	 * status, data,report,extentTest, WoWLoginPage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WeeklyPlanningState"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales");
	 * pageMonthlyPlanningGroupPO.SelectGroupDropdownToView(
	 * objMonthlyPlanningGroupObjects,extentTest,getContext().getStringProperty(
	 * "RegionToView")); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.BDGTRate,
	 * objWeeklyPlanningStoreObjects.BudgetWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * BudgetSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FCSTRate,
	 * objWeeklyPlanningStoreObjects.ForecastWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * ForecastSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned BDGT Rate - WP Business Unit") public void
	 * PlannedSpendToBDGTandFSCTRate_WPBusinessUnit() throws Exception { loginPage =
	 * new WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WeeklyPlanningBU"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.BDGTRate,
	 * objWeeklyPlanningStoreObjects.BudgetWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * BudgetSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FCSTRate,
	 * objWeeklyPlanningStoreObjects.ForecastWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * ForecastSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 * 
	 * @Test(description =
	 * "Weekly Planning - Store, Planned BDGT Rate - WP WOWGroup") public void
	 * PlannedSpendToBDGTandFSCTRate_WPWOWGroup() throws Exception { loginPage = new
	 * WoWLoginPage().createPage(getDriver(), getContext(), log, status,
	 * data,report,extentTest, WoWLoginPage.class); pageMonthlyPlanningGroupPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageWeeklyPlanningPO =
	 * loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"))
	 * ; TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.EnterStrNbr(objWeeklyPlanningStoreObjects,extentTest);
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WeeklyPlanningWOWGroup"),extentTest);
	 * pageWeeklyPlanningPO.SelectHistoryDropdownToView(
	 * objWeeklyPlanningStoreObjects,extentTest,"Sales"); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.BDGTRate,
	 * objWeeklyPlanningStoreObjects.BudgetWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * BudgetSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * if(OptionToView.contains("Forecast"))
	 * pageWeeklyPlanningPO.PlannedSalestoBDGTRate(objWeeklyPlanningStoreObjects,
	 * extentTest,objWeeklyPlanningStoreObjects.FCSTRate,
	 * objWeeklyPlanningStoreObjects.ForecastWagesPercent,
	 * objWeeklyPlanningStoreObjects.PlannedSales,objWeeklyPlanningStoreObjects.
	 * ForecastSales,objWeeklyPlanningStoreObjects.PlannedWages);
	 * pageWeeklyPlanningPO.LogOFF(objWeeklyPlanningStoreObjects); }
	 */

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
		objMonthlyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningStoreObjects.class);
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
		cName = this.getClass().getSimpleName();
		reportPath = new File(getContext().getStringProperty("reportPath"));
		report = new ExtentReports(
				getContext().getStringProperty("reportPath") + "\\" + this.getClass().getSimpleName() + ".html");
		classList.add(this.getClass().getSimpleName());
		initializeTestcontext();
		System.out.println("test1 - Beforeclass");
		data = new XMLDataReader(log);
		customreport = new CustomExtentReports();
		createDriver(getContext().getStringProperty("WLPUrl"), getContext().getStringProperty("browser"), cName, mName,
				extentTest, driver1);
		log.info("Loading data for the class: ");
		data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningStore.xml");
		customreport.createExtentReport();
	}

}