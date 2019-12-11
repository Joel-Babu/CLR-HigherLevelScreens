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

public class MonthlyPlanningState extends TestBase {

	protected Logger log = LogManager.getLogger(MonthlyPlanningState.class);
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

	public MonthlyPlanningState() {
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

	// Sales
	@Test(description = "Monthly Planning - State, Sales history - First Month", priority = 1)
	public void SalesHistoryFirstMonth() throws Exception {
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
				objWeeklyPlanningStoreObjects.MPscreenToView, "State");
		/*pageWeeklyPlanningPO.verifyPageName(extentTest, objWeeklyPlanningStoreObjects.DataFrame,
				objWeeklyPlanningStoreObjects.pageName, getContext().getStringProperty("MonthlyPlanningState"));*/

		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Sales");
	
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);
		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Sales history - Second Month", priority = 2)
	public void SalesHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Sales history - Third Month", priority = 3)
	public void SalesHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Sales history - Fourth Month", priority = 4)
	public void SalesHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String SalesHistory = data.getText("SalesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SalesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SalesHistoryTotal = data.getText("SalesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SalesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Wages
	@Test(description = "Monthly Planning - State, Wages history - First Month", priority = 5)
	public void WagesHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wages");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Wages history - Second Month", priority = 6)
	public void WagesHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Wages history - Third Month", priority = 7)
	public void WagesHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Wages history - Fourth Month", priority = 8)
	public void WagesHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String WagesHistory = data.getText("WagesHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagesHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagesHistoryTotal = data.getText("WagesHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagesHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Items
	@Test(description = "Monthly Planning - State, Items history - First Month", priority = 9)
	public void ItemsHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Items");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemsHistory = data.getText("ItemsHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Items history - Second Month", priority = 10)
	public void ItemsHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String ItemsHistory = data.getText("ItemsHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Items history - Third Month", priority = 11)
	public void ItemsHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String ItemsHistory = data.getText("ItemsHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Items history - Fourth Month", priority = 12)
	public void ItemsHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String ItemsHistory = data.getText("ItemsHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemsHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemsHistoryTotal = data.getText("ItemsHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemsHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Customers
	@Test(description = "Monthly Planning - State, Customers history - First Month", priority = 13)
	public void CustomersHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Customers");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CustomerHistory = data.getText("CustomerHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Customers history - Second Month", priority = 14)
	public void CustomersHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String CustomerHistory = data.getText("CustomerHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Customers history - Third Month", priority = 15)
	public void CustomersHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String CustomerHistory = data.getText("CustomerHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Customers history - Fourth Month", priority = 16)
	public void CustomersHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String CustomerHistory = data.getText("CustomerHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CustomerHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CustomerHistoryTotal = data.getText("CustomerHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CustomerHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	// Item Price
	@Test(description = "Monthly Planning - State, Item Price history - First Month", priority = 17)
	public void ItemPriceHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Item Price");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Item Price history - Second Month", priority = 18)
	public void ItemPriceHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Item Price history - Third Month", priority = 19)
	public void ItemPriceHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Item Price history - Fourth Month", priority = 20)
	public void ItemPriceHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String ItemPriceHistory = data.getText("ItemPriceHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String ItemPriceHistoryTotal = data.getText("ItemPriceHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				ItemPriceHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Wage Percentage
	@Test(description = "Monthly Planning - State, WagePercentage history - First Month", priority = 21)
	public void WagePercentageHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Wage %");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, WagePercentage history - Second Month", priority = 22)
	public void WagePercentageHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, WagePercentage history - Third Month", priority = 23)
	public void WagePercentageHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, WagePercentage history - Fourth Month", priority = 24)
	public void WagePercentageHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String WagePercentageHistory = data.getText("WagePercentageHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String WagePercentageHistoryTotal = data.getText("WagePercentageHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				WagePercentageHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// Paid Hours
	@Test(description = "Monthly Planning - State, Paid Hours history - First Month", priority = 25)
	public void PaidHoursHistoryFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "Paid Hours");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Paid Hours history - Second Month", priority = 26)
	public void PaidHoursHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Paid Hours history - Third Month", priority = 27)
	public void PaidHoursHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Paid Hours history - Fourth Month", priority = 28)
	public void PaidHoursHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String PaidHoursHistory = data.getText("PaidHoursHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PaidHoursHistoryTotal = data.getText("PaidHoursHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PaidHoursHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	// CPH
	@Test(description = "Monthly Planning - State, CPH history - First Month", priority = 29)
	public void CPHFirsthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		pageWeeklyPlanningPO.SelectHistoryDropdownToView(objWeeklyPlanningStoreObjects, extentTest, "CPH");
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FirstMonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FirstMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, CPH history - Second Month", priority = 30)
	public void CPHHistorySecondMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.SecondMonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SecondMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, CPH history - Third Month", priority = 31)
	public void CPHHistoryThirdMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.ThirdMonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.ThirdMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, CPH history - Fourth Month", priority = 32)
	public void CPHHistoryFourthMonth() throws Exception {
		pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(), getContext(), log, status, data,
				getReport(), getReportLogger(), WeeklyPlanningStorePage.class);
		pageMonthlyPlanningGroupPO = new MonthlyPlanningGroupPage().createPage(getDriver(), getContext(), log, status,
				data, getReport(), getReportLogger(), MonthlyPlanningGroupPage.class);
		TestStatus status = getTestStatus();
		String Month = pageWeeklyPlanningPO.MonthName(objWeeklyPlanningStoreObjects, extentTest,
				objMonthlyPlanningStoreObjects.FourthmonthNm);

		String CPHHistory = data.getText("CPHHistory");
		DBValues = sql.CLRexecuteQuery(getContext(),
				CPHHistory.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String CPHHistoryTotal = data.getText("CPHHistoryTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				CPHHistoryTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.FourMonth);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	////////////////////////// CURRENT MONTH ///////////////////////////////
	@Test(description = "Monthly Planning - State, Budget Sales - Current Month", priority = 37)
	public void BudgetSales_CurrentMonth() throws Exception {
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
			String BudgetSales = data.getText("BudgetSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetSalesTotal = data.getText("BudgetSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.BudgetSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningGroupObjects.BudgetSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastSales = data.getText("ForecastSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastSalesTotal = data.getText("ForecastSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ForecastSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningStoreObjects.ForecastSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - State, Generated Sales- Current Month", priority = 38)
	public void GeneratedSales_CurrentMonth() throws Exception {
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

		String GeneratedSales = data.getText("GeneratedSales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				GeneratedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String GeneratedSalesTotal = data.getText("GeneratedSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				GeneratedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.GeneratedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.GeneratedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Planned Sales- Current Month", priority = 39)
	public void PlannedSales_CurrentMonth() throws Exception {
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

		String PlannedSales = data.getText("PlannedSales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedSalesTotal = data.getText("PlannedSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.PlannedSales);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Budget Wages - Current Month", priority = 43)
	public void BudgetWages_CurrentMonth() throws Exception {
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
			String BudgetWages = data.getText("BudgetWages");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetWagesTotal = data.getText("BudgetWagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.BudgetWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningGroupObjects.BudgetWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastWages = data.getText("ForecastWages");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastWages.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastWagesTotal = data.getText("ForecastWagesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastWagesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningStoreObjects.ForecastWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningStoreObjects.ForecastWages);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - State, BaseCost Wages - Current Month", priority = 44)
	public void BaseCostWages_CurrentMonth() throws Exception {
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

		String BaseCost = data.getText("BaseCost");
		DBValues = sql.CLRexecuteQuery(getContext(),
				BaseCost.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String BaseCostTotal = data.getText("BaseCostTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				BaseCostTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.BaseCostWages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.BaseCostWages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Planned Wages - Current Month", priority = 45)
	public void PlannedWages_CurrentMonth() throws Exception {
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

		String PlannedWages = data.getText("PlannedWages");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedWagesTotal = data.getText("PlannedWagesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedWages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.PlannedWages);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Budget WagesPercentage - Current Month", priority = 47)
	public void BudgetWagesPercentage_CurrentMonth() throws Exception {
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
			String BudgetWagesPercentage = data.getText("BudgetWagesPercentage");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetWagesPercentageTotal = data.getText("BudgetWagesPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningGroupObjects.BudgetWagesPercentage);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningGroupObjects.BudgetWagesPercentage);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastWagesPercentage = data.getText("ForecastWagesPercentage");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastWagesPercentageTotal = data.getText("ForecastWagesPercentageTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentage);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentage);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - State, Planned WagesPercentage - Current Month", priority = 48)
	public void PlannedWagesPercentage_CurrentMonth() throws Exception {
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

		String PlannedWagesPercentage = data.getText("PlannedWagesPercentage");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedWagesPercentageTotal = data.getText("PlannedWagesPercentageTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedLYWagesPercentage);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.PlannedLYWagesPercentage);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, LYWagesPercentage_CurrentMonth - Current Month", priority = 49)
	public void LYWagesPercentage_CurrentMonth() throws Exception {
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

		String LYWagesPercentage = data.getText("LYWagesPercentage");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYWagesPercentage.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYWagesPercentageTotal = data.getText("LYWagesPercentageTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYWagesPercentageTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYWagesPercentage);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.LYWagesPercentage);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, SMS Hours - Current Month", priority = 50)
	public void SMSHours_CurrentMonth() throws Exception {
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

		String SMSHours = data.getText("SMSHours");
		DBValues = sql.CLRexecuteQuery(getContext(),
				SMSHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String SMSHoursTotal = data.getText("SMSHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				SMSHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.SMSHours);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.SMSHours);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, BaseHours - Current Month", priority = 51)
	public void BaseHours_CurrentMonth() throws Exception {
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

		String Basehours = data.getText("Basehours");
		DBValues = sql.CLRexecuteQuery(getContext(),
				Basehours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String BasehoursTotal = data.getText("BasehoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				BasehoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.BaseHours);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.BaseHours);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, PlannedHours - Current Month", priority = 52)
	public void PlannedHours_CurrentMonth() throws Exception {
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

		String PlannedHours = data.getText("PlannedHours");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedHoursTotal = data.getText("PlannedHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedHours);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.PlannedHours);
		pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
	}

	/*
	 * @Test(description =
	 * "Monthly Planning - State, Budget and Forecast Rate - Current Month",priority
	 * =48) public void PlannedBDGTRateandFCSTRateCurrentMonth() throws Exception {
	 * pageWeeklyPlanningPO = new WeeklyPlanningStorePage().createPage(getDriver(),
	 * getContext(), log, status, data,getReport(), getReportLogger(),
	 * WeeklyPlanningStorePage.class); pageMonthlyPlanningPO = new
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
	 * if(OptionToView.contains("Budget")) { String Budget =
	 * data.getText("PlannedBudgetRate"); DBValues =
	 * sql.CLRexecuteQuery(getContext(), Budget.replace("store",
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
	 * String BudGetTotalMP = data.getText("PlannedBudgetRateTotal"); DBTotal =
	 * sql.CLRexecuteQuery(getContext(), BudGetTotalMP.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",Month)
	 * .replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UIValues =
	 * pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable,
	 * extentTest,objWeeklyPlanningStoreObjects.DepartmentNames,
	 * objMonthlyPlanningStoreObjects.BDGTRate);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBValues,UIValues)
	 * ;
	 * 
	 * UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest,
	 * objMonthlyPlanningGroupObjects.RowNameTotal,objMonthlyPlanningGroupObjects.
	 * BDGTRate);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * } if(OptionToView.contains("Forecast")) { String Forecast =
	 * data.getText("PlannedForecsatRate"); DBValues =
	 * sql.CLRexecuteQuery(getContext(), Forecast.replace("store",
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
	 * String ForecastTotal = data.getText("PlannedForecsatRateTotal"); DBTotal =
	 * sql.CLRexecuteQuery(getContext(), ForecastTotal.replace("store",
	 * getContext().getStringProperty("store")).replace("Month",Month)
	 * .replace("store",getContext().getStringProperty("store"))
	 * .replace("Area",getContext().getStringProperty("Area"))
	 * .replace("Zone",getContext().getStringProperty("Zone"))
	 * .replace("Week",getContext().getStringProperty("SelectFiscalWeek"))
	 * .replace("FinWeek",getContext().getStringProperty("FinWk"))
	 * .replace("Year",getContext().getStringProperty("Year"))
	 * .replace("Region",getContext().getStringProperty("Region"))
	 * .replace("Division",getContext().getStringProperty("Division")));
	 * System.out.println("DB Total: "+DBTotal);
	 * 
	 * UIValues =
	 * pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable,
	 * extentTest,objWeeklyPlanningStoreObjects.DepartmentNames,
	 * objMonthlyPlanningStoreObjects.FCSTRate);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBValues,UIValues)
	 * ;
	 * 
	 * UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest,
	 * objMonthlyPlanningGroupObjects.RowNameTotal,objMonthlyPlanningGroupObjects.
	 * FCSTRate);
	 * pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest,DBTotal,UITotal);
	 * } }
	 */

	@Test(description = "Monthly Planning - State, Planned BDGT Rate - Current Month", priority = 48)
	public void PlannedBDGTRate_CurrentMonth() throws Exception {
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

		String PositiveFactor = data.getText("PositiveFactorTotal");
		DBPstive = sql.CLRexecuteQuery(getContext(),
				PositiveFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("PositveFactor: " + DBPstive);

		String NegativeFactor = data.getText("NegativeFactorTotal");
		DBNgtive = sql.CLRexecuteQuery(getContext(),
				NegativeFactor.replace("Region", getContext().getStringProperty("Region")).replace("Month",
						getContext().getStringProperty("MonthToTest")));
		System.out.println("NegativeFactor: " + DBNgtive);
		if (OptionToView.contains("Budget"))
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforGroup(objMonthlyPlanningGroupObjects, extentTest,
					objMonthlyPlanningGroupObjects.MPTable, objMonthlyPlanningGroupObjects.BDGTRate,
					objMonthlyPlanningGroupObjects.BudgetWagesPercentage, objMonthlyPlanningGroupObjects.PlannedSales,
					objMonthlyPlanningGroupObjects.BudgetSales, objMonthlyPlanningGroupObjects.PlannedWages,
					objMonthlyPlanningGroupObjects.BudgetWages, DBPstive, DBNgtive);
		if (OptionToView.contains("Forecast"))
			pageMonthlyPlanningPO.PlannedSalestoBDGTRateforGroup(objMonthlyPlanningGroupObjects, extentTest,
					objMonthlyPlanningGroupObjects.MPTable, objMonthlyPlanningStoreObjects.FCSTRate,
					objMonthlyPlanningStoreObjects.ForecastWagesPercentage,
					objMonthlyPlanningStoreObjects.FCSTPlannedSales, objMonthlyPlanningStoreObjects.ForecastSales,
					objMonthlyPlanningGroupObjects.PlannedWages, objMonthlyPlanningStoreObjects.ForecastWages, DBPstive,
					DBNgtive);
	}

	//////////////////////////////////////////////// CR 09
	//////////////////////////////////////////////// /////////////////////////////////////////////////////////////////
	@Test(description = "Monthly Planning - State, Var to BUD Sales- Current Month", priority = 40)
	public void VarToBUDSales_CurrentMonth() throws Exception {
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
			String VartoBUD = data.getText("VartoBUD");
			DBValues = sql.CLRexecuteQuery(getContext(),
					VartoBUD.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String VartoBUDTotal = data.getText("VartoBUDTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					VartoBUDTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.VarToBudSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningGroupObjects.VarToBudSales);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String VartoFCST = data.getText("VartoFCST");
			DBValues = sql.CLRexecuteQuery(getContext(),
					VartoFCST.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String VartoFCSTTotal = data.getText("VartoFCSTTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					VartoFCSTTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.VarToForecast);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningGroupObjects.VarToForecast);
			pageMonthlyPlanningPO.CompareValues(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - State, LYPercentage Sales- Current Month", priority = 41)
	public void LYPercentageSales_CurrentMonth() throws Exception {
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

		String LYPercentageSales = data.getText("LYPercentageSales");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYPercentageSales.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYPercentageSalesTotal = data.getText("LYPercentageSalesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYPercentageSalesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYPercentageSales);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.LYPercentageSales);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, BudgetPercentage Sales- Current Month", priority = 42)
	public void BudgetPercentageSales_CurrentMonth() throws Exception {
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
			String BUDPercentageSales = data.getText("BUDPercentageSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BUDPercentageSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BUDPercentageSalesTotal = data.getText("BUDPercentageSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BUDPercentageSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningGroupObjects.BudgetPercentageSales);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningGroupObjects.BudgetPercentageSales);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String FCSTPercentageSales = data.getText("FCSTPercentageSales");
			DBValues = sql.CLRexecuteQuery(getContext(),
					FCSTPercentageSales.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String FCSTPercentageSalesTotal = data.getText("FCSTPercentageSalesTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					FCSTPercentageSalesTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
							.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames,
					objMonthlyPlanningGroupObjects.ForecastPercentageSales);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);
			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objMonthlyPlanningGroupObjects.ForecastPercentageSales);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - State, LYPercentage Wages - Current Month", priority = 46)
	public void LYPercentageWages_CurrentMonth() throws Exception {
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

		String LYPercentageWages = data.getText("LYPercentageWages");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYPercentageWages.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYPercentageWagesTotal = data.getText("LYPercentageWagesTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYPercentageWagesTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYPercentageWages);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.LYPercentageWages);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, LYPercentage Hours - Current Month", priority = 53)
	public void LYPercentageHours_CurrentMonth() throws Exception {
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

		String LYHours = data.getText("LYHours");
		DBValues = sql.CLRexecuteQuery(getContext(),
				LYHours.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String LYHoursTotal = data.getText("LYHoursTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				LYHoursTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.LYPercentageHours);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.LYPercentageHours);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Budget and Forecast OR - Current Month", priority = 54)
	public void BGDT_FCST_OR_CurrentMonth() throws Exception {
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
			String BudgetOR = data.getText("BudgetOR");
			DBValues = sql.CLRexecuteQuery(getContext(),
					BudgetOR.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String BudgetORTotal = data.getText("BudgetORTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					BudgetORTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.BdgtOR);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.BdgtORTtl);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
		if (OptionToView.contains("Forecast")) {
			String ForecastOR = data.getText("ForecastOR");
			DBValues = sql.CLRexecuteQuery(getContext(),
					ForecastOR.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Value: " + DBValues);

			String ForecastORTotal = data.getText("ForecastORTotal");
			DBTotal = sql.CLRexecuteQuery(getContext(),
					ForecastORTotal.replace("Region", getContext().getStringProperty("Region"))
							.replace("Area", getContext().getStringProperty("Area"))
							.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
			System.out.println("DB Total: " + DBTotal);

			UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
					objWeeklyPlanningStoreObjects.DepartmentNames, objWeeklyPlanningStoreObjects.FcstOR);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

			UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
					objWeeklyPlanningStoreObjects.FcstORTtl);
			pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
		}
	}

	@Test(description = "Monthly Planning - State, Planned OR - Current Month", priority = 55)
	public void PlannedOR_CurrentMonth() throws Exception {
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

		String PlannedOR = data.getText("PlannedOR");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedOR.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedORTotal = data.getText("PlannedORTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedORTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedOR);
		pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.PlannedOR);
		pageMonthlyPlanningPO.CompareValuesforOR(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, Planned CPH - Current Month", priority = 56)
	public void PlannedCPH_CurrentMonth() throws Exception {
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

		String PlannedCPH = data.getText("PlannedCPH");
		DBValues = sql.CLRexecuteQuery(getContext(),
				PlannedCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String PlannedCPHTotal = data.getText("PlannedCPHTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				PlannedCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.PlannedCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.PlannedCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(description = "Monthly Planning - State, GrowthLY CPH - Current Month", priority = 57)
	public void GrowthLYCPH_CurrentMonth() throws Exception {
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

		String GrowthLYCPH = data.getText("GrowthLYCPH");
		DBValues = sql.CLRexecuteQuery(getContext(),
				GrowthLYCPH.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Value: " + DBValues);

		String GrowthLYCPHTotal = data.getText("GrowthLYCPHTotal");
		DBTotal = sql.CLRexecuteQuery(getContext(),
				GrowthLYCPHTotal.replace("Region", getContext().getStringProperty("Region"))
						.replace("Area", getContext().getStringProperty("Area"))
						.replace("Zone", getContext().getStringProperty("Zone")).replace("Month", Month)
						.replace("LYMnth", LYMonthName).replace("Division", getContext().getStringProperty("Division")));
		System.out.println("DB Total: " + DBTotal);

		UIValues = pageWeeklyPlanningPO.UIResultsMP(objMonthlyPlanningStoreObjects.MPTable, extentTest,
				objWeeklyPlanningStoreObjects.DepartmentNames, objMonthlyPlanningGroupObjects.GrowthLYCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBValues, UIValues);

		UITotal = pageMonthlyPlanningGroupPO.GetTotalMP(extentTest, objMonthlyPlanningGroupObjects.RowNameTotal,
				objMonthlyPlanningGroupObjects.GrowthLYCPH);
		pageMonthlyPlanningPO.CompareValueDecimalFields(extentTest, DBTotal, UITotal);
	}

	@Test(priority = 60)
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
			data.loadTestDataXML(System.getProperty("user.dir") + "\\TestData\\WLP\\MonthlyPlanningState.xml");
			customreport.createExtentReport();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}