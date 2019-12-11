package au.wow.WLPmain.MonthlyPlanning;

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
import au.wow.WLPmain.objects.MonthlyPlanningGroupObjects;
import au.wow.WLPmain.objects.MonthlyPlanningStoreObjects;
import au.wow.WLPmain.objects.WeeklyPlanningStoreObjects;
import au.wow.WLPmain.pages.CommonFunctions;
import au.wow.WLPmain.pages.DailyPlanningMainPage;
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

public class MonthlyPlanningZoneWeekTable extends TestBase {

	protected Logger log = LogManager.getLogger(MonthlyPlanningZoneWeekTable.class);
	TestStatus status;
	ExtentReportsScreenshotCode ScreenShotUtil;
	SQLWrapper sql = new SQLWrapper(log);
	WoWLoginPage loginPage;
	CommonRepository CommonFunc;
	WeeklyPlanningStoreObjects objWeeklyPlanningStoreObjects;
	MonthlyPlanningStoreObjects objMonthlyPlanningStoreObjects;
	MonthlyPlanningGroupObjects objMonthlyPlanningGroupObjects;
	WeeklyPlanningStorePage pageWeeklyPlanningPO;
	MonthlyPlanningStorePage pageMonthlyPlanningPO;
	MonthlyPlanningGroupPage pageMonthlyPlanningGroupPO;
	BasePage BaseObj;
	CommonFunctions common;
	StoreProperties storeprop;
	List<List<Object>> DBValues, DBPstive, DBNgtive;
	List<List<Object>> DBTotal;
	List<List<Object>> UIValues;
	List<List<Object>> UITotal;
	String OptionToView, Name;

	public MonthlyPlanningZoneWeekTable() {
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
		pageWeeklyPlanningPO = loginPage.navToWeeklyPlanningStorePg(getContext().getStringProperty("store"));
	}

	// PST 760 Week DATA NEW TABLE

	@Test(description = "Monthly Planning - Zone, Sales history - First Month Week Data", priority = 57)
	public void SalesHistoryFirstMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectWeekFromDropdown(objWeeklyPlanningStoreObjects, extentTest,
				getContext().getStringProperty("SelectFiscalWeek"));

		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.planningScreens, "Planning Screens");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.monthlyPlanning, "Monthly Planning");
		pageWeeklyPlanningPO.selectPage(extentTest, objWeeklyPlanningStoreObjects.menuBar,
				objWeeklyPlanningStoreObjects.MPscreenToView, "Zone");
		//pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				//objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("MonthlyPlanningZone"));

		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");

		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);
		String SalesHistoryWeekData = data.getText("SalesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Sales history - Second Month Week Data", priority = 58)
	public void SalesHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String SalesHistoryWeekData = data.getText("SalesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Sales history - Third Month Week Data", priority = 59)
	public void SalesHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String SalesHistoryWeekData = data.getText("SalesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Sales history - Fourth Month Week Data", priority = 60)
	public void SalesHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String SalesHistoryWeekData = data.getText("SalesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	// Wages
	@Test(description = "Monthly Planning - Zone, Wages history - First Month Week Data", priority = 61)
	public void WagesHistoryFirsthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagesHistoryWeekData = data.getText("WagesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, Wages history - Second Month Week Data", priority = 62)
	public void WagesHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String WagesHistoryWeekData = data.getText("WagesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, Wages history - Third Month Week Data", priority = 63)
	public void WagesHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String WagesHistoryWeekData = data.getText("WagesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Wages history - Fourth Month Week Data", priority = 64)
	public void WagesHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String WagesHistoryWeekData = data.getText("WagesHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	// Items
	@Test(description = "Monthly Planning - Zone, Items history - First Month Week Data", priority = 65)
	public void ItemsHistoryFirsthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemsHistoryWeekData = data.getText("ItemsHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Items history - Second Month Week Data", priority = 66)
	public void ItemsHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String ItemsHistoryWeekData = data.getText("ItemsHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Items history - Third Month Week Data", priority = 67)
	public void ItemsHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String ItemsHistoryWeekData = data.getText("ItemsHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Items history - Fourth Month Week Data", priority = 68)
	public void ItemsHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String ItemsHistoryWeekData = data.getText("ItemsHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	// Customers
	@Test(description = "Monthly Planning - Zone, Customers history - First Month Week Data", priority = 69)
	public void CustomersHistoryFirsthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CustomerHistoryWeekData = data.getText("CustomerHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Customers history - Second Month Week Data", priority = 70)
	public void CustomersHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String CustomerHistoryWeekData = data.getText("CustomerHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Customers history - Third Month Week Data", priority = 71)
	public void CustomersHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String CustomerHistoryWeekData = data.getText("CustomerHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Customers history - Fourth Month Week Data", priority = 72)
	public void CustomersHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String CustomerHistoryWeekData = data.getText("CustomerHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);
	}

	// Item Price
	@Test(description = "Monthly Planning - Zone, Item Price history - First Month Week Data", priority = 73)
	public void ItemPriceHistoryFirsthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemPriceHistoryWeekData = data.getText("ItemPriceHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, Item Price history - Second Month Week Data", priority = 74)
	public void ItemPriceHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String ItemPriceHistoryWeekData = data.getText("ItemPriceHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, Item Price history - Third Month Week Data", priority = 75)
	public void ItemPriceHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String ItemPriceHistoryWeekData = data.getText("ItemPriceHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, Item Price history - Fourth Month Week Data", priority = 76)
	public void ItemPriceHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String ItemPriceHistoryWeekData = data.getText("ItemPriceHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);
	}

	// Wage Percentage
	@Test(description = "Monthly Planning - Zone, WagePercentage history - First Month Week Data", priority = 77)
	public void WagePercentageHistoryFirsthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagePercentageHistoryWeekData = data.getText("WagePercentageHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, WagePercentage history - Second Month Week Data", priority = 78)
	public void WagePercentageHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String WagePercentageHistoryWeekData = data.getText("WagePercentageHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, WagePercentage history - Third Month Week Data", priority = 79)
	public void WagePercentageHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String WagePercentageHistoryWeekData = data.getText("WagePercentageHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, WagePercentage history - Fourth Month Week Data", priority = 80)
	public void WagePercentageHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String WagePercentageHistoryWeekData = data.getText("WagePercentageHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	// Paid Hours
	@Test(description = "Monthly Planning - Zone, Paid Hours history - First Month Week Data", priority = 81)
	public void PaidHoursHistoryFirsthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String PaidHoursHistoryWeekData = data.getText("PaidHoursHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Paid Hours history - Second Month Week Data", priority = 82)
	public void PaidHoursHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String PaidHoursHistoryWeekData = data.getText("PaidHoursHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Paid Hours history - Third Month Week Data", priority = 83)
	public void PaidHoursHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String PaidHoursHistoryWeekData = data.getText("PaidHoursHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Paid Hours history - Fourth Month Week Data", priority = 84)
	public void PaidHoursHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String PaidHoursHistoryWeekData = data.getText("PaidHoursHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	// CPH
	@Test(description = "Monthly Planning - Zone, CPH history - First Month Week Data", priority = 85)
	public void CPHFirsthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CPHHistoryWeekData = data.getText("CPHHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, CPH history - Second Month Week Data", priority = 86)
	public void CPHHistorySecondMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String CPHHistoryWeekData = data.getText("CPHHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, CPH history - Third Month Week Data", priority = 87)
	public void CPHHistoryThirdMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String CPHHistoryWeekData = data.getText("CPHHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, CPH history - Fourth Month Week Data", priority = 88)
	public void CPHHistoryFourthMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String CPHHistoryWeekData = data.getText("CPHHistoryWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistoryWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	////////////////////////// CURRENT MONTH ///////////////////////////////
	@Test(description = "Monthly Planning - Zone, Budget Sales - Current Month Week Data", priority = 89)
	public void BudgetSales_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetSalesWeekData = data.getText("BudgetSalesWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetSalesWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.BudgetSalesWeekTotal);
			pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

		}
		if (OptionToView.contains("Forecast")) {
			String ForecastSalesWeekData = data.getText("ForecastSalesWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastSalesWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningStoreObjects.ForecastSalesWeekTotal);
			pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

		}
	}

	@Test(description = "Monthly Planning - Zone, Generated Sales- Current Month Week Data", priority = 90)
	public void GeneratedSales_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String GeneratedSalesWeekData = data.getText("GeneratedSalesWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				GeneratedSalesWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.GeneratedSalesWeekTotal);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Planned Sales- Current Month Week Data", priority = 91)
	public void PlannedSales_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedSalesWeekData = data.getText("PlannedSalesWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedSalesWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedSales);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Budget Wages - Current Month Week Data", priority = 92)
	public void BudgetWages_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetWagesWeekData = data.getText("BudgetWagesWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetWagesWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.BudgetWages);
			pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

		}
		if (OptionToView.contains("Forecast")) {
			String ForecastWagesWeekData = data.getText("ForecastWagesWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastWagesWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ForecastWages);
			pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

		}
	}

	@Test(description = "Monthly Planning - Zone, BaseCost Wages - Current Month Week Data", priority = 93)
	public void BaseCostWages_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String BaseCostWeekData = data.getText("BaseCostWagesWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				BaseCostWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.BaseCostWages);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Planned Wages - Current Month Week Data", priority = 94)
	public void PlannedWages_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedWagesWeekData = data.getText("PlannedWagesWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedWagesWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedWages);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Budget WagesPercentage - Current Month Week Data", priority = 95)
	public void BudgetWagesPercentage_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetWagesPercentageWeekData = data.getText("BudgetWagesPercentageWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetWagesPercentageWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningGroupObjects.BudgetWagesPercentage);
			pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

		}
		if (OptionToView.contains("Forecast")) {
			String ForecastWagesPercentageWeekData = data.getText("ForecastWagesPercentageWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastWagesPercentageWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentage);
			pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

		}
	}

	@Test(description = "Monthly Planning - Zone, Planned WagesPercentage - Current Month Week Data", priority = 96)
	public void PlannedWagesPercentage_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedWagesPercentageWeekData = data.getText("PlannedWagesPercentageWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedLYWagesPercentage);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, LYWagesPercentage_CurrentMonth - Current Month Week Data", priority = 97)
	public void LYWagesPercentage_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYWagesPercentageWeekData = data.getText("LYWagesPercentageWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYWagesPercentageWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Division", getContext().getStringProperty("Division"))
						.replace("LYMonth", LYMonthName));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYWagesPercentage);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);
	}

	@Test(description = "Monthly Planning - Zone, SMS Hours - Current Month Week Data", priority = 98)
	public void SMSHours_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String SMSHoursWeekData = data.getText("SMSHoursWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SMSHoursWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SMSHours);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, BaseHours - Current Month Week Data", priority = 99)
	public void BaseHours_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String BasehoursWeekData = data.getText("BaseHoursWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				BasehoursWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.BaseHours);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, PlannedHours - Current Month Week Data", priority = 100)
	public void PlannedHours_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedHoursWeekData = data.getText("PlannedHoursWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedHoursWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedHours);
		pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Budget and Forecast Rate - Current Month Week Data", priority = 101)
	public void PlannedBDGTRateandFCSTRateCurrentMonth_WeektableData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String PositiveFactor = data.getText("PositiveFactor");
		DBPstive = sql.CLRexecuteQuery(getContext(),
				PositiveFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("PositveFactor: " + DBPstive);

		String NegativeFactor = data.getText("NegativeFactor");
		DBNgtive = sql.CLRexecuteQuery(getContext(),
				NegativeFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("NegativeFactor: " + DBNgtive);
		if (OptionToView.contains("Budget"))
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforWeektable(objMonthlyPlanningGroupObjects, extentTest,
					objMonthlyPlanningGroupObjects.MPTable, objMonthlyPlanningGroupObjects.BDGTRate,
					objMonthlyPlanningGroupObjects.BudgetWagesPercentage, objMonthlyPlanningGroupObjects.PlannedSales,
					objMonthlyPlanningGroupObjects.BudgetSales, objMonthlyPlanningGroupObjects.PlannedWages,
					objMonthlyPlanningGroupObjects.BudgetWages, DBPstive, DBNgtive);
		if (OptionToView.contains("Forecast"))
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforWeektable(objMonthlyPlanningGroupObjects, extentTest,
					objMonthlyPlanningGroupObjects.MPTable, objMonthlyPlanningGroupObjects.FCSTRate,
					objMonthlyPlanningGroupObjects.ForecastWagesPercentage, objMonthlyPlanningGroupObjects.PlannedSales,
					objMonthlyPlanningStoreObjects.ForecastSales, objMonthlyPlanningGroupObjects.PlannedWages,
					objMonthlyPlanningStoreObjects.ForecastWages, DBPstive, DBNgtive);
	}

	/*
	 * @Test(description =
	 * "Monthly Planning - Zone, Budget and Forecast Rate - Current Month Week Data"
	 * ,priority=101) public void PlannedBDGTRateandFCSTRateCurrentMonth_WeekData()
	 * throws Exception { pageWeeklyPlanningPO = new
	 * WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
	 * pageMonthlyPlanningPO = new
	 * MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
	 * pageMonthlyPlanningGroupPO = new
	 * MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
	 * data,getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
	 * TestStatus status = getTestStatus(); OptionToView =
	 * getContext().getStringProperty("OptionToView");
	 * if(OptionToView.contains("Budget")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.CurrentMonth);
	 * if(OptionToView.contains("Forecast")) Name=
	 * pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects,
	 * extentTest,objMonthlyPlanningStoreObjects.ForecastCurrentMonth); String
	 * MonthName = Name.replace("Total","").trim(); String Month =
	 * MonthName.concat(getContext().getStringProperty("Month"));
	 * if(OptionToView.contains("Budget")) { String PlannedBudgetRateWeekData =
	 * data.getText("PlannedBudgetRateWeekData"); DBValues =
	 * sql.CLRexecuteQuery(getContext(), PlannedBudgetRateWeekData.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",Month)
	 * .replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Value: "+DBValues);
	 * 
	 * 
	 * UIValues =
	 * pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable,
	 * extentTest,objWeeklyPlanningStoreObjects.DepartmentNames,
	 * objMonthlyPlanningStoreObjects.BDGTRate);
	 * pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest,DBValues
	 * ,UIValues);
	 * 
	 * 
	 * } if(OptionToView.contains("Forecast")) { String PlannedForecsatRateWeekData
	 * = data.getText("PlannedForecsatRateWeekData"); DBValues =
	 * sql.CLRexecuteQuery(getContext(),
	 * PlannedForecsatRateWeekData.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",Month)
	 * .replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Value: "+DBValues);
	 * 
	 * 
	 * UIValues =
	 * pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable,
	 * extentTest,objWeeklyPlanningStoreObjects.DepartmentNames,
	 * objMonthlyPlanningStoreObjects.FCSTRate);
	 * pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest,DBValues
	 * ,UIValues);
	 * 
	 * } }
	 */

	//////////////////////////////////////////////// CR 09
	//////////////////////////////////////////////// /////////////////////////////////////////////////////////////////
	@Test(description = "Monthly Planning - Zone, Var to BUD Sales- Current Month Week Data ", priority = 102)
	public void VarToBUDSales_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String VartoBUDWeekData = data.getText("VartoBUDWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					VartoBUDWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.VarToBudSales);
			pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

		}
		if (OptionToView.contains("Forecast")) {
			String VartoFCSTWeekData = data.getText("VartoFCSTWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					VartoFCSTWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.VarToForecast);
			pageWeeklyPlanningPO.CompareValuesWeekTableMP(extentTest, DBValues, UIValues);

		}
	}

	@Test(description = "Monthly Planning - Zone, LYPercentage Sales- Current Month Week Data", priority = 103)
	public void LYPercentageSales_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYPercentageSalesWeekData = data.getText("LYPercentageSalesWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYPercentageSalesWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Division", getContext().getStringProperty("Division")).replace("LYMonth", LYMonthName)
						.replace("Month", Month));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYPercentageSales);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, BudgetPercentage Sales- Current Month Week Data", priority = 104)
	public void BudgetPercentageSales_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		if (OptionToView.contains("Budget")) {
			String BUDPercentageSalesWeekData = data.getText("BUDPercentageSalesWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BUDPercentageSalesWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Month", Month)
							.replace("LYMonth", LYMonthName));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningGroupObjects.BudgetPercentageSales);
			pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

		}
		if (OptionToView.contains("Forecast")) {
			String FCSTPercentageSalesWeekData = data.getText("FCSTPercentageSalesWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					FCSTPercentageSalesWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone"))
							.replace("Division", getContext().getStringProperty("Division")).replace("Month", Month)
							.replace("LYMonth", LYMonthName));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningGroupObjects.ForecastPercentageSales);
			pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

		}
	}

	@Test(description = "Monthly Planning - Zone, LYPercentage Wages - Current Month Week Data", priority = 105)
	public void LYPercentageWages_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYPercentageWagesWeekData = data.getText("LYPercentageWagesWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYPercentageWagesWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Division", getContext().getStringProperty("Division")).replace("LYMonth", LYMonthName)
						.replace("Month", Month));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYPercentageWages);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, LYPercentage Hours - Current Month Week Data", priority = 106)
	public void LYPercentageHours_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String LYHoursWeekData = data.getText("LYHoursWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYHoursWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Division", getContext().getStringProperty("Division")).replace("LYMonth", LYMonthName)
						.replace("Month", Month));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYPercentageHours);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Budget or Forecast OR - Current Month Week Data ", priority = 107)
	public void BGDT_FCST_OR_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		if (OptionToView.contains("Budget")) {
			String BudgetORWeekData = data.getText("BudgetORWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetORWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.BdgtOR);
			pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

		}
		if (OptionToView.contains("Forecast")) {
			String ForecastORWeekData = data.getText("ForecastORWeekData");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastORWeekData.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FcstOR);
			pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

		}
	}

	@Test(description = "Monthly Planning - Zone, Planned OR - Current Month Week Data", priority = 108)
	public void PlannedOR_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedORWeekData = data.getText("PlannedORWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedORWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedOR);
		pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, Planned CPH - Current Month Week Data", priority = 109)
	public void PlannedCPH_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));

		String PlannedCPHWeekData = data.getText("PlannedCPHWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedCPHWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedCPH);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(description = "Monthly Planning - Zone, GrowthLY CPH - Current Month Week Data", priority = 110)
	public void GrowthLYCPH_CurrentMonth_WeekData() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningPO = new MonthlyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		OptionToView = getContext().getStringProperty("OptionToView");
		if (OptionToView.contains("Budget"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.CurrentMonth);
		if (OptionToView.contains("Forecast"))
			Name = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
					objMonthlyPlanningStoreObjects.ForecastCurrentMonth);
		String MonthName = Name.replace("Total", "").trim();
		String Month = MonthName.concat(getContext().getStringProperty("Month"));
		String LYMonthName = MonthName.concat(getContext().getStringProperty("LYMonth"));

		String GrowthLYCPHWeekData = data.getText("GrowthLYCPHWeekData");
		DBValues = sql.CLRexecuteQuery(getContext(),
				GrowthLYCPHWeekData.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone"))
						.replace("Division", getContext().getStringProperty("Division")).replace("LYMonth", LYMonthName)
						.replace("Month", Month));
		System.out.println("DB Value: " + DBValues);

		UIValues = pageWeeklyPlanningPO.UIResultsGroup(objMonthlyPlanningGroupObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.GrowthLYCPH);
		pageWeeklyPlanningPO.CompareValueDecimalFieldsWeekTableMP(extentTest, DBValues, UIValues);

	}

	@Test(priority = 111)
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
		pageMonthlyPlanningPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupPage.class);
		BaseObj = PageFactory.initElements(this.getDriver(), BasePage.class);
		common = PageFactory.initElements(this.getDriver(), CommonFunctions.class);
		storeprop = PageFactory.initElements(this.getDriver(), StoreProperties.class);
		objWeeklyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), WeeklyPlanningStoreObjects.class);
		objMonthlyPlanningStoreObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningStoreObjects.class);
		objMonthlyPlanningGroupObjects = PageFactory.initElements(this.getDriver(), MonthlyPlanningGroupObjects.class);
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningZoneWeekTable.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}