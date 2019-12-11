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
import java.util.Arrays;
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
import au.wow.WLPmain.objects.DailyTradingStatementGroupObjects;
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

public class CR_12_Sorting extends TestBase {
	protected Logger log = LogManager.getLogger(CR_12_Sorting.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	DailyTradingStatementGroupPage pageDailyTradingStatementGroupPO;
	DailyTradingStatementGroupObjects objDailyTradingStatementGroupObjects;
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

	public CR_12_Sorting() {
		super.log = log;
	}

	@Test(priority = 0)
	public void LogintoCLRApplication() throws Exception {
		loginPage = new WoWLoginPage().createPage(getDriver(), getContext(), log, status, data, report, extentTest,
				WoWLoginPage.class);
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	@Test(description = "Verify Options in Page to Region DropDown for Group Manager", priority = 1)
	public void VerifySortingForGroupManager() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningGroup"), extentTest);
		String[] Regions = pageWeeklyPlanningPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects, extentTest,
				"Group");
		pageWeeklyPlanningPO.OrderVerification(Regions, "Group", extentTest);
	}

	@Test(description = "Verify Options in Page to View DropDown for Zone Manager", priority = 2)
	public void VerifySortingForForZoneManager() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningZone"), extentTest);
		String[] Regions = pageWeeklyPlanningPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects, extentTest,
				"Zone");
		pageWeeklyPlanningPO.OrderVerification(Regions, "Zone", extentTest);
	}

	@Test(description = "Verify Options in Page to View DropDown for State Manager", priority = 3)
	public void VerifySortingForStateManager() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("WeeklyPlanningState"), extentTest);
		String[] Regions = pageWeeklyPlanningPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects, extentTest,
				"State");
		pageWeeklyPlanningPO.OrderVerification(Regions, "State", extentTest);
	}

	@Test(description = "Verify Options in Page to Region DropDown for Group Manager", priority = 4)
	public void VerifySortingForGroupManagerMP() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("MonthlyPlanningGroup"), extentTest);
		String[] Regions = pageWeeklyPlanningPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects, extentTest,
				"Group");
		pageWeeklyPlanningPO.OrderVerification(Regions, "Group", extentTest);
	}

	@Test(description = "Verify Options in Page to View DropDown for Zone Manager", priority = 5)
	public void VerifySortingForForZoneManagerMP() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("MonthlyPlanningZone"), extentTest);
		String[] Regions = pageWeeklyPlanningPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects, extentTest,
				"Zone");
		pageWeeklyPlanningPO.OrderVerification(Regions, "Zone", extentTest);
	}

	@Test(description = "Verify Options in Page to View DropDown for State Manager", priority = 6)
	public void VerifySortingForStateManagerMP() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,
				getContext().getStringProperty("MonthlyPlanningState"), extentTest);
		String[] Regions = pageWeeklyPlanningPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects, extentTest,
				"State");
		pageWeeklyPlanningPO.OrderVerification(Regions, "State", extentTest);
	}

	@Test(description = "Verify Options in Page to Region DropDown for Group Manager", priority = 7)
	public void VerifySortingForGroupManagerDTS() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("DTSGroup"),
				extentTest);
		String[] Regions = pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects,
				extentTest, "Group");
		pageWeeklyPlanningPO.OrderVerification(Regions, "Group", extentTest);
	}

	@Test(description = "Verify Options in Page to View DropDown for Zone Manager", priority = 8)
	public void VerifySortingForForZoneManagerDTS() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("DTSZone"),
				extentTest);
		String[] Regions = pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects,
				extentTest, "Zone");
		pageWeeklyPlanningPO.OrderVerification(Regions, "Zone", extentTest);
	}

	@Test(description = "Verify Options in Page to View DropDown for State Manager", priority = 9)
	public void VerifySortingForStateManagerDTS() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageDailyTradingStatementGroupPO = new DailyTradingStatementGroupPage().createPage(getDriver(), getContext(),
				log, status, data, getReport(), getReportLogger(), DailyTradingStatementGroupPage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		TestStatus status = getTestStatus();
		System.out.println("Welcome " + getContext().getStringProperty("username"));
		System.out.println("Entering into TestCase");
		pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("username"));
		pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects, getContext().getStringProperty("DTSState"),
				extentTest);
		String[] Regions = pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(objWeeklyPlanningStoreObjects,
				extentTest, "State");
		pageWeeklyPlanningPO.OrderVerification(Regions, "State", extentTest);
	}

	/*
	 * @Test(description =
	 * "Verify Options in Page to Region DropDown for Group Manager",priority=10)
	 * public void VerifySortingForGroupManagerWTS() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageDailyTradingStatementGroupPO = new
	 * DailyTradingStatementGroupPage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(),
	 * DailyTradingStatementGroupPage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WTSGroup"),extentTest); String[] Regions =
	 * pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(
	 * objWeeklyPlanningStoreObjects,extentTest,"Group");
	 * pageWeeklyPlanningPO.OrderVerification(Regions,"Group",extentTest); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for Zone Manager",priority=11)
	 * public void VerifySortingForForZoneManagerWTS() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageDailyTradingStatementGroupPO = new
	 * DailyTradingStatementGroupPage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(),
	 * DailyTradingStatementGroupPage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WTSZone"),extentTest); String[] Regions =
	 * pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(
	 * objWeeklyPlanningStoreObjects,extentTest,"Zone");
	 * pageWeeklyPlanningPO.OrderVerification(Regions,"Zone",extentTest); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for State Manager",priority=12)
	 * public void VerifySortingForStateManagerWTS() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageDailyTradingStatementGroupPO = new
	 * DailyTradingStatementGroupPage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(),
	 * DailyTradingStatementGroupPage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("WTSState"),extentTest); String[] Regions =
	 * pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(
	 * objWeeklyPlanningStoreObjects,extentTest,"State");
	 * pageWeeklyPlanningPO.OrderVerification(Regions,"State",extentTest); }
	 * 
	 * 
	 * 
	 * @Test(description =
	 * "Verify Options in Page to Region DropDown for Group Manager",priority=13)
	 * public void VerifySortingForGroupManagerMTS() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageDailyTradingStatementGroupPO = new
	 * DailyTradingStatementGroupPage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(),
	 * DailyTradingStatementGroupPage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MTSGroup"),extentTest); String[] Regions =
	 * pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(
	 * objWeeklyPlanningStoreObjects,extentTest,"Group");
	 * pageWeeklyPlanningPO.OrderVerification(Regions,"Group",extentTest); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for Zone Manager",priority=14)
	 * public void VerifySortingForForZoneManagerMTS() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageDailyTradingStatementGroupPO = new
	 * DailyTradingStatementGroupPage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(),
	 * DailyTradingStatementGroupPage.class); TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MTSZone"),extentTest); String[] Regions =
	 * pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(
	 * objWeeklyPlanningStoreObjects,extentTest,"Zone");
	 * pageWeeklyPlanningPO.OrderVerification(Regions,"Zone",extentTest); }
	 * 
	 * @Test(description =
	 * "Verify Options in Page to View DropDown for State Manager",priority=15)
	 * public void VerifySortingForStateManagerMTS() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageDailyTradingStatementGroupPO = new
	 * DailyTradingStatementGroupPage().createPage(getDriver(), getContext(), log,
	 * status, data,getReport(), getReportLogger(),
	 * DailyTradingStatementGroupPage.class); pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * TestStatus status = getTestStatus();
	 * System.out.println("Welcome "+getContext().getStringProperty("username"));
	 * System.out.println("Entering into TestCase");
	 * pageWeeklyPlanningPO.VerifyUserName(objWeeklyPlanningStoreObjects,extentTest,
	 * getContext().getStringProperty("username"));
	 * pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects,
	 * extentTest,getContext().getStringProperty("SelectFiscalWeek"));
	 * pageWeeklyPlanningPO.SelectPage(objWeeklyPlanningStoreObjects,getContext().
	 * getStringProperty("MTSState "),extentTest); String[] Regions =
	 * pageDailyTradingStatementGroupPO.VerifyRegionDropdownList(
	 * objWeeklyPlanningStoreObjects,extentTest,"State");
	 * pageWeeklyPlanningPO.OrderVerification(Regions,"State",extentTest); }
	 */

	@AfterTest
	public void CloseExtentReport() throws Exception {
		report.endTest(extentTest);
		report.flush();
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